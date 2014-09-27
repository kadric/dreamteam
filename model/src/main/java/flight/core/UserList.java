
package flight.core;

import flight.util.AbstractDAO;
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

}
