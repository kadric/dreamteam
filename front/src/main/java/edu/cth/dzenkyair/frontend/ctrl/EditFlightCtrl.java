/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Status;
import edu.cth.dzenkyair.frontend.view.EditFlightBB;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author matej
 */
@Named
@RequestScoped
public class EditFlightCtrl implements Serializable{
    
    @Inject
    private FlightModel flightModel;
    
    private EditFlightBB flightBB;
    
    protected EditFlightCtrl() {
        // Must have for CDI
    }
    
    @Inject
    public void setFlightBB(EditFlightBB flightBB){
        this.flightBB = flightBB;
    }
    public String cancel() {
        return "/private/user/userpage?faces-redirect=true";
    }
    
    public String update() {
        Long id = flightBB.getId();
        Date departure = flightBB.getDeparture();
        Date arrival = flightBB.getArrival();
        String status = flightBB.getStatus();

        Flight f = flightModel.getFlightList().find(id);
       
            if(f == null){
               flightBB.setError("The flight id doesn't exist");
               return null;
               }
       
        Calendar dep = Calendar.getInstance();
        dep = f.getDeparture();
        
            if(departure!=null){
                dep.setTime(departure);
                }
        
        Calendar arr = Calendar.getInstance();
        arr = f.getArrival();
         
            if(arrival != null){
                arr.setTime(arrival);
            }
        
        Status stat = f.getStatus();
        
            if(status != null){
                stat = Status.valueOf(status);
            }
        
            
                     
            Flight newf = new Flight(id,f.getLine(), dep, arr, f.getMaxPass(),f.getPrice(), stat);
            flightModel.getFlightList().update(newf);
            
            return "/private/user/userpage?faces-redirect=true";
          
        }
        
    }

