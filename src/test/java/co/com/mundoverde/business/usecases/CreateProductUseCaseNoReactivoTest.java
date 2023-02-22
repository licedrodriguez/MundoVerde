package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.Repository;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.command.CreateProductCommand;
import co.com.mundoverde.domain.events.ProductCreated;
import co.com.mundoverde.domain.events.PromoCreated;
import co.com.mundoverde.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseNoReactivoTest {

    @Mock
    private Repository repository;

    private CreateProductUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new CreateProductUseCaseNoReactivo(repository);
    }

    @Test
    void sucessFullScenario(){
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

        ProductCreated event = new ProductCreated(new Name(PRODUCT_NAME)
                , new ProductType(PRODUCT_TYPE), new Price(PRICE), image
                , new Description(DESCRIPTION), new PromoId(PROMO_ID), new Percent(PERCENT));
        event.setAggregateRootId(PRODUCT_ID);

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any()))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());


    }
 }