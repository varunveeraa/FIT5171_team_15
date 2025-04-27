package fit5171.monash.edu;

import java.sql.*;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class TicketSystem {

    private Scanner in;
    private Passenger passenger;
    Ticket ticket;
    private Flight flight;

    public TicketSystem() {
        in = new Scanner(System.in);
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
    }

    /**
     * Method to choose a ticket.
     * Returns true if transfer fallback was triggered, false otherwise.
     */
    public boolean chooseTicket(String city1, String city2) {
        flight = FlightCollection.getFlightInfo(city1, city2);
        if (flight != null) {
            TicketCollection.getAllTickets();
            System.out.println("\nEnter the ID of the ticket you want to choose:");
            int ticket_id = in.nextInt();
            return buyTicket(ticket_id);
        }

        System.out.println("No direct flight found. Checking for transfer route...");
        Flight departToFlight = FlightCollection.getFlightInfo(city2);
        if (departToFlight == null) {
            System.out.println("No flights found to destination " + city2);
            return false;
        }

        String connectCity = departToFlight.getDepartFrom();
        Flight viaFlight = FlightCollection.getFlightInfo(city1, connectCity);

        if (viaFlight == null) {
            System.out.println("No connecting flight from " + city1 + " to " + connectCity);
            return false;
        }

        System.out.println("Transfer route found: " + city1 + " -> " + connectCity + " -> " + city2);
        int idFirst = departToFlight.getFlightID();
        int idSecond = viaFlight.getFlightID();
        return buyTicket(idFirst, idSecond);
    }

    /**
     * Method for buying a single (direct) ticket.
     * Returns true if booking successful, false otherwise.
     */
    public boolean buyTicket(int ticket_id) {
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);

        if (ticket_id <= 0 || validTicket == null) {
            System.out.println("This ticket does not exist or invalid ticket ID.");
            return false;
        } else {
            try {
                System.out.println("Enter your First Name: ");
                String firstName = in.next();
                passenger.setFirstName(firstName);

                System.out.println("Enter your Second Name:");
                String secondName = in.next();
                passenger.setSecondName(secondName);

                System.out.println("Enter your age:");
                int age = in.nextInt();
                passenger.setAge(age);

                System.out.println("Enter your gender:");
                String gender = in.next();
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address:");
                String email = in.next();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.next();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.next();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n1 - YES, 0 - NO");
                int purch = in.nextInt();
                if (purch == 0) {
                    return false;
                } else {
                    flight = FlightCollection.getFlightInfo(validTicket.getFlight().getFlightID());
                    int airplane_id = flight.getAirplane().getAirplaneID();
                    Airplane airplane = Airplane.getAirPlaneInfo(airplane_id);

                    ticket = validTicket;
                    ticket.setPassenger(passenger);
                    ticket.setTicketStatus(true);

                    if (ticket.getClassVip()) {
                        airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
                    } else {
                        airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
                    }
                }
                System.out.println("Your bill: " + ticket.getPrice() + "\n");

                System.out.println("Enter your card number:");
                String cardNumber = in.next();
                passenger.setCardNumber(cardNumber);

                System.out.println("Enter your security code:");
                int securityCode = in.nextInt();
                passenger.setSecurityCode(securityCode);

                return true;
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Method for buying a ticket with transfer (two tickets).
     * Returns true if booking successful, false otherwise.
     */
    public boolean buyTicket(int ticket_id_first, int ticket_id_second) {
        System.out.println(ticket_id_first + " " + ticket_id_second);
        Ticket validTicketFirst = TicketCollection.getTicketInfo(ticket_id_first);
        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_second);
        System.out.println("Processing...");

        if (validTicketFirst == null || validTicketSecond == null) {
            System.out.println("This ticket does not exist.");
            return false;
        } else {
            try {
                System.out.println("Enter your First Name: ");
                String firstName = in.next();
                passenger.setFirstName(firstName);

                System.out.println("Enter your Second Name:");
                String secondName = in.next();
                passenger.setSecondName(secondName);

                System.out.println("Enter your age:");
                int age = in.nextInt();
                passenger.setAge(age);

                System.out.println("Enter your gender:");
                String gender = in.next();
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address:");
                String email = in.next();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.next();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.next();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n1 - YES, 0 - NO");
                int purch = in.nextInt();
                if (purch == 0) {
                    return false;
                } else {
                    Flight flight_first = FlightCollection.getFlightInfo(validTicketFirst.getFlight().getFlightID());
                    Flight flight_second = FlightCollection.getFlightInfo(validTicketSecond.getFlight().getFlightID());

                    Airplane airplane_first = Airplane.getAirPlaneInfo(flight_first.getAirplane().getAirplaneID());
                    Airplane airplane_second = Airplane.getAirPlaneInfo(flight_second.getAirplane().getAirplaneID());

                    Ticket ticket_first = validTicketFirst;
                    Ticket ticket_second = validTicketSecond;

                    ticket_first.setPassenger(passenger);
                    ticket_first.setTicketStatus(true);
                    if (ticket_first.getClassVip()) {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }

                    ticket_second.setPassenger(passenger);
                    ticket_second.setTicketStatus(true);
                    if (ticket_second.getClassVip()) {
                        airplane_second.setBusinessSitsNumber(airplane_second.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_second.setEconomySitsNumber(airplane_second.getEconomySitsNumber() - 1);
                    }

                    ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());

                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    System.out.println("Enter your card number:");
                    String cardNumber = in.next();
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    int securityCode = in.nextInt();
                    passenger.setSecurityCode(securityCode);

                    return true;
                }
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Displays the purchased ticket details.
     */
    public void showTicket() {
        try {
            System.out.println("You have bought a ticket for flight " +
                    ticket.getFlight().getDepartFrom() + " - " + ticket.getFlight().getDepartTo() + "\n\nDetails:");
            System.out.println(ticket.toString());
        } catch (NullPointerException e) {
            System.out.println("No ticket purchased yet.");
        }
    }
}
