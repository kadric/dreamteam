
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class ViewOrderBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(ViewOrderBB.class.getName());
    
    @Inject // Bad use setter or constructor injection
    private FlightModel flightModel;
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Flight getFlight() {
        if(id == null)
            return null;
        Order o = flightModel.getOrderList().find(id);
        if(o == null)
            return null;
        Flight f = o.getOrderFlight();
        return f;
    }
    
    public List<Passenger> getPassengers() {
        if(id == null)
            return null;
        Order o = flightModel.getOrderList().find(id);
        if(o == null)
            return null;
        List<Passenger> ps = flightModel.getPassengerList().getByOrder(o);
        return ps;
    }
}
