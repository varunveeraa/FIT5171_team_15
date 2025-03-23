package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestBuyTicketTransferInvalid {
    @Test
    public void testBuyTicketWithTransferInvalidIds()
    {
        TicketSystem ticketSystem = new TicketSystem();
        assertDoesNotThrow(() -> ticketSystem.buyTicket(111, 222));
    }


}
