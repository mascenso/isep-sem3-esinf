package exames;

import exames.Recurso22.Recurso22;

import java.util.*;

public class Normal20 {

    /**
     * 1. Elabore um método que coloca numa lista de objetos Pair<Key,Value> a fusão ordenada de duas outras listas
     * de objetos Pair<Key,Value>, também ordenadas. Os elementos de valor igual nas duas listas deverão ser
     * colocados alternadamente na lista resultado. Por exemplo:
     * Lista A: [<A,2>, <A,2>, <A,5>] Lista B: [<B,1>, <B,1>, <B,2>, <B,3>, <B,4>, <B,4>, <B,5>]
     * Lista Resultado: [<B,1>, <B,1>, <A,2>, <B,2>, <A,2>, <B,3>, <B,4>, <B,4>, <A,5>, <B,5>]
     * Considere a seguinte assinatura
     * public static<K, E extends Comparable<E>> List<Pair<K,E>>
     * mergeLists(List<Pair<K,E>> A, List<Pair<K,E>> B)
     */
    public static<K, E extends Comparable<E>> List<Map.Entry<K,E>> mergeLists(List<Map.Entry<K,E>> A, List<Map.Entry<K,E>> B) {
        List<Map.Entry<K,E>> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.size() && j < B.size()) {
            Map.Entry<K,E> a = A.get(i);
            Map.Entry<K,E> b = B.get(j);

            if (a.getValue().compareTo(b.getValue()) < 0) {
                result.add(a);
                i++;

            } else if (a.getValue().compareTo(b.getValue()) > 0) {
                result.add(b);
                j++;

            } else {
                result.add(a);
                result.add(b);
                i++;
                j++;
            }
        }

        for(i=i; i < A.size();i++)
            result.add(A.get(i));
        for(j=j; j < B.size();j++)
            result.add(B.get(j));
        return result;
    }


    public static void main(String[] args) {
        System.out.println("Exame - Normal 20\n");
        //* Lista A: [<A,2>, <A,2>, <A,5>] Lista B: [<B,1>, <B,1>, <B,2>, <B,3>, <B,4>, <B,4>, <B,5>]
        //* Lista Resultado: [<B,1>, <B,1>, <A,2>, <B,2>, <A,2>, <B,3>, <B,4>, <B,4>, <A,5>, <B,5>]

        List<Map.Entry<String, Integer>> listaA = new ArrayList<>();
        List<Map.Entry<String, Integer>> listaB = new ArrayList<>();

        listaA.add(new AbstractMap.SimpleEntry<>("A", 2));
        listaA.add(new AbstractMap.SimpleEntry<>("A", 2));
        listaA.add(new AbstractMap.SimpleEntry<>("A", 5));

        listaB.add(new AbstractMap.SimpleEntry<>("B", 1));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 1));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 2));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 3));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 4));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 4));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 5));

        List<Map.Entry<String, Integer>> listaResultado = mergeLists(listaA, listaB);

        System.out.println("Lista A: " + listaA);
        System.out.println("Lista B: " + listaB);

        System.out.println("Lista Resultado: " + listaResultado);

    }
}
