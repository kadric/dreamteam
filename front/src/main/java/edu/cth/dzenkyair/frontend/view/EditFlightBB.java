/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author matej
 */
@Named
@ViewScoped
public class EditFlightBB implements Serializable{
    
    @Inject
    private FlightModel flightModel;
    
    private Long id;
    private Date departure;
    private Date arrival;
    private String status;
    private String error;
    

     public Flight getFlight() {
        if(id == null)
            return null;
        Flight f = flightModel.getFlightList().find(id);
        return f;
    }
    
       
     public Collection<String> getAllStatus(){
     List<String> statuses = new ArrayList<String>();
     statuses.add(Status.OK.name());
     statuses.add(Status.CANCELLED.name());
     statuses.add(Status.DELAYED.name());
     
     return statuses;
   }
     
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

       public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

   public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
