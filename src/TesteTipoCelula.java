import java.lang.reflect.Field;

import negocio.TipoCelula;


public class TesteTipoCelula {

	public static void main(String[] args) {
		
		for(Field campo :TipoCelula.class.getDeclaredFields()){
			//System.out.println(campo.getName());
			if(campo.getType().equals(TipoCelula.class) ){
				System.out.println(campo.getDeclaringClass());
				//campo.
			}
			
			
		}
		
	}
	
}
