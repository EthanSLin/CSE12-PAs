/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is the Student.java file. It contains the framework of the Student 
 * object I have created
 */
import java.util.Objects;

//This is the Student class. It houses all the the necessary variables, 
//constructors, and methods required for it to function
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Constructor to create a Student object
     * @param firstName of Student
     * @param lastName of Student
     * @param PID of Student
     */
    public Student(String firstName, String lastName, String PID) {
        //Check if arguments are valid
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * Gets last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * gets first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets PID
     * @return PID
     */
    public String getPID() {
        return PID;
    }

    @Override
    /**
     * Checks if all instance variables of two Student objects are equal
     * @param o the Student object being checked against
     * @return whether or not they are equal
     */
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Student)) {
            return false;
        }
        boolean matchFirstName = ((Student) o).getFirstName().equals(firstName);
        boolean matchLastName = ((Student) o).getLastName().equals(lastName);
        boolean matchPID = ((Student) o).getPID().equals(PID);
        if (matchFirstName && matchLastName && matchPID) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    /**
     * Returns the hash value
     * @return hash value generated with the firstName, lastName, and PID
     */
    public int hashCode() {
        return (Objects.hash(firstName, lastName, PID));
    }

    @Override
    /**
     * Evalues the difference between two Student objects
     * @param o Student object being compared against
     * @return 0 if they are equal, positive value if the current object 
     * lexicographically comes after Student, negative value if the current 
     * object lexicographically comes before Student o
     */
    public int compareTo(Student o) {
        int matchFirstName = firstName.compareTo(((Student) o).getFirstName());
        int matchLastName = lastName.compareTo(((Student) o).getLastName());
        int matchPID = PID.compareTo(((Student) o).getPID());
        if (matchFirstName == 0 && matchLastName == 0 && matchPID == 0) {
            return 0;
        }
        else if (matchLastName != 0) {
            return matchLastName;
        }
        else if (matchFirstName != 0) {
            return matchFirstName;
        }
        else {
            return matchPID;
        }
    }
}
