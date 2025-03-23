package fit5171.monash.edu;

import org.junit.jupiter.api.Test;

public class TestBuyTicketHandlesNoCrashes {
    @Test
    public void testBuyTicketHandlesUnexpectedCases() throws Exception {
        TicketSystem ticketSystem = new TicketSystem();
        ticketSystem.buyTicket(-1);  // Edge case: negative ID
        ticketSystem.buyTicket(0);   // Edge case: zero ID
    }
}

