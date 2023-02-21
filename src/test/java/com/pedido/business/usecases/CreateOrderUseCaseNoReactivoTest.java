package com.pedido.business.usecases;

import com.pedido.business.gateways.Repository;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseNoReactivoTest {

    @Mock
    private Repository repository;

    private CreateOrderUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){ useCase = new CreateOrderUseCaseNoReactivo(repository); }

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

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(OrderCreated.class))).thenAnswer(invocationOnMock -> {
            return invocationOnMock.getArgument(0);
        });

        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(orderCreated.aggregateRootId(), result.get(0).aggregateRootId());
    }
}