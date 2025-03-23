package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestShowTicket {
    @Test
    public void testShowTicketHandlesNullsGracefully() {
        TicketSystem ticketSystem = new TicketSystem();
        assertDoesNotThrow(ticketSystem::showTicket);
    }
}
