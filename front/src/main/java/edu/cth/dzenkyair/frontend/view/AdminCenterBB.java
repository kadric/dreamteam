package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.FlightModel;
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
public class AdminCenterBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(AdminCenterBB.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    private Long orderId;
    private String error;
    
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
