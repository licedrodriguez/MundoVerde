package com.pedido.business.gateways;

import com.pedido.business.generic.DomainEvent;

public interface EventBus {

    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
