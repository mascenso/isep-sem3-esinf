
package Trees.PL_Trees.Trees;

import java.util.ArrayList;
import java.util.List;

/*
 * @author DEI-ESINF
 * @param <E>
 */

public class TREE<E extends Comparable<E>> extends BST<E> {
 
   /*
   * @param element A valid element within the tree
   * @return true if the element exists in tree false otherwise
   */   
    public boolean contains(E element) {
         throw new UnsupportedOperationException("Not supported yet.");
    }

 
    public boolean isLeaf(E element){
         throw new UnsupportedOperationException("Not supported yet.");
    }

   /*
   * build a list with all elements of the tree. The elements in the 
   * left subtree in ascending order and the elements in the right subtree 
   * in descending order. 
   *
   * @return    returns a list with the elements of the left subtree 
   * in ascending order and the elements in the right subtree is descending order.
   */
    public Iterable<E> ascdes(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        throw new UnsupportedOperationException("Not supported yet.");          
    }
    
    private void desSubtree(Node<E> node, List<E> snapshot) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }
   
    /**
    * Returns the tree without leaves.
    * @return tree without leaves
    */
    public BST<E> autumnTree() {

        TREE<E> t = new TREE<>();
        t.root = copyRec(root());
        return t;

        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Node<E> copyRec(Node<E> node){

        if (node == null) {
            return null;
        }

        if (node.getLeft() == null && node.getRight() == null) {    //na folha
            return null;
        }

        return new Node<>(node.getElement(), copyRec(node.getLeft()), copyRec(node.getRight()));

        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
    * @return the the number of nodes by level.
    */
    public int[] numNodesByLevel(){

        int dim = height() + 1;
        int[] numNodes = new int[dim];

        numNodesByLevel(root(), numNodes, 0);
        return numNodes;

       //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void numNodesByLevel(Node<E> node, int[] result, int level){

        if (node == null) {
            return;
        }

        result[level]+=1;

        numNodesByLevel(node.getLeft(), result, level + 1);
        numNodesByLevel(node.getRight(), result, level + 1);

        //throw new UnsupportedOperationException("Not supported yet.");
        
    }
    
    public boolean perfectBalanced(){
        return perfectBalanced(root());



       //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private boolean perfectBalanced(Node<E> node){
        //pré  ordem

        if (node == null) {
            return true;
        }

       if (height(node.getLeft()) != height(node.getRight())) return false;

       if (!perfectBalanced(node.getLeft())) return false;

       return perfectBalanced(node.getRight());


        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public E lowestCommonAncestor(E elem1, E elem2){
            
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Node<E> lowestCommonAncestor(Node<E> node, E elem1, E elem2){
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public BST<E>  minCompletSubtree(E elem1, E elem2){
            
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    public BST<E>  construcTreeposOrder (ArrayList<E> posOrder){
            
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
