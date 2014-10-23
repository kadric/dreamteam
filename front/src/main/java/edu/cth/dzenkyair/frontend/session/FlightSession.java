package edu.cth.dzenkyair.frontend.session;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.backend.core.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 *
 * @author Dženan
 */
@Named
@SessionScoped
public class FlightSession implements Serializable {
    
    @Inject
    private FlightModel flightModel;
    
    private Line line;
    private Calendar departure;
    private Flight flight;
    private int nPassengers;
    private List<Passenger> passengerList;
    private User user;
    
    protected FlightSession() {
        // Must have for CDI
    }
    
    public Line getLine(){
        return line;
    }
    public void setLine(Line line) {
        this.line = line;
    }
    
    public Calendar getDeparture() {
        return departure;
    }
    public void setDeparture(Calendar departure) {
        this.departure = departure;
    }
    
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public int getNPassengers() {
        return nPassengers;
    }
    public void setNPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }
    
    public List<Passenger> getPassengerList() {
        return passengerList;
    }
    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
