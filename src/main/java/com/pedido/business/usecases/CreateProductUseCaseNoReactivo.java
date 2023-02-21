package com.pedido.business.usecases;

import com.pedido.business.gateways.Repository;
import com.pedido.business.generic.Command;
import com.pedido.business.generic.DomainEvent;
import com.pedido.business.generic.UseCaseForCommandNoReactivo;
import com.pedido.domain.Product;
import com.pedido.domain.command.CreateProductCommand;
import com.pedido.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class CreateProductUseCaseNoReactivo implements UseCaseForCommandNoReactivo {

    private Repository repository;

    public CreateProductUseCaseNoReactivo(Repository repository) { this.repository = repository; }

    @Override
    public List<DomainEvent> apply(Command command) {
        CreateProductCommand createProduct = (CreateProductCommand) command;
        Product product = new Product(ProductId.of(createProduct.getProductId()),
                new Name(createProduct.getProductName()),
                new ProductType(createProduct.getProductType()),
                new Price(createProduct.getPrice()),
                new Image(createProduct.getImageName(), createProduct.getUrl(), createProduct.getSize()),
                new Description(createProduct.getDescription()),
                new PromoId(),
                new Percent(Double.parseDouble("9808")));
        return  product.getUncommittedChanges().stream().map(event -> {
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
