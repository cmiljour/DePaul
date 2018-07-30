package ticket;

public class MeetAndGreetTicket implements ITicket {

    private final int price;
    private final String showName;



    public MeetAndGreetTicket(ITicket ticket) {
        this.price = ticket.getPrice();
        this.showName = ticket.getSummary();
    }

    @Override
    public int getPrice() {
        return price + 100;
    }

    @Override
    public String getSummary() {
        return showName + " + Meet and Greet";
    }
}
