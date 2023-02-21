package com.pedido.business.usecases;

import com.pedido.business.gateways.DomainEventRepository;
import com.pedido.business.gateways.EventBus;
import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.command.CreateProductCommand;
import com.pedido.domain.events.ProductCreated;
import com.pedido.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    protected CreateProductUseCase useCase;

    @BeforeEach
    void setUp(){ useCase = new CreateProductUseCase(repository, bus); }

    @Test
    void SuccessFullScenarios(){
        String PRODUCT_ID = "test-product-id";
        String PRODUCT_NAME = "test-product-name";
        String PRODUCT_TYPE = "test-product-type";
        Double PRICE = Double.parseDouble("45000");
        String IMAGE_NAME = "test-image-name";
        String URL = "test-image-url";
        String SIZE = "25gb";
        String DESCRIPTION = "test-product-desc";
        String PROMO_ID = "test-promo-id";
        Double PERCENT = Double.parseDouble("50");

        Image image = new Image(IMAGE_NAME, URL, SIZE);
        CreateProductCommand command = new CreateProductCommand(PRODUCT_ID
                , PRODUCT_NAME, PRODUCT_TYPE, PRICE, image, DESCRIPTION);

        ProductCreated productCreated = new ProductCreated(new Name(PRODUCT_NAME)
                , new ProductType(PRODUCT_TYPE), new Price(PRICE), image
                , new Description(DESCRIPTION), new PromoId(PROMO_ID), new Percent(PERCENT));
        productCreated.setAggregateRootId(PRODUCT_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any()))
                .thenAnswer(invocationOnMock -> Mono.just( invocationOnMock.getArgument(0)));

        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result).expectNextMatches(event1 -> {
            Assertions.assertEquals(event1.aggregateRootId(), productCreated.aggregateRootId());
            return event1.aggregateRootId().equals(productCreated.aggregateRootId());
         }).verifyComplete();
    }

}