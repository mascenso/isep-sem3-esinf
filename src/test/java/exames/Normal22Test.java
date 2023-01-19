package exames;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class Normal22Test {

    @Test
    void retornaListaAmplitudeTest() {
        Map<String, List<Integer>> map = new HashMap<>();

        int amplitude = 2;
        List<Integer> l = Arrays.asList(1, 2, 3, 3, 4, 6, 8, 9);

        String s1 = "[1,3]";
        List<Integer> l1 = new ArrayList<>();
        l1.add(1); l1.add(2); l1.add(3); l1.add(3);
        String s2 = "[4,6]";
        List<Integer> l2 = new ArrayList<>();
        l2.add(4); l2.add(6);



        map.put(s1, l1);
        map.put(s2, l2);

        Assertions.assertEquals(map, Normal22.retornaListaAmplitude(amplitude, l));

    }
}