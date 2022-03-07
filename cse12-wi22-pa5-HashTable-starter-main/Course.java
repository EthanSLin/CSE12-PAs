/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is the Course.java file. It contains the framework of the Course 
 * object I have created
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**This is the Course class. It houses all the the necessary variables, 
constructors, and methods required for it to function */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;
    private final static String SPACE = " ";
    private final static String OPEN_BRACKET = "[";
    private final static String CLOSE_BRACKET = "]";
    private final static String NEW_LINE = "\n";


    /**
     * Constructor to create a Course object
     * @param department the course falls undeer
     * @param number of the course
     * @param description of the course
     * @param capacity max number of students that can be enrolled
     */
    public Course(String department, String number, String description, 
        int capacity){
        if (department == null || number == null || description == null) {
            throw new IllegalArgumentException();
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.enrolled = new HashSet<Student>();
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
    }

    /**
     * Gets the department
     * @return the department
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Gets the number
     * @return the number
     */
    public String getNumber(){
        return number;
    }

    /**
     * 
     * Gets the description
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Gets the capacity
     * @return the capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Enrolls a student into the course
     * @param student the student being enrolled
     * @return boolean of whether or not the student was successfully enrolled
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (enrolled.size() == capacity || enrolled.contains(student)) {
            return false;
        }
        enrolled.add(student);
        if (enrolled.contains(student)) {
            return true;
        }
        return false;
    }

    /**
     * Unenroll a student from the course
     * @param student the student being unenrolled
     * @return boolean of whether or not the student was successfully unenrolled
     */
    public boolean unenroll(Student student) {
        //Check if arguments are valid
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (!enrolled.contains(student)) {
            return false;
        }
        enrolled.remove(student);
        if (!enrolled.contains(student)) {
            return true;
        }
        return false;
    }

    /**
     * Cancels the course and clears all students enrolled
     */
    public void cancel() {
        enrolled.clear(); 
    }

    /**
     * Checks if the course is at its capacity
     * @return boolean of whether or not the course is full
     */
    public boolean isFull() {
        if (enrolled.size() == capacity) { 
            return true;
        }
        return false;
    }

    /**
     * Shows how many students are enrolled in a course
     * @return the number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Shows how many more students can enroll in the course
     * @return number of open spaces
     */
    public int getAvailableSeats() {
        return (capacity - enrolled.size());
    }

    /**
     * Returns a shallow copy of all the students enrolled in the course
     * @return a shallow copy of all the students enrolled in the course
     */
    public HashSet<Student> getStudents() {
        return enrolled;
    }

    /**
     * Presents the roster of the course in a sorted ArrayList
     * @return an arraylist of the students in the course, in increasing order
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> enrolledList = new ArrayList<>();
        //Iterates through the Hashtable and adds all students to the ArrayList
        for (Student i : enrolled) {
            enrolledList.add(i);
        }
        Collections.sort(enrolledList);
        return enrolledList;
    }

    /**
     * Presents the course object in a string form
     * @return the course object in a string form
     */
    public String toString() {
        return (department + SPACE + number + SPACE + OPEN_BRACKET + capacity
         + CLOSE_BRACKET + NEW_LINE + description);
    }
}

