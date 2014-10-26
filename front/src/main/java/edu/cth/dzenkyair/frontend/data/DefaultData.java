package edu.cth.dzenkyair.frontend.data;
import edu.cth.dzenkyair.backend.core.Airport;
import edu.cth.dzenkyair.backend.core.Customer;
import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.backend.core.Status;
import edu.cth.dzenkyair.backend.core.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
/**
* This is just to put some data into the database
*
* @author Dženan
*/
@Startup
@Singleton
public class DefaultData {
    private static final Logger LOG = Logger.getLogger(DefaultData.class.getName());
    
    @Inject
    private FlightModel flightModel;
    
    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "*** Default data alive");
        //createtTestData(); // COMMENT OUT First run (no tables yet)
    }
    @PreDestroy
    public void destroy() {
        LOG.log(Level.INFO, "*** Default data will be destroyed");
        //clearTestData();
    }
    
    private void createtTestData() {
        LOG.log(Level.INFO, "*** Add default data");
        User u = new User("admin@admin.admin", "admin", Groups.ADMIN);
        flightModel.getUserList().create(u);
        u = new User("user@user.user", "user", Groups.USER);
        flightModel.getUserList().create(u);
        
        Long phone = (long) 387;
        Customer c = new Customer(u, "Firtynamy", "Lastyname", "Adressy", "Citky", phone);
        flightModel.getCustomerList().create(c);
        Airport sarajevo = new Airport("Sarajevo");
        Airport goteborg = new Airport("Göteborg");
        flightModel.getAirportList().create(sarajevo);
        flightModel.getAirportList().create(goteborg);
        Line sagbg = new Line(sarajevo, goteborg);
        flightModel.getLineList().create(sagbg);
        Calendar departure = Calendar.getInstance();
        departure.set(2015, 1, 1, 14, 0);
        Calendar arrival = Calendar.getInstance();
        arrival.set(2015, 1, 1, 16, 45);
        Flight flsagbg = new Flight(sagbg, departure, arrival, 10, 100, Status.OK);
        flightModel.getFlightList().create(flsagbg);
    }
    
    private void clearTestData() {
        flightModel.getUserList().delete(flightModel.getUserList().getByEmail("user@user.user").get(0).getId());
        flightModel.getUserList().delete(flightModel.getUserList().getByEmail("admin@admin.admin").get(0).getId());
    }
    
    // If using default digest algorithm
    // (also put Hex and UTF-8 in realm configuration
    private String getSHA256(String passwd) throws NoSuchAlgorithmException,
        UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = "admin";
        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }
}