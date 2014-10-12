package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.core.Line;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-12T21:01:08")
@StaticMetamodel(Flight.class)
public class Flight_ { 

    public static volatile SingularAttribute<Flight, Line> line;
    public static volatile SingularAttribute<Flight, Calendar> departure;
    public static volatile SingularAttribute<Flight, Calendar> arrival;
    public static volatile SingularAttribute<Flight, Integer> maxPass;

}