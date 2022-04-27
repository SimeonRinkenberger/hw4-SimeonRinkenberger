/**
 * Simeon Rinkenberger
 * EGR227 Data Structures
 * 4-30-22
 *
 * A helper class for Anagram.java. This class keeps track of an inventory
 * of letters of the alphabet.
 */
public class LetterInventory {
    private final int ALPHABETSIZE = 26;

    public int[] inventory;
    private int countSum = 0;

    /**
     * Constructor that initializes an empty inventory
     */
    public LetterInventory() {
        inventory = new int[ALPHABETSIZE];
    }

    /**
     * Constructor that initializes the inventory with the letters from
     * the passed string
     * @param data is for the constructor to use to initialize an inventory
     */
    public LetterInventory(String data) {
        inventory = new int[ALPHABETSIZE];
        for (int i = 0; i < data.length(); i ++) {
            int letter = data.charAt(i);
            if (letter >= 'A' && letter <= 'Z') {
                inventory[letter - 'A']++;
            } else if (letter >= 'a' && letter <= 'z') {
                inventory[letter - 'a']++;
            }
        }
    }

    /**
     * This method finds the value of a letter is in the inventory
     * @param letter the character to be searched for in the inventory
     * @return the value of the letter in the inventory
     */
    public int get(char letter) {
        if (letter < 'A' || (letter > 'Z' && letter < 'a') || letter > 'z')
            throw new IllegalArgumentException("non-alphabetic character was passed");

        if (letter < 'a') return inventory[letter - 'A'];
        else return inventory[letter - 'a'];
    }

    /**
     * This method changes the value of the letter in the inventory
     * @param letter the character to be searched for in the inventory
     * @param value the new value to change to
     */
    public void set(char letter, int value) {
        if (letter < 'A' || (letter > 'Z' && letter < 'a') || letter > 'z')
            throw new IllegalArgumentException("non-alphabetic character was passed");

        if (letter < 'a') inventory[letter - 'A'] = value;
        else inventory[letter - 'a'] = value;
    }

    /**
     * This method find the sum of all the counts in the inventory.
     * Only finds the sum of the counts the first time
     * @return the sum of the counts in the inventory
     */
    public int size() {
        if (countSum == 0) {
            for (int count : inventory) {
                countSum += count;
            }
        }
        return countSum;
    }

    /**
     * This method decides finds if the inventory is empty
     * @return whether the inventory is empty
     */
    public boolean isEmpty() {
        for (int count : inventory) {
            if (count != 0) return false;
        }
        return true;
    }

    /**
     * Converts the inventory into a string representation
     * all letters are lowercase in sorted order with [] on both sides
     * @return the string representation of the inventory
     */
    public String toString() {
        StringBuilder inventoryString = new StringBuilder("[");
        for(int i = 0; i < inventory.length; i ++) {
            for (int j = 0; j < inventory[i]; j ++) {
                inventoryString.append((char)('a' + i));
            }
        }
        inventoryString.append("]");
        return inventoryString.toString();
    }

    /**
     * Constructs a new LetterInventory that represents the sum of the current
     * inventory added to another inventory
     * @param other the inventory to be added to the current inventory
     * @return the new LetterInventory
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory sum = new LetterInventory();
        sum.inventory = inventory.clone();
        for (int i = 0; i < inventory.length; i ++) {
            sum.inventory[i] += other.inventory[i];
        }
        return sum;
    }

    /**
     * Constructs a new LetterInventory that represents the difference of
     * the current inventory and another inventory
     * @param other the inventory to be subtracted to the current inventory
     * @return the new LetterInventory
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory difference = new LetterInventory();
        difference.inventory = inventory.clone();
        for (int i = 0; i < inventory.length; i ++) {
            difference.inventory[i] -= other.inventory[i];
            if (difference.inventory[i] < 0) return null;
        }
        return difference;
    }

    /**
     * Finds the percentage of a letter in the inventory
     * @param letter the letter to find the percentage for
     * @return the percentage of the letter in the inventory
     */
    public double getLetterPercentage(char letter) {
        if (letter < 'A' || (letter > 'Z' && letter < 'a') || letter > 'z')
            throw new IllegalArgumentException("non-alphabetic character was passed");

        if (isEmpty()) return 0;

        if (letter < 'a') return ((double) inventory[letter - 'A']) / ((double) size());
        else return ((double) inventory[letter - 'a']) / ((double) size());
    }
}
