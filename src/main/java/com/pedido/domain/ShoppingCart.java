package com.pedido.domain;

import com.pedido.business.generic.Entity;
import com.pedido.domain.values.CartId;
import com.pedido.domain.values.CreationDate;
import com.pedido.domain.values.Item;

import java.util.Objects;

public class ShoppingCart extends Entity<CartId> {


    private final CreationDate date;

    private final Item item;

    public ShoppingCart(CartId cartId, CreationDate date, Item item) {
        super(cartId);
        this.date = Objects.requireNonNull(date);
        this.item = Objects.requireNonNull(item);
        if(item.value().amount() <= 0) {
            throw new IllegalArgumentException("El carrito debe contener productos");
        }

    }

    public CreationDate date() { return date; }

    public  Item item() { return item; }

}
