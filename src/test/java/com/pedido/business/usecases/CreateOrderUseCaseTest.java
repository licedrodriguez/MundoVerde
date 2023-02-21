package com.pedido.business.usecases;

import com.pedido.business.gateways.DomainEventRepository;
import com.pedido.business.gateways.EventBus;
import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.command.CreateOrderCommand;
import com.pedido.domain.events.OrderCreated;
import com.pedido.domain.values.CreationDate;
import com.pedido.domain.values.Location;
import com.pedido.domain.values.Name;
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
class CreateOrderUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private CreateOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateOrderUseCase(repository, bus);
    }

    @Test
    void SuccessFullScenarios() {
        String ORDER_ID = "order-id-test";
        Date CREATION_DATE = new Date("23/02/2023");
        String ADDRESS = "order-address-test";
        String SITE = "order-site-test";
        String ORDER_NAME = "order-name-tet";

        CreateOrderCommand command = new CreateOrderCommand(ORDER_ID, CREATION_DATE, ADDRESS, SITE, ORDER_NAME );

        OrderCreated orderCreated = new OrderCreated(new CreationDate(CREATION_DATE), new Location(ADDRESS, SITE), new Name(ORDER_NAME));
        orderCreated.setAggregateRootId(ORDER_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(OrderCreated.class))).thenAnswer(invocationOnMock -> {
            return Mono.just((DomainEvent) invocationOnMock.getArgument(0));
        });

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result).expectNextMatches(event ->{
            Assertions.assertEquals(event.aggregateRootId(), orderCreated.aggregateRootId());
            return event.aggregateRootId().equals(orderCreated.aggregateRootId());
        })
                .verifyComplete();

    }

}