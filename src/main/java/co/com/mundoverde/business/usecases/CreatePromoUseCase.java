package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.DomainEventRepository;
import co.com.mundoverde.business.gateways.EventBus;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForCommand;
import co.com.mundoverde.domain.Product;
import co.com.mundoverde.domain.command.CreatePromoCommand;
import co.com.mundoverde.domain.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreatePromoUseCase extends UseCaseForCommand<CreatePromoCommand> {

    private final DomainEventRepository repository;

    private  final EventBus bus;

    CreatePromoUseCase(DomainEventRepository repository, EventBus bus){
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreatePromoCommand> createPromoCommandMono) {
        return createPromoCommandMono.flatMapMany(command -> repository.finById(command.getProductId())
                    .collectList()
                    .flatMapIterable(events -> {
                        Product product = Product.from(ProductId.of(command.getProductId()), events);
                        product.createPromo(PromoId.of(command.getPromoId())
                                , new Time(command.getStartDate(), command.getEndDate())
                                , new Description(command.getDescription())
                                , new Percent(Double.parseDouble("5")));
                        return product.getUncommittedChanges();
                    }).map(event -> {
                        bus.publish(event);
                        return event;
                    }).flatMap(event -> repository.saveEvent(event))
        );
    }
}
