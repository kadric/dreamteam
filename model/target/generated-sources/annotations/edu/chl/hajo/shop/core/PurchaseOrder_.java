package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.core.Customer;
import edu.chl.hajo.shop.core.OrderItem;
import edu.chl.hajo.shop.core.PurchaseOrder.State;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-27T13:37:28")
@StaticMetamodel(PurchaseOrder.class)
public class PurchaseOrder_ { 

    public static volatile ListAttribute<PurchaseOrder, OrderItem> items;
    public static volatile SingularAttribute<PurchaseOrder, State> state;
    public static volatile SingularAttribute<PurchaseOrder, Date> date;
    public static volatile SingularAttribute<PurchaseOrder, Customer> customer;

}