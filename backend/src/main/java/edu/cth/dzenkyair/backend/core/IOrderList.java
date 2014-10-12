package edu.cth.dzenkyair.backend.core;


import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author matej
 */
@Local
public interface IOrderList extends IDAO<Order, Long> {
    
    public List<Order> getByFlight (Flight flight);
    
    public List<Order> getByUser (User user);
    
}
