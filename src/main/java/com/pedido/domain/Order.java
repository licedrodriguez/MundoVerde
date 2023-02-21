package com.pedido.domain;

import com.pedido.business.generic.AggregateRoot;
import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.events.OrderCreated;
import com.pedido.domain.values.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order extends AggregateRoot<OrderId> {

    protected CreationDate date;

    protected Location location;

    protected Name orderName;

    public Order(OrderId id, CreationDate date, Location location, Name orderName){
        super(id);
        subscribe(new OrderEventChange(this));
        appendChange(new OrderCreated(date, location, orderName)).apply();
    }

    private Order(OrderId id){
        super(id);
        subscribe(new OrderEventChange(this));
    }

    public static Order from(OrderId id, List<DomainEvent> events) {
        var order = new Order(id);
        events.forEach(order::applyEvent);
        return order;
    }


}
