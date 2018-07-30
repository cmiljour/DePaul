package order;

import ticket.ITicket;

public class TicketOrderProxy implements ITicketOrder {
    private final TicketOrder _ticketOrder;

    public TicketOrderProxy(ITicket ticket, int quantity) {
        _ticketOrder = new TicketOrder(ticket, quantity);
    }


    @Override
    public void submitTicketOrder() {
        String insuranceUrl = "https://www.ticketinsurance.xyz/" + _ticketOrder.getOrderNumber();
        // make API call to insuranceUrl. Notice _ticketOrder.getOrderNumber is a method
        // on the concrete implementation of TicketOrder; it is not a method on ITicketOrder.
        _ticketOrder.submitTicketOrder();
    }
}
