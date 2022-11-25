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

    /**
     * Return the numeric key of a given airport
     * @param airport
     * @return
     */
    public int keyAirport(String airport){
        return  airports.key(airport);
    }


    /**
     * Return the airport with a specific numeric key
     * @param key
     * @return
     */
    public String airportbyKey(int key){
        return airports.vertex(key);
        //or:
       //ArrayList<String> vertices = airports.vertices();

       //if (key<0 || key>=vertices.size())
       //    return null;

       //return vertices.get(key);
    }

    /**
     * Return the traffic between two directly linked airports, note that the traffic (number of passangers
     * carried) between two airports may be different in the two connections
     * @param airp1
     * @param airp2
     * @return
     */
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

    /**
     * Return the miles between two directly linked airports
     * @param airp1
     * @param airp2
     * @return
     */
    public Double milesAirports(String airp1, String airp2){

       Edge<String, Route> edge = airports.edge(airp1, airp2);

         if (edge == null) return null;

         return edge.getWeight().miles;
    }

    /**
     * Return the number of routes origin and destination for all airports
     * @return
     */
    public Map<String,Integer> nroutesAirport(){

        Map<String, Integer> m = new HashMap<>();

        for (String airp : airports.vertices()) {
            int grau = airports.outDegree(airp)+airports.inDegree(airp);
            m.put(airp, grau);
        }
        return m;
    }

    /**
     * Return the number of routes origin and destination for all airports
     * @return
     */
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

    /**
     * Check whether two airports are reachable
     * @param airport1
     * @param airport2
     * @return
     */
    public Boolean connectAirports(String airport1, String airport2){
        //Quando precisar de verificar quais os vertices acessiveis atraves de um dado aeroporto,
        //é o algo DFS que deve ser utilizado
        if (!airports.validVertex(airport1) || !airports.validVertex(airport2)) return false;
        LinkedList<String> qairps = Algorithms.DepthFirstSearch(airports, airport1);
        return qairps.contains(airport2);
    }

    @Override
    public String toString() {
        return airports.toString();
    }
}