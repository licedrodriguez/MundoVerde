package com.pedido.business.usecases;

import com.pedido.business.gateways.Repository;
import com.pedido.business.generic.Command;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommandNoReactivo;
import com.pedido.domain.Product;
import com.pedido.domain.Promo;
import com.pedido.domain.command.CreatePromoCommand;
import com.pedido.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class CreatePromoUseCaseNoReactivo implements UseCaseForCommandNoReactivo<CreatePromoCommand> {

    private Repository repository;

    public CreatePromoUseCaseNoReactivo(Repository repository) { this.repository = repository; }

    @Override
    public List<DomainEvent> apply(CreatePromoCommand command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getProductId());
        Product product = Product.from(ProductId.of(command.getProductId()), events);
        product.createPromo(PromoId.of(command.getPromoId()),
                new Time(command.getStartDate(), command.getEndDate()),
                new Description(command.getDescription()),
                new Percent(Double.parseDouble("5")));
        return product.getUncommittedChanges().stream().map(event -> {
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
