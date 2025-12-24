public class AirPlane {
    private int flightNumber;
    private String departureTime;
    private String arrivalTime;
    private boolean[] seats = new boolean[100]; // 100 seats

    public AirPlane(int flightNumber, String departureTime, String arrivalTime) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber >= 0 && seatNumber < seats.length) {
            seats[seatNumber] = true;
        }
    }

    public void printFlightDetails() {
        System.out.println("Plane NTDuo Airport | Plane:" + flightNumber);
        System.out.println("Departure: " + departureTime);
        System.out.println("Arrival: " + arrivalTime);
    }
}
