public class Ticket {
    private String ticketID;
    private Trip trip;
    private String seatNumber;
    private int price;

    public Ticket(String ticketID, Trip trip, String seatNumber, int price) {
        this.ticketID = ticketID;
        this.trip = trip;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getTicketID() {
        return ticketID;
    }

    public Trip getTrip() {
        return trip;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getPrice() {
        return price;
    }

    public void printTicketDetails() {
        System.out.println("Tiket NTDuo Airport | Plane:" + trip.getFlightNumber());
        System.out.println("Departure: " + trip.getDepartureDate() + " at " + trip.getDepartureTime());
        System.out.println("Arrival: " + trip.getReturnDate() + " at " + trip.getReturnTime());
        System.out.println("Class: " + getSeatClass());
        System.out.println("Seat: " + seatNumber);
        System.out.println("Seri: " + ticketID);
        System.out.println("Price: " + price);
    }

    public String getSeatClass() {
        if (Integer.parseInt(seatNumber) <= 10) {
            return "First Class";
        } else if (Integer.parseInt(seatNumber) <= 50) {
            return "Business Class";
        } else {
            return "Economy Class";
        }
    }
}
