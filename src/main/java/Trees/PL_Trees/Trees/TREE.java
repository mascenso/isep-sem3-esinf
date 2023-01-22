
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
        Boolean found = false;
        Node<E> node = root;
        while (node != null && !found) {
            if (element.compareTo(node.getElement()) < 0) {
                node = node.getLeft();
            } else if (element.compareTo(node.getElement()) > 0) {
                node = node.getRight();
            } else {
                found = true;
            }
        }
        return found;
    }

 
    public boolean isLeaf(E element){

       if (element == null) {
            return false;
        }
        Node<E> node = root;
        while (node != null) {
            if (element.compareTo(node.getElement()) < 0) {
                node = node.getLeft();
            } else if (element.compareTo(node.getElement()) > 0) {
                node = node.getRight();
            } else {
                return node.getLeft() == null && node.getRight() == null;
            }
        }
        return false;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        ascSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getRight(), snapshot);
    }

    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        desSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
        desSubtree(node.getLeft(), snapshot);
    }

   /**
   * build a list with all elements of the tree. The elements in the 
   * left subtree in ascending order and the elements in the right subtree 
   * in descending order. 
   *
   * @return    returns a list with the elements of the left subtree 
   * in ascending order and the elements in the right subtree is descending order.
   */
    public Iterable<E> ascdes(){
        List<E> list = new ArrayList<>();
        ascSubtree(root.getLeft(), list);
        list.add(root.getElement());
        desSubtree(root.getRight(), list);
        return list;
    }


   
    /**
    * Returns the tree without leaves.
    * @return tree without leaves
    */
    public BST<E> autumnTree() {

        TREE<E> t = new TREE<>();
        t.root = copyRec(root());
        return t;
    }
    
    private Node<E> copyRec(Node<E> node){

        if (node == null) {
            return null;
        }

        if (node.getLeft() == null && node.getRight() == null) {    //na folha
            return null;
        }

        return new Node<>(node.getElement(), copyRec(node.getLeft()), copyRec(node.getRight()));
    }
    
    /**
    * @return the the number of nodes by level.
    */
    public int[] numNodesByLevel(){

        int dim = height() + 1;
        int[] numNodes = new int[dim];

        numNodesByLevel(root(), numNodes, 0);
        return numNodes;
    }
    
    private void numNodesByLevel(Node<E> node, int[] result, int level){

        if (node == null) {
            return;
        }

        result[level]+=1;

        numNodesByLevel(node.getLeft(), result, level + 1);
        numNodesByLevel(node.getRight(), result, level + 1);
        
    }
    
    public boolean perfectBalanced(){
        return perfectBalanced(root());
    }
    
    private boolean perfectBalanced(Node<E> node){
        //pré  ordem

        if (node == null) {
            return true;
        }

       if (height(node.getLeft()) != height(node.getRight())) return false;

       if (!perfectBalanced(node.getLeft())) return false;

       return perfectBalanced(node.getRight());
    }
    
    
    public E lowestCommonAncestor(E elem1, E elem2){

            return lowestCommonAncestor(root(), elem1, elem2).getElement();
    }
    
    private Node<E> lowestCommonAncestor(Node<E> node, E elem1, E elem2){
        if (node == null) {
            return null;
        }

        if (node.getElement().compareTo(elem1) > 0 && node.getElement().compareTo(elem2) > 0) {
            return lowestCommonAncestor(node.getLeft(), elem1, elem2);
        }

        if (node.getElement().compareTo(elem1) < 0 && node.getElement().compareTo(elem2) < 0) {
            return lowestCommonAncestor(node.getRight(), elem1, elem2);
        }

        return node;
    }
    
    
    public BST<E>  minCompletSubtree(E elem1, E elem2){
          throw new UnsupportedOperationException("Not supported yet.");
    }
    

    public BST<E>  construcTreeposOrder (ArrayList<E> posOrder){

        throw new UnsupportedOperationException("Not supported yet.");
    }


}
