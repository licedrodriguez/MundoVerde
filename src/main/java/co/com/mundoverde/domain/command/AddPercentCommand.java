package co.com.mundoverde.domain.command;

import co.com.mundoverde.business.generic.Command;

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
