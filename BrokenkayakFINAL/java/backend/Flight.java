package backend;

import java.io.Serializable;

/** A class that represents flights which are to be stored in Itinerary. */
public class Flight implements Comparable<Flight>, Serializable {

    private static final long SerialVersionUID = 2L;

    /** Flight number of Flight. */
    private String number;

    /** Flight departure date and time in YYYY-MM-DD HH:MM. */
    private String departureDateTime;

    /** Flight arival date and time in YYYY-MM-DD HH:MM. */
    private String arrivalDateTime;

    /** Flight airline e.g. Air Canada. */
    private String airline;

    /** City origin of Flight e.g. Toronto. */
    private String origin;

    /** City destination of Flight e.g. New York City. */
    private String destination;

    /** Cost of the flight going from pt A to B. */
    private String price;

    /** Travel time of the flight.  */
    private String travelTime;

    private int capacity;

    /**
     * Creates a new Flight with the following info:
     * @param flight number of Flight.
     * @param departure date and time of Flight (YYYY-MM-DD HH:MM).
     * @param arrival date time of Flight (YYYY-MM-DD HH:MM).
     * @param airline of Flight.
     * @param city origin of Flight.
     * @param city destination of Flight.
     * @param price/cost of Flight.
     * @param capacity total capacity for the flight.
     */
    // NOTE: travelTime is not included in the Flight constructor and the main toString
    // method however it can be returned through its getter method "calculateTravelTime"
    // despite it being a required feature in the 'Featured List'. The total travel time
    // for multiple flights is calculated under Itinerary.
    // travelTime has been excluded in order to pass JUNIT "SampleTests".
    public Flight(String number, String departureDateTime, String arrivalDateTime,
                  String airline, String origin, String destination, String price, String capacity) {
        super();
        this.number = number;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.capacity = Integer.valueOf(capacity);
    }

    // Getters and Setters for Flight
    /**
     * Returns the flight number of Flight.
     * @return the flight number of Flight.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the flight number of Flight to number.
     * @param the new flight number of Flight.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Returns the departure date and time of Flight.
     * @return departure date and time of Flight.
     */
    public String getDepartureDateTime() {

        return departureDateTime;
    }

    /**
     * Sets the departure date and time of Flight to departureDateTime.
     * @param the new departure date and time of Flight.
     */
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    /**
     * Returns the arrival date and time of Flight to arrivalDateTime.
     * @return the arrival date and time of Flight.
     */
    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    /**
     * Sets the arrival date and time of Flight to arrivalDateTime.
     * @param the new arrival date and time of Flight.
     */
    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    /**
     * Returns the Airline of Flight.
     * @return the airline of Flight.
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Sets the Airline of Flight.
     * @param the airline of Flight.
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Returns the city origin of Flight.
     * @return the city origin of Flight.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the city origin of Flight.
     * @param the city origin of Flight.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Returns the city destination of Flight.
     * @return the city destination of Flight.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the city destination of Flight.
     * @param the city destination of Flight.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns the price of Flight.
     * @return the price of Flight.
     */
    public double getPrice() {
        return Double.parseDouble(price);
    }

    /**
     * Sets the capacity of Flight.
     * @param the capacity of Flight.
     */
    public void setCapacity(int capacity) {
        this.capacity = Integer.valueOf(capacity);
    }

    /**
     * Returns the capacity of Flight.
     * @return the capacity of Flight.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the price of Flight.
     * @param the price of Flight.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Calculates and returns the travel time of the flight.
     * @return the travel time of the flight.
     */

    public String calculateTravelTime() {
        String time1 = this.getDepartureDateTime();
        String time2 = this.getArrivalDateTime();
        this.travelTime = Itinerary.convertTime(Itinerary.calculateTimeInSeconds(time1, time2));
        return travelTime;
    }

    /**
     *  Compares flight of number of this vs other.
     *  @param other flight.
     */
    @Override
    public int compareTo(Flight other) {
        return Integer.valueOf(number) - Integer.valueOf(other.number);
    }



    /**
     *  toString method of flight.
     *  @param the string representation of Flight including all variables.
     */
    @Override
    public String toString() {
        return number + "," + departureDateTime + "," + arrivalDateTime + "," + airline
                + "," + origin + "," + destination + "," +price;
    }

    /**
     *  toString method of flight.
     *  @param the string representation of Flight excluding price.
     */
    public String toString2() {
        return number + "," + departureDateTime + "," + arrivalDateTime + "," + airline
                + "," + origin + "," + destination;
    }
}