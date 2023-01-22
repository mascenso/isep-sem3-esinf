package Graphs.PL_Graphs.examples.matrix;

import Graphs.PL_Graphs.graph.Algorithms;
import Graphs.PL_Graphs.graph.Graph;
import Graphs.PL_Graphs.graph.matrix.MatrixGraph;

import java.util.*;

public class LabyrinthCheater {
    private static class Room{
        public final String name;
        public final boolean hasExit;
        public Room(String n, boolean exit){
            name = n;
            hasExit = exit;
        }

        /*
         * Implementation of equals
         * Comparison of rooms is by name only
         */
        @Override
        public boolean equals(Object other){
            if(!(other instanceof Room)) return false;
            return name.equals(((Room)other).name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, hasExit);
        }
    }

    /**
     * esta classe existe apenas por uma questão semântica....
     */
    private static class Door{
    }

    private final Graph<Room, Door> labyrinth;

    public LabyrinthCheater(){
        labyrinth = new MatrixGraph<>(false);
    }

    /**
     * Inserts a new room in the map
     *
     * @param name the room name
     * @param hasExit wheather the room has an exit
     * @return false if city exists in the map
     */

    public boolean insertRoom(String name, boolean hasExit){
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Inserts a new door in the map
     * fails if room does not exist or door already exists
     *
     * @param from room
     * @param to room
     *
     * @return false if a room does not exist or door already exists between rooms
     */

    public boolean insertDoor(String from, String to){
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns a list of rooms which are reachable from one room
     *
     * @param roomName room
     *
     * @return list of room names or null if room does not exist
     */

    public Collection<String> roomsInReach(String roomName){
        //visita em profundidade, ou visita em largura
        //dão nos uma sala, verifiamos se é valida, e percorremos todos à procura

        Room room = new Room (roomName, false);

        if (!labyrinth.validVertex(room))
            return null;

        LinkedList<Room> lst = Algorithms.DepthFirstSearch(labyrinth, room);

        ArrayList<String> lstrooms = new ArrayList<>();
        for (Room r : lst)
            lstrooms.add(r.name);

        return lstrooms;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the nearest room with an exit
     *
     * @param roomName from room
     *
     * @return room name or null if from room does not exist or there is no reachable exit
     */

    public String nearestExit(String roomName){
        //a pesquisa em largura, do inicio ao fim, vai dar a sala mais proxima

        Room room = new Room (roomName, false);

        if (!labyrinth.validVertex(room))
            return null;

        //este algoritmo ta a visitar todos os nós... neste caso podia parar q dava mais jjeito
        LinkedList<Room> lst = Algorithms.BreadthFirstSearch(labyrinth, room);

        for (Room r : lst)
            if (r.hasExit)
                return r.name;

        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the shortest path to the nearest room with an exit
     *
     * @param from from room
     *
     * @return list of room names or null if from room does not exist or there is no reachable exit
     */
    public LinkedList<String> pathToExit(String from){

        String nearExit = nearestExit(from);

        if (nearExit == null)
            return null;

        LinkedList<Room> shortPath =
                BFSshortestPath(new Room(from, false), new Room(nearExit, true));

        LinkedList <String> roomsToExit = new LinkedList<>();

        for (Room r : shortPath)
            roomsToExit.add(r.name);

        return roomsToExit;
       // throw new UnsupportedOperationException("Not supported yet.");
    }


    private LinkedList<Room> BFSshortestPath(Room rOrig , Room rDest){

        int numVertices = labyrinth.numVertices();

        boolean[] visited = new boolean[numVertices]; //default false

        int[] pred = new int[numVertices]; //default 0
        for (int i = 0; i < labyrinth.numVertices(); i++) {
            pred[i] = -1;
        }


        LinkedList<Room> qaux = new LinkedList<>();
        qaux.add(rOrig);

        boolean ends = false;

        while (!qaux.isEmpty() && !ends){
            rOrig = qaux.remove();
            int vKey = labyrinth.key(rOrig);
            visited[vKey] = true;

            for (Room rAdj : labyrinth.adjVertices(rOrig)){

                int adjKey = labyrinth.key(rAdj);

               if(!visited[adjKey]){
                   qaux.add(rAdj);
                     pred[adjKey] = vKey;

                     if (rAdj.equals(rDest))
                         ends = true;
               }
            }
        }

        //ninguem usa isto na pratica
        //isto é meramente curiosidade academica
        //o dijkstra é muito melhor
        //e ha algoritmos melhores ainda

        //metodo que analisa o vetor do final p inicio.. e faz uma inversao.. tipo dijkstra
        //ver depois
    return pathFromArray(pred, rDest);
    }

    private LinkedList<Room> pathFromArray(int[] pred, Room rDest) {
        LinkedList<Room> path = new LinkedList<>();

        int vKey = labyrinth.key(rDest);
        path.addFirst(rDest);

        while (pred[vKey] != -1){
            vKey = pred[vKey];
            path.addFirst(labyrinth.vertex(vKey));
        }

        return path;
    }

}
