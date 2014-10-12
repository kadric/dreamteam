package edu.cth.dzenkyair.backend.core;

import javax.ejb.Local;

/**
 * Public interface for the flight model
 * @author Dženan
 */
@Local
public interface IFlightModel {

    public IUserList getUserList();
    
    public IAirportList getAirportList();
    
    public ILineList getLineList();
    
    public IFlightList getFlightList();
}
