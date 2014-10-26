/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author matej
 */

@Named
@RequestScoped
public class EditProfileBB implements Serializable{
    
     private String email;
     private String oldPassword;
     private String newPassword;
     private String confNewPassword;
     private String error;

     @Inject
    FlightModel flightModel;
    
    @Inject
    FlightSession flightSession;
    
    @PostConstruct
    public void post() { 
     User u = flightSession.getUser();
        if(u != null){
            email = flightModel.getUserList().getByEmail(u.getEmail()).get(0).getEmail();
            oldPassword = flightModel.getUserList().getByEmail(u.getEmail()).get(0).getPassword();
        }
    }
    public EditProfileBB() {
        ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfNewPassword() {
        return confNewPassword;
    }

    public void setConfNewPassword(String conNewPassword) {
        this.confNewPassword = conNewPassword;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
     
     
    
}
