package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.Repository;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForEventNoReactivo;
import co.com.mundoverde.domain.Product;
import co.com.mundoverde.domain.events.PromoCreated;
import co.com.mundoverde.domain.values.ProductId;

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
