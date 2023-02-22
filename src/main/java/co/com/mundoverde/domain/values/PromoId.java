package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.Identity;

public class PromoId extends Identity {

    public PromoId(String promoId) { super(promoId);}

    public PromoId(){}

    public static PromoId of(String promoId) { return new PromoId(promoId);}
}
