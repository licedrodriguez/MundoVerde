package com.pedido.business.usecases;

import com.pedido.business.gateways.DomainEventRepository;
import com.pedido.business.gateways.EventBus;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommand;
import com.pedido.domain.Order;
import com.pedido.domain.command.CreateOrderCommand;
import com.pedido.domain.values.CreationDate;
import com.pedido.domain.values.Location;
import com.pedido.domain.values.Name;
import com.pedido.domain.values.OrderId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreateOrderUseCase extends UseCaseForCommand<CreateOrderCommand> {

    private final DomainEventRepository repository;

    private EventBus bus;

    CreateOrderUseCase(DomainEventRepository repository, EventBus bus){
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateOrderCommand> createOrderCommand) {
        return createOrderCommand.flatMapIterable(command -> {
            Order order = new Order(OrderId.of(command.getOrderId())
            , new CreationDate(command.getDate())
            , new Location(command.getAddress(), command.getSeat())
            , new Name(command.getName()));
            return order.getUncommittedChanges();
        }).flatMap(event -> repository.saveEvent(event))
                .map(event -> {
                    bus.publish(event);
                    return event;
                });
    }

}
