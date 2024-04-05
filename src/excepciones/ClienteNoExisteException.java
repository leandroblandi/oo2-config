package excepciones;

public class ClienteNoExisteException extends Exception {

	private static final long serialVersionUID = -7282011645233503822L;

	public ClienteNoExisteException(long id) {
		super("El cliente con id " + id + " no existe");
	}
	
	public ClienteNoExisteException(int dni) {
		super("El cliente con dni " + dni + " no existe");
	}
	
}
