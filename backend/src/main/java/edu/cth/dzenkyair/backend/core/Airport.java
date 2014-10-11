package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Dženan
 */
@Entity
@Table(name="AIRPORTS")
public class Airport extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    
    public Airport() {
        ;
    }
    
    public Airport(String name) {
        this.name = name;
    }

    public Airport(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Airport{" + "id=" + getId() + ", name=" + name + '}';
    }
}
