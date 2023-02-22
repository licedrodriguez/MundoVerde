package co.com.mundoverde.domain;

import co.com.mundoverde.business.generic.AggregateRoot;
import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.events.AddedPercent;
import co.com.mundoverde.domain.events.ProductCreated;
import co.com.mundoverde.domain.events.PromoCreated;
import co.com.mundoverde.domain.values.*;

import java.util.List;
import java.util.Objects;

public class Product extends AggregateRoot<ProductId> {

    protected Name productName;

    protected ProductType productType;

    protected Price price;

    protected Image image;

    protected Description description;

    protected List<Promo> promo;

    protected Promo promoPercent;

    protected PromoId promoId;

    protected Percent percent;



    public Product(ProductId entityId, Name productName, ProductType productType, Price price, Image image, Description description, PromoId promoId, Percent percent){
        super(entityId);
        subscribe(new ProductEventChange(this));
        appendChange(new ProductCreated(productName, productType, price,image, description, promoId, percent)).apply();
    }

    private Product(ProductId id) {
        super(id);
        subscribe(new ProductEventChange(this));
    }
    public static Product from(ProductId productId, List<DomainEvent> events) {
        var product = new Product(productId);
//        events.forEach(product::applyEvent);
        events.forEach(event -> product.applyEvent(event));
        return product;
    }

    public void createProduct(Name name, ProductType type, Price price, Image image, Description description, PromoId promoId, Percent percent){
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);
        Objects.requireNonNull(price);
        Objects.requireNonNull(image);
        Objects.requireNonNull(description);
        appendChange(new ProductCreated(name, type, price, image, description, promoId, percent)).apply();
    }

    public void createPromo(PromoId promoId, Time time, Description description, Percent percent){
        appendChange(new PromoCreated(promoId, time, description, percent)).apply();

    }

    public void addPercent(PromoId promoId, Percent percent) {
        appendChange(new AddedPercent(promoId, percent)).apply();
    }

}
