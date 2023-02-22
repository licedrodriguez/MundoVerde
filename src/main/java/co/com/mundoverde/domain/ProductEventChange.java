package co.com.mundoverde.domain;

import co.com.mundoverde.business.generic.EventChange;
import co.com.mundoverde.domain.events.AddedPercent;
import co.com.mundoverde.domain.events.ProductCreated;
import co.com.mundoverde.domain.events.PromoCreated;

public class ProductEventChange extends EventChange {

    public ProductEventChange(Product product) {

        apply((ProductCreated event) -> {
            product.productName = event.getProductName();
            product.productType = event.getProductType();
            product.price = event.getPrice();
            product.image = event.getImage();
            product.description = event.getDescription();
        });

       apply((PromoCreated event) -> {
            Promo promo = new Promo(event.getPromoId(), event.getTime(), event.getDescription());
            product.promo.add(promo);
        });

       apply((AddedPercent event) ->{
           Promo promo = new Promo(event.getPromoId(), event.getPercent());
           product.promoPercent = promo;
       });

    }
}
