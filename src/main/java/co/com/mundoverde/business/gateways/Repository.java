package co.com.mundoverde.business.gateways;

import co.com.mundoverde.business.generic.DomainEvent;

import java.util.List;

public interface Repository {

    DomainEvent saveEventNoReactivo(DomainEvent domainEvent);

    List<DomainEvent> findByIdNoReactivo(String aggregateRootId);
}
