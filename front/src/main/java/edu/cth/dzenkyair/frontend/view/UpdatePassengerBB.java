
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */
@Named
@ViewScoped
public class UpdatePassengerBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(UpdatePassengerBB.class.getName());
    
    @Inject // Bad use setter or constructor injection
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;
    
    private Long id;
    private String baggage;
    private String error;
    
    public Passenger getPassenger() {
        if(id == null)
            return null;
        Passenger p = flightModel.getPassengerList().find(id);
        if(p == null)
            return null;
        return p;
    }
    
    public List<String> getBaggages() {
        List<String> bs = new ArrayList<String>();
        Passenger p = flightModel.getPassengerList().find(id);
        String b = p.getBaggage();
        if(b.equals("Small"))
            bs.add("Large");
        else if(b.equals("Handbag")) {
            bs.add("Small");
            bs.add("Large");
        }
        return bs;
    }
    
    public double getPrice() {
        if(baggage == null)
            return 0;
        if(baggage.equals("Small"))
            return 10;
        return 20;
    }
    
    public boolean viewCheck() {
        if(id == null)
            return false;
        Passenger p = flightModel.getPassengerList().find(id);
        if(p == null)
            return false;
        if(p.getBaggage().equals("Large"))
            return false;
        Order o = p.getOrder();
        Calendar departure = o.getOrderFlight().getDeparture();
        Calendar current = Calendar.getInstance();
        if(departure.before(current))
            return false;
        User u = flightSession.getUser();
        boolean b = flightModel.getOrderList().getByUser(u).contains(o);
        return b;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBaggage() {
        return baggage;
    }
    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
