/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This file contains custom test cases for MyMinHeap and MyPriorityQueue.
 * I wrote these test cases to ensure that MyMinHeap and MyPriorityQueue work
 * as intended
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains custom test cases for MyMinHeap and MyPriorityQueue.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    
    /**
     * Test the constructor when there is a null in the collection
     */
    @Test
    public void testMyMinHeapConstructor() {
        boolean exception = false;
        ArrayList<Integer> listWithNull = new ArrayList<Integer>(
            Arrays.asList(
                    new Integer[] { 1, 2, null }
            )
        );
        try {
            MyMinHeap<Integer> minHeap = new MyMinHeap<>(listWithNull);
        }
        catch(NullPointerException e) {
            exception = true;
        }
        assertEquals(exception, true);
    }

    /**
     * Test the getMinChildIdx method when the index is of a node with only 
     * one child
     */
    @Test
    public void testGetMinChildIdx() {
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 5, 3}
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        assertEquals(1, heap.getMinChildIdx(0));
        Integer[] expected = { 5, 3 };
        for (int i = 0; i < 2; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

    /**
     * Test the percolateUp method when on a valid index at the end of the heap
     */
    @Test
    public void testPercolateUp() {
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 5, 4, 3, 2, 1 }
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        heap.percolateUp(4);
        Integer[] expected = { 1, 5, 3, 2, 4};
        for (int i = 0; i < 5; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

    /**
     * Test the percolateDown method when both children of an element are equal
     */
    @Test
    public void testPercolateDown() {
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 5, 3, 3}
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        heap.percolateDown(0);
        Integer[] expected = { 3, 5, 3};
        for (int i = 0; i < 3; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when the index is at the end
     */
    @Test
    public void testDeleteIndex() {
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 5, 4, 3, 2, 1}
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        heap.deleteIndex(4);
        Integer[] expected = { 5, 4, 3, 2};
        for (int i = 0; i < 4; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when the index is in the middle of the heap
     */
    @Test
    public void testDeleteIndex2() {
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 6, 5, 4, 3, 2, 1}
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        heap.deleteIndex(3);
        Integer[] expected = { 1, 6, 4, 5, 2};
        for (int i = 0; i < 5; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

    /**
     * Test the insert method when an element that shouldn't be percolated
     * is inserted
     */
    @Test
    public void testInsert(){
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 6, 5, 4, 3, 2, 1}
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        heap.insert(6);
        Integer[] expected = { 6, 5, 4, 3, 2, 1, 6};
        for (int i = 0; i < 7; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

    /**
     * Test the insert method when an element that should be percolated to the
     * middle of the heap is inserted
     */
    @Test
    public void testInsert2(){
        ArrayList<Integer> list = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] { 1, 2, 3, 4, 5, 6}
            )
        );
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = list;
        heap.insert(2);
        Integer[] expected = { 1, 2, 2, 4, 5, 6, 3};
        for (int i = 0; i < 7; i++) {
            assertEquals(
                    expected[i],
                    heap.data.get(i));
        }
    }

   
    /**
     * Test remove when the heap is empty
     */
    @Test
    public void testRemove(){
        ArrayList<Integer> smallList = new ArrayList<Integer>(
            Arrays.asList(
                    new Integer[] {1}));
        MyMinHeap<Integer> smallHeap = new MyMinHeap<>(smallList);
        smallHeap.remove();
        assertEquals(null, smallHeap.remove());
    }

  
    /**
     * Test getMin when the heap is empty
     */
    @Test
    public void testGetMin(){
        ArrayList<Integer> smallList = new ArrayList<Integer>(
            Arrays.asList(
                    new Integer[] {1}));
        MyMinHeap<Integer> smallHeap = new MyMinHeap<>(smallList);
        smallHeap.remove();
        assertEquals(null, smallHeap.getMin());
    }
}