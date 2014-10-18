package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.frontend.view.AddPassengerBB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class AddPassengerCtrl implements Serializable {

    @Inject
    private FlightModel flightModel;
    
    private AddPassengerBB passengerBB;
        
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    
    protected AddPassengerCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setPassengerBB(AddPassengerBB passengerBB) {
        this.passengerBB = passengerBB;
    }
    
    public Collection<Passenger> getPassengerList() {
        List<Passenger> ps = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
        
        return ps;
    }

    public String addPassanger() {
        if(passengerBB.getFirstName().isEmpty() || passengerBB.getLastName().isEmpty() || passengerBB.getBaggage().isEmpty()) {
            passengerBB.setError("Please type in all fields");
            return "addpassenger?faces-redirect=false";
        } else {
            List<Passenger> ps = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
            if(ps == null) {
                Passenger p = new Passenger(null, null, passengerBB.getFirstName(), 
                        passengerBB.getLastName(), passengerBB.getBaggage());
                ps = new ArrayList<Passenger>();
                ps.add(p);
                externalContext.getSessionMap().put("passengerList", ps);
            } else {
                Passenger p = new Passenger(null, null, passengerBB.getFirstName(), 
                        passengerBB.getLastName(), passengerBB.getBaggage());
                ps.add(p);
                externalContext.getSessionMap().put("passengerList", ps);
            }   
        }
        return "addpassenger?faces-redirect=true";
    }
    
    public String cont(){
       List<Passenger> pl = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
        if(pl == null){
            passengerBB.setError("Please add passenger(s)");
        return "addpassenger?faces-redirect=false";
        }else{
            return "register?faces-redirect=true";
        }
      
    }
    
    public String deletePassenger(String fname, String lname) {
         List<Passenger> ps = (List<Passenger>) externalContext.getSessionMap().get("passengerList");
         for(int i=0; i<ps.size(); i++) {
             if(ps.get(i).getFirstName().equals(fname) && ps.get(i).getLastName().equals(lname)) {
                 ps.remove(i);
                 break;
             }
         }
         externalContext.getSessionMap().put("passengerList", ps);
         return "addpassenger?faces-redirect=true";
    }
}
