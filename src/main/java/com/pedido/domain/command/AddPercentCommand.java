package com.pedido.domain.command;

import com.pedido.business.generic.Command;
import com.pedido.domain.values.Price;

import java.util.Date;

public class AddPercentCommand extends Command {

    private String productId;
    private String promoId;

    private Double percent;

    public AddPercentCommand(String promoId, Double percent, String productId){
        this.promoId = promoId;
        this.percent = percent;
        this.productId = productId;
    }

    public String getPromoId() { return promoId; }

    public Double  getPercent() { return percent; }

    public String getProductId() { return productId; }
}
