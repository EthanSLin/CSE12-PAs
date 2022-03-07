/**
 * This is my implementation of a Calendar
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is a class file that contains the framework of MyCalendar. It contains 
 * the variables, constructors, and methods required to implement my version of
 * a Calendar
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * Constructor that initalizes the calendar object
     */
    public MyCalendar() {
        this.calendar = new MyTreeMap<>();
    }
    
    /**
     * Books a new event
     * @param start of the event
     * @param end of the event
     * @return whether or not the event was successfully booked
     */
    public boolean book(int start, int end) {
        if (start < 0 || start >= end) {
            throw new IllegalArgumentException();
        }
        Integer latestBefore = calendar.get(calendar.floorKey(start));
        Integer earliestAfter = calendar.ceilingKey(start);
        // Checks of the adding the event would cause double booking
        if ((latestBefore == null || latestBefore <= start) 
        && (earliestAfter == null || earliestAfter >= end)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

    /**
     * Returns the calendar
     * @return the calendar
     */
    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}