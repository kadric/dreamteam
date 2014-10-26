package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import edu.cth.dzenkyair.frontend.view.AddPassengerBB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
    
    @Inject
    private FlightSession flightSession;
    
    private AddPassengerBB passengerBB;
        
    protected AddPassengerCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setPassengerBB(AddPassengerBB passengerBB) {
        this.passengerBB = passengerBB;
    }
    
    public Collection<Passenger> getPassengerList() {
        List<Passenger> ps = flightSession.getPassengerList();
        return ps;
    }
    public Collection<String> getBaggages(){
        List<String> b = new ArrayList<String>();
        b.add("Handbag");
        b.add("Small");
        b.add("Large");
        return b;
    } 

    public String addPassanger() {
        String regex = "^\\pL+[\\pL\\pZ\\pP]{0,}$";
        Pattern pattern = Pattern.compile(regex);
        if(passengerBB.getFirstName().isEmpty() || passengerBB.getLastName().isEmpty() || passengerBB.getBaggage() == null) {
            passengerBB.setError("Please type in all fields");
            return "addpassenger?faces-redirect=false";
        } 
        if(!(pattern.matcher(passengerBB.getFirstName()).find()) || !(pattern.matcher(passengerBB.getLastName()).find())) {
            passengerBB.setError("Names can only contain letters");
            return "addpassenger?faces-redirect=false";
        } 
        if(flightSession.getPassengerList() == null) {
            ; //Do nothing
        } else if(flightSession.getPassengerList().size() >= flightSession.getNPassengers()) {
            passengerBB.setError("You can not add more passengers");
            return "addpassenger?faces-redirect=false";
        }
        List<Passenger> ps = flightSession.getPassengerList();
        if(ps == null) {
            Passenger p = new Passenger(null, null, passengerBB.getFirstName(), 
                    passengerBB.getLastName(), passengerBB.getBaggage());
            ps = new ArrayList<Passenger>();
            ps.add(p);
            flightSession.setPassengerList(ps);
        } else {
            Passenger p = new Passenger(null, null, passengerBB.getFirstName(), 
                    passengerBB.getLastName(), passengerBB.getBaggage());
            ps.add(p);
            flightSession.setPassengerList(ps);
        }   
        return "addpassenger?faces-redirect=true";
    }
    
    public String cont(){
        List<Passenger> pl = flightSession.getPassengerList();
        if(pl == null){
            passengerBB.setError("Please add passenger(s)");
            return "addpassenger?faces-redirect=false";
        } else if(pl.size() != flightSession.getNPassengers()) {
            passengerBB.setError("You need to add more passengers");
            return "addpassenger?faces-redirect=false";
        }else{
            if(flightSession.getUser() == null){
                return "register?faces-redirect=true";
            }else{
                return "submitorder?faces-redirect=true";
            }
        } 
    }
    
    public String deletePassenger(String fname, String lname) {
        List<Passenger> ps = flightSession.getPassengerList();
        for(int i=0; i<ps.size(); i++) {
            if(ps.get(i).getFirstName().equals(fname) && ps.get(i).getLastName().equals(lname)) {
                ps.remove(i);
                break;
            }
        }
        if(ps.size() <= 0)
            flightSession.setPassengerList(null);
        else
            flightSession.setPassengerList(ps);
        return "addpassenger?faces-redirect=true";
    }
}
