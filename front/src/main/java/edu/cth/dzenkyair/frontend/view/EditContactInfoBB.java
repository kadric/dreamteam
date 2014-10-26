
package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Customer;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dženan
 */

@Named
@RequestScoped
public class EditContactInfoBB implements Serializable{
    
    private String firstName;
    private String lastName;
    private String adress;
    private String city;
    private Long phone;
    private String error;
    
    @Inject
    FlightModel flightModel;
    
    @Inject
    FlightSession flightSession;

    public EditContactInfoBB() {
        ;
    }
    
    @PostConstruct
    public void post() {
        User u = flightSession.getUser();
        if(u != null && u.getGroups().get(0) != Groups.ADMIN) {
            Customer c = flightModel.getCustomerList().getByUser(u).get(0);
            firstName = c.getFirstName();
            lastName = c.getLastName();
            adress = c.getAdress();
            city = c.getCity();
            phone = c.getPhone();
        }
    }

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
    
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    } 
    
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
