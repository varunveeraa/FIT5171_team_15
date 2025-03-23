package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestChooseTicketFallbackToTransfer {
    @Test
    public void testChooseTicketTriggersFallbackTransfer() {
        TicketSystem ticketSystem = new TicketSystem();
        assertDoesNotThrow(() -> ticketSystem.chooseTicket("CityA", "CityB"));
    }
}
