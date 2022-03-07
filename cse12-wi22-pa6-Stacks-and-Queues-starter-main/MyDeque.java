
/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * This file contains a MyDeque class, which is an implementation of the
 * DequeInterface. Elements can be added/removed into a circular array.
 */

public class MyDeque<E> implements DequeInterface<E> {
    Object[] data;
    int size;
    int rear;
    int front;
    static final int EXPAND_CAPACITY = 10;

    /**
     * Initialize the Object array data with length of initialCapacity
     * @param initialCapacity the length of data
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
        this.rear= 0;
        this.front = 0;
    }

    /**
     * Returns the size of the deque
     * @return the number of elements that exist in the deque 
     */
    public int size() {
        return size;
    }

    /**
     * Expand the capacity of the deque, by creating a new array with the new
     * capacity and copying over the elements from the old array
     */
    public void expandCapacity() {
        Object[] newData;
        Object[] tempData = data;
        //Determine what the new capacity should be
        if (data.length == 0) {
            newData = new Object[EXPAND_CAPACITY];
        }
        else {
            newData = new Object[data.length * 2];
        }
        data = newData;
        //Copy the original elements and size to the expanded list
        for (int i = 0; i < tempData.length; i++) {
            data[i] = tempData[(i + front)%tempData.length];
        }
        front = 0;
        rear = size - 1;
    }
    /**
     * Add the specified element to the front of the deque
     * @param element the specified element
     */
    public void addFirst (E element){
        if (element == null) {
            throw new NullPointerException();
        }
        //Check if the capacity needs to be expanded
        if (data.length == size) {
            expandCapacity();
        }
        // Replace the front value only if the size is greater than 0            
        if (size > 0) {
            front = (front - 1 + data.length) % data.length;
        }
        data[front] = element;
        size++;
    }
    /**
     * Add the specified element to the end of the deque
     * @param element the specified element
     */
    public void addLast (E element){
        if (element == null) {
            throw new NullPointerException();
        }
        //Check if the capacity needs to be expanded
        if (data.length == size) {
            expandCapacity();
        }      
        // Replace the rear value only if the size is greater than 0            
        if (size > 0) {
            rear = (rear + 1 + data.length) % data.length;
        }
        data[rear] = element;
        size++;
    }


    /**
     * Remove the element at the front of the deque
     * @return the element that was removed
     */
    @SuppressWarnings("unchecked")
    public E removeFirst(){           
        if (size == 0){
            return null;
        }
        E element = (E) data[front];
        data[front] = null;
        front = (front + 1 + data.length) % data.length;
        size--;
        return element;
    }
    /**
     * Remove the element at the end of the deque
     * @return the element that was removed
     */
    @SuppressWarnings("unchecked")
    public E removeLast(){           
        if (size == 0){
            return null;
        }
        E element = (E) data[rear];
        data[rear] = null;
        rear = (rear - 1 + data.length) % data.length;
        size--;
        return element;
    }
    /**
     * Return the element at the front of the deque
     * @return the element at the front of the deque
     */
    @SuppressWarnings("unchecked")
    public E peekFirst(){           
        if (size == 0){
            return null;
        }
        return (E) data[front];
    }

    /**
     * Return the element at the end of the deque
     * @return the element at the end of the deque
     */
    @SuppressWarnings("unchecked")
    public E peekLast(){           
        if (size == 0){
            return null;
        }
        return (E) data[rear];
    }
}
