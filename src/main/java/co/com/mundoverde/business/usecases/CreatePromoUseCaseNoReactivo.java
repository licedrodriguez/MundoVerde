package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.Repository;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForCommandNoReactivo;
import co.com.mundoverde.domain.Product;
import co.com.mundoverde.domain.command.CreatePromoCommand;
import co.com.mundoverde.domain.values.*;

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
