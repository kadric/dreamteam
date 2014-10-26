package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dženan
 */
@Entity
@Table(name="PASSENGERS")
public class Passenger extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    private Flight flight;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Order order;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String baggage;
    
    public Passenger() {
        ;
    }
    
    public Passenger(Flight flight, Order order, String firstName, 
            String lastName, String baggage) {
        this.flight = flight;
        this.order = order;
        this.firstName = firstName;
        this.lastName = lastName;
        this.baggage = baggage;
    }

    public Passenger(Long id, Flight flight, Order order,
            String firstName, String lastName, String baggage) {
        super(id);
        this.flight = flight;
        this.order = order;
        this.firstName = firstName;
        this.lastName = lastName;
        this.baggage = baggage;
    }

    public Flight getFlight() {
        return flight;
    }

    public Order getOrder() {
        return order;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getBaggage() {
        return baggage;
    }
     
    @Override
    public String toString() {
        return "Flight{" + "Id=" + getId() + ", Flight=" + flight.toString() 
                + ", Order =" + order.toString()
                + ", First name =" + firstName
                + ", Last name =" + lastName
                + ", Baggage type =" + baggage
                + '}';
        
        
    }
}
