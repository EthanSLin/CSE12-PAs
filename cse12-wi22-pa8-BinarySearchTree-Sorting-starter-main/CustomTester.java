/**
 * This is my Tester file for MyBST.java, MyBSTIterator.java, and 
 * MyCalendar.java
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is my custom tester file. It ensures that MyBST.java, 
 * MyBSTIterator.java, and MyCalendar.java function as intended
 */
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;


public class CustomTester {

    MyBST<Integer, Integer> completeTree;
    MyBST<Integer, Integer> emptyTree;


    // 
    /**
     * The setup method create a complete tree with a height of three
     * The tree has the following structure and will be used for testing
     *           4
     *         /  \
     *       2     6
     *     /  |   /
     *    1   3  5
     */
    @Before
    public void setup(){

        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = 
            new MyBST.MyBSTNode(2, 2, root);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode(6, 3, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode(1, 4, two);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode(3, 5, two);
        MyBST.MyBSTNode<Integer, Integer> five = 
            new MyBST.MyBSTNode(5, 6, six);

        this.completeTree = new MyBST();
        this.completeTree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        this.completeTree.size = 6;

        this.emptyTree = new MyBST();

    }
    // Test insert method when the key is an existing key 
    @Test
    public void testInsert1(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertEquals((Integer)6, completeTree.insert(5, 1));
        assertEquals((Integer)5, root.getRight().getLeft().getKey());
    }
    // Test insert method when the key is the existing key of the root
    @Test
    public void testInsert2(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertEquals((Integer)1, completeTree.insert(4, 10));
        assertEquals((Integer)10, root.getValue());
    }
    // Test search method when the key is null
    @Test
    public void testSearch(){
        assertEquals(null, completeTree.search(null));
    }
    // Test remove method when a node with two children is removed
    @Test
    public void testRemove1(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertEquals((Integer)2, completeTree.remove(2));
        assertNull(root.getLeft().getRight());
        assertEquals((Integer)3, root.getLeft().getKey());
        assertEquals((Integer)5, root.getLeft().getValue());
    }
    // Test remove method when the root is removed
    @Test
    public void testRemove2(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertEquals((Integer)1, completeTree.remove(4));
        assertNull(root.getRight().getLeft());
        assertEquals((Integer)5, root.getKey());
        assertEquals((Integer)6, root.getValue());
    }
    // Test inorder method when the tree is empty
    @Test
    public void testInorder(){
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes 
            = new ArrayList<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actualRes 
            = emptyTree.inorder();
        for (int i=0; i<expectedRes.size(); i++){
            assertSame(expectedRes.get(i), actualRes.get(i));
        }
    }
    // Test the iterator when there is no next node
    @Test
    public void testIterator(){
        MyBSTIterator<Integer, Integer> iteratorTree = new MyBSTIterator();
        iteratorTree.root = completeTree.root;
        boolean exception = false;
        MyBSTIterator<Integer, Integer>.MyBSTValueIterator valueIterator = 
            iteratorTree.new MyBSTValueIterator(iteratorTree.root);
        valueIterator.nextNode();
        valueIterator.nextNode();
        valueIterator.nextNode();
        try {
            valueIterator.nextNode();
        }
        catch (NoSuchElementException e) {
            exception = true;
        }
        assertTrue(exception);
    }
    // Test the book method when it tries to book an event that starts earlier
    // and ends later than an existing event
    @Test
    public void testBook1(){
        MyCalendar cal = new MyCalendar();
        cal.getCalendar();
        cal.book(10, 20);
        assertFalse(cal.book(5, 25));
    }
    // Test the book method when start >= end
    @Test
    public void testBook2(){
        MyCalendar cal = new MyCalendar();
        cal.getCalendar();
        cal.book(10, 20);
        boolean exception = false;
        try { 
            cal.book(40, 30);
        }
        catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }
    // Test the book method when start < 0
    @Test
    public void testBook3(){
        MyCalendar cal = new MyCalendar();
        cal.getCalendar();
        cal.book(10, 20);
        boolean exception = false;
        try { 
            cal.book(-10, 0);
        }
        catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }
}