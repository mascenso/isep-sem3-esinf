package exames.Recurso22;

import PL_Graphs.graph.Graph;
import PL_Graphs.graph.matrix.MatrixGraph;

import java.util.*;

import static PL_Graphs.graph.Algorithms.minDistGraph;
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

    /**
     * 2. Seja o seguinte método:
     * Analise o método quanto à sua complexidade temporal, utilizando a notação Big-Oh. Justifique
     *     adequadamente.
     */

    public static void complexity(int n)
    {
        long count = 0;
        for (int i = 1; i < n; i=i*2) // O(log n)
        {
            for (int j = n; j > 1; j=j/2) // O(log n)
            {
                // something O(1)
            }
        }
    } // Total: log n * log n  = log^2 n;

    /**
     * 3. Dada uma árvore binária de pesquisa, implemente o método na classe genérica TREE<E> que devolva os
     * caminhos do nó raiz para todos os nós terminais.
     */
    public static class TREE<E extends Comparable<E>> extends BST<E> {

        public void allPathsFromRootToLeaves(List<List<E>> allPaths){
            allPaths.clear();
            allPathsFromRootToLeaves(root, allPaths);
        }

        private void allPathsFromRootToLeaves(Node<E> node, List<List<E>> allPaths){
            Stack<E> stack = new Stack<>(); // Could use a LinkedList also, as a stack
            inOrderSubtree(node, stack, allPaths);
        }

        private void inOrderSubtree(Node<E> node, Stack<E> stack, List<List<E>> allPaths) {
            if (node == null) return;
            stack.push(node.getElement()); // stackLL.offerLast(node.getElement());

            inOrderSubtree(node.getLeft(), stack, allPaths);
            if (node.getLeft() == null && node.getRight() == null) {
                List<E> path = new ArrayList<>(stack);
                allPaths.add(path);
            }
            inOrderSubtree(node.getRight(), stack, allPaths);
            stack.pop(); // stackLL.removeLast();
        }
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

    public static void main(String[] args) {
        System.out.println("Exame 2022-02-24 - Recurso 22\n");

        System.out.println(" Questão 1");
        Map.Entry<Integer, String> a = new AbstractMap.SimpleEntry<>(1930, "Uruguay");
        Map.Entry<Integer, String> b = new AbstractMap.SimpleEntry<>(1934, "Italy");
        Map.Entry<Integer, String> c = new AbstractMap.SimpleEntry<>(1938, "Italy");
        Map.Entry<Integer, String> d = new AbstractMap.SimpleEntry<>(1950, "Uruguay");
        Map.Entry<Integer, String> f = new AbstractMap.SimpleEntry<>(1958, "Brazil");
        Map.Entry<Integer, String> g = new AbstractMap.SimpleEntry<>(1962, "Brazil");
        Map.Entry<Integer, String> h = new AbstractMap.SimpleEntry<>(1966, "England");
        Map.Entry<Integer, String> i = new AbstractMap.SimpleEntry<>(1970, "Brazil");

        System.out.println(footWorldCup(Arrays.asList(a,b,c,d,f,g,h,i)));

        System.out.println();

        System.out.println(" Questão 3");

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

        System.out.printf(tree.toString());

        List<List<Integer>> allPaths = new ArrayList<>();
        tree.allPathsFromRootToLeaves(allPaths);

        System.out.println(allPaths);
    }
}