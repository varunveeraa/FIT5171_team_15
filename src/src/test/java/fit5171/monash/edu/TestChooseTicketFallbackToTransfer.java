package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TestChooseTicketFallbackToTransfer {

    @Test
    public void testChooseTicketTriggersFallbackWhenNoDirectFlight() {
        TicketSystem ticketSystem = new TicketSystem();

        // Step 1: Set up dummy Scanner input to simulate user typing
        String simulatedInput = "John\nDoe\n30\nM\njohn.doe@example.com\n+7000000000\nAB1234567\n1\n1234567812345678\n123\n";
        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        // Step 2: Set up dummy flights (FlightCollection)
        Flight flight1 = new Flight();
        flight1.setFlightID(1);
        flight1.setDepartFrom("CityA");
        flight1.setDepartTo("CityC");

        Flight flight2 = new Flight();
        flight2.setFlightID(2);
        flight2.setDepartFrom("CityC");
        flight2.setDepartTo("CityB");

        FlightCollection.addFlight(flight1);
        FlightCollection.addFlight(flight2);

        // Step 3: Set up dummy tickets (TicketCollection)
        ArrayList<Ticket> tickets = new ArrayList<>();

        Passenger dummyPassenger = new Passenger();
        dummyPassenger.setFirstName("John");
        dummyPassenger.setSecondName("Doe");
        dummyPassenger.setAge(30);
        dummyPassenger.setGender("M");
        dummyPassenger.setEmail("john.doe@example.com");
        dummyPassenger.setPhoneNumber("+7000000000");
        dummyPassenger.setPassport("AB1234567");

        Ticket firstLegTicket = new Ticket();
        firstLegTicket.setTicket_id(1);
        firstLegTicket.setClassVip(false);
        firstLegTicket.setPassenger(dummyPassenger);
        firstLegTicket.setFlight(flight1);
        firstLegTicket.setPrice(150);

        Ticket secondLegTicket = new Ticket();
        secondLegTicket.setTicket_id(2);
        secondLegTicket.setClassVip(false);
        secondLegTicket.setPassenger(dummyPassenger);
        secondLegTicket.setFlight(flight2);
        secondLegTicket.setPrice(200);

        tickets.add(firstLegTicket);
        tickets.add(secondLegTicket);
        TicketCollection.addTickets(tickets);

        // Step 4: Now chooseTicket should find the transfer route
        boolean fallbackResult = ticketSystem.chooseTicket("CityA", "CityB");

        assertTrue(fallbackResult, "Fallback transfer should be triggered when no direct flight exists");
    }

    @Test
    public void testChooseTicketHandlesNoException() {
        TicketSystem ticketSystem = new TicketSystem();

        assertDoesNotThrow(() -> {
            ticketSystem.chooseTicket("RandomCityX", "RandomCityY");
        }, "Choosing a ticket should not throw exceptions even if no valid flights exist");
    }

    @Test
    public void testChooseTicketFailsGracefullyIfCompletelyInvalid() {
        TicketSystem ticketSystem = new TicketSystem();

        boolean result = ticketSystem.chooseTicket("", "");

        assertFalse(result, "Choosing a ticket with empty cities should fail gracefully");
    }
}
