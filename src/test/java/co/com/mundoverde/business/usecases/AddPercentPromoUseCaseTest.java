package co.com.mundoverde.business.usecases;

import co.com.mundoverde.business.gateways.DomainEventRepository;
import co.com.mundoverde.business.gateways.EventBus;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.events.AddedPercent;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AddPercentPromoUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private AddPercentPromoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AddPercentPromoUseCase(repository, bus);
    }

    @Test
    void seccessFullScenario(){
        String PRODUCT_ID = "test-product-id";
        String PROMO_ID = "test-promo-id";
        Double PERCENT = Double.parseDouble("50");
        String DESCRIPTION = "test-product-desc";
        String PRODUCT_NAME = "test-product-name";
        String PRODUCT_TYPE = "test-product-type";
        Double PRICE = Double.parseDouble("45000");
        String IMAGE_NAME = "test-image-name";
        String URL = "test-image-url";
        String SIZE = "25gb";
        Image image = new Image(IMAGE_NAME, URL, SIZE);
        Date START_DATE = new Date("23/02/2023");
        Date END_DATE = new Date("06/07/2023");



        ProductCreated product = new ProductCreated(new Name(PRODUCT_NAME)
                , new ProductType(PRODUCT_TYPE), new Price(PRICE), image
                , new Description(DESCRIPTION), new PromoId(PROMO_ID), new Percent(PERCENT));
        product.setAggregateRootId(PRODUCT_ID);
        Flux<DomainEvent> events = Flux.just(product);

        PromoCreated promoCreated= new PromoCreated(new PromoId(PROMO_ID)
                , new Time(START_DATE, END_DATE)
                , new Description(DESCRIPTION)
                , new Percent(PERCENT));
        promoCreated.setAggregateRootId(PRODUCT_ID);
        events.just(promoCreated);

        //Mockito.when(repository.finById(ArgumentMatchers.any())).thenReturn(events);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(AddedPercent.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0)));

        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(promoCreated));

        StepVerifier.create(result).expectNextMatches(event1 -> {
            AddedPercent percent = (AddedPercent) event1;
            Assertions.assertEquals(percent.getPromoId().value(), promoCreated.getPromoId().value());
            return event1.aggregateRootId().equals(promoCreated.aggregateRootId());
        }).verifyComplete();
    }
}