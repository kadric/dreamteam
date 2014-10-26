package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.Collection;
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
public class SubmitOrder implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubmitOrder.class.getSimpleName());

    @Inject // Bad use setter or constructor injection
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;

    public Flight getFlight() {
        Flight f = flightSession.getFlight();
        return f;
    }
    
    public Collection<Passenger> getPassengerList() {
        List<Passenger> ps = flightSession.getPassengerList();
        return ps;
    }
    
    public double getFlightPrice() {
        Flight f = flightSession.getFlight();
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
        List<Passenger> ps = flightSession.getPassengerList();
        if(ps == null)
            return total;
        for(int i=0; i<ps.size(); i++) {
            total = total + getFlightPrice() + getBaggagePrice(ps.get(i).getBaggage());
        }
        return total;
    }
    
    public String submitOrder() {
        
        // GET FROM SESSION
        Flight f = flightSession.getFlight();
        List<Passenger> ps = flightSession.getPassengerList();
        User u = flightSession.getUser();
        Order o = new Order(f, u);
        int newMaxPass = f.getMaxPass() - ps.size();
        Flight newf = new Flight(f.getId(), f.getLine(), f.getDeparture(), 
                f.getArrival(), newMaxPass, f.getPrice(), f.getStatus());
        
        // CHECK CONCURRENCY
        Flight fc = flightModel.getFlightList().find(f.getId());
        if(fc.getMaxPass() != f.getMaxPass()) {
            flightSession.clearOrder();
            return "/index?faces-redirect=true";
        }
        
        // STORE IN DATABASE
        flightModel.getOrderList().create(o);
        for(int i=0; i<ps.size(); i++) {
            Passenger p = ps.get(i);
            p = new Passenger(p.getId(), f, o, p.getFirstName(), p.getLastName(), p.getBaggage());
            flightModel.getPassengerList().create(p);
        }
        flightModel.getFlightList().update(newf);
        
        // DELETE SESSION
        flightSession.clearOrder();
        return "/private/user/userpage?faces-redirect=true";
    }

}
