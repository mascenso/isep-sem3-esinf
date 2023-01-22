
package Graphs.PL_Graphs.examples.matrix;

import Graphs.PL_Graphs.graph.Algorithms;
import Graphs.PL_Graphs.graph.matrix.MatrixGraph;
import Graphs.PL_Graphs.graph.Graph;


class Bridges {
   /**
    * Minimum spanning tree - PRIM Algorithm
    *
    * @return minimum spanning tree
    */
    public static MatrixGraph mstPRIM(Graph<Integer, Double> g){
        return Algorithms.getMinimumSpanTreePrim(g, Double::compare, Double.POSITIVE_INFINITY, 0.0);
    }

    //or
    
    /**
    * Minimum spanning tree - Kruskall Algorithm 
    * @return minimum spanning tree
    */
    public static MatrixGraph<Integer, Double> mstKruskall(Graph<Integer, Double> g){
        return Algorithms.getMinimumSpanTreeKruskal(g, Double::compare);
    }
     
}
