/**
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: None. Put "None" if you did not have any external help
 * 
 * This is the MyLinkedList class file. It provides the framework for the
 * MyLinkedList, MyListIterator, and Node classes
 */

import java.util.ListIterator;
import java.util.Iterator;
import java.util.AbstractList;
import java.util.NoSuchElementException;

/** 
 * This is the MyLinkedList class. It contains the constructors, methods, and 
 * instance variables to create my own implementation of LinkedList
 */

public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null    
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev - new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;        
        }

        /** 
         * Set the parameter next as the next node
         * @param next - new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element - new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        }

        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */
    public MyLinkedList() {
        size = 0;
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    @Override
    //Returns size of the LinkedList
    /**
     * @return the size of the LinkedList
     */
    public int size() {
        return size;
    }

    @Override
    //Returns the data of a Node at a specified index
    //Takes the index of the Node from which data will be extracted
    /**
     * @param index the specified index of the node from which data will
     * be retrieved
     * @return the data value of the node
     */
    public E get(int index) {
        //Throws an IndexOutOfBoundException if index < 0 or >= size of list
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head.next;
        //Iterates node along the list, stopping when node is at the index
        for (int i = 0; i < index; i++) {
            node.setElement(node.next.getElement());
            node = node.next;
        }
        return node.getElement();
    }

    @Override
    //Inserts a data value at a specified index of the LinkedList
    //Takes an index of where the Node will be inserted and data of the new Node
    /**
     * @param index the specified index where the new node will be inserted
     * @param data the data value of the new node
     */
    public void add(int index, E data) {
        //Throws a NullPointerException if data of the new node is null
        if (data == null) {
            throw new NullPointerException();
        }    
        //Throws an IndexOutOfBoundException when index < 0 or > size of list
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(data);
        Node prevNode = head;
        //Iterates through the list, setting the prevNode to the node located at 
        //the index before the index argument
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.getNext();
        }
        Node nextNode = prevNode.getNext();
        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        newNode.setNext(nextNode);
        nextNode.setPrev(newNode);
        size++;
    }
    //Appends a data value to the end of the LinkedList
    /** 
     * @param data the data value added to a new node
     * @return the boolean of true
     */
    public boolean add(E data) {
        //Throws a NullPointerException if data of the new node is null
        if (data == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(data);
        Node endNode = tail.getPrev();
        endNode.setNext(newNode);
        tail.setPrev(newNode);
        newNode.setNext(tail);
        newNode.setPrev(endNode);
        size++;
        return true; 
    }

    //Set the element for the node at index to data and return the element that 
    //was previously stored in this node.
    /** Takes the specified index of where the node is, and what its new data is
        * @param index specified index where the node will be altered
        * @param data the new data for the node
        * @return the data that was in the removed node
    */
    public E set(int index, E data) {
        //Throws a NullPointerException if the new data is null
        if (data == null) {
            throw new NullPointerException();
        }
        //Throws an IndexOutOfBoundException if index < 0 or >= size of list
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head.next;
        //Iterates node along the list, stopping when node is at the index
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E oldData = node.getElement();
        node.setElement(data);
        return oldData;
    }
    //Remove the node from the position index in this list and return the data 
    //within the removed node.
    /**
     * @param index the position index of where the node will be removed
     * @return the data that was in the removed node
     */
    public E remove(int index) {
        //Throws an IndexOutOfBoundException if index < 0 or >= size of list
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head.next;
        //Iterates node along the list, stopping when node is at the index
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E oldData = node.getElement();
        node.next.setPrev(node.prev);
        node.prev.setNext(node.next);
        size--;
        return oldData;
    }
    //Remove all nodes from the list
    public void clear() {
        size = 0;
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
    }
    //Determine if the list is empty
    /** 
     * @return a boolean that answers if the list is empty
     * */
    public boolean isEmpty() {
        //Returns true if the next pointer of head is pointed at tail and the 
        //previous pointer of tail is pointed at head
        if (head.getNext() == tail && tail.getPrev() == head) {
            return true;
        }
        else {
            return false;
        }
    }

    //Helper method that returns the Node at a specified index, not the content
    /**
     * @param index Takes the specified index of where the node is
     * @return the node at the specified index
     * */
    protected Node getNth(int index) {
        //Throws an IndexOutOfBoundException if index < 0 or >= size of list
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head.next;
        //Iterates node along the list, stopping when node is at the index
        for (int i = 0; i < index; i++) {
            node.setElement(node.next.getElement());
            node = node.next;
        }
        return node;
    }

    /** 
     * This is the MyListIterator class. It contains the constructors, methods, 
     * and instance variables to create my own implementation of the ListIterator
     * interface as an inner class
     */
    protected class MyListIterator implements ListIterator<E> {
        Node left, right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
         * Constructor that is used to initialize the iterator
         */
        public MyListIterator() {
            this.left = head;
            this.right = head.next;
            this.idx = 0;
            this.forward = true;
            this.canRemoveOrSet = false;
        }

        /**
         * Return true if there is an element node when going in the forward
         * (head to tail) direction from the current iterator position. 
         * Sentinel (dummy) nodes do not count as element nodes.
         * @return the boolean of if there is an element node in the forward
         * direction
         */
        public boolean hasNext() {
            //Determines if the next Node is an element node by checking if its
            //data is null
            if (right.data != null) {
                return true;
            }
            else {
                return false;
            }
        }

        /**
         * Return the next element in the list when going forward, and move the 
         * iterator forward by one node.
         * @return element of the node on the right
         */
        public E next() {
            //Check if the node on the right contains a valid element and throw
            //an exception if it does not
            if (right.data == null) {
                throw new NoSuchElementException();
            }
            left = right;
            right = right.next;
            idx++;
            forward = true;
            canRemoveOrSet = true;
            return left.data;
        }

        /**
         * Return true if there is an element node when going in the backward
         * (tail to head) direction from the current iterator position. 
         * Sentinel (dummy) nodes do not count as element nodes.
         * @return the boolean of if there is an element node in the backward
         * direction
         */
        public boolean hasPrevious() {
            //Determines if the previous Node is an element node by checking if 
            //its data is null
            if (left.data != null) {
                return true;
            }
            else {
                return false;
            }
        }

        /**
         * Return the next element in the list when going backward, and move the 
         * iterator forward by one node.
         * @return element of the node on the left
         */
        public E previous() {
            //Check if the node on the right contains a valid element and throw
            //an exception if it does not
            if (left.data == null) {
                throw new NoSuchElementException();
            }
            right = left;
            left = left.prev;
            idx--;
            forward = false;
            canRemoveOrSet = true;
            return right.data;
        }

        /**
         * Return the index of the element that would be returned by a call to 
         * next(). Return the list size if at the end of the list.
         * @return idx plus one, or the size of the list if the index is at the
         * end of the list
         */
        public int nextIndex() {
            if (right.data == null) {
                return size ;
            }
            return idx;
        }

        /**
         * Return the index of the element that would be returned by a call to 
         * previous(). Return -1 if at the start of the list.
         * @return idx minus one
         */
        public int previousIndex() {
            return idx - 1;
        }

        /**
         * Insert the given item into the list immediately before the element 
         * that would be returned by next(). The value of the current index of 
         * the list iterator is increased by one.
         * @param element the element of the new Node
         */

        public void add(E element) {
            //Check if element is null
            if (element == null) {
                throw new NullPointerException();
            }
            Node newNode = new Node(element);
            idx++;
            left.next = newNode;
            left = newNode;
            newNode.next = right;
            right.prev = newNode;
            canRemoveOrSet = false;
            size++;
        }

        /**
         * For the node returned by the most recent next/previous call, replace 
         * its value with the new value element.
         * @param element the new element for the recently called node
         */
        public void set(E element) {
            //Check if element is null
            if (element == null) {
                throw new NullPointerException();
            }
            //Check if canRemoveOrSet is true
            if (canRemoveOrSet == true) {
                //Determine which node to change based on if previous() or 
                //next() were called most recently
                if (forward == true) {
                    left.data = element;
                }
                else {
                    right.data = element;
                }
            }
            else {
                throw new IllegalStateException();
            }
        }

        public void remove() {
            //Check if canRemoveOrSet is true
            if (canRemoveOrSet == true) {
                //Determine which node to remove based on if previous() or 
                //next() were called most recently
                if (forward == true) {
                    left = left.prev;
                    left.next = right;
                    idx--;
                }
                else {
                    right = right.next;
                    left.next = right;
                }
                size--;
                canRemoveOrSet = false;
            }
            else {
                throw new IllegalStateException();
            }
        }
    }
    /**
     * Creates a MyListIterator to override the AbstractList implementations
     * @return a new MyListIterator
     */
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    /**
     * Creates a MyListIterator to override the AbstractList implementations
     * @return a new MyListIterator
     */
    public Iterator<E> iterator() {
        return new MyListIterator();
    }
}