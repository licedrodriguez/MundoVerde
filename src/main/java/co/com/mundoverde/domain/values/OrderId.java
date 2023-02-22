package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.Identity;

public class OrderId extends Identity {

    public OrderId(String orderId) { super(orderId); }

    public OrderId(){}

    public static OrderId of(String orderId) { return new OrderId(orderId); }
}
