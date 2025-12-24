import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Airport {
    private List<Trip> trips = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Method to create a new trip
    public void createNewTrip() {
        System.out.println("=== Create New Trip ===");
        System.out.print("Enter airplane number: ");
        int planeNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter departure location and time (e.g., Hanoi - Moscow, 2025-12-15 10:00): ");
        String departure = scanner.nextLine();
        System.out.print("Enter return location and time (e.g., Hanoi - Moscow, 2025-12-15 14:00): ");
        String returnDetails = scanner.nextLine();

        AirPlane plane = new AirPlane(planeNumber, "2025-12-15 10:00", "2025-12-15 14:00");
        Trip newTrip = new Trip(departure, "2025-12-15", "10:00", returnDetails, "14:00", "T" + (trips.size() + 1), plane);
        trips.add(newTrip);
        System.out.println("Trip created successfully!\n");
    }

    // Method to change a trip
    public void changeTrip() {
        System.out.println("=== Change Trip ===");
        if (trips.isEmpty()) {
            System.out.println("No trips available to change.");
            return;
        }

        System.out.println("Available Trips:");
        for (int i = 0; i < trips.size(); i++) {
            System.out.println((i + 1) + ". " + trips.get(i).getTripID());
        }

        System.out.print("Select trip to change (enter number): ");
        int tripIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (tripIndex < 0 || tripIndex >= trips.size()) {
            System.out.println("Invalid trip selection.");
            return;
        }

        Trip selectedTrip = trips.get(tripIndex);
        System.out.print("Enter new departure date and time (current: " + selectedTrip.getDepartureDate() + " " + selectedTrip.getDepartureTime() + "): ");
        String newDeparture = scanner.nextLine();
        System.out.print("Enter new return date and time (current: " + selectedTrip.getReturnDate() + " " + selectedTrip.getReturnTime() + "): ");
        String newReturn = scanner.nextLine();

        selectedTrip = new Trip(selectedTrip.getDestination(), newDeparture.split(" ")[0], newDeparture.split(" ")[1],
                newReturn.split(" ")[0], newReturn.split(" ")[1], selectedTrip.getTripID(), selectedTrip.getAirplane());
        trips.set(tripIndex, selectedTrip);

        System.out.println("Trip updated successfully!");
    }

    // Method to change a ticket
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

    // Method to buy a ticket
    public void buyTicket() {
        System.out.println("=== Buy Ticket ===");
        if (trips.isEmpty()) {
            System.out.println("No trips available.");
            return;
        }

        System.out.println("Available Trips:");
        for (int i = 0; i < trips.size(); i++) {
            System.out.println((i + 1) + ". " + trips.get(i).getTripID());
            trips.get(i).printTripDetails();
        }

        System.out.print("Select trip (enter number): ");
        int tripChoice = Integer.parseInt(scanner.nextLine());
        if (tripChoice < 1 || tripChoice > trips.size()) {
            System.out.println("Invalid trip number.");
            return;
        }

        Trip selectedTrip = trips.get(tripChoice - 1);
        System.out.println("Available seat classes:");
        System.out.println("1. First Class");
        System.out.println("2. Business Class");
        System.out.println("3. Economy Class");
        System.out.print("Select class: ");
        int classChoice = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter seat number (1-100): ");
        String seatNumber = scanner.nextLine();

        int price = 0;
        if (classChoice == 1) {
            price = 3000;
        } else if (classChoice == 2) {
            price = 800;
        } else {
            price = 450;
        }

        String ticketID = "T" + (tickets.size() + 1);
        Ticket newTicket = new Ticket(ticketID, selectedTrip, seatNumber, price);
        tickets.add(newTicket);

        System.out.println("Ticket purchased successfully!");
        System.out.println("Ticket Serial: " + ticketID);
        System.out.println("Class: " + (classChoice == 1 ? "First Class" : classChoice == 2 ? "Business Class" : "Economy Class"));
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: " + price);
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

    // Method to exit the system
    public void exitAirport() {
        System.out.println("Exiting Airport...");
    }
}
