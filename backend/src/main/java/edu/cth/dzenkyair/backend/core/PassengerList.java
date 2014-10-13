package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All passengers
 *
 * @author Dženan
 */
@Stateless
public class PassengerList extends AbstractDAO<Passenger, Long>
        implements IPassengerList {
    
    @PersistenceContext
    private EntityManager em;
    
    public PassengerList() {
        super(Passenger.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Passenger> getByFlight(Flight flight){
        String jpql = "select p from Passenger p where p.flight=:flight";
        return em.createQuery(jpql, Passenger.class).
                setParameter("flight", flight).getResultList();
    }
    
    @Override
    public List<Passenger> getByOrder(Order order){
        String jpql = "select p from Passenger p where p.order=:order";
        return em.createQuery(jpql, Passenger.class).
                setParameter("order", order).getResultList();
    }
}
