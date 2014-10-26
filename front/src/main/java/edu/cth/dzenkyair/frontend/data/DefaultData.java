package edu.cth.dzenkyair.frontend.data;

import edu.cth.dzenkyair.backend.core.Airport;
import edu.cth.dzenkyair.backend.core.Customer;
import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.backend.core.Line;
import edu.cth.dzenkyair.backend.core.Order;
import edu.cth.dzenkyair.backend.core.Passenger;
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
        User u = new User("user@user.user", "user", Groups.USER);
        flightModel.getUserList().create(u);
        u = new User("admin@admin.admin", "admin", Groups.ADMIN);
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
        departure.set(2015, 2, 2, 14, 0);
        arrival.set(2015, 2, 2, 16, 45);
        flsagbg = new Flight(sagbg, departure, arrival, 10, 100, Status.OK);
        flightModel.getFlightList().create(flsagbg);
        
        Line gbgsa = new Line(goteborg, sarajevo);
        flightModel.getLineList().create(gbgsa);
        Flight flgbgsa = new Flight(gbgsa, departure, arrival, 10, 100, Status.OK);
        flightModel.getFlightList().create(flgbgsa);
        
        Airport istanbul = new Airport("Istanbul");
        Airport london = new Airport("London");
        flightModel.getAirportList().create(istanbul);
        flightModel.getAirportList().create(london);
        Line islo = new Line(istanbul, london);
        Line lois = new Line(london, istanbul);
        flightModel.getLineList().create(islo);
        flightModel.getLineList().create(lois);
        Flight flislo = new Flight(islo, departure, arrival, 20, 200, Status.OK);
        Flight fllois = new Flight(lois, departure, arrival, 20, 200, Status.OK);
        flightModel.getFlightList().create(flislo);
        flightModel.getFlightList().create(fllois);
        
        Airport frankfurt = new Airport("Frankfurt am Main");
        flightModel.getAirportList().create(frankfurt);
        Line frlo = new Line(frankfurt, london);
        flightModel.getLineList().create(frlo);
        Flight flfrlo = new Flight(frlo, departure, arrival, 30, 300, Status.OK);
        flightModel.getFlightList().create(flfrlo);
        
        departure.set(2014, 1, 1, 16, 45);
        arrival.set(2015, 1, 1, 21, 45);
        flfrlo = new Flight(frlo, departure, arrival, 30, 300, Status.OK);
        flightModel.getFlightList().create(flfrlo);
        Order o = new Order(flfrlo, u);
        flightModel.getOrderList().create(o);
        Passenger p = new Passenger(flfrlo, o, "Firstynamy", "Lastynamy", "Small");
        flightModel.getPassengerList().create(p);
        
    }

    private void clearTestData() {
        flightModel.getUserList().delete(flightModel.getUserList().getByEmail("user@user.user").get(0).getId());
        flightModel.getUserList().delete(flightModel.getUserList().getByEmail("admin@admin.admin").get(0).getId());

    }

    // If using default digest algorithm 
    //  (also put Hex and UTF-8 in realm configuration
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