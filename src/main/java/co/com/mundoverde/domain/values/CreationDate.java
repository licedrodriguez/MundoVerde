package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.ValueObject;

import java.util.Date;
import java.util.Objects;

public class CreationDate implements ValueObject<Date> {

    private final Date date;

    public CreationDate(Date date){
        this.date = Objects.requireNonNull(date);
        if(this.date.equals(null)) {
            throw new IllegalArgumentException("La fecha de creación no es valida");
        }
    }
    @Override
    public Date value() { return date; }
}
