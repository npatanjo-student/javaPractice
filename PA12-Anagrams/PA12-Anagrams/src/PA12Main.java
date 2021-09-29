import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * AUTHOR: Nate Patanjo
 * FILE: PA12Main.java
 * ASSIGNMENT: Anagrams
 * COURSE: CSC 210
 * PURPOSE: The purpose of this program is to take in a dictionary,
 * a string, and a max. The program finds all the anagrams in the string
 * from the dictionary word bank. If the max is 0 than it will
 * give all of the anagrams.
 * 
 * USAGE:
 * 
 * input:
 * dict1.txt barbarabush 0
 * 
 * output:
 * Phrase to scramble: barbarabush
 *
 * All words found in barbarabush:
 * [abash, aura, bar, barb, brush, bus, hub, rub, shrub, sub]
 *
 * Anagrams for barbarabush:
 * [abash, bar, rub]
 * [abash, rub, bar]
 * [bar, abash, rub]
 * [bar, rub, abash]
 * [rub, abash, bar]
 * [rub, bar, abash]
 *
 */
public class PA12Main {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	String path = args[0];
    	String word = args[1];
    	int max = Integer.valueOf(args[2]);
    	List<String> dictSet = extract(path); 
    	LetterInventory inventory = new LetterInventory(word);
    	List<String> choices = findChoices(dictSet, inventory);
    	System.out.println("Phrase to scramble: " + word);
    	System.out.println();
    	System.out.println("All words found in " + word + ":");
    	System.out.println(choices);
    	System.out.println();
    	System.out.println("Anagrams for " + word + ":");
    	anagram(inventory, choices, max);
    }
    
    /**
     * Extract. Extracts the contents of the file
     * and puts it in a list
     *
     * @param path the path
     * @return the list
     */
    public static List<String> extract(String path) {
    	Scanner scanner = null;
        List<String> dictList = new ArrayList<String>();
    	try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
        	String line = scanner.nextLine();
        	dictList.add(line);
        }
        scanner.close();
        return dictList;
    }
    
    /**
     * Find choices. Finds the different choices. These are the words
     * in the dictionary that are made up of words from arg 1
     *
     * @param dictList the dict list
     * @param inventory the inventory
     * @return the list
     */
    public static List<String> findChoices(List<String> dictList, LetterInventory inventory) {
    	List<String> choices = new ArrayList<String>();
    	for (String dictWord : dictList) {
    		if (inventory.contains(dictWord)) {
    			choices.add(dictWord);
    		}
    	}
    	return choices;
    }
    
    /**
     * Anagram. Creates a chosen list and checks to see if there is a specified max
     * if there is than it calls a backtracking method for max.
     *
     * @param inventory the inventory
     * @param choices the choices
     * @param max the max
     */
    public static void anagram(LetterInventory inventory, List<String> choices, int max) {
    	List<String> chosen = new ArrayList<String>();
    	if (max == 0) {
    		anagramHelperNoMax(inventory, choices, chosen);
    	} else {
    		anagramHelper(inventory, choices, chosen, max);
    	}
    }
    
    /**
     * Anagram helper. This is the backtracking method for max input
     *
     * @param inventory the inventory
     * @param choices the choices
     * @param chosen the chosen
     * @param max the max
     */
    public static void anagramHelper(LetterInventory inventory, List<String> choices, List<String> chosen, int max) {
    	if (inventory.isEmpty()) {
    		System.out.println(chosen.toString());
    	}
    	for (int i = 0; i < choices.size(); i++) {
    		if (inventory.contains(choices.get(i)) && chosen.size() < max) {
    			chosen.add(choices.get(i));
    			inventory.subtract(choices.get(i));
    			anagramHelper(inventory, choices, chosen, max);
    			inventory.add(choices.get(i));
    			chosen.remove(chosen.size() - 1);
    		}
    	}
    }
    
    /**
     * Anagram helper no max. this is the backtracking method for no max
     *
     * @param inventory the inventory
     * @param choices the choices
     * @param chosen the chosen
     */
    public static void anagramHelperNoMax(LetterInventory inventory, List<String> choices, List<String> chosen) {
    	if (inventory.isEmpty()) {
    		System.out.println(chosen.toString());
    	}
    	for (int i = 0; i < choices.size(); i++) {
    		if (inventory.contains(choices.get(i))) {
    			chosen.add(choices.get(i));
    			inventory.subtract(choices.get(i));
    			anagramHelperNoMax(inventory, choices, chosen);
    			inventory.add(choices.get(i));
    			chosen.remove(chosen.size() - 1);
    		}
    	}
    }
}
