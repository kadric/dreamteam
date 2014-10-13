
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to passenger list
 * @author Dženan
 */
@Local
public interface IPassengerList extends IDAO<Passenger, Long> {

    public List<Passenger> getByFlight(Flight flight);
    
    public List<Passenger> getByOrder(Order order);
     
}
