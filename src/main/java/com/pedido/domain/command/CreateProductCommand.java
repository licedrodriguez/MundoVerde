package com.pedido.domain.command;

import com.pedido.business.generic.Command;
import com.pedido.domain.values.*;

public class CreateProductCommand extends Command {

    private String productId;

    private String productName;

    private String productType;

    private Double price;

    private String imageName;

    private String url;

    private String size;

    private String description;

    public CreateProductCommand(String id, String name, String type, Double price, Image image, String description){
        this.productId = id;
        this.productName = name;
        this.productType = type;
        this.price = price;
        this.description = description;
        this.imageName = image.value().name();
        this.url = image.value().url();
        this.size = image.value().size();
    }

    public String getProductId() { return productId; }

    public String getProductName() { return productName; }

    public String getProductType() { return productType; }

    public Double getPrice() { return  price; }

    public String getImageName() { return imageName; }

    public  String getUrl() { return  url; }

    public String getSize() { return size;}

    public String getDescription() { return description; }
}
