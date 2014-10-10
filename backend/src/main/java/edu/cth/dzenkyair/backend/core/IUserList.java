
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to user list
 * @author Dženan
 */
@Local
public interface IUserList extends IDAO<User, Long> {

     public List<User> getByEmail(String name);
}
