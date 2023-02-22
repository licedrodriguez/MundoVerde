package co.com.mundoverde.business.gateways;

import co.com.mundoverde.business.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> finById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
}
