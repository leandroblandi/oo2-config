package test;
import java.time.LocalDate;

import excepciones.ClienteYaExisteException;
import negocio.ClienteABM;

public class Test {

	public static void main(String[] args) {
		
		ClienteABM abm = new ClienteABM();
		
		try {
			abm.agregar("Blandi", "Gabriel", 23493076, LocalDate.now().minusDays(1L));
		} catch (ClienteYaExisteException e) {
			e.printStackTrace();
		}

		System.out.println(abm.traer());
		
		System.out.println("OK");
	}
}


