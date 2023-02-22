package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.DomainEventRepository;
import co.com.mundoverde.business.gateways.EventBus;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.Product;
import co.com.mundoverde.domain.events.PromoCreated;
import co.com.mundoverde.domain.values.ProductId;
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
