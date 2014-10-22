package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dženan
 */
@Entity
@Table(name="FLIGHTS")
public class Flight extends AbstractEntity {

    @ManyToOne
    private Line line;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar departure;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar arrival;
    @Column(nullable = false)
    private int maxPass;
    @Column(nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    
    public Flight() {
        ;
    }
    
    public Flight(Line line, Calendar departure, Calendar arrival, int maxPass, 
            double price, Status status) {
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        this.maxPass = maxPass;
        this.price = price;
        this.status = status;
    }

    public Flight(Long id, Line line, Calendar departure, Calendar arrival, 
            int maxPass, double price, Status status) {
        super(id);
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        this.maxPass = maxPass;
        this.price = price;
        this.status = status;
    }

    public Line getLine() {
        return line;
    }

    public Calendar getDeparture() {
        return departure;
    }
    
    public Calendar getArrival() {
        return arrival;
    }
    
    public int getMaxPass() {
        return maxPass;
    }
    
    public double getPrice() {
        return price;
    }
    
    public Status getStatus() {
        return status;
    }
     
    @Override
    public String toString() {
        return "Flight{" + "Id=" + getId() + ", Line=" + line.toString() 
                + ", Departure =" + departure.toString()
                + ", Arrival =" + arrival.toString() 
                + ", Max passengers =" + maxPass 
                + ", Price =" + price
                + ", Status =" + status.name()
                + '}';
        
        
    }
}
