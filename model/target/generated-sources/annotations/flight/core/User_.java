package flight.core;

import flight.core.Groups;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-02T19:09:26")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Groups> groups;

}