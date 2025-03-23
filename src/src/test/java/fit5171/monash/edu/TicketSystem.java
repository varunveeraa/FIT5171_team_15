package fit5171.monash.edu;

import java.util.Scanner;
import java.util.Optional;

public class TicketSystem {

    private Scanner in;

    public TicketSystem() {
        in = new Scanner(System.in);
    }

    /**
     * Allows a user to choose a ticket based on the departure and arrival cities.
     */
    public void chooseTicket(String city1, String city2) {
        Flight flight = FlightCollection.getFlightInfo(city1, city2);

        if (flight != null) {
            TicketCollection.getAllTickets();
            System.out.println("\nEnter the ID of the ticket you want to choose:");
            int ticketId = in.nextInt();
            buyTicket(ticketId);
        } else {
            Flight connectingFlight = FlightCollection.getFlightInfo(city2);
            if (connectingFlight == null) {
                System.out.println("No flights available.");
                return;
            }

            String connectCity = connectingFlight.getDepartFrom();
            Flight firstLegFlight = FlightCollection.getFlightInfo(city1, connectCity);

            if (firstLegFlight != null) {
                System.out.println("Transfer option available.");
                buyTicket(connectingFlight.getFlightID(), firstLegFlight.getFlightID());
            } else {
                System.out.println("No possible routes available.");
            }
        }
    }

    /**
     * Handles booking a single (direct) ticket.
     */
    public void buyTicket(int ticketId) {
        Ticket ticket = TicketCollection.getTicketInfo(ticketId);
        if (ticket == null) {
            System.out.println("This ticket does not exist.");
            return;
        }

        Flight flight = ticket.getFlight();
        if (flight == null || flight.getAvailableSeats() <= 0) {
            System.out.println("No seats available on this flight.");
            return;
        }

        Passenger passenger = collectPassengerDetails();
        if (passenger == null) return;

        System.out.println("Do you want to purchase?\n1 - YES, 0 - NO");
        if (in.nextInt() == 0) return;

        processTicketPurchase(ticket, flight, passenger);
    }

    /**
     * Handles booking a transfer (two tickets).
     */
    public void buyTicket(int firstTicketId, int secondTicketId) {
        Ticket firstTicket = TicketCollection.getTicketInfo(firstTicketId);
        Ticket secondTicket = TicketCollection.getTicketInfo(secondTicketId);

        if (firstTicket == null || secondTicket == null) {
            System.out.println("One or both tickets do not exist.");
            return;
        }

        Passenger passenger = collectPassengerDetails();
        if (passenger == null) return;

        System.out.println("Do you want to purchase?\n1 - YES, 0 - NO");
        if (in.nextInt() == 0) return;

        processTicketPurchase(firstTicket, firstTicket.getFlight(), passenger);
        processTicketPurchase(secondTicket, secondTicket.getFlight(), passenger);

        double totalPrice = firstTicket.getPrice() + secondTicket.getPrice();
        System.out.println("Your total bill: " + totalPrice);
        processPayment(passenger);
    }

    /**
     * Collects passenger details.
     */
    private Passenger collectPassengerDetails() {
        Passenger passenger = new Passenger();

        System.out.println("Enter your First Name: ");
        passenger.setFirstName(in.next());

        System.out.println("Enter your Second Name: ");
        passenger.setSecondName(in.next());

        System.out.println("Enter your Age: ");
        passenger.setAge(in.nextInt());

        System.out.println("Enter your Gender: ");
        passenger.setGender(in.next());

        System.out.println("Enter your E-mail Address: ");
        passenger.setEmail(in.next());

        System.out.println("Enter your Phone Number (+7): ");
        passenger.setPhoneNumber(in.next());

        System.out.println("Enter your Passport Number: ");
        passenger.setPassport(in.next());

        return passenger;
    }

    /**
     * Processes the ticket purchase.
     */
    private void processTicketPurchase(Ticket ticket, Flight flight, Passenger passenger) {
        ticket.setPassenger(passenger);
        ticket.setTicketStatus(true);

        Airplane airplane = flight.getAirplane();
        if (airplane == null) {
            System.out.println("Error: Airplane data missing.");
            return;
        }

        // Adjust seat count
        if (ticket.getClassVip()) {
            airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
        } else {
            airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
        }

        System.out.println("Ticket successfully booked! Total Price: " + ticket.getPrice());
        processPayment(passenger);
    }

    /**
     * Processes payment.
     */
    private void processPayment(Passenger passenger) {
        System.out.println("Enter your card number:");
        passenger.setCardNumber(in.next());

        System.out.println("Enter your security code:");
        passenger.setSecurityCode(in.nextInt());

        System.out.println("Payment successful!");
    }

    /**
     * Displays ticket details.
     */
    public void showTicket(Ticket ticket) {
        if (ticket != null) {
            System.out.println("You have bought a ticket for flight " +
                    ticket.getFlight().getDepartFrom() + " - " + ticket.getFlight().getDepartTo() + "\n\nDetails:");
            System.out.println(ticket.toString());
        } else {
            System.out.println("No ticket found.");
        }
    }
}
