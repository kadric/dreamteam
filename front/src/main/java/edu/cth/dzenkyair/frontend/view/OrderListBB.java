/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author matej
 */
@Named
@ViewScoped
public class OrderListBB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(ViewOrderBB.class.getName());
    
    @Inject // Bad use setter or constructor injection
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;
    
    private Long id;
    
       public OrderListBB(){
        ;
    }
   public String findOrder(ActionEvent actionEvent) {
       
        return "/private/user/viewpage.xhtml?id=" + id + "?faces-redirect=true";
    }
     
   
    public Collection<Order> getOrders(){
        
        List<Order> orders = flightModel.getOrderList().getByUser(flightSession.getUser());
                
           
        return orders;
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}