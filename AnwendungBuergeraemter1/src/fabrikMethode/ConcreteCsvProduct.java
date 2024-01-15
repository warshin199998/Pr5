package fabrikMethode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import business.Buergeramt.Buergeramt;


public class ConcreteCsvProduct extends Product {
	
    BufferedWriter aus ;

    
	public ConcreteCsvProduct() throws IOException {
		aus	= new BufferedWriter(new FileWriter("Buergeraemter.csv", true));
	}
	

	@Override
	//
	public void fuegeInDateiHinzu(Object object) throws IOException {
		Buergeramt buergeramt = (Buergeramt) object;
		aus.write(buergeramt.gibBuergeramtZurueck(';'));

	}
	//
	@Override
	public void schliessDatei() throws IOException {

		aus.close();
	}

}
