package com.pedido.business.usecases;

import com.jayway.jsonpath.internal.path.PathToken;
import com.pedido.business.gateways.DomainEventRepository;
import com.pedido.business.gateways.EventBus;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommand;
import com.pedido.domain.Product;
import com.pedido.domain.command.CreatePromoCommand;
import com.pedido.domain.values.*;
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
