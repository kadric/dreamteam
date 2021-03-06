/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.backend.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Dženan
 */
@ApplicationScoped
public class FlightModel implements IFlightModel {
    
    @EJB
    private IUserList userList;
    @EJB
    private IAirportList airportList;
    @EJB
    private ILineList lineList;
    @EJB
    private IFlightList flightList;
    @EJB
    private IOrderList orderList;
    @EJB
    private IPassengerList passengerList;
    @EJB
    private ICustomerList customerList;
    
    public FlightModel() {
        Logger.getAnonymousLogger().log(Level.INFO, "Flight model alive");
    }
    
    @Override
    public IUserList getUserList() {
        return userList;
    }
    
    @Override
    public IAirportList getAirportList() {
        return airportList;
    }

    @Override
    public ILineList getLineList() {
        return lineList;
    }
    
    @Override
    public IFlightList getFlightList() {
        return flightList;
    }
    
    @Override
    public IOrderList getOrderList(){
        return orderList;
    }
    
    @Override
    public IPassengerList getPassengerList(){
        return passengerList;
    }
    
    @Override
    public ICustomerList getCustomerList(){
        return customerList;
    }
}
