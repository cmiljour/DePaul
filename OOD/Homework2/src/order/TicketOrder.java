package order;

import ticket.ITicket;

import java.util.Random;

public class TicketOrder implements ITicketOrder {

    private final ITicket _ticket;
    private final int _quantity;
    private final String _orderNumber;

    public TicketOrder(ITicket ticket, int quantity) {
        _ticket = ticket;
        _quantity = quantity;
        _orderNumber = Integer.toString(new Random().nextInt(Integer.MAX_VALUE));
    }

    @Override
    public void submitTicketOrder() {
        // Make API call to submit order
    }

    public String getOrderNumber(){
        return _orderNumber;
    }
}
