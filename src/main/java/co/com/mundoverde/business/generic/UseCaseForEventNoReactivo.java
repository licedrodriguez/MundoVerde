package co.com.mundoverde.business.generic;

import java.util.List;

public interface UseCaseForEventNoReactivo <R extends DomainEvent>{

    List<DomainEvent> apply(R DomainEvent);
}
