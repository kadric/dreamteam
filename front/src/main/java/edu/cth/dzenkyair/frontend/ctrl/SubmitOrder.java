package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.backend.core.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class SubmitOrder implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubmitOrder.class.getSimpleName());

    @Inject // Bad use setter or constructor injection
    private FlightModel flightModel;

    public Flight getFlight() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        Flight f = (Flight) externalContext.getSessionMap().get("flight");
        return f;
    }
    
    public Collection<Passenger> getPassengerList() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        List<Passenger> ps = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
        return ps;
    }
    
    public double getFlightPrice() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Flight f = (Flight) externalContext.getSessionMap().get("flight");
        return f.getPrice();
    }
    
    public double getBaggagePrice(String baggage) {
        if(baggage.equals("Large"))
            return 20;
        else if(baggage.equals("Small"))
            return 10;
        else
            return 0;
    }
    
    public double getTotalPrice() {
        double total = 0;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        List<Passenger> ps = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
        if(ps == null)
            return total;
        for(int i=0; i<ps.size(); i++) {
            total = total + getFlightPrice() + getBaggagePrice(ps.get(i).getBaggage());
        }
        return total;
    }
    
    public String submitOrder() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        // GET FROM SESSION
        Flight f = (Flight) externalContext.getSessionMap().get("flight");
        List<Passenger> ps = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
        User u = (User) externalContext.getSessionMap().get("user");
        Order o = new Order(f, u);
        
        // STORE IN DATABASE
        flightModel.getOrderList().create(o);
        for(int i=0; i<ps.size(); i++) {
            Passenger p = ps.get(i);
            p = new Passenger(p.getId(), f, o, p.getFirstName(), p.getLastName(), p.getBaggage());
            flightModel.getPassengerList().create(p);
        }
        
        // DELETE SESSION
        externalContext.getSessionMap().put("line", null);
        externalContext.getSessionMap().put("departure", null);
        externalContext.getSessionMap().put("flight", null);
        externalContext.getSessionMap().put("passengerList", null);
        return "userpage?faces-redirect=true";
    }

}
