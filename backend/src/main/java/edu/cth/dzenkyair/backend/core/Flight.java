package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Temporal(TemporalType.DATE)
    private Calendar departure;
    @Temporal(TemporalType.DATE)
    private Calendar arrival;
    @Column(nullable = false)
    private int maxPass;
    
    public Flight() {
        ;
    }
    
    public Flight(Line line, Calendar departure, Calendar arrival, int maxPass) {
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        this.maxPass = maxPass;
    }

    public Flight(Long id, Line line, Calendar departure, Calendar arrival, int maxPass) {
        super(id);
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        this.maxPass = maxPass;
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
     
    @Override
    public String toString() {
        return "Flight{" + "Id=" + getId() + ", Line=" + line.toString() 
                + ", Departure =" + departure.toString()
                + ", Arrival =" + arrival.toString() 
                + ", Max passengers =" + maxPass 
                + '}';
        
        
    }
}
