/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heaps.PL_Heaps.PL_PriorityQueue_Resolved.PQExamples;

//import static java.lang.System.out;
import Heaps.PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.Entry;
import Heaps.PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.HeapPriorityQueue;

import java.util.ArrayList;
import java.util.Objects;

public class AirTrafficCC {
    
    private HeapPriorityQueue<Integer,String> cc;
    int timeslot = 5;  // time slot allocated to land each plane
    
    public AirTrafficCC(Integer[] p, String[] f) {
        this.cc = new HeapPriorityQueue(p,f);
    }
    
    public String nextPlaneLanding(){
        return cc.removeMin().getValue();
    }
    
    public void addPlane2Queue(String id, Integer pr) {
        cc.insert(pr,id);
    }
    
    public Entry<Integer,String> clearPlane4Landing() {
        return cc.removeMin();
    }
    
    public Integer nrPlanesWaiting() {
      return   cc.size();
    }
    
    public Integer time2land(String id) throws CloneNotSupportedException {
        HeapPriorityQueue<Integer, String> clone=cc.clone();
        int time=0;
        Entry<Integer,String> land=null;

        while(!clone.isEmpty()){
            land=clone.removeMin();

            if(Objects.equals(land.getValue(), id)){
                break;
            }
           time+=5;
        }

        return time;
    }
    
    public void changePriority2(String id, Integer newp) throws CloneNotSupportedException {
        HeapPriorityQueue<Integer, String> clone=cc.clone();
       ArrayList< Entry<Integer,String>> list=new ArrayList<>();
        Entry<Integer,String> land;

        while(!clone.isEmpty()){
            land=clone.removeMin();

            if(Objects.equals(land.getValue(), id)){
                clone.insert(newp,id);
                break;
            }
            list.add(land);
        }
        for( Entry<Integer,String> land2 : list){
            cc.insert(land2.getKey(),land2.getValue());

        }
       cc=clone;
    }
    
    public Integer testRemove() {       
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return cc.toString();
    }
}
