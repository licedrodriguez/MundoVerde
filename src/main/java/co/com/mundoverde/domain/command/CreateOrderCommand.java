package co.com.mundoverde.domain.command;

import co.com.mundoverde.business.generic.Command;

import java.util.Date;

public class CreateOrderCommand extends Command {

    private String orderId;
    private final Date date;

    private final String address;

    private final String seat;

    private final String name;

    public CreateOrderCommand(String id, Date date, String address, String seat, String name) {
        this.orderId = id;
        this.date = date;
        this.address = address;
        this.seat = seat;
        this.name = name;
    }

    public Date getDate() { return date; }

    public String getAddress() { return address; }

    public String getSeat() { return seat; }

    public String getName() { return name; }

    public String getOrderId() { return orderId; }

}
