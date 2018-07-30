package ticket;

public class BonusPackageTicket implements ITicket {
    private final String showName;
    private final int price;

    public BonusPackageTicket(ITicket ticket) {
        this.showName = ticket.getSummary();
        this.price = ticket.getPrice();
    }

    @Override
    public int getPrice() {
        return price + 50;
    }

    @Override
    public String getSummary() {
        return showName + " + Bonus Package";
    }
}
