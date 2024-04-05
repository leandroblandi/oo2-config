package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.ClienteDao;
import excepciones.ClienteNoExisteException;
import excepciones.ClienteYaExisteException;
import modelo.Cliente;

public class ClienteABM {
	
	ClienteDao dao = new ClienteDao();
	
	public Cliente traer(long idCliente) {
		return dao.traer(idCliente);
	}
	public Cliente traer(int dni) {
		return dao.traer(dni);
	}
	public int agregar(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento) throws ClienteYaExisteException {
		// consultar si existe un cliente con el mismo dni, y si existe, arrojar la Excepcion
		
		Cliente clienteExistente = traer(dni);
		
		if(clienteExistente != null) {
			throw new ClienteYaExisteException(dni);
		}
		
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		return dao.agregar(c);
	}
	public void modificar(Cliente c) throws ClienteNoExisteException, ClienteYaExisteException {

		// Verificamos que exista el cliente a modificar
		Cliente cliente = traer(c.getIdCliente());
		
		if(cliente == null) {
			throw new ClienteNoExisteException(c.getIdCliente());
		}
		
		// Verificamos que el DNI modificado no pertenezca a otro Cliente
		Cliente clienteConDniModificado = traer(c.getDni());
		
		if(clienteConDniModificado != null) {
			throw new ClienteYaExisteException(c.getDni(), "No se puede modificar el cliente, ya hay otro cliente con el mismo DNI");
		}
		
		dao.actualizar(c);
	}
	public void eliminar(long idCliente) throws ClienteNoExisteException {
		/*
		 * En este caso la baja es física y sabemos que la entidad no tiene relaciones
		 * pero en caso de tenerlas, hay que validar que el cliente no tenga dependencias que
		 * generen errores al borrarlo.
		 */
		Cliente c = dao.traer(idCliente);
		// Implementar que si es null que arroje la excepción la Excepción de que el cliente no existe
		
		if(c == null) {
			throw new ClienteNoExisteException(idCliente);
		}
		
		dao.eliminar(c);
	}
	public List<Cliente> traer() {
		return dao.traer();
	}
}
