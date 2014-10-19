package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.frontend.view.ListAirportsBB;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 *
 * @author Dženan
 */
@Named
@ViewScoped
public class ListAirportsCtrl implements Serializable {
    
    @Inject
    private FlightModel flightModel;
    
    private ListAirportsBB airportsBB;
    
    protected ListAirportsCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setPassengerBB(ListAirportsBB airportsBB) {
        this.airportsBB = airportsBB;
    }
    
    public void setFromAirport(Long id) {
        airportsBB.setFromId(id);
    }
    public String search() {
        if(airportsBB.getLineId() == null) {
            airportsBB.setError("Please select a destination");
            return "index?faces-redirect=false";
        }
        Line l = flightModel.getLineList().find(airportsBB.getLineId());
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("line", l);
        return "selectflight?faces-redirect=true";
    }
}
