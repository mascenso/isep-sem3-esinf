package examples.map;

import graph.*;
import graph.map.MapGraph;

import java.util.*;

/**
 *
 * @author DEI-ESINF
 *
 */
public class AirportNet {

    private static class Route {
        public final int passengers;
        public final double miles;

        public Route(int passengers, double miles) {
            this.passengers = passengers;
            this.miles = miles;
        }
    }

    final private Graph<String, Route> airports;

    public AirportNet(){
        
        airports = new MapGraph<>(true);
    }

    public void addAirport(String a){
        
        airports.addVertex(a);
    }

    public void addRoute(String a1, String a2, double miles, Integer passengers){
        airports.addEdge(a1, a2, new Route(passengers, miles));

    }

    public int keyAirport(String airport){
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String airportbyKey(int key){

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer trafficAirports(String airp1, String airp2){

        Integer npassengers = 0;

        Edge<String, Route> edge1 = airports.edge(airp1, airp2);
        Edge<String, Route> edge2 = airports.edge(airp2, airp1);

        if (edge1 != null) {
            npassengers += edge1.getWeight().passengers;
        }

        if (edge2 != null) {
            npassengers += edge2.getWeight().passengers;
        }

        return npassengers;
    }

    public Double milesAirports(String airp1, String airp2){

       Edge<String, Route> edge = airports.edge(airp1, airp2);

         if (edge == null) return null;

         return edge.getWeight().miles;
    }

    public Map<String,Integer> nroutesAirport(){

        Map<String, Integer> m = new HashMap<>();

        for (String airp : airports.vertices()) {
            int grau = airports.outDegree(airp)+airports.inDegree(airp);
            m.put(airp, grau);
        }
        return m;
    }

    public List<ArrayList<String>> airpMaxMiles( ){

        List<ArrayList<String>> airMaxMiles = new LinkedList<>();

        double maxMiles = Double.MIN_VALUE;
        for (Edge<String, Route> edge : airports.edges()) {

            if (edge.getWeight().miles >= maxMiles) {

                if (edge.getWeight().miles > maxMiles) {
                    maxMiles = edge.getWeight().miles;
                    airMaxMiles.clear();
                }

                airMaxMiles.add(new ArrayList<>(Arrays.asList(edge.getVOrig(), edge.getVDest())));
            }
        }
        return airMaxMiles;
    }

    public Boolean connectAirports(String airport1, String airport2){

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return airports.toString();
    }
}