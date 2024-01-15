package business.StaetdtischeEinrichtungen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import ownUtil.MyObservable;
import ownUtil.MyObserver;

public class StaedtischeEinrichtungenModel implements MyObservable {

    LinkedList<MyObserver> liste = new LinkedList<MyObserver>();

    private ArrayList<StaedtischeEinrichtung> einrichtungenList = new ArrayList<StaedtischeEinrichtung>();

    BufferedWriter aus;
    
    public static StaedtischeEinrichtungenModel instanz = null;

    public static StaedtischeEinrichtungenModel getInstanz() {
        if (instanz == null) {
            instanz = new StaedtischeEinrichtungenModel();
        }
        return instanz;
    }

    public ArrayList<StaedtischeEinrichtung> getEinrichtungenList() {
        return einrichtungenList;
    }

    private StaedtischeEinrichtungenModel() {

    }


  
    
    public void addEinrichtung(StaedtischeEinrichtung einrichtung) {
        einrichtungenList.add(einrichtung);
        notifyObserver();
    }
    public void leseStaedtischeEinrichtungAusCsvDatei() throws IOException {
        BufferedReader ein = new BufferedReader(new FileReader("StaedtischeEinrichtung.csv"));
        ArrayList<StaedtischeEinrichtung> ergebnis = new ArrayList<>();
        String zeileStr = ein.readLine();

        while (zeileStr != null) {
            String[] zeile = zeileStr.split(";");
            
            // Überprüfe, ob genügend Elemente in der Zeile vorhanden sind
            if (zeile.length >= 3) {
                float geoeffnetVon = Float.parseFloat(zeile[1]);
                float geoeffnetBis = Float.parseFloat(zeile[2]);

                ergebnis.add(new StaedtischeEinrichtung(
                        zeile[0],    // Name
                        geoeffnetVon,
                        geoeffnetBis,
                        zeile[3],    // StrasseHNr
                        Arrays.copyOfRange(zeile, 4, zeile.length)  // Dienstleistungen
                ));
            } else {
                // Fügen Sie geeignete Maßnahmen hinzu, wenn die Zeile nicht das erwartete Format hat
                System.out.println("Fehlerhafte Zeile: " + zeileStr);
            }

            zeileStr = ein.readLine();
        }

        ein.close();
        this.einrichtungenList = ergebnis;
    }




    public void schreibeEinrichtungenInCsvDatei() throws IOException {
    	aus = new BufferedWriter(new FileWriter("StaedtischeEinrichtung.csv", true));
		for(StaedtischeEinrichtung sp : getEinrichtungenList()) {
			aus.write(sp.gibStaedtischeEinrichtungZurueck(';'));
		}
		aus.flush();
		aus.close();
    }

    public void schreibeEinrichtungenInTxtDatei() throws IOException {
    	aus = new BufferedWriter(new FileWriter("StaedtischeEinrichtung.txt", true));
		for(StaedtischeEinrichtung sp : getEinrichtungenList()) {
			aus.write(sp.gibStaedtischeEinrichtungZurueck(';'));
		}
		aus.flush();
		aus.close();
    }

    public void addObserver(MyObserver obs) {
        liste.add(obs);
    }

    public void removeObserver(MyObserver obs) {
        liste.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for (MyObserver o : liste) {
            o.update();
        }
    }
}
