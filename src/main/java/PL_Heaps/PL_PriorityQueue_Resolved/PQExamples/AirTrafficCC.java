/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL_Heaps.PL_PriorityQueue_Resolved.PQExamples;

//import static java.lang.System.out;
import PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.Entry;
import PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.HeapPriorityQueue;

/**
 *
 * @author antoniosilva
 */
public class AirTrafficCC {
    
    private HeapPriorityQueue<Integer,String> cc;
    int timeslot = 5;  // time slot allocated to land each plane
    
    public AirTrafficCC(Integer[] p, String[] f) {
        this.cc = new HeapPriorityQueue(p,f);
    }
    
    public String nextPlaneLanding(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void addPlane2Queue(String id, Integer pr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Entry<Integer,String> clearPlane4Landing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Integer nrPlanesWaiting() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Integer time2land(String id) {  
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void changePriority2(String id, Integer newp){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Integer testRemove() {       
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
