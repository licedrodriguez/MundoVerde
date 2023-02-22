package co.com.mundoverde.business.gateways;


import co.com.mundoverde.business.generic.DomainEvent;

public interface EventBus {

    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
