/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "ORDERS")
public class Order extends AbstractEntity {
    
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Flight flight;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
    
    public Order(){
        ;
    }
    
    public Order(Flight flight, User user){
        this.flight = flight;
        this.user = user;
    }
    
    public Order(Long id, Flight flight, User user){
        super(id);
        this.flight = flight;
        this.user = user;
    }
    
    
    public Flight getOrderFlight (){
        return flight;
    }
    
    public User getOrderUser(){
        return user;
    }
    
    @Override
    public String toString(){
        return "Order{ " + "id=" + getId() + ", flight= " + flight.toString() + ", user =" + user.getId() + "}";
    }
}
