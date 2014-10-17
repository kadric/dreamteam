
package edu.cth.dzenkyair.frontend.view;

import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class AddPassengerBB implements Serializable {

    private String firstName;
    private String lastName;
    private String baggage;
    private String error;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getBaggage() {
        return baggage;
    }
    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
