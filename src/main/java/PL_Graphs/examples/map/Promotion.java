package PL_Graphs.examples.map;

import PL_Graphs.graph.Edge;
import PL_Graphs.graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *  @author DEI-ESINF
 *
 */
public class Promotion {

    /**
     * Implement a method that, given a graph and a n number of promotions, return a list with the employees
     * to promote. The goal is only to find one of the solutions. The method to be developed must obey the
     * interface:
     */
   List<String> getPromotions_Op1(Graph<String,Integer> g, Integer n){
       Set<String> ss = new HashSet<>();
       while (ss.size()< n ){
           String nxt = findPromotion(g, ss);
           if (nxt != null) ss.add(nxt); else return null;
       }
   return new ArrayList<>(ss);
   }

   private String findPromotion(Graph<String,Integer> g, Set<String> ss) {
       for (String v : g.vertices()) {
           String nxt = testPromotion(g, ss, v);
           if (nxt != null) return nxt;
           return null;
       }
       return null;
   }
    private String testPromotion(Graph<String, Integer> g, Set<String> ss, String v) {
        for (Edge<String, Integer> e : g.incomingEdges(v)) {
            if (!ss.contains(e.getVOrig())) return null;
        }
        return v;
    }


    //Solution w/ vertex removal --> Simplier
    //However, not optimal either, in a case of a graph with a lot of vertices, it would be slow
    //Since the whole graph is cloned

    private String findPromotion(Graph<String, Integer> gp) {
        for (String v : gp.vertices()) {
            if (gp.inDegree(v) == 0) {
                return v;
            }
        }
        return null;
    }

    public List<String> getPromotions_Op2(Graph<String, Integer> g, Integer n) {
        List<String> lr = new ArrayList<>();
        Graph<String, Integer> gp = g.clone();
        while (lr.size() < n) {
            String nxt = findPromotion(gp);
            if (nxt != null) {
                gp.removeVertex(nxt);
                lr.add(nxt);
            } else return null;
        }
        return lr;
    }

    public List<String> getPromotions_3(Graph<String, Integer> g, Integer n) {
        Set<String> ss = new HashSet<>();
        Set<String> sv = new HashSet<>(g.numVertices());
        Set<String> sp = getPromotionsRecursive(g, ss, sv, n);
        return (sp == null) ? null : new ArrayList<>(sp);
    }

    private Set<String> getPromotionsRecursive(Graph<String, Integer> g, Set<String> ss, Set<String> sv, Integer n) {
        if (n == null) return ss;
        String nxt = findPromotion(g, ss, sv);
        if (nxt != null) {
            ss.add(nxt);
            sv.remove(nxt);
            return getPromotionsRecursive(g, ss, sv, n);
        } else return null;
    }

    private String findPromotion(Graph<String, Integer> g, Set<String> ss, Set<String> sv) {
        for (String v : sv) {
            String nxt = testPromotion(g, ss, v);
            if (nxt != null) return nxt;
        }
        return null;
    }

}
