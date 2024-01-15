package gui.guiStaedtischeEinrichtungen;

import java.io.IOException;

import business.Buergeramt.BuergeraemterModel;
import business.StaetdtischeEinrichtungen.StaedtischeEinrichtungenModel;
import gui.guiBuergeraemter.BuergeraemterView;
import javafx.stage.Stage;
import ownUtil.MyObserver;

public class StaedtischeEinrichtungenControl implements MyObserver{
	
	private StaedtischeEinrichtungenView staedtischeEinrichtungenView;
	//
	private StaedtischeEinrichtungenModel staedtischeEinrichtungenModel;
	//
	private BuergeraemterModel buergeraemterModel;

	public StaedtischeEinrichtungenControl(Stage primaryStage){
		this.buergeraemterModel = BuergeraemterModel.getInstanz();
		//
		this.staedtischeEinrichtungenModel = StaedtischeEinrichtungenModel.getInstanz();
		//
		this.staedtischeEinrichtungenView = new StaedtischeEinrichtungenView(primaryStage,buergeraemterModel,this,staedtischeEinrichtungenModel);
		buergeraemterModel.addObserver(this);
		//
		staedtischeEinrichtungenModel.addObserver(this);
		
	}
	//
	public void leseStaedtischeEinrichtungAusCsvDatei()  {
		try {
			staedtischeEinrichtungenModel.leseStaedtischeEinrichtungAusCsvDatei();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			staedtischeEinrichtungenView.zeigeInformationsfensterAn("Fehler beim lesen aus der CSV Datei");		
			}
	}
	//
	@Override
	public void update() {
		this.staedtischeEinrichtungenView.zeigeBuergeramtAn();
	}

}
