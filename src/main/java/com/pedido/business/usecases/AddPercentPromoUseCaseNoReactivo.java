package com.pedido.business.usecases;

import com.pedido.business.gateways.Repository;
import com.pedido.business.generic.Command;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommandNoReactivo;
import com.pedido.business.generic.UseCaseForEventNoReactivo;
import com.pedido.domain.Product;
import com.pedido.domain.command.AddPercentCommand;
import com.pedido.domain.events.ProductCreated;
import com.pedido.domain.events.PromoCreated;
import com.pedido.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class AddPercentPromoUseCaseNoReactivo implements UseCaseForEventNoReactivo<PromoCreated> {

    private Repository repository;

    public AddPercentPromoUseCaseNoReactivo(Repository repository) { this.repository = repository; }

    @Override
    public List<DomainEvent> apply(PromoCreated promoCreated) {
        List<DomainEvent> events = repository.findByIdNoReactivo(promoCreated.aggregateRootId());

        Product product = Product.from(ProductId.of(promoCreated.aggregateRootId()), events);
        product.addPercent(promoCreated.getPromoId(),
                promoCreated.getPercent());
        return product.getUncommittedChanges().stream().map(event -> {
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }


}
