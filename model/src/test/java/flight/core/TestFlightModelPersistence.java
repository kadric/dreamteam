package flight.core;

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
                .addPackage("flight.core")
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
        utx.commit();
    }

}
