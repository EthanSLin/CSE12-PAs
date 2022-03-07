/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements
import static org.junit.Assert.*;
import org.junit.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class ReverseLinkedListTester {


    Integer[] arrInts = {1, 2, 3, 4, 5};

    private MyLinkedList listWithInt;
    /**
     * Run before every test
     */
    @Before
    public void setUp(){
        listWithInt = new MyLinkedList<Integer>(arrInts);
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        boolean exception = false;
        try {
            listWithInt.reverseRegion(1, 5);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals(true, exception);
        exception = false;
        try {
            listWithInt.reverseRegion(-1, 3);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals(true, exception);
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        listWithInt.reverseRegion(4, 3);
        assertEquals(listWithInt.getNth(3).data, 4);      
        assertEquals(listWithInt.getNth(4).data, 5);        
  
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){
        listWithInt.reverseRegion(3, 4);
        assertEquals(5, listWithInt.getNth(3).data);      
        assertEquals(4, listWithInt.getNth(4).data); 
    }

    /**
     * Tests reverseRegion method when it is called on an empty list. Previous
     * tests were done on populatated lists, as opposed to a null list here
    */
    @Test
    public void testReverseCustom(){
        boolean exception = false;
        try {
            listWithInt.reverseRegion(-1, 1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals(true, exception);
    }


}
