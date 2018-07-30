package ticket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    @Test
    public void testGetPriceForRegularTicket(){
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80);
        ITicket ticketToTest = tb.getTicket();

        // act
        int price = ticketToTest.getPrice();

        //assert
        assertEquals(80, price);
    }

    @Test
    public void testGetPriceForMeetAndGreetTicketAdds100ToPrice(){
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80)
            .addMeetAndGreet();
        ITicket ticketToTest = tb.getTicket();

        // act
        int price = ticketToTest.getPrice();

        //assert
        assertEquals(180, price);
    }

    @Test
    public void testGetPriceForBonusPackageTicketAdds50ToPrice(){
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80)
                .addBonusPackage();
        ITicket ticketToTest = tb.getTicket();

        // act
        int price = ticketToTest.getPrice();

        //assert
        assertEquals(130, price);
    }

    @Test
    public void testGetPriceForMeetAndGreetAndBonusPackageTicketAdds150ToPrice() {
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80)
            .addMeetAndGreet()
            .addBonusPackage();
        ITicket ticketToTest = tb.getTicket();

        // act
        int price = ticketToTest.getPrice();

        //assert
        assertEquals(230, price);
    }

    @Test
    public void testGetSummaryForRegularTicket(){
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80);
        ITicket ticketToTest = tb.getTicket();

        // act
        String summary = ticketToTest.getSummary();

        //assert
        assertEquals("Pearl Jam", summary);
    }

    @Test
    public void testGetSummaryForMeetAndGreetTicket(){
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80)
                .addMeetAndGreet();
        ITicket ticketToTest = tb.getTicket();

        // act
        String summary = ticketToTest.getSummary();

        //assert
        assertEquals("Pearl Jam + Meet and Greet", summary);
    }

    @Test
    public void testGetSummaryForBonusPackageTicket(){
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80)
                .addBonusPackage();
        ITicket ticketToTest = tb.getTicket();

        // act
        String summary = ticketToTest.getSummary();

        //assert
        assertEquals("Pearl Jam + Bonus Package", summary);
    }

    @Test
    public void testGetSummaryForMeetAndGreetAndBonusPackageTicket() {
        // arrange
        TicketBuilder tb = new TicketBuilder();
        tb.addTicket("Pearl Jam", 80)
                .addMeetAndGreet()
                .addBonusPackage();
        ITicket ticketToTest = tb.getTicket();

        // act
        String summary = ticketToTest.getSummary();

        //assert
        assertEquals("Pearl Jam + Meet and Greet + Bonus Package", summary);
    }
}
