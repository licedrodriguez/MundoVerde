package com.pedido.domain.events;

import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.values.CartId;
import com.pedido.domain.values.CreationDate;
import com.pedido.domain.values.Location;
import com.pedido.domain.values.Name;

import java.util.Set;

public class OrderCreated extends DomainEvent {
    private final CreationDate date;

    private final Location location;

    private final Name orderName;
    public OrderCreated(CreationDate date, Location location, Name orderName) {
        super("liced.rodriguez.orderCreated");
        this.date = date;
        this.location = location;
        this.orderName = orderName;
    }

    public CreationDate getDate() { return date; }

    public Location getLocation() { return location; }

    public Name getOrderName() { return orderName; }

}
