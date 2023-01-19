package exames;

import graph.Graph;
import graph.matrix.MatrixGraph;

import java.util.*;

import static graph.Algorithms.minDistGraph;
import static java.util.Comparator.reverseOrder;


public class Recurso22 {

    /**
     1. Considere uma lista de objetos Map.Entry<Integer, String> que contem os países vencedores da Football
     World Cup por edição, por exemplo: [(1930,"Uruguay"), (1934,"Italy"), (1938,"Italy"), (1950,"Uruguay"),...,(2006,
     "Italy"), (2010,"Spain"), ...]. Desenvolva um método que devolve num map cada país e a respetiva lista dos
     anos em que foi vencedor da taça. A lista dos anos deve estar ordenada decrescentemente. Para a lista de
     objetos Map.Entry acima deve devolver Uruguay -> [1950, 1930]; Italy -> [2006, 1938, 1934]; Spain ->
     [2010].

     public static Map<String, List<Integer>> footWorldCup
     (List<Map.Entry<Integer,String>> lf)
     **/
    public static Map<String, List<Integer>> footWorldCup(List<Map.Entry<Integer, String>> paisesVencedores){

        Map<String, List<Integer>> invertedMap = new HashMap<>();

        for (Map.Entry<Integer, String> map : paisesVencedores){ //para cada um dos pares

            Integer mapYear = map.getKey();
            String mapCountry = map.getValue();

            if ( invertedMap.containsKey(mapCountry) ){
               List<Integer> a = invertedMap.get(mapCountry);
               a.add(map.getKey());
               a.sort(reverseOrder());
               invertedMap.put(mapCountry, a);
            }
            else{
                //criar uma lista com o ano, do maior para o menor
                List<Integer> a = new ArrayList<>();
                a.add(mapYear);
                invertedMap.put(mapCountry, a);
            }
        }

        return invertedMap;
    }


    /**4. Considere uma rede social em que dois utilizadores amigos têm ligação direta entre eles. Admita que
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