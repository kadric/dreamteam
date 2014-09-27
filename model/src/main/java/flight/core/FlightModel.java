/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author Dženan
 */
public class FlightModel implements IFlightModel {
    
    @EJB
    private IUserList userList;
    
    public FlightModel() {
        Logger.getAnonymousLogger().log(Level.INFO, "Flight model alive");
    }
    
    @Override
    public IUserList getUserList() {
        return userList;
    }
}
