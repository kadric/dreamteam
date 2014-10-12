package edu.cth.dzenkyair.backend.core;

import java.util.Calendar;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Testing the persistence layer
 *
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded
 * database) GlassFish not needed using embedded
 *
 * @author Dženan
 */
@RunWith(Arquillian.class)
public class TestFlightModelPersistence {

    @Inject
    FlightModel flightModel;

    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                // Add all classes
                .addPackage("edu.cth.dzenkyair.backend.core")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before  // Run before each test
    public void before() throws Exception {
        clearAll();
    }
    
    @Test
    public void testPersistAUser() throws Exception {
        User u = new User("user@user.user", "password", Groups.USER);
        flightModel.getUserList().create(u);
        List<User> us = flightModel.getUserList().findAll();
        assertTrue(us.size() > 0);
        assertTrue(us.get(0).getEmail().equals(u.getEmail()));
    }
    
    @Test
    public void testUpdateAUser() throws Exception {
        User u = new User("user@user.user", "password", Groups.USER);
        flightModel.getUserList().create(u);
        User u2 = new User(u.getId(), "user@user.user", "password", Groups.ADMIN);
        flightModel.getUserList().update(u2);
        List<User> us = flightModel.getUserList().findAll();
        assertTrue(us.size() > 0);
        assertTrue(us.get(0).getGroups().get(0).equals(u2.getGroups().get(0)));
    }
    
    @Test
    public void testDeleteAUser() throws Exception {
        User u = new User("user@user.user", "password", Groups.USER);
        flightModel.getUserList().create(u);
        flightModel.getUserList().delete(u.getId());
        List<User> us = flightModel.getUserList().findAll();
        assertTrue(us.size() == 0);
    }
    
    @Test
    public void testUserGetByEmail() throws Exception {
        User u = new User("user@user.user", "password", Groups.USER);
        flightModel.getUserList().create(u);
        List<User> us = flightModel.getUserList().getByEmail("user@user.user");
        assertTrue(us.size() > 0);
        assertTrue(us.get(0).getEmail().equals(u.getEmail()));
    }
    
    @Test
    public void testAirport() throws Exception {
        Airport a = new Airport("Sarajevo");
        
        // CREATE TEST
        flightModel.getAirportList().create(a);
        List<Airport> as = flightModel.getAirportList().getByName(a.getName());
        assertTrue(as.size() > 0);
        assertTrue(as.get(0).getName().equals(a.getName()));
        
        // UPDATE TEST
        Airport anew = new Airport(a.getId(), "Tuzla");
        flightModel.getAirportList().update(anew);
        as = flightModel.getAirportList().getByName(anew.getName());
        assertTrue(as.size() > 0);
        assertTrue(as.get(0).getName().equals(anew.getName()));
        
        // DELETE TEST
        flightModel.getAirportList().delete(a.getId());
        as = flightModel.getAirportList().getByName(anew.getName());
        assertTrue(as.isEmpty());
    }
    
    @Test
    public void testLine() throws Exception {
    Airport a = new Airport("Goteborg");
    Airport b = new Airport("Sarajevo");
    flightModel.getAirportList().create(a);
    flightModel.getAirportList().create(b);
        
        a = flightModel.getAirportList().getByName(a.getName()).get(0);
        b = flightModel.getAirportList().getByName(b.getName()).get(0);
        Line line = new Line(a , b);
        
        // CREATE TEST
        // test getByFromAirport
        flightModel.getLineList().create(line);
        List<Line> fromList = flightModel.getLineList().getByFromAirport(a);
        assertTrue(fromList.size() > 0);
        assertTrue(fromList.get(0).getFromAirport().getName().equals(a.getName()));
        // test getByToAIrport
        List<Line> toList = flightModel.getLineList().getByToAirport(b);
        assertTrue(toList.size() > 0);
        assertTrue(toList.get(0).getToAirport().getName().equals(b.getName()));
        
         // UPDATE TEST
        Airport c = new Airport("London");
        Airport d = new Airport("Tokyo");
        flightModel.getAirportList().create(c);
        flightModel.getAirportList().create(d);
        
        c=flightModel.getAirportList().getByName(c.getName()).get(0);
        d=flightModel.getAirportList().getByName(d.getName()).get(0);
        Line line2 = new Line(c,d);
        
        flightModel.getLineList().update(line2);
        fromList = flightModel.getLineList().getByFromAirport(c);
        assertTrue(fromList.size() > 0 );
        assertTrue(fromList.get(0).getFromAirport().getName().equals(c.getName()));
        
        toList = flightModel.getLineList().getByToAirport(d);
        assertTrue(toList.size() > 0);
        assertTrue(toList.get(0).getToAirport().getName().equals(d.getName()));
        
        // DELETE TEST
        
        flightModel.getLineList().delete(line.getId());
        fromList = flightModel.getLineList().getByFromAirport(a);
        assertTrue(fromList.isEmpty());
        
    }
    
    @Test
    public void testFlight() throws Exception {
        flightModel.getAirportList().create(new Airport("Sarajevo"));
        flightModel.getAirportList().create(new Airport("Tuzla"));
        
        List<Airport> la = flightModel.getAirportList().findAll(); 
        assertTrue(la.size() > 1);
        Line satu = new Line(la.get(0), la.get(1));
        
        flightModel.getLineList().create(satu);
        satu = flightModel.getLineList().findAll().get(0);
        
        Calendar departure = Calendar.getInstance();
        departure.set(2015, 1, 1, 14, 0);
        Calendar arrival = Calendar.getInstance();
        arrival.set(2015, 1, 1, 16, 45);
        Flight flight = new Flight(satu, departure, arrival, 10);
        
        // CREATE TEST
        flightModel.getFlightList().create(flight);
        List<Flight> fs = flightModel.getFlightList().findAll();
        assertTrue(fs.size() > 0);
        int i = fs.get(0).getDeparture().compareTo(departure);
        assertTrue(0 == i);
        
        // GET BY LINE TEST
        fs = flightModel.getFlightList().getByLine(satu);
        assertTrue(fs.size() > 0);
        
        // UPDATE TEST
        Flight oldFlight = flightModel.getFlightList().findAll().get(0);
        Flight newFlight = new Flight(oldFlight.getId(), satu, departure, arrival, 50);
        flightModel.getFlightList().update(newFlight);
        int newPass = flightModel.getFlightList().findAll().get(0).getMaxPass();
        assertTrue(newPass == newFlight.getMaxPass());
        
        //DELETE TEST
        flightModel.getFlightList().delete(oldFlight.getId());
        fs = flightModel.getFlightList().findAll();
        assertTrue(fs.isEmpty());
    }
    
    // Need a standalone em to remove testdata between tests
    // No em accessible from interfaces
    @PersistenceContext(unitName = "flight_model_test_pu")
    @Produces
    @Default
    EntityManager em;

    // Order matters
    private void clearAll() throws Exception {  
        utx.begin();  
        em.joinTransaction();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Flight").executeUpdate();
        em.createQuery("delete from Line").executeUpdate();
        em.createQuery("delete from Airport").executeUpdate();
        utx.commit();
    }

}
