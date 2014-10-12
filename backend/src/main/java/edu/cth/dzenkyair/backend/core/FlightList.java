package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All flights
 *
 * @author Dženan
 */
@Stateless
public class FlightList extends AbstractDAO<Flight, Long>
        implements IFlightList {
    
    @PersistenceContext
    private EntityManager em;
    
    public FlightList() {
        super(Flight.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Flight> getByLine(Line line){
        String jpql = "select f from Flight f where f.line=:line";
        return em.createQuery(jpql, Flight.class).
                setParameter("line", line).getResultList();
    }
}
