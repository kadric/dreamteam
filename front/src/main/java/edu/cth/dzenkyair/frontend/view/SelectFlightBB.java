package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
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
public class SelectFlightBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(SelectFlightBB.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;
    
    private Long id;
    private String error;
    
    protected SelectFlightBB() {
        // Must have for CDI
    }
    
    public Collection<Flight> getFlightList() {
        Line line = flightSession.getLine();
        Calendar departure = flightSession.getDeparture();
        int nPassengers = flightSession.getNPassengers();
        
        return flightModel.getFlightList().getByLineDeparturePassenger(line, departure, nPassengers);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
}
