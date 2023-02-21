package com.pedido.domain.events;

import com.pedido.business.generic.DomainEvent;
import com.pedido.domain.values.Description;
import com.pedido.domain.values.Percent;
import com.pedido.domain.values.PromoId;
import com.pedido.domain.values.Time;

public class PromoCreated extends DomainEvent {

    private final PromoId promoId;

    private final Time time;

    private  final Description description;


    private final Percent percent;
    public PromoCreated(PromoId promoId, Time time, Description description, Percent percent) {
        super("liced.rodriguez.PromoCreated");
        this.promoId = promoId;
        this.time = time;
        this.description = description;
        this.percent = percent;
    }

    public PromoId getPromoId() { return promoId; }

    public Time getTime() { return time; }

    public Description getDescription() { return description; }

    public Percent getPercent() { return percent; }
}
