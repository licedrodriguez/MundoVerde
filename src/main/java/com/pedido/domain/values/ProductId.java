package com.pedido.domain.values;

import com.pedido.business.generic.Identity;

public class ProductId extends Identity {

    public ProductId(String idProduct) { super(idProduct);}

    public ProductId(){}

    public static ProductId of(String idProduct) { return new ProductId(idProduct);}
}
