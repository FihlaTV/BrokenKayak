package backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * A class that represents an itinerary made up of flights
 * */
public class Itinerary implements Serializable {

    private static final long SerialVersionUID = 3L;

    private LinkedList<Flight> flights;
    private Double totalCost;
    private String totalDuration;

    /**
     * Creates a new Itinerary object.
     */
    public Itinerary() {
        flights = new LinkedList<Flight>();
        totalCost = (double) 0;
        totalDuration = "";
    }

    /**
     * Checks whether a flight can be added and if it can, adds the flight
     * to the itinerary. Also adds the cost of each flight to totalCost every time
     * a flight is added to the itinerary.
     *
     * @param flight flight to be added
     * @return true if the flight can be added and false otherwise
     */
    public boolean addFlight(Flight flight) {
        if (checkValid(flight)) {
            this.flights.add(flight);
            this.totalCost += Double.valueOf(flight.getPrice());
            return true;
        } return false;

    }

    /**
     * Checks if the flight to be added has the same origin as the destination
     * of the last flight in itinerary. Also checks if the flight to be added
     * is within 6 hours of the last flight.
     *
     * @param flight flight to be added
     * @return true if the flight is valid or false otherwise
     */

    private boolean checkValid(Flight flight) {
        if (flight.getCapacity() > 0) {
            if (flights.isEmpty()) {
                return true;
            } else {
                for (Flight f : flights) {
                    if (flight.getDestination().equals(f.getOrigin())) {
                        return false;
                    }
                }
                long time = calculateTimeInSeconds(flights.getLast().getArrivalDateTime(),
                        flight.getDepartureDateTime());
                if (time > 0 && time <= 21600000) { //checks if flight is greater than 6 hours
                    flight.setCapacity(flight.getCapacity() - 1);
                    return true;
                }
            }

        }else if (flight.getCapacity() == 0){
            return false;
        }
        return false;
    }

    /**
     * Returns the first flight in the linked list itinerary
     *
     * @return the first flight in the linked list itinerary
     */

    public Flight getFirst() {

        return this.flights.getFirst();
    }

    /**
     * Returns the total cost of the itinerary.
     *
     * @return the total cost of the itinerary
     */

    public double getTotalCost() {
        return this.totalCost;
    }

    /**
     * Calculates the total duration of the itinerary from the first flight's
     * departure time to the last flight's arrival time.
     *
     */
    public void calculateTotalDuration() {
    	if (! this.flights.isEmpty()) {
    		String time1 = this.flights.getFirst().getDepartureDateTime();
            String time2 = this.flights.getLast().getArrivalDateTime();
            this.totalDuration = convertTime(calculateTimeInSeconds(time1, time2));
    	} else {
    		this.totalDuration = null;
    	}
    }
    
    /**
     * Returns the total duration of the itinerary.
     *
     * @return the total duration of the itinerary.
     */
    public String getTotalDuration() {
        return this.totalDuration;
    }
    /**
     * Calculates and returns the time in seconds between time1 and time2.
     * @param time1
     *                the first time in the form yyyy-MM-dd HH:mm
     * @param time2
     *                the second time in the form yyyy-MM-dd HH:mm
     *
     * @return the time in seconds between time1 and time2.
     */
    public static long calculateTimeInSeconds(String time1, String time2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(time1);
            d2 = format.parse(time2);
            long diff = d2.getTime() - d1.getTime();
            return diff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * Converts and returns time in seconds to hours and minutes
     *
     * @param diff
     *              the difference between two given times in seconds
     *
     * @return the time converted from seconds to hours and minutes
     */

    public static String convertTime(long diff) {
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long diffHours = diff / (60 * 60 * 1000) % 24 + diffDays * 24;
        long diffMinutes = diff / (60 * 1000) % 60;
        if (diffHours <= 10 && diffMinutes <= 10) {
            return "0" + diffHours + ":0" + diffMinutes;
        } else if (diffMinutes <= 10) {
            return diffHours + ":0" + diffMinutes;
        } else if (diffHours <= 10){
            return "0" + diffHours + ":" + diffMinutes;
        } else {
            return diffHours + ":" + diffMinutes;
        }
    }
    /**
     * Returns a string representation of itinerary that includes each flight,
     * it's info and the total cost and duration of the itinerary.
     *
     * @return  a string representation of itinerary that includes each flight,
     * it's info and the total cost and duration of the itinerary.
     */

    public String toString() {
        String result = "-------- \n";
        for (Flight f: this.flights) {
            if (result != "") {
                result += "\n";
            }
            result += f.toString();
        } result += "\n" + "Total Time :" + this.totalDuration +  "\n" + "Total Cost of Itinerary: $" + String.format("%.2f", this.totalCost)+ "\n" ;
        return result;
    }
    
    
    /**
     * Returns a string includes each flight's info and the total cost and 
     * duration of the itinerary.
     *
     * @return  a string rincludes each flight's info and the total cost and 
     * duration of the itinerary.
     */

    public String toString2() {
        String result = "";
        for (Flight f: this.flights) {
            if (result != "") {
                result += "\n";
            }
            result += f.toString2();
        } result += "\n" + String.format("%.2f", this.totalCost) +  "\n" + this.totalDuration;
        return result;
    }
    
}