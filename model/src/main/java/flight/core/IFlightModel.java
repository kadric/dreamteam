package flight.core;

import javax.ejb.Local;

/**
 * Public interface for the shop
 * @author Dženan
 */
@Local
public interface IFlightModel {

    public IUserList getUserList();
}
