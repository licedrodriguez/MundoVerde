package com.pedido.business.gateways;

import com.pedido.business.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> finById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
}
