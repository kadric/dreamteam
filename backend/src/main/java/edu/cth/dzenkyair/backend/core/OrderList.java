package edu.cth.dzenkyair.backend.core;



import edu.cth.dzenkyair.backend.util.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author matej
 */
@Stateless
public class OrderList extends AbstractDAO<Order, Long> 
                            implements IOrderList{
    
        @PersistenceContext
        private EntityManager em;

        public OrderList(){
            super(Order.class);
        }
        
         @Override
        public EntityManager getEntityManager() {
            return em;
           }

    @Override
    public List<Order> getByFlight(Flight flight) {
         String jpql = "select o from Order o where o.flight=:flight";
        return em.createQuery(jpql, Order.class).
                setParameter("flight", flight).getResultList(); 
    }

    @Override
    public List<Order> getByUser(User user) {
        String jpql = "select o from Order o where o.user=:user";
        return em.createQuery(jpql, Order.class).
                setParameter("user", user).getResultList();
    }
    
}
