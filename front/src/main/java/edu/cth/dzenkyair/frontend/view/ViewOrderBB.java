package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
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
    
    @Inject
    private FlightSession flightSession;
    
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
    
    public boolean notDepartured() {
        if(id == null)
            return false;
        Order o = flightModel.getOrderList().find(id);
        Calendar departure = o.getOrderFlight().getDeparture();
        Calendar current = Calendar.getInstance();
        return departure.after(current);
    }
    
    public boolean updatableBaggage(String baggage) {
        User u = flightSession.getUser();
        Order o = flightModel.getOrderList().find(id);
        if(!o.getOrderUser().equals(u))
            return false;
        if((baggage.equals("Handbag") || baggage.equals("Small")) && notDepartured())
            return true;
        return false;
    }
    
    public boolean viewCheck() {
        if(id == null)
            return false;
        Order o = flightModel.getOrderList().find(id);
        if(o == null)
            return false;
        User u = flightSession.getUser();
        if (u.getGroups().get(0).equals(Groups.ADMIN))
            return true;
        if(o.getOrderUser().getId() == u.getId())
            return true;
        return false;
    }
}
