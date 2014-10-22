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
        Flight flight = new Flight(satu, departure, arrival, 10, 1000, Status.OK);
        
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
        Flight newFlight = new Flight(oldFlight.getId(), satu, departure, arrival, 50, 1000, Status.OK);
        flightModel.getFlightList().update(newFlight);
        int newPass = flightModel.getFlightList().findAll().get(0).getMaxPass();
        assertTrue(newPass == newFlight.getMaxPass());
        
        // GET BY LINE AND DEPARTURE
        departure.set(2014, 12, 12);
        fs = flightModel.getFlightList().getByLineAndDeparture(satu, departure);
        assertTrue(fs.size() > 0);
        departure.set(2016, 01, 01);
        fs = flightModel.getFlightList().getByLineAndDeparture(satu, departure);
        assertTrue(fs.isEmpty());
        oldFlight = flightModel.getFlightList().findAll().get(0);
        newFlight = new Flight(oldFlight.getId(), satu, departure, arrival, 50, 1000, Status.CANCELLED);
        flightModel.getFlightList().update(newFlight);
        departure.set(2014, 01, 01);
        fs = flightModel.getFlightList().getByLineAndDeparture(satu, departure);
        assertTrue(fs.isEmpty());
        
        //DELETE TEST
        flightModel.getFlightList().delete(oldFlight.getId());
        fs = flightModel.getFlightList().findAll();
        assertTrue(fs.isEmpty());
    }
    
     @Test
    public void testOrder() throws Exception {
    
        Airport a1 = new Airport("Göteborg");
        Airport a2 = new Airport("Sarajevo"); 
        flightModel.getAirportList().create(a1);
        flightModel.getAirportList().create(a2);
        
        a1 = flightModel.getAirportList().getByName(a1.getName()).get(0);
        a2 = flightModel.getAirportList().getByName(a2.getName()).get(0);
        Line line = new Line(a1 , a2);
        flightModel.getLineList().create(line);
        
        Calendar dep = Calendar.getInstance();
        dep.set(2015, 1, 1, 14, 0);
        Calendar arr = Calendar.getInstance();
        arr.set(2015, 1, 1, 15, 0);
        
        int maxP = 12;
        
        Flight f = new Flight(line,dep,arr,maxP, 1000, Status.OK);
        flightModel.getFlightList().create(f);
        User u = new User("Bakir", "Bake", Groups.USER);
        flightModel.getUserList().create(u);
        
        List<User> ul = flightModel.getUserList().getByEmail(u.getEmail());
        assertTrue(ul.size() >0);
                
        Order order = new Order(f,u);
        
        /*Create test*/
        flightModel.getOrderList().create(order);
        List<Order> ol1 = flightModel.getOrderList().getByFlight(f);
        assertTrue(ol1.size()>0);
        assertTrue(ol1.get(0).getOrderFlight().getLine().getId() == f.getLine().getId());
        
        List<Order> ol2 = flightModel.getOrderList().getByUser(u);
         assertTrue(ol2.size() > 0);
         assertTrue(ol2.get(0).getOrderUser().getEmail().equals(u.getEmail()));
        
         /* Update test */
         Airport a3 = new Airport("London");
         Airport a4 = new Airport("Tokyo"); 
         flightModel.getAirportList().create(a3);
         flightModel.getAirportList().create(a4);
        
        a3 = flightModel.getAirportList().getByName(a3.getName()).get(0);
        a4 = flightModel.getAirportList().getByName(a4.getName()).get(0);
        Line line2 = new Line(a3 , a4);
        flightModel.getLineList().create(line2);
        
        Calendar dep2 = Calendar.getInstance();
        dep.set(2015, 1, 1, 20, 0);
        Calendar arr2 = Calendar.getInstance();
        arr.set(2015, 1, 1, 22, 0);
        
               
        Flight f2 = new Flight(line2,dep2,arr2,maxP, 1000, Status.OK);
        flightModel.getFlightList().create(f2);
        User u2 = new User("Nick", "diaz", Groups.USER);
        flightModel.getUserList().create(u2);
        
        List<User> ul2 = flightModel.getUserList().getByEmail(u2.getEmail());
        assertTrue(ul2.size() >0);
                
        Order order2 = new Order(f2,u2);
         
        flightModel.getOrderList().update(order2);
        ol1 = flightModel.getOrderList().getByFlight(f2);
        assertTrue(ol1.size()>0);
        assertTrue(ol1.get(0).getOrderFlight().getLine().getId() == f2.getLine().getId());
        
        ol2 = flightModel.getOrderList().getByUser(u2);
        assertTrue(ol2.size()>0);
        assertTrue(ol2.get(0).getOrderUser().getEmail().equals(u2.getEmail()));
        
        /* Delete test  */
        flightModel.getOrderList().delete(order.getId());
        ol1 = flightModel.getOrderList().getByFlight(f);
        assertTrue(ol1.isEmpty());
        
       
    }
    
    @Test
    public void testPassenger() throws Exception {
        
        // INIT
        flightModel.getAirportList().create(new Airport("Sarajevo"));
        flightModel.getAirportList().create(new Airport("Tuzla"));
        
        List<Airport> la = flightModel.getAirportList().findAll(); 
        assertTrue(la.size() > 0);
        flightModel.getLineList().create(new Line(la.get(0), la.get(1)));
        
        List<Line> ll = flightModel.getLineList().findAll();
        assertTrue(ll.size() > 0);
        Calendar departure = Calendar.getInstance();
        departure.set(2015, 1, 1, 14, 0);
        Calendar arrival = Calendar.getInstance();
        arrival.set(2015, 1, 1, 16, 45);
        Flight flight = new Flight(ll.get(0), departure, arrival, 10, 1000, Status.OK);
        flightModel.getFlightList().create(flight);
        
        flightModel.getUserList().create(new User("user", "password", Groups.USER));
        List<User> lu = flightModel.getUserList().findAll();
        assertTrue(lu.size() > 0);
        Order order = new Order(flight, lu.get(0));
        flightModel.getOrderList().create(order);
        
        // CREATE TEST
        Passenger p = new Passenger(flight, order, "Dzeno", "Bazdar", "Handbag");
        flightModel.getPassengerList().create(p);
        List<Passenger> lp = flightModel.getPassengerList().findAll();
        assertTrue(lp.size() > 0);
        Passenger po = lp.get(0);
        assertTrue(po.getFirstName().equals(p.getFirstName()));
        
        // UPDATE TEST
        Passenger pnew = new Passenger(po.getId(), po.getFlight(), po.getOrder(), 
                "Dzeno", "Bazdar", "XXL Bag");
        flightModel.getPassengerList().update(pnew);
        lp = flightModel.getPassengerList().findAll();
        assertTrue(lp.size() > 0);
        assertTrue(lp.get(0).getBaggage().equals(pnew.getBaggage()));
        
        // GET BY FLIGHT TEST
        lp = flightModel.getPassengerList().getByFlight(flight);
        assertTrue(lp.size() > 0);
        
        // GET BY ORDER TEST
        lp = flightModel.getPassengerList().getByOrder(order);
        assertTrue(lp.size() > 0);
        
        // DELETE TEST
        flightModel.getPassengerList().delete(po.getId());
        lp = flightModel.getPassengerList().findAll();
        assertTrue(lp.isEmpty());
    }
    
     @Test
    public void testCustomer() throws Exception {
        
        User u = new User("bake@sda.com", "bakebake", Groups.USER);
        flightModel.getUserList().create(u);
        
        Customer c = new Customer(u, "Bakir", "Bake", "Marsala Tita 1","Sarajevo", 033223344);
        flightModel.getCustomerList().create(c);
        
        /* Create test*/
        List<Customer> cl = flightModel.getCustomerList().getByUser(u);
        assertTrue(cl.size() > 0);
        assertTrue(cl.get(0).getFirstName().equals(c.getFirstName()));
        
        /*Update test*/
         User u2 = new User("nick@nick.com", "diazdiaz", Groups.USER);
        flightModel.getUserList().create(u2);
        u2 = flightModel.getUserList().getByEmail(u2.getEmail()).get(0);
       
           
        Customer c2 = new Customer(u2, "Nick", "Diaz", "Stockton 209","California", 03321324);
        flightModel.getCustomerList().update(c2);
        c2 = flightModel.getCustomerList().getByUser(c2.getUser()).get(0);
        
        cl = flightModel.getCustomerList().getByUser(u2);
        assertTrue(cl.size() > 0);
        assertTrue(cl.get(0).getFirstName().equals(c2.getFirstName()));
        
        /*Delete test*/
        flightModel.getCustomerList().delete(c.getId());
        cl = flightModel.getCustomerList().getByUser(c.getUser());
         assertTrue(cl.isEmpty());
        
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
        em.createQuery("delete from Passenger").executeUpdate();
        em.createQuery("delete from Order").executeUpdate();
        em.createQuery("delete from Customer").executeUpdate();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Flight").executeUpdate();
        em.createQuery("delete from Line").executeUpdate();
        em.createQuery("delete from Airport").executeUpdate();
        
        utx.commit();
    }

}
