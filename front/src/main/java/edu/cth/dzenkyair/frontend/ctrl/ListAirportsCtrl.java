package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import edu.cth.dzenkyair.frontend.view.ListAirportsBB;
import java.io.Serializable;
import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class ListAirportsCtrl implements Serializable {
    
    @Inject
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;
    
    private ListAirportsBB listAirportsBB;
    
    protected ListAirportsCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setPassengerBB(ListAirportsBB airportsBB) {
        this.listAirportsBB = airportsBB;
    }
    
    public void setFromAirport(Long id) {
        listAirportsBB.setFromId(id);
    }
    public String search() {
        if(listAirportsBB.getLineId() == null || listAirportsBB.getDate() == null 
                || listAirportsBB.getNPassengers() <= 0) {
            listAirportsBB.setError("Please fill all fields");
            return null;
        }
        Line l = flightModel.getLineList().find(listAirportsBB.getLineId());
        Calendar d = Calendar.getInstance();
        d.setTime(listAirportsBB.getDate());
        flightSession.setLine(l);
        flightSession.setDeparture(d);
        flightSession.setNPassengers(listAirportsBB.getNPassengers());

        return "pages/selectflight?faces-redirect=true";
    }
}
