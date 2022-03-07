/**
 * Add your file header
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: None. Put "None" if you did not have any external help
 * 
 * This is a custom tester file for PA3. It contains test cases for the 
 * MyLinkedList and Node classes.
 */

import static org.junit.Assert.*;
import org.junit.*;

/** 
 * This is the custom tester class. It will run tests to ensure MyLinkedList 
 * works as expected
 * 
 * IMPORTANT: Do not change the method headers and points are awarded 
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
	private MyLinkedList<Integer> emptyIntegerList;
	private MyLinkedList<String> threeStringList;
	private String[] strData = {"ahaha", "aheehee", "ahoohoo"};
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	
	@Before
	public void setUp() throws Exception {
		emptyIntegerList = new MyLinkedList<Integer>();
		threeStringList = new MyLinkedList<>();
	}
	private void populateLinkedList() {
		MyLinkedList<String>.Node node0 =  
			this.threeStringList.new Node(this.strData[0]);
		MyLinkedList<String>.Node node1 =  
			this.threeStringList.new Node(this.strData[1]);
		MyLinkedList<String>.Node node2 =  
			this.threeStringList.new Node(this.strData[2]);

		this.threeStringList.head.next = node0;
		node0.prev = this.threeStringList.head;
		node0.next = node1;
		node1.prev = node0;
		node1.next = node2;
		node2.prev = node1;
		node2.next = this.threeStringList.tail;
		this.threeStringList.tail.prev = node2;
		this.threeStringList.size = 3;
	}
	/**
	 * test the add method when a null value is added to a not empty list
	 */
	@Test
	public void testAdd() {
		boolean exception = false;
        try {		
			this.populateLinkedList();
			this.threeStringList.add(null);
        }
        catch (NullPointerException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}
	//test the add method when a null value is added to an empty list

	@Test
	public void testAdd2() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.add(null);
        }
        catch (NullPointerException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the add with index method when a null value is added to a 
	 * non-empty list
	 */
	@Test
	public void testAddWithIndexTestOne() {
		boolean exception = false;
        try {		
			this.threeStringList.add(1, null);
        }
        catch (NullPointerException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the add with index method when index > number of elements in
	 * the list
	 */	
	@Test
	public void testAddWithIndexTestTwo() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.add(1, 3);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the get method when index > number of elements in the list
	 */
	@Test
	public void testGet() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.get(1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the getNth method when index > number of elements in the list
	 */
	@Test
	public void testGetNth() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.getNth(1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the set method when index > number of elements in the list
	 */
	@Test
	public void testSet() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.set(1, 2);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}
	/**
	 * test the set method when when a null value is added to a non-empty
	 * list
	 */
	@Test
	public void testSet2() {
		boolean exception = false;
        try {		
			this.threeStringList.set(1, null);
        }
        catch (NullPointerException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the remove method when index > number of elements in the list
	 */
	@Test
	public void testRemoveTestOne() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.remove(1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}
	
	/**
	 * test the remove method when index < 0
	 */
	@Test
	public void testRemoveTestTwo() {
		boolean exception = false;
        try {		
			this.emptyIntegerList.remove(-1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals("Exception caught", true, exception);
	}

	/**
	 * test the clear method when the list is empty
	 */
	@Test
	public void testClear() {
		emptyIntegerList.clear();
		assertEquals("Size should be updated", 0, emptyIntegerList.size());
		assertTrue("LinkedList should be empty", emptyIntegerList.isEmpty());
	}

	/**
	 * test the size method when the size is 3
	 */
	@Test
	public void testSize() {
		this.populateLinkedList();
		assertEquals("A list with 3 elements have a size of 3", 
			3, threeStringList.size());
	}
	//test the isEmpty method when the list is empty
	@Test
	public void testIsEmpty() {
		assertTrue("An empty list should be empty", 
			emptyIntegerList.isEmpty());
	}
}