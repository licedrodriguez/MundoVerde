package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.ValueObject;

import java.util.Objects;

public class Percent implements ValueObject<Double> {

    private final Double valuePercent;


    public Percent(Double valuePercent) {
        this.valuePercent  = Objects.requireNonNull(valuePercent);
        if (this.valuePercent.isNaN()){
            throw new IllegalArgumentException("El porcentaje no es valido");
        }
        if (valuePercent <= 0) {
            throw new IllegalArgumentException("El porcentaje no es valido");
        }
    }


    @Override
    public Double value() { return valuePercent; }
}
