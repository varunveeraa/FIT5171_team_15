package fit5171.monash.edu;

import java.sql.*;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class TicketSystem {

    // Instance variables shared by both functionalities
    private Scanner in;
    private Passenger passenger;
    private Ticket ticket;
    private Flight flight;

    public TicketSystem() {
        in = new Scanner(System.in);
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
    }

    /**
     * Method to choose a ticket.
     * First it looks for a direct flight from city1 to city2.
     * If found, it displays all tickets and lets the user choose one.
     * Otherwise it searches for a transfer option.
     */
    public void chooseTicket(String city1, String city2) throws Exception {
        int counter = 1;
        int idFirst = 0;
        int idSecond = 0;

        // Search for a direct flight from city1 to city2
        flight = FlightCollection.getFlightInfo(city1, city2);

        if (flight != null) {
            // Display available tickets (assuming TicketCollection.getAllTickets() prints them)
            TicketCollection.getAllTickets();
            System.out.println("\nEnter the ID of the ticket you want to choose:");
            int ticket_id = in.nextInt();
            // Validate ticket (could add additional validation here)
            buyTicket(ticket_id);
        } else {
            // In case there is no direct flight
            // First, select a flight where depart_to = city2
            Flight departToFlight = FlightCollection.getFlightInfo(city2);
            // Then use its departFrom as a connection city and search for a flight from city1 to that city
            String connectCity = departToFlight.getDepartFrom();
            Flight flightConnectingTwoCities = FlightCollection.getFlightInfo(city1, connectCity);
            if (flightConnectingTwoCities != null) {
                System.out.println("There is a transfer option available. Way №" + counter);
                idFirst = departToFlight.getFlightID();
                idSecond = flightConnectingTwoCities.getFlightID();
            }
            counter++;
            buyTicket(idFirst, idSecond); // Purchase two tickets for the transfer option

            if (counter == 1) {
                System.out.println("There are no possible variants.");
            }
            return;
        }
    }

    /**
     * Method for buying a single (direct) ticket.
     */
    public void buyTicket(int ticket_id) throws Exception {
        // Select ticket by its ID
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);

        // Check if the ticket exists (if validTicket is null, then it does not exist)
        if (validTicket == null) {
            System.out.println("This ticket does not exist.");
            return;
        } else {
            int flight_id = validTicket.getFlight().getFlightID();
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
                    return;
                } else {
                    flight = FlightCollection.getFlightInfo(flight_id);
                    int airplane_id = flight.getAirplane().getAirplaneID();
                    Airplane airplane = Airplane.getAirPlaneInfo(airplane_id);

                    // Re-fetch the ticket info (if needed)
                    ticket = TicketCollection.getTicketInfo(ticket_id);
                    ticket.setPassenger(passenger);
                    ticket.setTicket_id(ticket_id);
                    ticket.setFlight(flight);
                    ticket.setPrice(ticket.getPrice());
                    ticket.setClassVip(ticket.getClassVip());
                    ticket.setTicketStatus(true);

                    // Adjust available seats
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
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
            }
        }
    }

    /**
     * Method for buying a ticket when a transfer (two tickets) is required.
     */
    @SuppressWarnings("null")
    public void buyTicket(int ticket_id_first, int ticket_id_second) throws Exception {
        System.out.println(ticket_id_first + " " + ticket_id_second);
        Ticket validTicketFirst = TicketCollection.getTicketInfo(ticket_id_first);
        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_second);
        System.out.println("Processing...");

        // Check if both tickets exist
        if (validTicketFirst == null || validTicketSecond == null) {
            System.out.println("This ticket does not exist.");
            return;
        } else {
            int flight_id_first = validTicketFirst.getFlight().getFlightID();
            int flight_id_second = validTicketSecond.getFlight().getFlightID();
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
                    return;
                } else {
                    // Process first flight/ticket
                    Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);
                    int airplane_id_first = flight_first.getAirplane().getAirplaneID();
                    Airplane airplane_first = Airplane.getAirPlaneInfo(airplane_id_first);

                    // Process second flight/ticket
                    Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);
                    int airplane_id_second = flight_second.getAirplane().getAirplaneID();
                    Airplane airplane_second = Airplane.getAirPlaneInfo(airplane_id_second);

                    Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);
                    Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                    ticket_first.setPassenger(passenger);
                    ticket_first.setTicket_id(ticket_id_first);
                    ticket_first.setFlight(flight_first);
                    ticket_first.setPrice(ticket_first.getPrice());
                    ticket_first.setClassVip(ticket_first.getClassVip());
                    ticket_first.setTicketStatus(true);
                    if (ticket_first.getClassVip()) {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }
                    System.out.println("--*-*-");

                    ticket_second.setPassenger(passenger);
                    ticket_second.setTicket_id(ticket_id_second);
                    ticket_second.setFlight(flight_second);
                    ticket_second.setPrice(ticket_second.getPrice());
                    ticket_second.setClassVip(ticket_second.getClassVip());
                    ticket_second.setTicketStatus(true);
                    if (ticket_second.getClassVip()) {
                        airplane_second.setBusinessSitsNumber(airplane_second.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_second.setEconomySitsNumber(airplane_second.getEconomySitsNumber() - 1);
                    }
                    System.out.println("--*-*-");

                    // Combine the prices of both tickets for the final bill
                    ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());
                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    System.out.println("Enter your card number:");
                    String cardNumber = in.next();
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    int securityCode = in.nextInt();
                    passenger.setSecurityCode(securityCode);
                }
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
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
            return;
        }
