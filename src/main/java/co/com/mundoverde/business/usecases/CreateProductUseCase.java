package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.DomainEventRepository;
import co.com.mundoverde.business.gateways.EventBus;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.business.generic.UseCaseForCommand;
import co.com.mundoverde.domain.Product;
import co.com.mundoverde.domain.command.CreateProductCommand;
import co.com.mundoverde.domain.values.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreateProductUseCase extends UseCaseForCommand<CreateProductCommand> {

    private final DomainEventRepository repository;

    private EventBus bus;

    CreateProductUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository; this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<CreateProductCommand> createProductCommandMono) {
        return createProductCommandMono.flatMapIterable(command -> {
            Product product = new Product(ProductId.of(command.getProductId()),
                    new Name(command.getProductName()),
                    new ProductType(command.getProductType()),
                    new Price(command.getPrice()),
                    new Image(command.getImageName(), command.getUrl(), command.getSize()),
                    new Description(command.getDescription()),
                    new PromoId(),
                    new Percent(Double.parseDouble("50")));
            return product.getUncommittedChanges();
        }).flatMap(event -> repository.saveEvent(event));
    }
}
