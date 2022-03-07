/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is the custom tester file. It contains tests to verify that
 * Student.java, Course.java, and Sanctuary.java work as intended
 */

import java.util.*;

import javax.crypto.Cipher;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This is the CustomTester class. It houses all the tests that I have created
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {

    // ----------------Student class----------------
    /**
     * Test the equals method when only the PID is different
     */
    @Test
    public void testEquals() {
        Student jim1 = new Student(new String("Jim"), 
            new String("Hee"), new String("A12345678"));
        Student jim2 = new Student(new String("Jim"), 
            new String("Hee"), new String("A12345679"));
        assertFalse(jim1.equals(jim2));
    }

    /**
     * Test the compareTo method when used on identical Students
     */
    @Test
    public void testCompareTo() {
        Student jim1 = new Student(new String("Jim"), 
            new String("Hee"), new String("A12345678"));
        Student jim2 = new Student(new String("Jim"), 
            new String("Hee"), new String("A12345678"));
        assertEquals(0, jim1.compareTo(jim2));
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when enrolling an existing student
     */
    @Test
    public void testEnroll() {
        Course course = new Course("ABC", "123", "Lol", 5);
        Student jim = new Student("Jim", "Hee", "A12345678");
        course.enroll(jim);
        assertFalse(course.enroll(jim));
    }

    /**
     * Test the unenroll method when the course is empty
     */
    @Test
    public void testUnenroll() {
        Course course = new Course("ABC", "123", "Lol", 5);
        Student jim = new Student("Jim", "Hee", "A12345678");
        assertFalse(course.unenroll(jim));
    }

    /**
     * Test the getRoster method when roster is not sorted
     */
    @Test
    public void testGetRoster() {
        Course course = new Course("ABC", "123", "Lol", 5);
        course.enrolled = new HashSet<>();
        Student studentC = new Student("C", "Hee", "A12345678");
        Student studentA = new Student("A", "Hee", "A12345678");
        Student studentB = new Student("B", "Hee", "A12345678");
        course.enroll(studentC);
        course.enroll(studentA);
        course.enroll(studentB);

        ArrayList<Student> enrolledList = new ArrayList<>();
        enrolledList.add(studentA);
        enrolledList.add(studentB);
        enrolledList.add(studentC);
        assertEquals(course.getRoster(), enrolledList);
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor when an invalid argument is passed
     */
    @Test
    public void testSanctuaryConstructor() {
        boolean exception = false;
        try {
            Sanctuary invalid = new Sanctuary(500, -1);
        }
        catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }

    /**
     * Test the rescue method when rescuing a species that nearly has the max
     * amount of animals
     */
    @Test
    public void testRescueTestOne(){
        Sanctuary nearlyFull = new Sanctuary(5, 20);
        nearlyFull.rescue("Baboon", 4);
        assertEquals(nearlyFull.rescue("Baboon", 3), 2);
        assertEquals(nearlyFull.getNum("Baboon"), 5);
    }

    /**
     * Test the rescue method when rescuing a new species when there is a max 
     * amount of species already
     */
    @Test
    public void testRescueTestTwo(){
        Sanctuary full = new Sanctuary(5, 2);
        full.rescue("Baboon", 4);
        full.rescue("Ape", 4);
        assertEquals(full.rescue("Gorilla", 3), 3);
        assertEquals(full.getTotalAnimals(), 8);
        assertEquals(full.getTotalSpecies(), 2);
    }

    /**
     * Test the release method when all the remaining animals of a species are
     * released
     */
    @Test
    public void testReleaseTestOne(){
        Sanctuary full = new Sanctuary(5, 2);
        full.rescue("Baboon", 4);
        full.rescue("Ape", 4);
        full.release("Ape", 4);
        assertEquals(full.getTotalAnimals(), 4);
        assertEquals(full.getTotalSpecies(), 1);
    }

    /**
     * Test the release method when species does not exist in the sanctuary
     */
    @Test
    public void testReleaseTestTwo(){
        boolean exception = false;
        try {
            Sanctuary empty = new Sanctuary(500, 0);
            empty.release("Ape", 4);
        }
        catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }
}

