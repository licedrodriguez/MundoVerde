package com.pedido.domain.values;

import com.pedido.business.generic.Identity;

public class OrderId extends Identity {

    public OrderId(String orderId) { super(orderId); }

    public OrderId(){}

    public static OrderId of(String orderId) { return new OrderId(orderId); }
}
