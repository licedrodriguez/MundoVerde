package co.com.mundoverde.domain.values;

import co.com.mundoverde.business.generic.ValueObject;

import java.security.PublicKey;
import java.util.Objects;

public class Location implements ValueObject<Location.ILocation> {

    private final String address;

    private final String seat;

    public Location(String address, String seat) {
        this.address = Objects.requireNonNull(address);
        if(this.address.isEmpty()){
            throw new IllegalArgumentException("La dirección no es valida");
        }
        this.seat = Objects.requireNonNull(seat);
        if (this.seat.isEmpty()){
            throw new IllegalArgumentException("La sede no es valida");
        }
    }


    @Override
    public ILocation value() {
        return new ILocation() {
            @Override
            public String address() { return address; }

            @Override
            public String seat() { return seat; }
        };
    }

    public  interface ILocation{
        String address();
        String seat();


    }
}
