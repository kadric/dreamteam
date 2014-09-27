package flight.util;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here 
 * (Create, Read, Update, Delete = CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 * @param <T> Any type
 * @param <K> Key
 */
public abstract class AbstractDAO<T, K>
        implements IDAO<T, K> {

    // Emulate a database
    //private final List<T> elems = new ArrayList<>();
    private final Class<T> clazz;
    
    public AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;        
    }
    
    protected abstract EntityManager getEntityManager();
    
    @Override
    public void create(T t) {
        getEntityManager().persist(t);
    }

    @Override
    public void delete(K id) {
        T t = getEntityManager().getReference(clazz, id);
        if (t != null) {
            getEntityManager().remove(t);
        }
    }

    @Override
    public void update(T t) {
        getEntityManager().merge(t);
    }

    @Override
    public T find(K id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return get(true, -1, -1);
    }

    @Override
    public List<T> findRange(int first, int n) {
        return get(false, first, n);
    }
    
    private List<T> get(boolean all, int first, int n) {
        EntityManager em = getEntityManager();
        List<T> found = new ArrayList<>();
        // Warning because typename not found in string (clazz.getSimpleName())
        // Criteria API better, possible misstakes in String, NOTE space before t
        TypedQuery<T> q = em.createQuery("select t from " + clazz.getSimpleName() + " t", clazz);
        if (!all) {
            q.setFirstResult(first);
            q.setMaxResults(n);
        }
        found.addAll(q.getResultList());
        return found;
    }

    @Override
    public int count() {
        EntityManager em = getEntityManager();
        // Warning because typename not found in string (clazz.getSimpleName())
        // Criteria API better, possible misstakes in String, NOTE space before t      
        Long n = em.createQuery("select count(t) from " + clazz.getSimpleName() + " t", Long.class)
                .getSingleResult();
        return n.intValue();
    }
}
