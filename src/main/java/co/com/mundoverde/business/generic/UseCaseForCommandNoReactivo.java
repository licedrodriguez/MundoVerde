package co.com.mundoverde.business.generic;

import java.util.List;

public interface UseCaseForCommandNoReactivo <R extends Command> {

    List<DomainEvent> apply(R command);
}
