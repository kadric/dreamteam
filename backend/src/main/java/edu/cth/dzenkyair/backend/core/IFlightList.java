
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to flight list
 * @author Dženan
 */
@Local
public interface IFlightList extends IDAO<Flight, Long> {

    public List<Flight> getByLine(Line line);
    
    public List<Flight> getByLineAndDeparture(Line line, Calendar departure);
     
}
