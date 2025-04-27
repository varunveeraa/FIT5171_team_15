package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBuyTicketTransferInvalid {

    @Test
    public void testBuyTicketWithInvalidBothTicketIds() {
        TicketSystem ticketSystem = new TicketSystem();

        // Both tickets invalid
        boolean result = ticketSystem.buyTicket(111, 222);
        assertFalse(result, "Buying transfer ticket with both invalid IDs should fail");
    }

    @Test
    public void testBuyTicketWithFirstTicketInvalid() {
        TicketSystem ticketSystem = new TicketSystem();

        // First ticket invalid, second ticket maybe valid (mocked second ID here)
        boolean result = ticketSystem.buyTicket(-5, 222);
        assertFalse(result, "Buying transfer ticket with invalid first ID should fail");
    }

    @Test
    public void testBuyTicketWithSecondTicketInvalid() {
        TicketSystem ticketSystem = new TicketSystem();

        // First ticket maybe valid (mocked), second ticket invalid
        boolean result = ticketSystem.buyTicket(111, -7);
        assertFalse(result, "Buying transfer ticket with invalid second ID should fail");
    }

    @Test
    public void testBuyTicketWithZeroIds() {
        TicketSystem ticketSystem = new TicketSystem();

        // Zero IDs
        boolean result = ticketSystem.buyTicket(0, 0);
        assertFalse(result, "Buying transfer ticket with zero IDs should fail");
    }

    @Test
    public void testBuyTicketHandlesUnexpectedCasesGracefully() {
        TicketSystem ticketSystem = new TicketSystem();

        // This just ensures no crash even with very weird values
        assertDoesNotThrow(() -> ticketSystem.buyTicket(Integer.MAX_VALUE, Integer.MIN_VALUE),
                "TicketSystem should not crash even with extreme ID values");
    }
}
