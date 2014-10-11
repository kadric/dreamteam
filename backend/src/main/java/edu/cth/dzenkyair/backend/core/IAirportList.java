
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to airport list
 * @author Dženan
 */
@Local
public interface IAirportList extends IDAO<Airport, Long> {

    public List<Airport> getByName(String name);
     
}
