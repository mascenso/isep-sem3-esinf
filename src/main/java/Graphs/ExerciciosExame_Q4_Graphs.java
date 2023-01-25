package Graphs;

import Graphs.PL_Graphs.graph.Graph;
import Graphs.PL_Graphs.graph.matrix.MatrixGraph;

import static Graphs.PL_Graphs.graph.Algorithms.minDistGraph;

public class ExerciciosExame_Q4_Graphs {

    /**
     * Recurso 2022
     * 4. Considere uma rede social em que dois utilizadores amigos têm ligação direta entre eles. Admita que
     esta rede de amizades é conectada e as ligações entre os amigos é bidirecional e unitária. Elabore um
     método que devolva o número de ligações entre os dois utilizadores mais afastados nesta rede (diâmetro
     do grafo).
     public Integer graphDiameter (Graph<User,Integer> g)
     **/
    public static <V> Integer graphDiameter (Graph<String,Integer> g){
        MatrixGraph<String, Integer> graph = minDistGraph(g, Integer::compareTo, Integer::sum);

        System.out.println(graph);

        int diameter = 0;

        for (int i = 0; i < graph.numVertices(); i++){
            for (int j = i; j < graph.numVertices(); j++) {
                if (graph.edge(i,j) != null && graph.edge(i, j).getWeight().compareTo(diameter) > 0 )
                    diameter = graph.edge(i, j).getWeight();
            }
        }
        return diameter;
    }
}
