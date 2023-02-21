package com.pedido.domain;

import com.pedido.business.generic.Entity;
import com.pedido.domain.values.*;

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
