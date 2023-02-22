package co.com.mundoverde.domain.events;

import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.values.CreationDate;
import co.com.mundoverde.domain.values.Location;
import co.com.mundoverde.domain.values.Name;

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
