package exames.recurso20220224;

import graph.Graph;
import graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.*;

import static exames.recurso20220224.WorldCup.footWorldCup;
import static exames.recurso20220224.WorldCup.graphDiameter;
import static org.junit.jupiter.api.Assertions.*;

class WorldCupTest {

    @Test
    void footWorldCupTest() {

        List<Map.Entry<Integer, String>> paisesVencedores = new ArrayList<>();

        Map<Integer, String> tuples = new HashMap<>();
        tuples.put(1990, "Holanda" );
        tuples.put(1991, "Holanda" );
        tuples.put(1992, "Portugal");
        tuples.put(1970, "Holanda" );
        tuples.put(1971, "Portugal");

        paisesVencedores.addAll(tuples.entrySet());

        System.out.println(
                footWorldCup(paisesVencedores)
        );
    }


    @Test
    void graphDiameterTest() {

        Graph<String, Integer> g = new MatrixGraph<>(true);

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");
        g.addVertex("G");

        g.addEdge("A", "B", 1);
        g.addEdge("A", "C", 1);
        g.addEdge("A", "E", 1);

        g.addEdge("B", "A", 1);
        g.addEdge("B", "C", 1);

        g.addEdge("C", "B", 1);
        g.addEdge("C", "A", 1);
        g.addEdge("C", "F", 1);

        g.addEdge("D", "E", 1);

        g.addEdge("E", "A", 1);
        g.addEdge("E", "D", 1);
        g.addEdge("E", "F", 1);
        g.addEdge("E", "G", 1);

        g.addEdge("F", "E", 1);
        g.addEdge("F", "C", 1);

        g.addEdge("G", "E", 1);

        System.out.println(graphDiameter(g));
        assertEquals(3, graphDiameter(g));

    }
}