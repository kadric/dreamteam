package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.core.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-15T19:44:44")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile SingularAttribute<Customer, String> adress;
    public static volatile SingularAttribute<Customer, Long> phone;
    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, User> user;
    public static volatile SingularAttribute<Customer, String> city;

}