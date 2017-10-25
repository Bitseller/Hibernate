package persistencia.dao.impl;

import persistencia.dao.GenericDAO;
import persistencia.hibernate.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {
    private SessionFactory sessionFactory;
    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplHibernate.class.getName());

    public GenericDAOImplHibernate() {
        sessionFactory=HibernateUtil.getSessionFactory();
    }
    
    @Override
    public void guardarOActualizar(T entity) throws Exception {
        Session session = sessionFactory.openSession();//.getCurrentSession();	
    	try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            cerrarSession(session);
        } 
        catch (Exception ex) {
            rollback(session);
            cerrarSession(session);
            throw ex;
        }
    }
    
    @Override
    public T buscarPorId(ID id) throws Exception {
        Session session = sessionFactory.openSession();//.getCurrentSession();	
        try {
            session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);
            session.getTransaction().commit();
            cerrarSession(session);
            return entity;
        }   
        catch (Exception ex) {
            rollback(session);
            cerrarSession(session);
            throw ex;
        }
    }
    
    @Override
    public void borrar(ID id) throws Exception {
        Session session = sessionFactory.openSession();//.getCurrentSession();	
        try {
            session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);
            if (entity != null) {
	            session.delete(entity);
	            session.getTransaction().commit();
            }
            cerrarSession(session);
        } 
        catch (Exception ex) {
            rollback(session);
            cerrarSession(session);
            throw ex;
        }
    }

    @Override
    public List<T> traerTodos() throws Exception {
        Session session = sessionFactory.openSession();//.getCurrentSession();	
        try {
            Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
            List<T> entities = query.list();
            cerrarSession(session);
            return entities;
        } catch (Exception ex) {
            rollback(session);
            cerrarSession(session);
            throw ex;
        }
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

	private void cerrarSession(Session session) {
		if(session.isOpen())
			session.close();
	}

	private void rollback(Session session) {
		try {
		    if (session.getTransaction().isActive()) {
		        session.getTransaction().rollback();
		    }
		} catch (Exception exc) {
		    LOGGER.log(Level.WARNING,"Error en la transaccion y no pudo rebertirse", exc);
		}
	}
    
    
}
