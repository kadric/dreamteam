
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to line list
 * @author matej
 */
@Local
public interface ILineList extends IDAO<Line, Long> {

    public List<Line> getByToAirport (Airport toAirport);
    
    public List<Line> getByFromAirport (Airport toAirport);
     
}
