package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.Repository;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForCommandNoReactivo;
import co.com.mundoverde.domain.Order;
import co.com.mundoverde.domain.command.CreateOrderCommand;
import co.com.mundoverde.domain.values.CreationDate;
import co.com.mundoverde.domain.values.Location;
import co.com.mundoverde.domain.values.Name;
import co.com.mundoverde.domain.values.OrderId;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderUseCaseNoReactivo implements UseCaseForCommandNoReactivo<CreateOrderCommand> {

    private Repository repository;

    public  CreateOrderUseCaseNoReactivo(Repository repository) { this.repository = repository; }

    @Override
    public List<DomainEvent> apply(CreateOrderCommand command) {
        Order order = new Order(OrderId.of(command.getOrderId())
                , new CreationDate(command.getDate())
                , new Location(command.getAddress(), command.getSeat())
                , new Name(command.getName()));
        return order.getUncommittedChanges().stream().map(event ->{
                    return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
