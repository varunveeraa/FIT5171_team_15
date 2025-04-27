package fit5171.monash.edu;

import java.util.ArrayList;

public class FlightCollection {

    private static ArrayList<Flight> flights = new ArrayList<>(); // initialized properly

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static void addFlight(Flight flight) {
        flights.add(flight);
    }

    public static void addFlights(ArrayList<Flight> flightsToAdd) {
        flights.addAll(flightsToAdd);
    }

    public static Flight getFlightInfo(String city1, String city2) {
        for (Flight flight : flights) {
            if (flight.getDepartFrom().equalsIgnoreCase(city1) &&
                    flight.getDepartTo().equalsIgnoreCase(city2)) {
                return flight; // Found direct flight
            }
        }
        return null; // No direct flight found
    }

    public static Flight getFlightInfo(String city) {
        for (Flight flight : flights) {
            if (flight.getDepartTo().equalsIgnoreCase(city)) {
                return flight; // Found a flight that arrives at 'city'
            }
        }
        return null; // No flight found
    }

    public static Flight getFlightInfo(int flight_id) {
        for (Flight flight : flights) {
            if (flight.getFlightID() == flight_id) {
                return flight; // Found by ID
            }
        }
        return null; // No flight found
    }
}
