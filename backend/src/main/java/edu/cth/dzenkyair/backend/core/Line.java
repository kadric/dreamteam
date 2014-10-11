package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author matej
 */
@Entity
@Table(name="Lines")
public class Line extends AbstractEntity {

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Airport fromAirport;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Airport toAirport;
    
    public Line() {
        
    }
    
    public Line(Airport fromAirport,Airport toAirport) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
    }

    public Line(Long id, Airport fromAirport,Airport toAirport) {
        super(id);
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    
    public Airport getToAirport() {
        return toAirport;
    }

    @Override
    public String toString() {
        return "Line{" + "id=" + getId() + ", from=" + fromAirport.getName() + ", to=" + toAirport.getName() + '}';
    }
}
