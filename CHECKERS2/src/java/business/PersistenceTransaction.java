package business;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lucas Fernando Frighetto
 * @param <TYPE>
 */
public class PersistenceTransaction <TYPE> {
    
    private Session session;
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final PersistenceTransaction instance = new PersistenceTransaction();

    private PersistenceTransaction() {}

    public static synchronized PersistenceTransaction instance() {
        return instance;
    }
    
    public void create(Object o) {
        session = sessionFactory.openSession();
        session.beginTransaction();         
        session.save(o);     
        session.getTransaction().commit();        
        session.close();
    }
    
    public TYPE read(Class<TYPE> type, Serializable id) {
        session = sessionFactory.openSession();
        session.beginTransaction();            
        TYPE valueObject = session.get(type, id);        
        session.getTransaction().commit();
        session.close();
        return valueObject;
    }                
    
    public void update(Object o) {
        session = sessionFactory.openSession();
        session.beginTransaction();               
        session.update(o);        
        session.getTransaction().commit();        
        session.close();
    }
    
    public void delete(Object o) {        
        session = sessionFactory.openSession();
        session.beginTransaction();          
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }
    
}