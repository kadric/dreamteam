/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Customer;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.view.RegisterBB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author matej
 */
@Named
@RequestScoped
public class RegisterCtrl implements Serializable{
    
    @Inject
    private FlightModel flightModel;
    
    private RegisterBB registerBB;
        
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    
    
    protected RegisterCtrl(){
    
    }
    
    @Inject
    public void setRegisterBB(RegisterBB registerBB) {
        this.registerBB = registerBB;
    }
    
    public Collection<Customer> getCustomerList(){
        //List<Customer> cl = (List<Customer>) externalContext.getSessionMap().get("customerList");
        List<Customer>cl = flightModel.getCustomerList().findAll();
        return cl;
    }
    
    public String register(){
   
        if(registerBB.getFirstName().isEmpty() || registerBB.getLastName().isEmpty() 
                || registerBB.getPassword().isEmpty() || registerBB.getConfirmPassword().isEmpty()
                || registerBB.getEmail().isEmpty() || registerBB.getAdress().isEmpty()
                || registerBB.getCity().isEmpty() || registerBB.getPhone() == null){
            
                registerBB.setError("Please type in all fields");
                return "register?faces-redirect=false";
        }
        
        else if( !(registerBB.getPassword()).equals(registerBB.getConfirmPassword())){
        
                registerBB.setError("Make sure that password is correct");
                return "register?faces-redirect=false";
        
        }else if( flightModel.getUserList().getByEmail(registerBB.getEmail()).size() > 0  ){
                
                registerBB.setError("User exists");
                return "register?faces-redirect=false";
        
        }else {
            
            
                List<Customer>cl = flightModel.getCustomerList().findAll();
                User u = new User(registerBB.getEmail(),registerBB.getPassword(),Groups.USER);
                Customer c = new Customer(u,registerBB.getFirstName(),registerBB.getLastName(),
                                        registerBB.getAdress(),registerBB.getCity(),registerBB.getPhone());
                    
                flightModel.getUserList().create(u);
                
                flightModel.getCustomerList().create(c);
            
            
            }
        return "login?faces-redirect=true";
    }
      
    
   
}
