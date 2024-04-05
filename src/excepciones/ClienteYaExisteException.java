package excepciones;

public class ClienteYaExisteException extends Exception {

	private static final long serialVersionUID = 5971682531447744103L;

	public ClienteYaExisteException(long id) {
		super("El cliente con id " + id + " ya existe");
	}
	
	public ClienteYaExisteException(int dni) {
		super("El cliente con dni " + dni + " ya existe");
	}
	
	public ClienteYaExisteException(int dni, String msj) {
		super("El cliente con dni " + dni + " ya existe. Detalle: " + msj);
	}
	
}
