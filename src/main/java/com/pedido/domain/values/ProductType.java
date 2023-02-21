package com.pedido.domain.values;

import com.pedido.business.generic.ValueObject;

import java.util.Objects;

public class ProductType implements ValueObject<String> {

    private final String type;

    public ProductType(String type) {
        this.type = Objects.requireNonNull(type);
        if (this.type.isEmpty()){
            throw new IllegalArgumentException("El nombre del producto no es valido");
        }
    }


    @Override
    public String value() {
        return type;
    }
}
