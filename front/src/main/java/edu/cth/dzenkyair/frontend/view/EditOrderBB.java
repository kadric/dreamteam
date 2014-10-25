package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import java.io.Serializable;
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
public class EditOrderBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(EditOrderBB.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    private Long id;
    private Long flightId;
    private String error;

    public Order getOrder() {
        if(id == null)
            return null;
        Order o = flightModel.getOrderList().find(id);
        return o;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
