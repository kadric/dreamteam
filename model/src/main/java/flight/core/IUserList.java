
package flight.core;

import flight.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to product catalogue
 * @author Dženan
 */
@Local
public interface IUserList extends IDAO<User, Long> {

     public List<User> getByEmail(String name);
}
