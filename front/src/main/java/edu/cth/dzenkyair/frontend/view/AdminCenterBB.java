package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Airport;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */
@Named
@ViewScoped
public class AdminCenterBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(AdminCenterBB.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    private Long orderId;
    private Long fromAirportId;
    private Long lineId;
    private String error;
       
    public Collection<Airport> getAllAirport() {
        return flightModel.getAirportList().findAll();
    }
    
    public Collection<Line> getLines() {
        if(fromAirportId == null)
            return new ArrayList<Line>();
        Airport airport = flightModel.getAirportList().find(fromAirportId);
        return flightModel.getLineList().getByFromAirport(airport);
    }
    
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getFromAirportId() {
        return fromAirportId;
    }
    public void setFromAirportId(Long fromAirportId) {
        this.fromAirportId = fromAirportId;
    }
    
    public Long getLineId() {
        return lineId;
    }
    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
