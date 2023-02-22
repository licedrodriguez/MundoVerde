package co.com.mundoverde.domain;

import co.com.mundoverde.business.generic.AggregateRoot;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.events.OrderCreated;
import co.com.mundoverde.domain.values.CreationDate;
import co.com.mundoverde.domain.values.Location;
import co.com.mundoverde.domain.values.Name;
import co.com.mundoverde.domain.values.OrderId;

import java.util.List;

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
