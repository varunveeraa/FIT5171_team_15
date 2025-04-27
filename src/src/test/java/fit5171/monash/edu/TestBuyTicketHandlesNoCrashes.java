package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBuyTicketHandlesNoCrashes {
    @Test
    public void testBuyTicketHandlesUnexpectedCases() throws Exception {
        TicketSystem ticketSystem = new TicketSystem();

        // Negative ID should not book a ticket
        boolean resultNegative = ticketSystem.buyTicket(-1);
        assertFalse(resultNegative, "Ticket buying with negative ID should fail");

        // Zero ID should not book a ticket
        boolean resultZero = ticketSystem.buyTicket(0);
        assertFalse(resultZero, "Ticket buying with zero ID should fail");
    }
}


