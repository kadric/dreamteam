package edu.cth.dzenkyair.frontend.auth;

import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class AuthBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AuthBean.class.getSimpleName());
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String error;

    @Inject // Bad use setter or constructor injection
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;

    public String login() {
        LOG.log(Level.INFO, "*** Try login {0} {1}", new Object[]{email, password});
        
        // Really check is there some data in database?
        List<User> us =  flightModel.getUserList().getByEmail(email);
        if(us.size() <= 0) {
            LOG.log(Level.INFO, "*** Login failed");
            error = "The username is wrong";
            return "login?faces-redirect=false";
        }
        User u = us.get(0);
        
        LOG.log(Level.INFO, "*** Found {0} {1}", new Object[]{u.getEmail(), u.getPassword()});
                
        if(!(u.getPassword().equals(password))) {
            LOG.log(Level.INFO, "*** Login failed");
            error = "The password is wrong";
            return "login?faces-redirect=false";
        }
        
        LOG.log(Level.INFO, "*** Login success");
        flightSession.setUser(u);  // Store User in session
        return "/private/user/userpage?faces-redirect=true";
    }
    
    public String logout() {
        flightSession.setUser(null); 
        LOG.log(Level.INFO, "*** Logout success");
        return "/pages/login?faces-redirect=true";
    }
    
    public boolean isAdmin() {
        User u = flightSession.getUser();
        return u.getGroups().get(0).equals(Groups.ADMIN);
    }

    // ------------------------------
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public String getError() {
        return error;
    }
}
