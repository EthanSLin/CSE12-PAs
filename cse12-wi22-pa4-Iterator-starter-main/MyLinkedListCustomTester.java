
/**
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: None. Put "None" if you did not have any external help
 * 
 * This is a custom tester file for PA4. It contains test cases for the 
 * MyListIterator, MyLinkedList, and Node classes
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * This is the custom tester class. It will run tests to ensure 
 * MyListIterator.java works as expected
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList listLen0, listLen1, listLen2;
    private MyLinkedList.MyListIterator listLen0Iter, listLen1Iter, listLen2Iter;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen0 = new MyLinkedList<>();
        listLen0Iter = listLen0.new MyListIterator();
        
        listLen1 = new MyLinkedList();
        listLen1.add("aheehee");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("aha");
        listLen2.add("aho");
        listLen2Iter = listLen2.new MyListIterator();

        

    }

    @Test
    public void testHasNext() {
        assertFalse("call hasNext when there isn't a next node", 
        listLen0Iter.hasNext());
    }

    @Test
    public void testNext() {
        try {
            listLen0Iter.next();
        }
        catch (NoSuchElementException e) {}
        
    }

    @Test
    public void testHasPrevious() {
        assertFalse("call hasPrevious when there isn't a previous node", 
        listLen0Iter.hasPrevious());
    }

    @Test
    public void testPrevious() {
        try {
            listLen0Iter.previous();
        }
        catch (NoSuchElementException e) {}
    }


    @Test
    public void testNextIndex() {
        assertEquals("Index when empty list", 0, listLen0Iter.nextIndex());
    }


    @Test
    public void testPreviousIndex() {
        assertEquals("Index when empty list", -1, listLen0Iter.previousIndex());
    }


    @Test
    public void testSet() {
        try {
            listLen0Iter.set("ahaha?");
        }
        catch (IllegalStateException e) {}
    }

 
    @Test
    public void testRemoveTestOne() {
        try {
            listLen0Iter.remove();
        }
        catch (IllegalStateException e) {}
    }


    @Test
    public void testRemoveTestTwo() {
        listLen1Iter.next();
        listLen1Iter.add("ooh");
        try {
            listLen1Iter.remove();
        }
        catch (IllegalStateException e) {}
    }

    @Test
    public void testAdd() {
        listLen0Iter.add("ahoohoo");
        assertEquals("Valid add left", "ahoohoo",
                listLen0Iter.left.getElement());
        assertEquals("Valid add right", null,
                listLen0Iter.right.getElement());
        assertEquals("Index change after add", 1, listLen0Iter.idx);
        assertFalse("Cannot remove immediately after add",
                listLen0Iter.canRemoveOrSet);
    }

}