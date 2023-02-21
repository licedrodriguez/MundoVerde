package com.pedido.domain;

import com.pedido.business.generic.EventChange;
import com.pedido.domain.Product;
import com.pedido.domain.events.AddedPercent;
import com.pedido.domain.events.ProductCreated;
import com.pedido.domain.events.PromoCreated;
import com.pedido.domain.values.Percent;

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
