package com.pedido.domain;

import com.pedido.business.generic.EventChange;
import com.pedido.domain.events.OrderCreated;

public class OrderEventChange extends EventChange {

    public OrderEventChange(Order order) {
        apply((OrderCreated event) -> {
            order.date = event.getDate();
            order.location = event.getLocation();
            order.orderName = event.getOrderName();
        });
    }
}
