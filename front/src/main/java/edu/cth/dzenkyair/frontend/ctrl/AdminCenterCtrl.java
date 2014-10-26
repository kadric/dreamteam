package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.frontend.view.AdminCenterBB;
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
public class AdminCenterCtrl implements Serializable {

    @Inject
    private FlightModel flightModel;
    
    private AdminCenterBB adminCenterBB;
    
    @Inject
    public void setAdminCenterBB (AdminCenterBB adminCenterBB){
        this.adminCenterBB = adminCenterBB;
    } 

    public String searchOrder() {
        Long id = adminCenterBB.getOrderId();
        if(id == null) {
            adminCenterBB.setError("Please type in order id");
            return null;
        }
        Order o = flightModel.getOrderList().find(id);
        if(o == null) {
            adminCenterBB.setError("The order id doesn't exist");
            return null;
        }
        return "/private/user/vieworder?faces-redirect=true&id="+id;
    }
    
    public String searchFlight() {
        Long id = adminCenterBB.getLineId();
        if(id == null) {
            adminCenterBB.setError("Please select from and to airport");
            return null;
        }

        return "/private/admin/searchflight?faces-redirect=true&id="+id;
    }
}
