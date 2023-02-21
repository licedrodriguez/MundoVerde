package com.pedido.domain.values;

import com.pedido.business.generic.Identity;

public class PromoId extends Identity {

    public PromoId(String promoId) { super(promoId);}

    public PromoId(){}

    public static PromoId of(String promoId) { return new PromoId(promoId);}
}
