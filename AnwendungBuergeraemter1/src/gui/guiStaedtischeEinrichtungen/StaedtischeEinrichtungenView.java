package gui.guiStaedtischeEinrichtungen;

import business.Buergeramt.BuergeraemterModel;
import business.Buergeramt.Buergeramt;
import business.StaetdtischeEinrichtungen.StaedtischeEinrichtung;
import business.StaetdtischeEinrichtungen.StaedtischeEinrichtungenModel;
import gui.guiBuergeraemter.BuergeraemterControl;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class StaedtischeEinrichtungenView {
	
	private BuergeraemterModel buergeraemterModel;
	private BuergeraemterControl buergeraemterControl;
	private StaedtischeEinrichtungenModel staedtischeEinrichtungenModel;
    private StaedtischeEinrichtungenControl staedtischeEinrichtungenControl;
    
    
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane = new Pane();
    private Label lblAnzeigeBuergeraeamter = new Label("Anzeige Bürgerämter");
    private TextArea txtAnzeigeBuergeraeamter = new TextArea();
    private Button btnAnzeigeBuergeraeamter = new Button("Anzeige");
    //-------Ende Attribute der grafischen Oberflaeche-------
    private Pane paneSta = new Pane();
    private Label lblAnzeigeEinrichtung = new Label("Anzeige Staedtische");
    private TextArea txtAnzeigeEinrichtung = new TextArea();
    private Button btnAnzeigeEinrichtung = new Button("csv-Import und Anzeige");
    
    HBox hb = new HBox();
    
   

    public StaedtischeEinrichtungenView(Stage primaryStage, BuergeraemterModel frMod, StaedtischeEinrichtungenControl spCon, StaedtischeEinrichtungenModel spMod){
		Scene scene = new Scene(this.hb, 560, 360);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anzeige von Steadtische Einrichtung");
		primaryStage.show();
		// Hier ergaenzen
    	this.buergeraemterModel = frMod;
    	this.staedtischeEinrichtungenControl = spCon;
    	this.staedtischeEinrichtungenModel = spMod;
    	
    	this.initKomponentenStaedtischeEinrichtung();
    	this.initKomponentenBuergeramt();
    	this.initListener();
	}

    private void initKomponentenBuergeramt(){
		// Label
		Font font = new Font("Arial", 20);
		lblAnzeigeBuergeraeamter.setLayoutY(40);
		lblAnzeigeBuergeraeamter.setFont(font);
		lblAnzeigeBuergeraeamter.setStyle("-fx-font-weight: bold;"); 
   	pane.getChildren().add(lblAnzeigeBuergeraeamter);    
    	// Textbereich	
    	txtAnzeigeBuergeraeamter.setEditable(false);
    	txtAnzeigeBuergeraeamter.setLayoutY(90);
    	txtAnzeigeBuergeraeamter.setPrefWidth(220);
    	txtAnzeigeBuergeraeamter.setPrefHeight(185);
   	pane.getChildren().add(txtAnzeigeBuergeraeamter);        	
    	// Button
   	btnAnzeigeBuergeraeamter.setLayoutY(290);
    	pane.getChildren().add(btnAnzeigeBuergeraeamter);
        	
        //Menu
      
    	//HBox
    	hb.getChildren().add(pane);
    	hb.setMargin(pane, new Insets(0,30,0,30));
     }
	
	private void initKomponentenStaedtischeEinrichtung() {
		// Label
		Font font = new Font("Arial", 20);
		lblAnzeigeEinrichtung.setLayoutY(40);
		lblAnzeigeEinrichtung.setFont(font);
		lblAnzeigeEinrichtung.setStyle("-fx-font-weight: bold;"); 
		paneSta.getChildren().add(lblAnzeigeEinrichtung);    
    	// Textbereich	
    	txtAnzeigeEinrichtung.setEditable(false);
    	txtAnzeigeEinrichtung.setLayoutY(90);
    	txtAnzeigeEinrichtung.setPrefWidth(220);
    	txtAnzeigeEinrichtung.setPrefHeight(185);
    	paneSta.getChildren().add(txtAnzeigeEinrichtung);        	
    	// Button
    	btnAnzeigeEinrichtung.setLayoutY(290);
    	paneSta.getChildren().add(btnAnzeigeEinrichtung);
    	//Menu
       
    	//HBox
    	hb.getChildren().add(paneSta);
    	hb.setMargin(paneSta, new Insets(0,30,0,30));
    	
	}

    private void initListener() {
    	btnAnzeigeEinrichtung.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeSteadtischeEinrichtungAn();
			}
		});
 	   
    }

    public void zeigeBuergeramtAn() {
        if (buergeraemterModel.getBuergeraemterList().size() > 0) {
            StringBuffer text = new StringBuffer();
            for (Buergeramt buergeramt : buergeraemterModel.getBuergeraemterList()) {
                text.append(buergeramt.gibBuergeramtZurueck(' ')).append("\n");
            }
            txtAnzeigeBuergeraeamter.setText(text.toString());
        } else {
            zeigeInformationsfensterAn("Bisher wurde kein Buergeramt aufgenommen!");
        }
    }

    public void zeigeSteadtischeEinrichtungAn() {
    	staedtischeEinrichtungenControl.leseStaedtischeEinrichtungAusCsvDatei();
        if (staedtischeEinrichtungenModel.getEinrichtungenList().size() > 0) {
            StringBuffer text = new StringBuffer();
            for (StaedtischeEinrichtung buergeramt : staedtischeEinrichtungenModel.getEinrichtungenList()) {
                text.append(buergeramt.gibStaedtischeEinrichtungZurueck(' ')).append("\n");
            }
            txtAnzeigeEinrichtung.setText(text.toString());
        } else {
            zeigeInformationsfensterAn("Bisher wurde kein Buergeramt aufgenommen!");
        }
    }
    public void zeigeInformationsfensterAn(String meldung) {
        new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
    }

}
