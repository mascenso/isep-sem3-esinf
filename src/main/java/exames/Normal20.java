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
    public static<K, E extends Comparable<E>> List<Map.Entry<K,E>> mergeLists(List<Map.Entry<K,E>> A, List<Map.Entry<K,E>> B){

        List<Map.Entry<K,E>> lista = new ArrayList<>();
        Map.Entry<K,E> temp;

        for (int i=0; i < Math.min(A.size(), B.size()) ; i++)
        {
            //guardo o maior no temp e adiciono o menor
            E valueA = A.get(i).getValue();
            E valueB = B.get(i).getValue();

            if (valueA.compareTo(valueB) >= 0) {
                temp = A.get(i);
                lista.add(B.get(i));
            } else {
                temp = B.get(i);
                lista.add(A.get(i));
            }
            lista.add(temp);
        }

        // add remaining elements from list A
        for (int i = Math.min(A.size(), B.size()); i < A.size(); i++) {
            lista.add(A.get(i));
        }

        // add remaining elements from list B
        for (int i = Math.min(A.size(), B.size()); i < B.size(); i++) {
            lista.add(B.get(i));
        }

        return lista;
    }

    public static void main(String[] args) {
        System.out.println("Exame - Normal 20\n");

    }
}
