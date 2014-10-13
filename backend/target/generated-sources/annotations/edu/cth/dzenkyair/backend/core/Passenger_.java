package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.core.Flight;
import edu.cth.dzenkyair.backend.core.Order;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-13T14:45:07")
@StaticMetamodel(Passenger.class)
public class Passenger_ { 

    public static volatile SingularAttribute<Passenger, String> baggage;
    public static volatile SingularAttribute<Passenger, Flight> flight;
    public static volatile SingularAttribute<Passenger, String> lastName;
    public static volatile SingularAttribute<Passenger, Order> order;
    public static volatile SingularAttribute<Passenger, String> firstName;

}