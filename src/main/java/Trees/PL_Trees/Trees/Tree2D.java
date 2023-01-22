package Trees.PL_Trees.Trees;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;


public class Tree2D<T extends Comparable<T>> extends BST<T> {

    protected static class Node2D<T> extends BST.Node<T> {
        private Point2D.Double coordinates;

        public Node2D(Point2D.Double coordinatesXY,T t,Node2D<T> leftChild,Node2D<T> rightChild){
            super(t,leftChild,rightChild);
            setCoordinates(coordinatesXY);
        }

        //SET'S
        public void setCoordinates(Point2D.Double coordinatesXY){
            coordinates = coordinatesXY;
        }

        public void setNode(Node2D<T> node) {
            super.setElement(node.getElement());
            super.setRight(node.getRight());
            super.setLeft(node.getLeft());
            this.coordinates = node.getCoordinates();
        }

        //GET'S
        public Point2D.Double getCoordinates(){
            return coordinates;
        }

        public double getX(){
            return getCoordinates().getX();
        }

        public double getY(){
            return getCoordinates().getY();
        }
    }


    protected Node2D<T> root = null;     // root of the tree

    protected Node2D<T> root() {
        return root;
    }


    private final Comparator<Node2D<T>> cmpX = Comparator.comparingDouble(Node2D::getX);

    private final Comparator<Node2D<T>> cmpY = Comparator.comparingDouble(Node2D::getY);

    public void insert(Point2D.Double coordinates, T element) {

        Node2D<T> node = new Node2D<>(coordinates, element, null, null);

        if(root()==null) {
            root = node;
        } else {
            insert(root(), node, true);
        }

    }

    // Insert node in 2DTREE

