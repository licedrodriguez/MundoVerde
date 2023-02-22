package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {

    public final String name;

    public Name(String name) {
        this.name = Objects.requireNonNull(name);
        if (this.name.isEmpty()){
            throw new IllegalArgumentException("El nombre del producto no es valido");
        }
    }

    @Override
    public String value() { return value(); }
}
