package com.pedido.domain.values;

import com.pedido.business.generic.ValueObject;

import java.util.Objects;

public class Description implements ValueObject {

    private final String value;

    public Description(String value) {
        this.value = Objects.requireNonNull(value);

    }

    @Override
    public String value() { return value; }
}
