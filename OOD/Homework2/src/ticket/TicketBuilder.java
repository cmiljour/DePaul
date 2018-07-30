package ticket;


public class TicketBuilder {
    ITicket ticket;


    public TicketBuilder addTicket(String showName, int price) {
        ticket = new Ticket(showName, price);
        return this;
    }

    public ITicket getTicket() {
        return ticket;
    }

    public TicketBuilder addMeetAndGreet() {
        //throw new Error("Uncomment below and implement the MeetAndGreetTicket class");
        ticket = new MeetAndGreetTicket(ticket);
        return this;
    }

    public TicketBuilder addBonusPackage() {
        //throw new Error("Uncomment below and implement the BonusPackageTicket class");
        ticket = new BonusPackageTicket(ticket);
        return this;
    }
}

