package co.com.mundoverde.domain.events;

import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.values.Percent;
import co.com.mundoverde.domain.values.PromoId;

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
