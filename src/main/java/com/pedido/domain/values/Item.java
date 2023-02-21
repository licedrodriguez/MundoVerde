package com.pedido.domain.values;

import com.pedido.business.generic.ValueObject;

import java.util.Objects;

public class Item implements ValueObject<Item.IItem> {

    public final Integer amount;

    public final String productId;

    public Item(Integer amount, String productId) {
        this.amount = Objects.requireNonNull(amount);
        this.productId = Objects.requireNonNull(productId);
        if (amount <= 0){
            throw new IllegalArgumentException("El Carrito esta vacio");
        }
        if(productId.isEmpty()) {
            throw new IllegalArgumentException("El valor de producto no es valido");
        }
    }
    @Override
    public IItem value() {
        return new IItem() {
            @Override
            public Integer amount() { return amount; }

            @Override
            public String productId() { return productId; }
        };
    }

    public interface IItem {
        Integer amount();

        String productId();
    }
}
