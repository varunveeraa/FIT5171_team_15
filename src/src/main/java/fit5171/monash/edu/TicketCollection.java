package fit5171.monash.edu;

import java.util.ArrayList;

public class TicketCollection {

    private static ArrayList<Ticket> tickets = new ArrayList<>(); // initialize properly

    // Return the list of tickets
    public static ArrayList<Ticket> getTickets() {
        return tickets;
    }

    // Add multiple tickets to the collection
    public static void addTickets(ArrayList<Ticket> tickets_db) {
        tickets.addAll(tickets_db);
    }

    // Display all available tickets
    public static void getAllTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No available tickets at the moment.");
            return;
        }

        System.out.println("Available Tickets:");
        for (Ticket ticket : tickets) {
            System.out.println(ticket.toString());
        }
    }

    // Search for a ticket by ID
    public static Ticket getTicketInfo(int ticket_id) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicket_id() == ticket_id) {
                return ticket;
            }
        }
        return null; // No matching ticket found
    }
}
