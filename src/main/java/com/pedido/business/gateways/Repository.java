package com.pedido.business.gateways;

import com.pedido.business.generic.DomainEvent;

import java.util.List;

public interface Repository {

    DomainEvent saveEventNoReactivo(DomainEvent domainEvent);

    List<DomainEvent> findByIdNoReactivo(String aggregateRootId);
}
