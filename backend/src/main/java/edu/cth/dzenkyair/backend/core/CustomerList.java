package edu.cth.dzenkyair.backend.core;


import edu.cth.dzenkyair.backend.core.Customer;
import edu.cth.dzenkyair.backend.core.ICustomerList;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.backend.core.User;
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
public class CustomerList extends AbstractDAO<Customer, Long>
        implements ICustomerList {

    @PersistenceContext
    private EntityManager em;
    
    public CustomerList() {
        super(Customer.class);
    }
    @Override
    protected EntityManager getEntityManager() {
            return em;
        }

    @Override
    public List<Customer> getByUser (User user) {
        String jpql = "select c from Customer c where c.user=:user";
        return em.createQuery(jpql, Customer.class).
                setParameter("user", user).getResultList();
    }
    
}
