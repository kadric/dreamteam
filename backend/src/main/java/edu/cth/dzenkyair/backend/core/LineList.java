package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractDAO;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matej
 */
@Stateless
public class LineList extends AbstractDAO<Line, Long> implements ILineList
        {
    
    @PersistenceContext
    private EntityManager em;
    
    public LineList() {
        super(Line.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    

    @Override
    public List<Line> getByFromAirport(Airport fromAirport) {
          
        String jpql = "select l from Line l where l.fromAirport=:fromAirport";
        return em.createQuery(jpql, Line.class).
                setParameter("fromAirport", fromAirport).getResultList(); 
    }

    @Override
    public List<Line> getByToAirport(Airport toAirport) {
        String jpql = "select l from Line l where l.toAirport=:toAirport";
        return em.createQuery(jpql, Line.class).
                setParameter("toAirport", toAirport).getResultList();
    }
}
