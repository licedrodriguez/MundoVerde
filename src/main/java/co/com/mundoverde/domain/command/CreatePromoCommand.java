package co.com.mundoverde.domain.command;

import co.com.mundoverde.business.generic.Command;
import java.util.Date;

public class CreatePromoCommand extends Command {

    private final String productId;
    private final String promoId;

    private final Date startDate;

    private  final  Date endDate;

    private final String  description;

    public CreatePromoCommand(String promoId, Date startDate, Date endDate, String description, String productId){
        this.promoId = promoId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.productId = productId;
    }

    public  String getProductId() { return productId; }
    public String getPromoId() { return promoId; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }

    public String  getDescription() { return description; }
}
