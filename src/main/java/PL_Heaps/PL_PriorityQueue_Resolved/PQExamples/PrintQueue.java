/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL_Heaps.PL_PriorityQueue_Resolved.PQExamples;

import PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.Entry;
import PL_Heaps.PL_PriorityQueue_Resolved.Priority_queue.HeapPriorityQueue;

/**
 *
 */
public class PrintQueue {
    
    //------------ Static nested Document class ------------
    
    public static class Document {
       
        private Integer id;
        private Integer npags;
        
        public Document (Integer i, Integer np) {id=i; npags=np;}
        
        public Integer getId() {return id;}
        public void setId(Integer i) {id=i;}
        
        Integer getNPags() {return npags;}
        public void setNPags(Integer np) {npags=np;}
        
        @Override
        public boolean equals(Object otherObj) {
            if (this == otherObj){
                return true;
            }
            if (otherObj == null || this.getClass() != otherObj.getClass()){
                return false;
            }
            Document otherDoc = (Document) otherObj;
        
            return this.id == otherDoc.id;
        }
    
        @Override
        public String toString() {
            return "Doc." + id +" (N. pags " + npags +")" ; 
        }
    }
    //------------ end of Static nested Room class ------------
    
    private HeapPriorityQueue<Integer,Document> prn;  
     
    /**
    * add a Document to the printing queue
    */
    public void addDoc2Queue(Integer p,Document doc) {
        prn.insert(p,doc);
    }
    
    /**
    * send a Document to printer, removing it from the queue
    */
    public Entry<Integer,Document> send2Printer()  {
       return prn.removeMin();
    } 
    
    /**
    * returns the next Document in line to be printed
    */
    public Document nextDoc2Print()  {
        return prn.removeMin().getValue();
    }
    
    /**
    * returns the estimated time before the printing of a specific document starts,
    * considering that the printer takes in average 2 seconds to print each page
    */
    public double time2print(Document doc, double timeslot) throws CloneNotSupportedException {
        HeapPriorityQueue<Integer,Document> clone=prn.clone();
        double time=0;
        Document docs;
        while (!clone.isEmpty()){
            docs=clone.removeMin().getValue();
            time+=((docs.npags<<1));
        }
        return time;
    } 
                                 

    
}
