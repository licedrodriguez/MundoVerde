package com.pedido.business.usecases;

import com.pedido.business.gateways.Repository;
import com.pedido.business.generic.Command;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommandNoReactivo;
import com.pedido.domain.Order;
import com.pedido.domain.command.CreateOrderCommand;
import com.pedido.domain.values.CreationDate;
import com.pedido.domain.values.Location;
import com.pedido.domain.values.Name;
import com.pedido.domain.values.OrderId;

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
