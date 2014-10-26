
package edu.cth.dzenkyair.frontend.ctrl;

import edu.cth.dzenkyair.backend.core.Customer;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.User;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import edu.cth.dzenkyair.frontend.view.EditContactInfoBB;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author matej
 */
@Named
@RequestScoped
public class EditContactInfoCtrl implements Serializable{
    
    @Inject
    private FlightModel flightModel;
    
    @Inject
    private FlightSession flightSession;
    
    private EditContactInfoBB contactInfoBB;
    
    protected EditContactInfoCtrl(){
        ;
    }
    
    @Inject
    public void setProfileBB (EditContactInfoBB contactInfoBB){
        this.contactInfoBB = contactInfoBB;
    } 
    
    public String apply(){
        User u = flightSession.getUser();
        Customer c = flightModel.getCustomerList().getByUser(u).get(0);
        String regex = "^\\pL+[\\pL\\pZ\\pP]{0,}$";
        Pattern pattern = Pattern.compile(regex);
        
        if(contactInfoBB.getFirstName().isEmpty() || contactInfoBB.getLastName().isEmpty() 
                || contactInfoBB.getAdress().isEmpty() || contactInfoBB.getCity().isEmpty()
                || contactInfoBB.getPhone() == null){
            contactInfoBB.setError("Make sure that all fields are filled");
            return "editcontactinfo?faces-redirect=false";
        }else if(!(pattern.matcher(contactInfoBB.getFirstName()).find()) 
                || !(pattern.matcher(contactInfoBB.getLastName()).find())){
            contactInfoBB.setError("Names can only contain letters");
            return "editcontactinfo?faces-redirect=false";
        }
        Customer newc = new Customer(c.getId(), u, 
                contactInfoBB.getFirstName(),contactInfoBB.getLastName(), 
                contactInfoBB.getAdress(), contactInfoBB.getCity(), 
                contactInfoBB.getPhone());
        flightModel.getCustomerList().update(newc);
        
        return "userpage?faces-redirect=true";
    }
}
