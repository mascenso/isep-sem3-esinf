package Complexity;

import java.util.Arrays;

public class ExerciciosExame_Q2_Complexity {

    /**
     * Recurso 2022 - 24/02/2022
     * 2. Seja o seguinte método:
     * Analise o método quanto à sua complexidade temporal, utilizando a notação Big-Oh. Justifique
     *     adequadamente.
     */
    public static void complexity (int n)
    {
        long count = 0;
        for (int i = 1; i < n; i=i*2)
        {
            for (int j = n; j > 1; j=j/2)
            {
                System.out.print("c"); //this method runs n times
                // something O(1)
            }
        }
    } // Total: log n * log n  = log^2 n;

    /**
     * Especial 2022 - 08/09/2022
     * Analise o método quanto à sua complexidade temporal, utilizando a notação Big-Oh. Justifique adequadamente.
     */
    public static void complexity (Integer a[], int li, int ls) {

        if (li < ls) {
            int i=li ;
            int j=ls-1 ;
            while (i < j) {

                System.out.print("y"); //this method runs n times

                int temp=a[i] ;
                a[i]=a[j] ;
                a[j]=temp ;
                i++ ;
                j-- ;
            }
            ls=ls/2;
            complexity (a,li,ls);
        }
    }

    /**
     * Binary Search exercise (not from exams)
     * Binary Search is O(log n)
     * n = 10 -> prints 4 times
     * n = 100 -> prints 7 times
     * n = 1000 -> prints 10 times
     * n = 10000 -> prints 14 times
     * n = 100000 -> prints 17 times
     * n = 1000000 -> prints 20 times
     * n = 10000000 -> prints 24 times
     * note that log_2 (10000000) = 24
     * look at image: src/main/resources/Complexity/binarySearch.jpeg
     * @param a
     * @param x
     */
    public static void binarySearch(int[] a, int x) {
        int li = 0;
        int ls = a.length - 1;
        while (li <= ls) {
            System.out.print("B");
            int m = (li + ls) / 2;
            if (a[m] == x) {
                System.out.println("Found");
                return;
            }
            if (a[m] < x) {
                li = m + 1;
            } else {
                ls = m - 1;
            }
        }
        System.out.println("Not found");
    }

    /**
     * Exame Normal - 19 de Fevereiro 2021
     * @param n
     */
    static public void mistery (int n) {
        if (n > 0) {
            for (int i=1; i<=n; i=i*2) { //log n
                System.out.print("u");; // método de complexidade O(1)
            }
            mistery(n-1); // recursive call runs n times
        }
    } //Total: n * log n

    public static void main(String[] args) {
        long start;
        long end;


        System.out.println(" Recurso 2022 - 24/02/2022");
        start = System.nanoTime();
        complexity(1000000000);
        end = System.nanoTime();
        System.out.println();
        System.out.println("Time: " + (end - start) + " ns");



        System.out.println("Especial 2022 - 08/09/2022");
        //Pode se ver q ele corre aproximadamente n vezes
        // para n = 10 -> ~10
        // para n = 100 -> ~100
        // para n = 1000 -> ~1000
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        complexity(a, 0, a.length);
        System.out.println();
        System.out.println(Arrays.toString(a));


        System.out.println("Binary Search:");
        int[] b = new int[1000000000];
        //time
        start = System.nanoTime();
        binarySearch(b, 7); //a nr that is not in the array
        end = System.nanoTime();
        System.out.println("Time: " + (end - start) + " ms");

        System.out.println("Mistery:");
        //Para n = 100 printa 580 vezes
        //Para numeros muito grandes, ate da stack overflow
        //pcausa do nr exagerado de chamadas recursivas
        //Confirma complexidade O(n*log n)
        start = System.nanoTime();
        mistery (1000);
        end = System.nanoTime();
        System.out.println();
        System.out.println("Time: " + (end - start) + " ms");

    }
}
