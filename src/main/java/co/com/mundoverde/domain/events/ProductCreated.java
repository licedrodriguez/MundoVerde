package co.com.mundoverde.domain.events;


import co.com.mundoverde.business.generic.DomainEvent;
import co.com.mundoverde.domain.values.*;

public class ProductCreated extends DomainEvent {

    private final Name productName;

    private final ProductType productType;

    private final Price price;

    private final Image image;

    private final Description description;

    private final PromoId promoId;

    private  final Percent percent;



    public ProductCreated(Name name, ProductType type, Price price, Image image, Description description, PromoId promoId, Percent percent) {
        super("liced.rodriguez.productCreatedPerecent");
        this.productName = name;
        this.productType = type;
        this.price = price;
        this.image = image;
        this.description = description;
        this.promoId = promoId;
        this.percent = percent;
    }

    public Name getProductName() { return productName;}

    public ProductType getProductType() { return productType; }

    public Price getPrice() { return price; }

    public Image getImage() { return image; }

    public Description getDescription() { return description; }

    public PromoId getPromoId() { return promoId; }

    public Percent getPercent() { return percent; }
}
