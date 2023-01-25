package Trees;
import Trees.PL_Trees.Trees.TREE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExerciciosExame_Q3_Trees {

    /**
     * 3. Dada uma árvore binária de pesquisa, implemente o método na classe genérica TREE<E> que devolva os
     * caminhos do nó raiz para todos os nós terminais.
     */
    public static class TREE_exam<E extends Comparable<E>> extends TREE<E> {

        public void allPathsFromRootToLeaves(List<List<E>> allPaths){
            allPaths.clear();
            allPathsFromRootToLeaves(root, allPaths);
        }

        private void allPathsFromRootToLeaves(Node<E> node, List<List<E>> allPaths){
            Stack<E> stack = new Stack<>(); // Could use a LinkedList also, as a stack
            inOrderSubtree(node, stack, allPaths);
        }

        private void inOrderSubtree(Node<E> node, Stack<E> stack, List<List<E>> allPaths) {
            if (node == null) return;
            stack.push(node.getElement()); // stackLL.offerLast(node.getElement());

            inOrderSubtree(node.getLeft(), stack, allPaths);
            if (node.getLeft() == null && node.getRight() == null) {
                List<E> path = new ArrayList<>(stack);
                allPaths.add(path);
            }
            inOrderSubtree(node.getRight(), stack, allPaths);
            stack.pop(); // stackLL.removeLast();
        }
    }

    /**
     * 3 - Adicione à classe TREE<E> um metodo generico que devolva o predecessor de um dado elemento.
     * Se o elemento nao existir na arvore, devolve null.
     */
    public static class TREE_exam23<E extends Comparable<E>> extends TREE<E> {

        public E predecessor(E elem) {

            Node<E> node = root;
            Node<E> predecessor = null;

            while (node != null) {
                if (elem.compareTo(node.getElement()) < 0) {
                    node = node.getLeft();
                } else if (elem.compareTo(node.getElement()) > 0) {
                    predecessor = node;
                    node = node.getRight();
                } else {
                    if (node.getLeft() != null) {
                        predecessor = node.getLeft();
                        while (predecessor.getRight() != null) {
                            predecessor = predecessor.getRight();
                        }
                    }
                    break;
                }
            }
            return predecessor == null ? null : predecessor.getElement();
        }

    }

    public static void main(String[] args) {

        System.out.println(" Questão 3");

        TREE_exam<Integer> tree = new TREE_exam<>();
        tree.insert(20);
        tree.insert(15);
        tree.insert(40);
        tree.insert(10);
        tree.insert(17);
        tree.insert(30);
        tree.insert(50);
        tree.insert(8);
        tree.insert(13);
        tree.insert(7);

        System.out.printf(tree.toString());

        List<List<Integer>> allPaths = new ArrayList<>();
        tree.allPathsFromRootToLeaves(allPaths);

        System.out.println(allPaths);
    }


}



