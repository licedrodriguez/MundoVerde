package com.pedido.domain.values;

import com.pedido.business.generic.ValueObject;

import java.util.Objects;

public class Image implements ValueObject<Image.IImage> {

    private final String name;

    private final String url;

    private final String size;

    public Image(String name, String url, String size){
        this.name = Objects.requireNonNull(name);
        this.url = Objects.requireNonNull(url);
        this.size = size;
        if(this.name.isEmpty()){
            throw new IllegalArgumentException("El nombre de la imagen no es valido");
        }
        if(this.url.isEmpty()){
            throw new IllegalArgumentException(("La url de la imagen no es valida"));
        }
    }

    @Override
    public IImage value() {
        return new IImage() {
            @Override
            public String name() { return name; }

            @Override
            public String url() { return url; }

            @Override
            public String size() { return size; }
        };
    }

    public interface IImage{
        String name();

        String url();

        String size();
    }
}
