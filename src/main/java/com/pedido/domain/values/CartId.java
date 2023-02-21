package com.pedido.domain.values;

import com.pedido.business.generic.Identity;

public class CartId extends Identity {

    public CartId(String cartId){ super(cartId); }

    public CartId(){}

    public static CartId of(String cartId) { return new CartId(cartId); }
}
