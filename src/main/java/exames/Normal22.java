package exames;
import java.util.*;

public class Normal22 {

    /**
     1. Implemente um método que dada uma lista de inteiros ordenados por ordem crescente e um valor inteiro
     relativo à amplitude do(s) intervalo(s), devolva um map com o(s) intervalo(s) contidos na lista e respetivos
     valores da lista nesse intervalo. Só devem ser considerados os intervalos cujos extremos pertençam à lista e
     cada valor contido no intervalo não pode ser repetido noutro intervalo. Por exemplo, a tabela abaixo contém
     a lista a considerar e o resultado para algumas amplitudes: (...)

     Ex. Lista = [1, 2, 3, 3, 4, 6, 8, 9]
        Amplitude = 2
        Resultado = {[1,3] -> [1, 2, 3, 3], [4,6] -> [4, 6]}

     Map<String, List<Integer>> retornaListaAmplitude (int amplitude, List<Integer> l)
     **/
    public static Map<String, List<Integer>> retornaListaAmplitude(int amplitude, List<Integer> l){

        Map<String, List<Integer>> map = new HashMap<>();

        int i = 0;
        while (i < l.size()) {
            int beggining = l.get(i);
            int end = beggining + amplitude;

            if (end > l.get(l.size() - 1)) {
                break;
            }

            List<Integer> lista = new ArrayList<>();

            int j = i;
            while (j < l.size()) {
                if (l.get(j) <= end) {
                    lista.add(l.get(j));
                    j++;
                } else {
                    i = j - 1;
                    break;
                }
            }
            map.put("[" + beggining + "," + end + "]", lista);
            i++;
        }
        return map;
    }
}