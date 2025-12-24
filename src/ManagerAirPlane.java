import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class ManagerAirPlane {
    private List<Trip> trips;
    private List<Ticket> tickets;
    private Scanner scanner = new Scanner(System.in);
    private Airport airport; // Add Airport instance

    // Constructor to initialize the lists for trips and tickets and the airport instance
    public ManagerAirPlane(Airport airport, List<Trip> trips, List<Ticket> tickets) {
        this.airport = airport;
        this.trips = trips;
        this.tickets = tickets;
    }

    // Method to create a flight (adds a trip to the list of trips)
    public void createFlight() {
        System.out.println("=== Create New Flight ===");
        airport.createNewTrip();  // This can call the createNewTrip method from Airport to add trips.
    }

    // Method to book a ticket (lets the user book a ticket for an available trip)
    public void bookTicket() {
        System.out.println("=== Book a Ticket ===");
        if (trips.isEmpty()) {
            System.out.println("No available trips to book.");
            return;
        }

        System.out.println("Available Trips:");
        for (int i = 0; i < trips.size(); i++) {
            trips.get(i).printTripDetails();
        }

        System.out.print("Select trip to book (enter number): ");
        int tripChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (tripChoice < 0 || tripChoice >= trips.size()) {
            System.out.println("Invalid trip selection.");
            return;
        }

        Trip selectedTrip = trips.get(tripChoice);

        // Selecting a seat and class
        System.out.println("Available seat classes:");
        System.out.println("1. First Class");
        System.out.println("2. Business Class");
        System.out.println("3. Economy Class");
        System.out.print("Select class (1-3): ");
        int classChoice = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter seat number (1-100): ");
        String seatNumber = scanner.nextLine();

        // Determining the price based on class
        int price = 0;
        if (classChoice == 1) {
            price = 3000;
        } else if (classChoice == 2) {
            price = 800;
        } else if (classChoice == 3) {
            price = 450;
        }

        // Creating ticket ID and storing the ticket
        String ticketID = "T" + (tickets.size() + 1);
        Ticket newTicket = new Ticket(ticketID, selectedTrip, seatNumber, price);
        tickets.add(newTicket);

        System.out.println("Ticket booked successfully!");
        System.out.println("Ticket Serial: " + ticketID);
        System.out.println("Class: " + (classChoice == 1 ? "First Class" : classChoice == 2 ? "Business Class" : "Economy Class"));
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: " + price);
    }

    // Method to look up booked tickets
    public void lookupTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets booked.");
        } else {
            System.out.println("=== Your Booked Tickets ===");
            int index = 1;
            for (Ticket ticket : tickets) {
                System.out.println(index + ". ");
                ticket.printTicketDetails();  // Print each ticket details
                index++;
            }
        }
    }

    // Method to change an existing ticket
    public void changeTicket() {
        System.out.println("=== Change Ticket ===");
        if (tickets.isEmpty()) {
            System.out.println("No tickets available to change.");
            return;
        }

        System.out.println("Available Tickets:");
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println((i + 1) + ". " + tickets.get(i).getTicketID());
        }

        System.out.print("Select ticket to change (enter number): ");
        int ticketIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (ticketIndex < 0 || ticketIndex >= tickets.size()) {
            System.out.println("Invalid ticket selection.");
            return;
        }

        Ticket selectedTicket = tickets.get(ticketIndex);
        System.out.print("Enter new seat number (current: " + selectedTicket.getSeatNumber() + "): ");
        String newSeat = scanner.nextLine();
        selectedTicket = new Ticket(selectedTicket.getTicketID(), selectedTicket.getTrip(), newSeat, selectedTicket.getPrice());

        tickets.set(ticketIndex, selectedTicket);
        System.out.println("Ticket updated successfully!");
    }

    // Method to print all tickets to file (or just print them)
    public void printToFile() {
        System.out.println("=== Print To File ===");
        if (tickets.isEmpty()) {
            System.out.println("No tickets to print.");
            return;
        }

        System.out.print("Enter filename to save: ");
        String filename = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (Ticket ticket : tickets) {
                writer.println("Tiket NTDuo Airport | Plane:" + ticket.getTrip().getFlightNumber());
                writer.println("Departure: " + ticket.getTrip().getDepartureDate() + " at " + ticket.getTrip().getDepartureTime());
                writer.println("Arrival: " + ticket.getTrip().getReturnDate() + " at " + ticket.getTrip().getReturnTime());
                writer.println("Class: " + ticket.getSeatClass());
                writer.println("Seat: " + ticket.getSeatNumber());
                writer.println("Seri: " + ticket.getTicketID());
                writer.println("Price: " + ticket.getPrice());
                writer.println("===============================");
            }
            System.out.println("Ticket printed successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error printing the ticket to file.");
        }
    }

    // Method to exit the system
    public void exitAirport() {
        System.out.println("Exiting Airport...");
    }
}
