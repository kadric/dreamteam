package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Passenger;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import edu.cth.dzenkyair.frontend.view.AddPassengerBB;
import edu.cth.dzenkyair.frontend.view.UpdatePassengerBB;
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
public class UpdatePassengerCtrl implements Serializable {

    @Inject
    private FlightModel flightModel;
    
    private UpdatePassengerBB updatePassengerBB;
        
    protected UpdatePassengerCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setPassengerBB(UpdatePassengerBB updatePassengerBB) {
        this.updatePassengerBB = updatePassengerBB;
    }
    
    public String cancel() {
        Long id = updatePassengerBB.getId();
        if(id == null)
            return "vieworder?faces-redirect=true";
        Long oid = flightModel.getPassengerList().find(id).getOrder().getId();
        return "vieworder?faces-redirect=true&id="+oid;
    }
    
    public String change() {
        Long id = updatePassengerBB.getId();
        if(updatePassengerBB.getBaggage() == null) {
            updatePassengerBB.setError("Please select a baggage");
            return null; //View scope, cache-storage?
        }
        Passenger p = flightModel.getPassengerList().find(id);
        Passenger pn = new Passenger(p.getId(), p.getFlight(), p.getOrder(), 
                p.getFirstName(), p.getLastName(), updatePassengerBB.getBaggage());
        flightModel.getPassengerList().update(pn);
        Long oid = flightModel.getPassengerList().find(id).getOrder().getId();
        return "vieworder?faces-redirect=true&id="+oid;
    }

}
