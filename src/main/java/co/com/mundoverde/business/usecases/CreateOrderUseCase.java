package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.DomainEventRepository;
import co.com.mundoverde.business.gateways.EventBus;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForCommand;
import co.com.mundoverde.domain.Order;
import co.com.mundoverde.domain.command.CreateOrderCommand;
import co.com.mundoverde.domain.values.CreationDate;
import co.com.mundoverde.domain.values.Location;
import co.com.mundoverde.domain.values.Name;
import co.com.mundoverde.domain.values.OrderId;
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
