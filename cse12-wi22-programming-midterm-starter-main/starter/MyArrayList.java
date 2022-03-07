/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * File description: This is the MyArrayList class file. It contains the 
 * framework for the MyArrayList class
 */

/**
 * This is the class MyArrayList. It lists the methods,
 * constructors, and variables required to create an implementation of
 * ArrayList
 */
public class MyArrayList<E> implements MyReverseList<E> {
    static final int DEFAULT_CAPACITY = 5;
    
    Object[] data;
    int size;

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!

    /**
     * Constructor to create an array list with the given array's elements
     * @param arr - array of elements to be used to construct the ArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            this.data = arr.clone();
            this.size = arr.length;
        }
    }

    /**
     * Reverses the elements in the list between fromIndex and toIndex 
     * @param fromIndex index of an element to be reversed
     * @param toIndex index of another element to be reversed
     */
    public void reverseRegion(int fromIndex, int toIndex){
        //Throws an exception if either of the indexes are out of bounds
        if (fromIndex > size - 1|| toIndex > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        //Only changes the list if fromIndex is less than toIndex
        if (fromIndex < toIndex) {
            Object[] newData = new Object[DEFAULT_CAPACITY];
            Object fromData = data[fromIndex];
            Object toData = data[toIndex];
            //Iterates through the list and swaps the elements of the objects 
            //at fromIndex and toIndex
            for (int i = 0; i < size; i++) {
                if (i == fromIndex) {
                    newData[i] = toData;
                }
                else if (i == toIndex) {
                    newData[i] = fromData;
                }
                else {
                    newData[i] = data[i];
                }
            }
            data = newData;
        }



    }

    @Override
    /**
     * A method that returns the number of valid elements
     * in the ArrayList 
     * @return - number of valid elements in the arraylist
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * A method that returns an Element at the specified index
     * @param index - the index of the return Element
     * @return Element at specified index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
