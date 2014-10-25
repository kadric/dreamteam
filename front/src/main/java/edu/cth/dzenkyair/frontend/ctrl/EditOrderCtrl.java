package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.frontend.view.EditOrderBB;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class EditOrderCtrl implements Serializable {

    @Inject
    private FlightModel flightModel;
    
    private EditOrderBB orderBB;
    
    protected EditOrderCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setOrderBB (EditOrderBB orderBB){
        this.orderBB = orderBB;
    } 
    
    public String cancel() {
        return "/private/user/vieworder?faces-redirect=true&id="+orderBB.getId();
    }
    
    public String update() {
        Long id = orderBB.getFlightId();
        if(id == null) {
            orderBB.setError("Please type in flight id");
            return null;
        }
        Flight f = flightModel.getFlightList().find(id);
        if(f == null) {
            orderBB.setError("The flight id doesn't exist");
            return null;
        }
        Order o = orderBB.getOrder();
        Order newo = new Order(o.getId(), f, o.getOrderUser());
        flightModel.getOrderList().update(newo);
        return "/private/user/vieworder?faces-redirect=true&id="+orderBB.getId();
    }
}
