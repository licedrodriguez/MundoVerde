package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.Repository;
import co.com.mundoverde.business.generic.Command;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForCommandNoReactivo;
import co.com.mundoverde.domain.Product;
import co.com.mundoverde.domain.command.CreateProductCommand;
import co.com.mundoverde.domain.values.*;

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
