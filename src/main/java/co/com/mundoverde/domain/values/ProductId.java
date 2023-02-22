package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.Identity;

public class ProductId extends Identity {

    public ProductId(String idProduct) { super(idProduct);}

    public ProductId(){}

    public static ProductId of(String idProduct) { return new ProductId(idProduct);}
}