    private Node2D<T> insert(Node2D<T> currentNode, Node2D<T> node, boolean divX) {

        if(node.coordinates.equals(currentNode.coordinates)) {
            return node;
        }
        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);
        if(cmpResult == -1) {
            if(currentNode.getLeft() == null) {
                currentNode.setLeft(node);
            } else {
                insert((Node2D<T>)currentNode.getLeft(), node, !divX);
            }
        } else {
            if(currentNode.getRight() == null) {
                currentNode.setRight(node);
            } else {
                insert((Node2D<T>)currentNode.getRight(), node, !divX);
            }
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    /**
     * Recursive method to implement the toString for the Tree2D
     * @param root of the Tree2D
     * @param level level of the Tree2D
     * @param sb stringBuilder for this method
     */
    private void toStringRec(Node2D<T> root, int level, StringBuilder sb) {
        if (root == null)
            return;

        toStringRec((Node2D<T>)root.getRight(), level + 1, sb);
        if(level != 0) {
            for(int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------" + root.getElement() + "\n");
        } else {
            sb.append(root.getElement() + "\n");
        }
        toStringRec((Node2D<T>)root.getLeft(), level + 1, sb);
    }

    // Finds the nearest neighbour of the provided coordinates.

    public T findNearestNeighbour(double x, double y) {
        Node2D<T> node = root();
        return findNearestNeighbour(root(), x, y, node, true);
    }


    // Finds the nearest neighbour of the provided coordinates.

    private T findNearestNeighbour(Node2D<T> node, double x, double y, Node2D<T> closestNode, boolean divX) {
        if(node == null) {
            return null;
        }

        double d = Point2D.distanceSq(node.coordinates.x, node.coordinates.y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.coordinates.x, closestNode.coordinates.y, x, y);

        if(closestDist > d) {
            closestNode.setNode(node);
        }

        double delta1 = divX ? x - node.coordinates.x : y - node.coordinates.y;
        double delta2 = delta1 * delta1;

        Node2D<T> node1 = (Node2D<T>)(delta1 < 0 ? node.getLeft() : node.getRight());
        Node2D<T> node2 = (Node2D<T>)(delta1 < 0 ? node.getRight() : node.getLeft());

        findNearestNeighbour(node1, x, y, closestNode, !divX);

        if(delta2 < closestDist) {
            findNearestNeighbour(node2, x, y, closestNode, !divX);
        }
        return closestNode.getElement();
    }


    // FR e MM - início

    /**
     * Class to define the quadrant's boundaries
     */
    protected class Region {
        private double x1;
        private double x2;
        private double y1;
        private double y2;

        /**
         * Full constructor for the Region objects
         *
         * @param x1
         * @param y1
         * @param x2
         * @param y2
         */
        public Region(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        /**
         * Method to verify if a given point is inside or outside of a region's area
         *
         * @param point
         * @return true if is inside of the boundaries area
         */
        public boolean containsPoint(Point2D point) {
            return point.getX() >= this.x1
                    && point.getX() < this.x2
                    && point.getY() >= this.y1
                    && point.getY() < this.y2;
        }


        private double getY2() {
            return this.y2;
        }

        private double getY1() {
            return this.y1;
        }

        private double getX1() {
            return this.x1;
        }

        private double getX2() {
            return this.x2;
        }


    }

    public ArrayList<T> searchByRange(double x1, double y1, double x2, double y2) {
        Region searchRegion = new Region(x1, y1, x2, y2);
        Node2D<T> node = root();
        return SearchByRange(root(), searchRegion, new ArrayList<T>(), true);
    }

    /**
     *
     * @param node Node to be verified if is in range
     * @param region Region to serve as parameters, where the method will check if the node is inside
     * @param accumulatedValues list of nodes which verify the condition of search and that are inside of the region
     * @param divX condition to check if we are on the right or left side of the tree
     * @return list of nodes that passed the search condition
     */
    private ArrayList<T> SearchByRange(Node2D<T> node, Region region, ArrayList<T> accumulatedValues, boolean divX) {
        if (node == null) {
            return null;
        }

        // Verificar se o ponto pertence à região
        if (region.containsPoint(node.getCoordinates())) {
            accumulatedValues.add(node.getElement());
        }

        if (divX) { //caso em que estamos a verificar ao nível do X
            if (node.getCoordinates().x > region.getX2()) {
                SearchByRange((Node2D<T>) node.getLeft(), region, accumulatedValues, !divX);
            } else if (node.getCoordinates().x < region.getX1()) {
                SearchByRange((Node2D<T>) node.getRight(), region, accumulatedValues, !divX);
            } else {
                SearchByRange((Node2D<T>) node.getLeft(), region, accumulatedValues, !divX);
                SearchByRange((Node2D<T>) node.getRight(), region, accumulatedValues, !divX);
            }
        } else { //caso em que estamos a verificar ao nível do Y
            if (node.getCoordinates().y > region.getY2()) {
                SearchByRange((Node2D<T>) node.getLeft(), region, accumulatedValues, !divX);
            } else if (node.getCoordinates().y < region.getY1()) {
                SearchByRange((Node2D<T>) node.getRight(), region, accumulatedValues, !divX);
            } else {
                SearchByRange((Node2D<T>) node.getLeft(), region, accumulatedValues, !divX);
                SearchByRange((Node2D<T>) node.getRight(), region, accumulatedValues, !divX);
            }
        }
        return accumulatedValues;
    }



    /*

    public ArrayList<T> searchByRange(double x1, double y1, double x2, double y2) {
        Region searchRegion = new Region(x1, y1, x2, y2);
        Node2D<T> node = root();
        return SearchByRange(root, searchRegion, new ArrayList<>(), true);
    }

    private ArrayList<T> SearchByRange(Node2D<T> node, Region region, ArrayList<T> countryList, boolean divX) {
        if (node == null) {
            return null;
        }

        // Verificar se o ponto pertence à região
        if (region.containsPoint(node.getCoordinates())) {
            countryList.add(node.getElement());
        }

        if (divX) {
            if (node.getCoordinates().x > region.getX2()) {
                SearchByRange((Node2D<T>) node.getLeft(), region, countryList, !divX);
            } else if (node.getCoordinates().x < region.getX1()) {
                SearchByRange((Node2D<T>) node.getRight(), region, countryList, !divX);
            } else {
                SearchByRange((Node2D<T>) node.getLeft(), region, countryList, !divX);
                SearchByRange((Node2D<T>) node.getRight(), region, countryList, !divX);
            }
        } else { //caso em que estamos a verificar o Y
            if (node.getCoordinates().y > region.getY2()) {
                SearchByRange((Node2D<T>) node.getLeft(), region, countryList, !divX);
            } else if (node.getCoordinates().y < region.getY1()) {
                SearchByRange((Node2D<T>) node.getRight(), region, countryList, !divX);
            } else {
                SearchByRange((Node2D<T>) node.getLeft(), region, countryList, !divX);
                SearchByRange((Node2D<T>) node.getRight(), region, countryList, !divX);
            }
        }
        return countryList;
    }

     */

}