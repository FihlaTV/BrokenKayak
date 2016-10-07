package backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node implements Serializable {

    private static final long SerialVersionUID = 4L;

    /** The map stored in this Node. */
    private HashMap<String, Set<Flight>> map;

    /**
     * Creates a new Node with the given placeName(destination) and map.
     *
     * @param placeName a destination.
     * @param map a map of flights to this destination.
     */
    public Node(String placeName, HashMap<String, Set<Flight>> map) {
        this.map = new HashMap<String, Set<Flight>>();
    }

    /**
     * Adds an edge for this node. Stores given destination as the key in the map
     * and set of flights to this destination as values.
     *
     * @param name a destination.
     * @param flight a flight to this destination.
     */
    public void addEdge(String name, Flight flight) {
        if(map.containsKey(name)) {
            Set<Flight> flights = map.get(name);
            int result = 0;
            for (Flight f: flights) {
                if (f.getNumber().equals(flight.getNumber())) {
                    result = 1;
                }
            }
            if (result == 0) {
                flights.add(flight);
                map.remove(name);
                map.put(name, flights);
            }
        } else {
            Set<Flight> flights = new HashSet<Flight>();
            flights.add(flight);
            this.map.put(name, flights);
        }
    }

    /**
     * Returns the map of this node.
     *
     * @return the map.
     */
    public Map<String, Set<Flight>> getMap() {
        return this.map;
    }
}