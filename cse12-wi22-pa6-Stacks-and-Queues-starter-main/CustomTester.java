/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * This file contains the custom tests I have written. They test MyDeque.java,
 * MyStack.java, and MyQueue.java, making sure they run properly
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class contains my custom test cases for MyDeque, MyStack, and MyQueue
 *  
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when capacity is 0
     */
    @Test
    public void testMyDequeConstructor() {
        MyDeque<Integer> deque = new MyDeque<>(0);
        assertEquals(0, deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);
    }

    /**
     * Test the expandCapacity method when front > rear in a deque with
     * several elements in place already
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque<Integer> origDeque = new MyDeque<>(6);
        origDeque.addFirst(3);
        origDeque.addLast(4);
        origDeque.addFirst(2);
        origDeque.addFirst(1);
        origDeque.expandCapacity();
        MyDeque<Integer> finalDeque = new MyDeque<>(12);
        finalDeque.addLast(1);
        finalDeque.addLast(2);
        finalDeque.addLast(3);
        finalDeque.addLast(4);
        for (int i = 0; i < finalDeque.size; i++) {
            assertEquals(origDeque.data[i], finalDeque.data[i]);
        }
        assertEquals(origDeque.front, finalDeque.front);
        assertEquals(origDeque.rear, finalDeque.rear);
        assertEquals(origDeque.size, finalDeque.size);
    }

    /**
     * Test the addFirst method when deque is full
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> origDeque = new MyDeque<>(2);
        origDeque.addFirst(3);
        origDeque.addLast(4);
        origDeque.addFirst(2);
        MyDeque<Integer> finalDeque = new MyDeque<>(4);
        finalDeque.addFirst(3);
        finalDeque.addLast(4);
        finalDeque.addFirst(2);
        for (int i = 0; i < finalDeque.size(); i++) {
            assertEquals(origDeque.data[i], finalDeque.data[i]);
        }
        assertEquals(origDeque.front, finalDeque.front);
        assertEquals(origDeque.rear, finalDeque.rear);
        assertEquals(origDeque.size, finalDeque.size);
    }

    /**
     * Test the addLast method when deque is full
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> origDeque = new MyDeque<>(2);
        origDeque.addFirst(3);
        origDeque.addLast(4);
        origDeque.addLast(5);
        MyDeque<Integer> finalDeque = new MyDeque<>(4);
        finalDeque.addFirst(3);
        finalDeque.addLast(4);
        finalDeque.addLast(5);
        for (int i = 0; i < finalDeque.size(); i++) {
            assertEquals(origDeque.data[i], finalDeque.data[i]);
        }
        assertEquals(origDeque.front, finalDeque.front);
        assertEquals(origDeque.rear, finalDeque.rear);
        assertEquals(origDeque.size, finalDeque.size);
    }

    /**
     * Test the removeFirst method when front > rear in a deque with
     * several elements in place already
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> origDeque = new MyDeque<>(6);
        origDeque.addFirst(3);
        origDeque.addLast(4);
        origDeque.addFirst(2);
        origDeque.removeFirst();
        MyDeque<Integer> finalDeque = new MyDeque<>(6);
        finalDeque.addFirst(3);
        finalDeque.addLast(4);
        for (int i = 0; i < finalDeque.size; i++) {
            assertEquals(origDeque.data[i], finalDeque.data[i]);
        }
        assertEquals(origDeque.front, finalDeque.front);
        assertEquals(origDeque.rear, finalDeque.rear);
        assertEquals(origDeque.size, finalDeque.size);
    }

    /**
     * Test the removeLast method when front > rear in a deque with
     * several elements in place already
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> origDeque = new MyDeque<>(6);
        origDeque.addFirst(3);
        origDeque.addFirst(4);
        origDeque.addLast(2);
        origDeque.removeLast();
        MyDeque<Integer> finalDeque = new MyDeque<>(6);
        finalDeque.addFirst(3);
        finalDeque.addFirst(4);
        for (int i = 0; i < finalDeque.size; i++) {
            assertEquals(origDeque.data[i], finalDeque.data[i]);
        }
        assertEquals(origDeque.front, finalDeque.front);
        assertEquals(origDeque.rear, finalDeque.rear);
        assertEquals(origDeque.size, finalDeque.size);
    }

    /**
     * Test the peekFirst method when front > rear in a deque with
     * several elements in place already
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> origDeque = new MyDeque<>(6);
        origDeque.addFirst(3);
        origDeque.addLast(4);
        origDeque.addFirst(2);
        assertEquals(2, (int) origDeque.peekFirst());
    }

    /**
     * Test the peekLast method when front > rear in a deque with
     * several elements in place already
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> origDeque = new MyDeque<>(6);
        origDeque.addFirst(3);
        origDeque.addLast(4);
        origDeque.addFirst(2);
        assertEquals(4, (int) origDeque.peekLast());
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when pushing to a full stack
     */
    @Test
    public void testMyStack(){
        MyQueue<Integer> origQueue = new MyQueue<>(2);
        origQueue.enqueue(3);
        origQueue.enqueue(4);
        origQueue.enqueue(2);
        MyQueue<Integer> finalQueue = new MyQueue<>(4);
        finalQueue.enqueue(3);
        finalQueue.enqueue(4);
        finalQueue.enqueue(2);
        assertEquals(origQueue.size(), finalQueue.size());    
        for (int i = 0; i < origQueue.size() + 2; i++) {
            assertEquals(origQueue.dequeue(), finalQueue.dequeue());
        }
        assertEquals(0, origQueue.size());    

        assertEquals(true, origQueue.empty());    
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when enqueueing to a full Queue
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> origQueue = new MyQueue<>(2);
        origQueue.enqueue(3);
        origQueue.enqueue(4);
        origQueue.enqueue(2);
        MyQueue<Integer> finalQueue = new MyQueue<>(4);
        finalQueue.enqueue(3);
        finalQueue.enqueue(4);
        finalQueue.enqueue(2);
        assertEquals(origQueue.size(), finalQueue.size());    
        for (int i = 0; i < origQueue.size() + 2; i++) {
            assertEquals(origQueue.dequeue(), finalQueue.dequeue());
        }
        assertEquals(0, origQueue.size());    
        assertEquals(true, origQueue.empty());   
    }
}
