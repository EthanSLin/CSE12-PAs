/**
 * This is my implementation of a Binary Search Tree Iterator
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is a class file that contains the framework of MyBSTIterator. It 
 * contains the variables, constructors, and methods required to implement my 
 * version of a BST Iterator
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Advances the iterator to the next node and returns the node we 
         * advanced (/visited) to
         * @return the node we advanced to
         */
        MyBSTNode<K, V> nextNode() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastVisited = next;
            next = next.successor();
            return lastVisited;
        }

        /**
         *  This method removes the last visited node from the tree.
         */
        public void remove() {
            //Check if their was last visited node at all
            if (lastVisited == null) {
                throw new IllegalStateException();
            }
            //Checks if the last visited node had two children
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) {
            //If it did, the last visited node is operated on next
                next = lastVisited;
            }
            //Remove the last visited node from the tree
            MyBSTIterator.this.remove(lastVisited.getKey());
            //Clears the last visited node's data
            lastVisited = null;
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}