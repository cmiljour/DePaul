package order;

import ticket.ITicket;

public class TicketOrderFactory {
    public static ITicketOrder createTicketOrder(ITicket ticket, int quantity, boolean purchasedInsurance) {
        if(purchasedInsurance)
            return new TicketOrderProxy(ticket, quantity);
        else
            return new TicketOrder(ticket, quantity);
    }
}
