package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestShowTicket {

    @Test
    public void testShowTicketHandlesNullsGracefully() {
        TicketSystem ticketSystem = new TicketSystem();

        // When no ticket is purchased yet, showTicket should not crash
        assertDoesNotThrow(ticketSystem::showTicket, "showTicket() should not throw exception even if no ticket exists");
    }

    @Test
    public void testShowTicketAfterBuyingTicket() {
        TicketSystem ticketSystem = new TicketSystem();

        // Setup dummy ticket and flight manually
        Flight flight = new Flight();
        flight.setFlightID(1);
        flight.setDepartFrom("CityX");
        flight.setDepartTo("CityY");

        Passenger passenger = new Passenger();
        passenger.setFirstName("Test");
        passenger.setSecondName("User");
        passenger.setAge(25);
        passenger.setGender("M");
        passenger.setEmail("testuser@example.com");
        passenger.setPhoneNumber("+7000000000");
        passenger.setPassport("XY123456");

        Ticket ticket = new Ticket();
        ticket.setTicket_id(1);
        ticket.setPassenger(passenger);
        ticket.setFlight(flight);
        ticket.setClassVip(false);
        ticket.setPrice(100);
        ticket.setTicketStatus(true);

        // Simulate that a ticket has been bought
        ticketSystem.ticket = ticket;

        // Now showTicket() should still not crash
        assertDoesNotThrow(ticketSystem::showTicket, "showTicket() should work fine after buying ticket");
    }
}
