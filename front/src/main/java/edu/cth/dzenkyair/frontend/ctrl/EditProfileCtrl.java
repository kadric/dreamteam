/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import edu.cth.dzenkyair.frontend.view.EditProfileBB;
import java.io.Serializable;
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
public class EditProfileCtrl implements Serializable{
    
    @Inject
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;
    
    private EditProfileBB profileBB;
    
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    
    
    protected EditProfileCtrl(){
        ;
    }
    
    @Inject
    public void setProfileBB (EditProfileBB profileBB){
        this.profileBB = profileBB;
    } 
    
    public String editEmail(){
    User u = (User) flightSession.getUser();
    String email = u.getEmail();
    
    if(!(profileBB.getEmail().equals(email))){
            User nUser = new User (u.getId(),profileBB.getEmail(),u.getPassword(),u.getGroups().get(0));
            flightModel.getUserList().update(nUser);
            flightSession.setUser(nUser);
        }
     return "/private/user/userpage?faces-redirect=true";
    }
    
    public String editPassword(){
    
    User u = (User) flightSession.getUser();
            
    String currPassword = u.getPassword();
    
        if(profileBB.getOldPassword().isEmpty() || profileBB.getNewPassword().isEmpty() || profileBB.getConfNewPassword().isEmpty()){
            profileBB.setError("Make sure that all fields are filled");
            return "editprofile?faces-redirect=false";
        }else if(!(profileBB.getOldPassword().equals(currPassword))){
            profileBB.setError("Wrong current password");
            return "editprofile?faces-redirect=false";
        }else if(!(profileBB.getNewPassword().equals(profileBB.getConfNewPassword()))){
            profileBB.setError("New password missmatch");
            return "editprofile?faces-redirect=false";
        }else{
              
            User nUser = new User(u.getId(),u.getEmail(),profileBB.getNewPassword(),u.getGroups().get(0));
            flightModel.getUserList().update(nUser);
            flightSession.setUser(nUser);
        }
    return "/private/user/userpage?faces-redirect=true";
    }
}
