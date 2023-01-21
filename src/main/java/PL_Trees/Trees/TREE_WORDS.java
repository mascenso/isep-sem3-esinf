
package PL_Trees.Trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {

    public void createTree() throws FileNotFoundException {
        Scanner readfile = new Scanner(new File("src/main/java/PL_Trees/Trees/xxx.xxx"));
        while (readfile.hasNextLine()) {
            String[] pal = readfile.nextLine().split("(\\,)|(\\s)|(\\.)");
            for (String word : pal)
                if (word.length() > 0)
                    insert(new TextWord(word, 1));
        }
        readfile.close();
    }

    /**
     * Inserts a new word in the tree, or increments the number of its occurrences.
     *
     * @param element
     */
    @Override
    public void insert(TextWord element) {
        root = insert(element, root);
        ///throw new UnsupportedOperationException("Not supported yet.");
    }

    private Node<TextWord> insert(TextWord element, Node<TextWord> node) {
        if (node == null)
            return new Node(element, null, null);

        if (node.getElement().compareTo(element) == 0) {
            node.setElement(new TextWord(element.getWord(), node.getElement().getOccurrences() + 1));
        }

        if (node.getElement().compareTo(element) > 0)
            node.setLeft(insert(element, node.getLeft()));
        else if (node.getElement().compareTo(element) < 0)
            node.setRight(insert(element, node.getRight()));
        return node;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns a map with a list of words for each occurrence found.
     *
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer, List<String>> getWordsOccurrences() {
        Map<Integer, List<String>> map = new HashMap<>();
        getWordsOccurrences(root, map);
        return map;

        //throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * Updates a map with a list of words for each occurrence found.
     *
     * @param node the node to be processed
     * @param map  the map to be updated
     */
    private void getWordsOccurrences(Node<TextWord> node, Map<Integer, List<String>> map) {
        if (node == null)
            return;
        for (TextWord tw : inOrder()) {

            if (map.containsKey(tw.getOccurrences())) {
                map.get(tw.getOccurrences()).add(tw.getWord());
            } else {
                List<String> list = new ArrayList<>();
                list.add(tw.getWord());
                map.put(tw.getOccurrences(), list);
            }
        }

    }

}
