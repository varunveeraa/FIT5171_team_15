package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestBuyTicketSingleInvalid {
    @Test
    public void testBuyTicketWithInvalidTicketId() throws Exception {
        TicketSystem ticketSystem = new TicketSystem();

        // Attempt to buy a ticket with an invalid ID
        boolean result = ticketSystem.buyTicket(999);

        // Assert that buying with invalid ticket ID fails
        assertFalse(result, "Buying a ticket with invalid ID (999) should fail and return false");
    }
}
