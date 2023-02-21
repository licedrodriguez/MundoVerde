package com.pedido.domain.values;

import com.pedido.business.generic.ValueObject;

import java.util.Date;
import java.util.Objects;

public class Time implements ValueObject<Time.ITime> {

    private final Date starteDate;

    private final Date endDate;

    public Time(Date starteDate, Date  endDate) {
        this.starteDate = Objects.requireNonNull(starteDate);
        if (this.starteDate.getTime() <= 0){
            throw new IllegalArgumentException("La fecha inicio no es valida");
        }
        this.endDate = Objects.requireNonNull(endDate);
        if (this.endDate.getTime() <=0 ) {
            throw new IllegalArgumentException("La fecha fin no es valida");
        }
    }

    @Override
    public ITime value() {
        return new ITime() {
            @Override
            public Date startDate() { return starteDate; }

            @Override
            public Date endDate() { return endDate; }
        };
    }

    public interface ITime{
        Date startDate();

        Date endDate();
    }
}
