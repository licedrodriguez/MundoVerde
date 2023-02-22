package co.com.mundoverde.domain;

import co.com.mundoverde.business.generic.EventChange;
import co.com.mundoverde.domain.events.OrderCreated;

public class OrderEventChange extends EventChange {

    public OrderEventChange(Order order) {
        apply((OrderCreated event) -> {
            order.date = event.getDate();
            order.location = event.getLocation();
            order.orderName = event.getOrderName();
        });
    }
}
