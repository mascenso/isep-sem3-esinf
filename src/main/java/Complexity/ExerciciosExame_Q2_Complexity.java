package Complexity;
import Trees.PL_Trees.Trees.SortingUtils;

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

                System.out.println("x"); //this method runs n times

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
     * @param a
     * @param x
     */
    public static void binarySearch(int[] a, int x) {
        int li = 0;
        int ls = a.length - 1;
        while (li <= ls) {
            System.out.print("X");
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

        System.out.println(" Especial 2022 - 08/09/2022");
        complexity(100);
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        complexity(a, 0, a.length);
        System.out.println(Arrays.toString(a));



        int[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        binarySearch(b, 10);

        // Para n = 100 printa 580 vezes
        // Confirma complexidade O(n*log n)
        mistery (100);

    }
}
