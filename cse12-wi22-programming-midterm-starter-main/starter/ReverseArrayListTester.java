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
public class ReverseArrayListTester {


    Integer[] arrInts = {1, 2, 3, 4, 5};

    private MyArrayList listWithInt;
    /**
     * Run before every test
     */
    @Before
    public void setUp(){
        listWithInt = new MyArrayList<Integer>(arrInts);
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        boolean exception = false;
        try {
            listWithInt.reverseRegion(1, 7);
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
        exception = false;
        try {
            listWithInt.reverseRegion(-1, 7);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals(true, exception);
        assertEquals(4, listWithInt.data[3]);      
        assertEquals(5, listWithInt.data[4]);   
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        listWithInt.reverseRegion(4, 3);
        assertEquals(4, listWithInt.data[3]);      
        assertEquals(5, listWithInt.data[4]);        
  
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){
        listWithInt.reverseRegion(3, 4);
        assertEquals(5, listWithInt.data[3]);      
        assertEquals(4, listWithInt.data[4]); 
    }

    /**
     * Tests reverseRegion method when fromIndex is greater than toIndex and 
     * also out of bounds
    */
    @Test
    public void testReverseCustom(){
        boolean exception = false;
        try {
            listWithInt.reverseRegion(6, 3);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertEquals(true, exception);
        assertEquals(4, listWithInt.data[3]);      
        assertEquals(5, listWithInt.data[4]);   
    }


}
