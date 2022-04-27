import java.util.*;

/**
 * Simeon Rinkenberger
 * EGR227 Data Structures
 * 4-30-22
 *
 * This class contains the methods necessary for the AnagramMain.java class.
 * It stores a dictionary and inventories and uses recursive backtracking
 * to find anagrams for a given word or phrase.
 */
public class Anagrams {
    private List<String> dictionary;
    private Map<String, LetterInventory> inventoryMap = new HashMap<>();

    /**
     * Constructor that initializes the dictionary to be used and finds an
     * inventory for each word
     * @param dictionary the dictionary that the dictionary will be
     *                   initialized to
     */
    public Anagrams(List<String> dictionary) {
        this.dictionary = dictionary;
        for (String word : dictionary) {
            LetterInventory inventory = new LetterInventory(word);
            inventoryMap.put(word, inventory);
        }
    }

    /**
     * A method that uses recursive backtracking to find combinations
     * of at most max words from dictionary that are anagrams of text
     * This method first prunes the dictionary to only the relevant
     * words and then start the recursive backtracking
     * @param text the word or phrase that anagrams are being found for
     * @param max the most amount of words to include in an anagram
     */
    public void print(String text, int max) {
        if (max < 0) throw new IllegalArgumentException("max cannot be less than 0");

        List<String> prunedDict = prune(dictionary, new LetterInventory(text));
        List<String> relevantWords = new Stack<>();
        recursivePrint(prunedDict, new LetterInventory(text), max, relevantWords);
    }

    /**
     * A helper method to prune the dictionary to only include words
     * that could be an anagram for textInventory
     * @param dictionary the original dictionary
     * @param textInventory a letterInventory that words are being
     *                      found for
     * @return a dictionary that only includes the words that could
     *          be anagrams for textInventory
     */
    private List<String> prune(List<String> dictionary, LetterInventory textInventory) {
        List<String> prunedDict = new Stack<>();
        for (String word : dictionary) {
            LetterInventory wordInventory = new LetterInventory(word);
            if (textInventory.subtract(wordInventory) != null) {
                prunedDict.add(word);
            }
        }
        return prunedDict;
    }

    /**
     * The method for the recursive backtracking. This method calls itself
     * exhaustively search for anagrams.
     * @param prunedDict the dictionary that has been pruned to only contain
     *                   relevant words
     * @param letterInventory the inventory of the word or phrase that anagrams
     *                        are being found for
     * @param max the max number of words for the anagrams
     * @param relevantWords the relevant words for the anagrams to be
     *                      formed from
     */
    private void recursivePrint(List<String> prunedDict, LetterInventory letterInventory, int max, List<String> relevantWords) {
        if (letterInventory.isEmpty()) {
            // base case - prints the anagrams
            System.out.print("[");
            for (int i = 0; i < relevantWords.size() - 1; i ++) {
                System.out.print(relevantWords.get(i) + ", ");
            }
            if (!relevantWords.isEmpty()) {
                System.out.print(relevantWords.get(relevantWords.size() - 1));
            }
            System.out.println("]");
        } else if (relevantWords.size() < max || max == 0) {
            //recursive case - as long as the inventory is not empty, more words are subtracted
            for (int i = 0; i < prunedDict.size(); i++) {
                String word = prunedDict.get(i);
                LetterInventory nextInventory = letterInventory.subtract(inventoryMap.get(word));
                if (nextInventory != null) {
                    relevantWords.add(word);
                    recursivePrint(prunedDict, nextInventory, max, relevantWords);
                    relevantWords.remove(relevantWords.size() - 1);
                }
            }
        }
    }
}
