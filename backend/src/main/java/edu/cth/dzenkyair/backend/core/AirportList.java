package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dženan
 */
@Stateless
public class AirportList extends AbstractDAO<Airport, Long>
        implements IAirportList {
    
    @PersistenceContext
    private EntityManager em;
    
    public AirportList() {
        super(Airport.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Airport> getByName(String name) {
        String jpql = "select a from Airport a where a.name=:name";
        return em.createQuery(jpql, Airport.class).
                setParameter("name", name).getResultList();
    }
}
