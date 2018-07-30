package ticket;

public class Ticket implements ITicket {

    private final int price;
    private final String showName;

    public Ticket(String showName, int price){
        this.price = price;
        this.showName = showName;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getSummary() {
        return showName;
    }
}
