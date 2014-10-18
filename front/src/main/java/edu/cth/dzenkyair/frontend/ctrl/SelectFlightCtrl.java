
package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.frontend.view.SelectFlightBB;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class SelectFlightCtrl implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(SelectFlightCtrl.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    private SelectFlightBB flightBB;
    
    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
    protected SelectFlightCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setFlightBB(SelectFlightBB flightBB) {
        this.flightBB = flightBB;
    }
    
    public String select() {
        if(flightBB.getId() == null) {
            flightBB.setError("You have not selected any flight");
            return "selectflight?faces-redirect=false";
        }
        Flight f = flightModel.getFlightList().find(flightBB.getId());
        sessionMap.put("flight", f);
        return "addpassenger?faces-redirect=true";
    }
    
    public void selectItem(Long id) {
        flightBB.setId(id);
    }
}
