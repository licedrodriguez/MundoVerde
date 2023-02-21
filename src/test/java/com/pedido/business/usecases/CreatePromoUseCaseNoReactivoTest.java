package com.pedido.business.usecases;

import com.pedido.business.gateways.Repository;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreatePromoUseCaseNoReactivoTest {

    @Mock
    private Repository repository;

    private CreatePromoUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreatePromoUseCaseNoReactivo(repository);
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

        Mockito.when(repository.findByIdNoReactivo(PRODUCT_ID)).thenReturn(List.of(event));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(ProductCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(command.getProductId(), result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(PromoCreated.class, result.get(0));
    }

}