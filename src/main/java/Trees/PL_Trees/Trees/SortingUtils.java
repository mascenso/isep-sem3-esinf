
package Trees.PL_Trees.Trees;

import java.util.List;

/**
 *
 * @author DEI-ESINF
 */
public class SortingUtils {
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted) {
        BST<E> treeAux = new BST<>();
        for(E a : listUnsorted) {
            treeAux.insert(a);
        }
        return treeAux.inOrder();
    }
}
