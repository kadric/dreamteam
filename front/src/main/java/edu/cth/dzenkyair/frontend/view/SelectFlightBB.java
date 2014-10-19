
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class SelectFlightBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(SelectFlightBB.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    private Long id;
    private String error;
    
    protected SelectFlightBB() {
        // Must have for CDI
    }
    
    public Collection<Flight> getFlightList() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        Line line = (Line) externalContext.getSessionMap().get("line");
        Calendar departure = (Calendar) externalContext.getSessionMap().get("departure");
        
        return flightModel.getFlightList().getByLineAndDeparture(line, departure);
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
