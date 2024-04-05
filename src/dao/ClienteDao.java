package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Cliente;
public class ClienteDao {
	
	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Cliente objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}
	
	public void actualizar(Cliente objeto) {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public void eliminar(Cliente objeto) {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public Cliente traer(long idCliente) {
		Cliente objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cliente) session.get(Cliente.class, idCliente);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	public Cliente traer(int dni) {
		Cliente cliente = null;
		try {
			iniciaOperacion();
			cliente = (Cliente) session.createQuery("from Cliente c where c.dni"
					+ "= :dni").setParameter("dni", dni)
					.uniqueResult();
					// En este caso :dni es un marcador de posición para el parámetro.
					// Al utilizar el método setParameter para asignar el valor del
					//parámetro dni esto ayuda a prevenir la inyección de SQL.
		} finally {
			session.close();
		}
		return cliente;
	}
	
	public List<Cliente> traer() {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			iniciaOperacion();
			Query<Cliente> query = session.createQuery("from Cliente c order by "
					+ "c.apellido asc, c.nombre asc", Cliente.class);
					lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
}

