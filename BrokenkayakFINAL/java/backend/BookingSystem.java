package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * The BookingSystem class represents BookingSystem type object. It is the main object
 * of the program and runs the interactive system.
 */
public class BookingSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * A Map stores all flights, editable by the Admin
     */
    public static Map<String, Flight> flightMap = new HashMap<String, Flight>();

    /**
     * A Map stores users, passwords and their login type
     * 1 = admin; 2 = client
     */
    public static Map<String, UserID> userIDMap = new HashMap<String, UserID>();

    /**
     * A Map stores users.
     */
    public static Map<String, User> userMap = new HashMap<String, User>();

    /**
     * A Map stores cities.
     */
    public static Map<String, Node> nodeMap = new HashMap<String, Node>();

    /**
     * A Map that temporarily stores searched itinerary results.
     */
    public static Map<Integer, Itinerary> itineraryMap = new HashMap<Integer, Itinerary>();

    /**
     * A ArrayList stores sorted itineraries.
     */
    public static ArrayList<Itinerary> al = new ArrayList<Itinerary>();

    /**
     * Stores client information to the userMap from the file at the given path.
     *
     * @param file a csv file which contains clients' information.
     * @throws FileNotFoundException
     */
    public static void uploadClients(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        String[] record;
        while (scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            User user = new User(record[0], record[1], record[2],
                    record[3], record[4], record[5]);
            if (!userMap.containsKey(record[2])) {
                userMap.put(record[2], user);
            } else {
                System.out.println(record[2] + " is already exist");
            }
            userMap.put(record[2], user);
        }
        scanner.close();
    }

    /**
     * Stores flight information to the flightMap from the file at the given path.
     *
     * @param file a csv file which contains flights' information.
     * @throws FileNotFoundException
     */
    public static void uploadFlights(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        String[] record;
        while (scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            if (record.length == 8) {
                Flight flight = new Flight(record[0], record[1], record[2],
                        record[3], record[4], record[5], record[6], record[7]);
                //create new nodes for origin or destination for the flight
                createNode(record[5]);
                createNode(record[4]);
                Node node = nodeMap.get(record[4]);
                node.addEdge(record[5], flight);
            }
        }
        scanner.close();
    }

    //helper for creating new city node
    public static void createNode(String place) {
        if (!nodeMap.containsKey(place)) {
            Node node = new Node(place, new HashMap<String, Set<Flight>>());
            nodeMap.put(place, node);
        }
    }

    /**
     * Searches possible itineraries based on the date, origin and destination which
     * the client inputs. And stores them to itineraryMap temporarily.
     *
     * @param date        a departure date.
     * @param origin      a itinerary origin.
     * @param destination a itinerary destination.
     */
    public static void searchItinerary(String date, String origin, String destination) {
        itineraryMap.clear();
        al.clear();
        int i = 1;
        if (origin != destination) {
            Map<String, Set<Flight>> map = nodeMap.get(origin).getMap();
            for (String d : map.keySet()) {
                for (Flight f : map.get(d)) {
                    if (date.equals(f.getDepartureDateTime().split(" ")[0])) {
                        Itinerary it = new Itinerary();
                        it.addFlight(f);
                        if (d.equals(destination)) {
                            it.calculateTotalDuration();
                            if (it.getTotalDuration() != null) {
                            	itineraryMap.put(i, it);
                                i++;
                            }
                        } else {
                            searchHelper(d, destination, it, i);
                        }
                    }
                }
            }
        } else {
            System.out.println("Origin and destination are same!");
        }
    }

    //helper for searching recursively
    private static String searchHelper(String origin, String destination, Itinerary it, int i) {
        Map<String, Set<Flight>> map = nodeMap.get(origin).getMap();
        String result = "";
        for (String d : map.keySet()) {
            for (Flight f : map.get(d)) {
                Itinerary it1 = new Itinerary();
                it1 = it;
                if (it1.addFlight(f)) {
                    if (d.equals(destination)) {
                        it1.calculateTotalDuration();
                        if (it.getTotalDuration() != null) {
                        	itineraryMap.put(i, it1);
                            result += it1.toString();
                            i++;
                        }
                    } else {
                        searchHelper(d, destination, it1, i);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Sorts searched itinerary result from itineraryMap and store them to linkedLs
     * sorted by time or cost.
     *
     * @param input "time" or "cost".
     */
    public static ArrayList<Itinerary> sortResult(String input) {
        for (int i = 1; i <= itineraryMap.size(); i++) {
            Itinerary it = itineraryMap.get(i);
            if (al.isEmpty()) {
                al.add(it);

            } else {
                for (int j = 0; j <= al.size() - 1; j++) {
                    if (input.equals("cost")) {
                        if (it.getTotalCost() <= ((Itinerary) al.get(j)).getTotalCost() && !al.contains(it)) {
                            al.add(j, it);

                        }
                    } else {
                        String[] time1 = it.getTotalDuration().split(":");
                        String[] time2 = ((Itinerary)al.get(j)).getTotalDuration().split(":");
                        if (!al.contains(it) && (Integer.valueOf(time1[0]) <
                                Integer.valueOf(time2[0]) || (Integer.valueOf(time1[0])
                                == Integer.valueOf(time2[0]) &&
                                Integer.valueOf(time1[1]) <= Integer.valueOf(time2[1])))) {
                            al.add(j, it);
                        }
                    }
                } if (!al.contains(it)) {
                    al.add(it);

                }
            }
        } return al;
    }

    /**
     * Prints all stored clients' information leading by its email.
     */
    public void printAllClients() {
        for (String i: userMap.keySet()) {
            User user = userMap.get(i);
            System.out.println(user.getEmail() + ": " + user.toString());
        }
    }
}
