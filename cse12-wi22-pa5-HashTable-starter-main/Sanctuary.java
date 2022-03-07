/**
 * Name: Ethan Lin
 * ID: A16780861
 * Email: etl003@ucsd.edu
 * Sources used: None
 * 
 * This is the Sanctuary.java file. It contains the framework of the Sanctuary 
 * object I have created
 */
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**This is the Sanctuary class. It houses all the the necessary variables, 
constructors, and methods required for it to function */
public class Sanctuary {
    
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Constructor to create a Sanctuary object
     * @param maxAnimals max amount of animals per species allowed
     * @param maxSpecies max amount of species allowed
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        //Check if arguments are valid
        if (maxAnimals < 0 || maxSpecies < 0) {
            throw new IllegalArgumentException();
        }
        this.sanctuary = new HashMap();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * Gets the number of animals of a specified species
     * @param species the specified species
     * @return amount of animals of the species
     */
    public int getNum(String species) {
        //Check if arguments are valid
        if (species == null) {
            throw new IllegalArgumentException();
        }
        if (sanctuary.containsKey(species)) {
            return sanctuary.get(species);
        }
        return 0;
    }
    
    /**
     * Gets the total number of animals in the Sanctuary
     * @return the total number of animals in the Sanctuary
     */
    public int getTotalAnimals() {
        int total = 0;
        //Iterates through the HashMap and calculates the total amount of
        //animals in the sanctuary
        for (int i: sanctuary.values()){
            total += i;
        }
        return total;
    }
    
    /**
     * Gets the total amount of species in the Sanctuary
     * @return the total amount of species in the Sanctuary
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * Attempts to add some animals to the Sanctuary
     * @param species of the animals being added
     * @param num of animals being added
     * @return how many animals could not be added due to the capacities
     */
    public int rescue(String species, int num) {
        //Check if arguments are valid
        if (sanctuary.size() == maxSpecies && !sanctuary.containsKey(species)) {
            return num;
        }
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        if (species == null) {
            throw new IllegalArgumentException();
        }
        int currentSize = 0;
        
        if (sanctuary.containsKey(species)){
            currentSize = sanctuary.get(species);
            sanctuary.remove(species);
        }
        if ((currentSize + num) > maxAnimals) {
            sanctuary.put(species, maxAnimals);
            return (currentSize + num) - maxAnimals;
        }
        else {
            sanctuary.put(species, (currentSize + num));
            return 0;
        }
    }

    /**
     * Attempts to release some animals from the Sanctuary
     * @param species of the animals being released
     * @param num of animals being released
     */
    public void release(String species, int num) {
        //Check if arguments are valid
        if (species == null || !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        if (num <= 0 || num > sanctuary.get(species)) {
            throw new IllegalArgumentException();
        }
    
        int currentSize = sanctuary.get(species);
        sanctuary.remove(species);
        if (num < currentSize) {
            sanctuary.put(species, (currentSize - num));
        }
    }
}
