package fabrikMethode;

import java.io.IOException;

import business.Buergeramt.Buergeramt;

public abstract class Product {
	//
	public abstract void fuegeInDateiHinzu(Object object) throws IOException;
	//
	public abstract void schliessDatei()throws IOException ;
}
