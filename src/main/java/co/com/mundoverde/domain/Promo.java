package co.com.mundoverde.domain;

import co.com.mundoverde.business.generic.Entity;
import co.com.mundoverde.domain.values.Description;
import co.com.mundoverde.domain.values.Percent;
import co.com.mundoverde.domain.values.PromoId;
import co.com.mundoverde.domain.values.Time;

import java.util.Objects;

public class Promo extends Entity<PromoId> {

    private Time time;

    private Percent percent;

    private Description description;

    public Promo(PromoId id, Time time, Description description) {
        super(id);
        this.time = time;
        this.description = description;
    }

    public Promo(PromoId id, Percent percent) {
        super(id);
        this.percent = percent;
    }

    public Time time() { return time; }

    public Percent percent() { return percent; }

    public  Description description() { return  description; }
 }
