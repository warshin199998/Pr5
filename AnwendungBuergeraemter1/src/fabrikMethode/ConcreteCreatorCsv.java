package fabrikMethode;

import java.io.IOException;

import business.Buergeramt.Buergeramt;

public class ConcreteCreatorCsv extends Creator {

	@Override
	public Product factoryMethod() throws IOException {
		return new ConcreteCsvProduct()  ;
		
	}
	



}


/*
 
 *    
		    
		   
 */
