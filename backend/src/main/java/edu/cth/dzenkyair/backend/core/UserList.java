
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
public class UserList extends AbstractDAO<User, Long> 
                    implements IUserList {
    
    @PersistenceContext
    private EntityManager em;
    
    public UserList() {
        super(User.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<User> getByEmail(String email) {
        String jpql = "select u from User u where u.email=:email";
        return em.createQuery(jpql, User.class).
                setParameter("email", email).getResultList();
    }

}
