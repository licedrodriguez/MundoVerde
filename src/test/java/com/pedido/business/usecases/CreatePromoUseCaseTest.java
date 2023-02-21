package com.pedido.business.usecases;

import com.pedido.business.gateways.DomainEventRepository;
import com.pedido.business.gateways.EventBus;
import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.command.CreatePromoCommand;
import com.pedido.domain.events.ProductCreated;
import com.pedido.domain.events.PromoCreated;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreatePromoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private CreatePromoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreatePromoUseCase(repository, bus);
    }

    @Test
    void successFullScenarios(){
        String PRODUCT_ID = "test-product-id";
        String PROMO_ID = "test-promo-id";
        Date START_DATE = new Date("23/02/2023");
        Date END_DATE = new Date("06/07/2023");
        Double PERCENT = Double.parseDouble("50");
        String DESCRIPTION = "test-product-desc";
        String PRODUCT_NAME = "test-product-name";
        String PRODUCT_TYPE = "test-product-type";
        Double PRICE = Double.parseDouble("45000");
        String IMAGE_NAME = "test-image-name";
        String URL = "test-image-url";
        String SIZE = "25gb";
        Image image = new Image(IMAGE_NAME, URL, SIZE);


        CreatePromoCommand command = new CreatePromoCommand(PROMO_ID
                , START_DATE, END_DATE, DESCRIPTION, PRODUCT_ID);

        ProductCreated event = new ProductCreated(new Name(PRODUCT_NAME)
                , new ProductType(PRODUCT_TYPE), new Price(PRICE), image
                , new Description(DESCRIPTION), new PromoId(PROMO_ID), new Percent(PERCENT));
        event.setAggregateRootId(PRODUCT_ID);

        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(ProductCreated.class)))
                .thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result).expectNextMatches(event1 -> {
            PromoCreated promoCreated = (PromoCreated) event1;
            Assertions.assertEquals(promoCreated.getPromoId().value(), event.getPromoId().value());
            return event1.aggregateRootId().equals(event.aggregateRootId());
        }).verifyComplete();
    }

}