package com.pedido.domain.events;

import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.values.Percent;
import com.pedido.domain.values.PromoId;

public class AddedPercent extends DomainEvent {

    private  final PromoId promoId;

    private final Percent percent;
    public AddedPercent(PromoId promoId, Percent percent) {
        super("liced.rodriguez.orderCreated");
        this.promoId = promoId;
        this.percent = percent;
    }

    public PromoId getPromoId() { return promoId; }

    public Percent getPercent() { return percent; }
}
