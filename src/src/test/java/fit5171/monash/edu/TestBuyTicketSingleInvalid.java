package fit5171.monash.edu;

import org.junit.jupiter.api.Test;

public class TestBuyTicketSingleInvalid {
    @Test
    public void testBuyTicketWithInvalidTicketId() throws Exception
    {
        TicketSystem ticketSystem = new TicketSystem();
        ticketSystem.buyTicket(999);  // Invalid ID: should trigger error message but not crash
    }

}
