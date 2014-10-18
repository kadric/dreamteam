/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.view;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author matej
 */

@Named
@RequestScoped
public class EditProfileBB implements Serializable{
    
     private String oldPassword;
     private String newPassword;
     private String confNewPassword;
     private String error;

    public EditProfileBB() {
        ;
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
