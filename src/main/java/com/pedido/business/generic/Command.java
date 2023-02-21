package com.pedido.business.generic;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Command implements Serializable {

    public final Instant when;

    private final String uuid;

    public  Command(){
        this.uuid = UUID.randomUUID().toString();
        this.when = Instant.now();
    }

    public Instant when() { return when; }

    public String uuid() { return uuid; }
}
