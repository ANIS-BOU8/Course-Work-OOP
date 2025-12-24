public class Trip {
    private String destination;
    private String departureDate;
    private String departureTime;
    private String returnDate;
    private String returnTime;
    private String tripID;
    private AirPlane plane;

    public Trip(String destination, String departureDate, String departureTime, String returnDate, String returnTime, String tripID, AirPlane plane) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.tripID = tripID;
        this.plane = plane;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public String getTripID() {
        return tripID;
    }

    public int getFlightNumber() {
        return plane.getFlightNumber();
    }

    public AirPlane getAirplane() {
        return plane;
    }

    public void printTripDetails() {
        System.out.println("Plane NTDuo Airport | Plane:" + plane.getFlightNumber());
        System.out.println("Departure: " + departureDate + " at " + departureTime);
        System.out.println("Arrival: " + returnDate + " at " + returnTime);
        System.out.println("Prices - First: 3000, Business: 1700, Economy: 1000");
    }
}
