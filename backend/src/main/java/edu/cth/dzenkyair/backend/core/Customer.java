/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author matej
 */
@Entity
@Table(name="CUSTOMERS")
public class Customer extends AbstractEntity{
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String adress;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private long phone;
    
    
    public Customer(){
        ;
    }
    
    public Customer(User user, String firstName, String lastName,
                        String adress, String city, long phone){
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.city = city;
        this.phone = phone;
    
    }
    
    public Customer(Long id, User user, String firstName, String lastName,
                        String adress, String city, long phone){
        super(id);
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.city = city;
        this.phone = phone;
    
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString(){
    return "Customer{" + "Id=" + getId() + ", User=" + user.getId()
                + ", First name =" + firstName
                + ", Last name =" + lastName
                + ", Adress =" + adress
                + ", City =" + city
                + ", Phone number =" + phone
                + '}';
    }
    
}
