package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

public class Price implements ValueObject<BigDecimal> {

    private final Double price;

    public Price(Double price){
        this.price = Objects.requireNonNull(price);
        if (this.price.isNaN()){
            throw  new IllegalArgumentException("El precio no es valido");
        }
    }


    @Override
    public BigDecimal value() {
        return null;
    }
}
