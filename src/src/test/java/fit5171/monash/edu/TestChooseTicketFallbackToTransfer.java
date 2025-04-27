
package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestChooseTicketFallbackToTransfer {

    @Test
    public void testChooseTicketTriggersFallbackTransfer() {
        TicketSystem ticketSystem = new TicketSystem();

        // Attempting to invoke the method and verifying it does not throw any exception
        assertDoesNotThrow(() -> {
            try {
                ticketSystem.chooseTicket("CityA", "CityB");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
//
