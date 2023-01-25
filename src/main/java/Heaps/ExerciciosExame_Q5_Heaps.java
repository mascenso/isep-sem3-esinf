package Heaps;
import Heaps.PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.Entry;
import Heaps.PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.HeapPriorityQueue;

import java.util.ArrayList;

public class ExerciciosExame_Q5_Heaps {
    /**
     * 5 - Implemente um metodo na classe HeapPriorityQueue que verifica se uma ArrayList<Entry<K,V>> representa um heap.
     */
    public static class HeapPriorityQueue_exam<K extends Comparable<K>,V> extends HeapPriorityQueue<K,V> {

        public boolean isPriorityQueue(ArrayList<Entry<K,V>> list){
            for (int i = 0; i < list.size(); i++){
                K key = list.get(i).getKey();
                K left = list.get(2*i + 1).getKey();
                K right = list.get(2*i + 2).getKey();
                if (key.compareTo(left) < 0 || key.compareTo(right) < 0) return false;
            }
            return true;
        }
    }


}
