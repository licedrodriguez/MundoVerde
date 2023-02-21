package com.pedido.business.usecases;

import com.pedido.business.gateways.DomainEventRepository;
import com.pedido.business.gateways.EventBus;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommand;
import com.pedido.domain.Product;
import com.pedido.domain.events.PromoCreated;
import com.pedido.domain.values.ProductId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class AddPercentPromoUseCase implements Function<Mono<PromoCreated>, Flux<DomainEvent>> {

    private DomainEventRepository repository;

    private EventBus bus;

    public AddPercentPromoUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<PromoCreated> promoCreatedMono) {
        return promoCreatedMono.flatMapIterable(event -> {
            Product product = Product.from(ProductId.of(event.aggregateRootId()), List.of(event));
            product.addPercent(event.getPromoId(), event.getPercent());
            return  product.getUncommittedChanges();
        }).flatMap(domainEvent -> repository.saveEvent(domainEvent)).map(domainEvent -> {
            bus.publish(domainEvent);
            return domainEvent;
        });
    }
}
