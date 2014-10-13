/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author matej
 * 
 */
@Local
public interface ICustomerList extends IDAO<Customer, Long> {
        
        public List<Customer> getByUser(User user);
}
