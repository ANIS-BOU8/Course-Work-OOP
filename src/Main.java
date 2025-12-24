import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airport airport = new Airport();

        int choice = -1;
        while (choice != 0) {
            // Menu options and prompts
            System.out.println("=== Welcome to NTDuo Airport ===");
            System.out.println("Please choose an option:");
            System.out.println("1. Create a new Trip");
            System.out.println("2. Change a Trip");
            System.out.println("3. Buy a ticket");
            System.out.println("4. Change a ticket");
            System.out.println("5. Print To File");
            System.out.println("6. Look up your booked tickets");
            System.out.println("0. Exit Airport");
            System.out.print("Your choice: ");
            
            // Input validation for non-numeric entries
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Use nextLine to consume the newline character
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;  // Skip rest of the loop and ask for input again
            }

            switch (choice) {
                case 1:
                    airport.createNewTrip();
                    break;
                case 2:
                    airport.changeTrip();
                    break;
                case 3:
                    airport.buyTicket();
                    break;
                case 4:
                    airport.changeTicket();
                    break;
                case 5:
                    airport.printToFile();
                    break;
                case 6:
                    airport.lookupTickets();
                    break;
                case 0:
                    airport.exitAirport();
                    break;
                default:
                    System.out.println("Please enter a valid number!");
            }
        }
        scanner.close();  // Close the scanner at the end
    }
}
