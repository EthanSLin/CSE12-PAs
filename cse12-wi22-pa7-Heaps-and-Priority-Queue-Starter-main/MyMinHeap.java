
/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This file contains the framework of my implementation of a min heap
 */

// Your import statements
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains the framework for a custom version of a min heap. I 
 * created instance variables, methods, and constructors to make my version of
 * a min heap
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{

    public ArrayList<E> data;

    /**
     * No arg cpnstructor that initializes data to an empty ArrayList
     */
    public MyMinHeap() {
        data = new ArrayList<>();
    }
    /**
     * Initializes a min-heap using the elements in collection
     * @param collection the collection of elements
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        //Iterate through data, percolating each element down
        if (data.size() > 1) {
            for (int i = data.size() - 1; i >= 0; i--) {
                percolateDown(i);
            }
        }

    }
    /**
     * Swap the elements at from and to indices in data.
     * @param from an element being swapped
     * @param to an element being swapped
     */
    protected void swap(int from, int to) {
        E temp = data.get(from);
        data.set(from, data.get(to));
        data.set(to, temp);
    }
    /**
     * Calculate and return the parent index of the parameter index.
     * @param index the parameter index
     * @return the parent index
     */
    protected int getParentIdx(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1)>>1;
    }
    /**
     * Calculate and return the left child index of the parameter index.
     * @param index the parameter index
     * @return the left child index
     */
    protected int getLeftChildIdx(int index) {
        return (index<<1) + 1;
    }
    /**
     * Calculate and return the right child index of the parameter index.
     * @param index the parameter index
     * @return the right child index
     */
    protected int getRightChildIdx(int index) {
        return (index<<1) + 2;
    }
    /**
     * Return the index of the smaller child of the element at index.
     * @param index the parameter index
     * @return the index of the smaller child
     */
    protected int getMinChildIdx(int index) {
        //If the node at index is a leaf (has no children), return -1.
        if ((index<<1) + 1 > data.size() - 1) {
            return -1;
        }
        //If the node has only one child, return the index of the left child
        else if ((index<<1) + 1 == data.size() - 1) {
            return (index<<1) + 1;
        }
        //Return the index of the right child only if it is smaller than the
        //left child
        else{
            if ((data.get((index<<1) + 1))
            .compareTo(data.get((index<<1) + 2)) > 0) {
                return (index<<1) + 2;
            }
            else {
                return (index<<1) + 1;
            }
        }
        
    }
    /**
     * Percolate the element at index up until no heap properties are violated 
     * by this element 
     * @param index of the element being percolated
     */
    protected void percolateUp(int index) {
        int currIdx = index;
        int parentIdx = getParentIdx(index);
        //Keep percolating until the heap properties are violated
        while ((data.get(currIdx)).compareTo(data.get(parentIdx)) < 0) {
            swap(currIdx, parentIdx);
            currIdx = parentIdx;
            parentIdx = getParentIdx(currIdx);
        }
    }
    /**
     * Percolate the element at index down until no heap properties are violated 
     * by this element 
     * @param index of the element being percolated
     */
    protected void percolateDown(int index) {
        int currIdx = index;
        int childIdx = getMinChildIdx(index);
        //Keep percolating until the heap properties are violated or one of the
        //the children is a leaf        
        while ((childIdx != -1) && 
        (data.get(currIdx)).compareTo(data.get(childIdx)) > 0) {
            swap(currIdx, childIdx);
            currIdx = childIdx;
            childIdx = getMinChildIdx(currIdx);
        }
    }
    /**
     * Remove the element at index from data and return it.
     * @param index the parameter index
     * @return the element at index
     */
    protected E deleteIndex(int index) {
        E element = data.get(index);
        if (index < data.size() - 1) {
            swap(index, (data.size()-1));
            percolateUp(index);
            percolateDown(index);
        }
        data.remove(data.size()-1);
        return element;
    }
    /**
     * Add element to the end of the heap and percolate it up as far as possible
     * @param element the element beimg inserted
     */
    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size() - 1);
    }
    /**
     * Return the root (this will be the smallest) element of the heap.
     * @return the root
     */
    public E getMin() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(0);
    }
    /**
     * Remove and return the root (this will be the smallest) element in the 
     * heap.
     * @return the root
     */
    public E remove() {
        if (data.size() == 0) {
            return null;
        }
        return deleteIndex(0);
    }
    /**
     * Return the number of elements in this min-heap
     * @return the size
     */
    public int size() {
        return data.size();
    }
    /**
     * Clear out the heap
     */
    public void clear() {
        data.clear();
    }
}