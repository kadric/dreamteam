package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.ArrayList;
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
public class SearchFlightBB implements Serializable {
        
    @Inject
    private FlightModel flightModel;
    
    private Long id;
    
    protected SearchFlightBB() {
        // Must have for CDI
    }
    
    public Collection<Flight> getFlightList() {
        if(id == null)
            return new ArrayList<Flight>();
        Line line = flightModel.getLineList().find(id);
        Calendar departure = Calendar.getInstance();
        
        return flightModel.getFlightList().getByLineAndDeparture(line, departure);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
