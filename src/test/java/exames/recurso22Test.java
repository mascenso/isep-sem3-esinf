package exames;

import Trees.TREE;
import graph.Graph;
import graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static exames.Recurso22.footWorldCup;

class Recurso22Test {

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

        System.out.println(Recurso22.graphDiameter(g));
        Assertions.assertEquals(3, Recurso22.graphDiameter(g));
    }

    /**
    3. Dada uma árvore binária de pesquisa, implemente o método na classe genérica TREE<E> que devolva os
    caminhos do nó raiz para todos os nós terminais. Para a árvore abaixo deve devolver os caminhos:
    allPaths = [[20, 15, 10, 8, 7],
            [20, 15, 10, 13],
            [20, 15, 17],
            [20, 40, 30],
            [20, 40, 50]]
     **/
    @Test
    public void allPathsTest()
    {

        //construct the tree = enunciado
        //int[] preOrder = new int[]{20, 15, 10, 8, 7, 13, 17, 40, 30, 50 };
        //int[] inOrder  = new int[]{7, 8, 13, 10, 17, 15, 30, 50, 40, 20 };

            TREE<Integer> tree = new TREE<>();

        tree.insert(20);
        tree.insert(15);
        tree.insert(40);
        tree.insert(10);
        tree.insert(17);
        tree.insert(30);
        tree.insert(50);
        tree.insert(8);
        tree.insert(13);
        tree.insert(7);

        System.out.println(tree);
    }
}