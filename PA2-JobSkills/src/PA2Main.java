/* 
 * AUTHOR: Nate Patanjo
 * FILE: PA2Main.java
 * ASSIGHNMENT: PA1 - Job Skills
 * COURSE: CSC210 - Spring 2020
 * PURPOSE: this program allows the user to find out specific
 * information about Jobs and Job Skills from any inputed .csv. It has two commands:
 * the first command is CATCOUNT which prints out the number of
 * positions for each category. The second command is LOCATIONS
 * which gives all the locations each category has. 
 * 
 * USAGE:
 * args[0]: .CSV file
 * args[1]: Command (LOCATIONS, CATCOUNT)
 * args[2]: Category for LOCATIONS command
 * 
 * ----------- EXAMPLE INPUT -------------
 * CATCOUNT:
 * ---------------------------------------
 * example.csv CATCOUNT
 * 
 * ----------- EXAMPLE OUTPUT ------------
 * CATCOUNT:
 * ---------------------------------------
 * Number of positions for each category
 * ---------------------------------------
 * CategoryX, 3
 * CategoryY, 1
 * 
 * ----------- EXAMPLE INPUT -------------
 * LOCATIONS:
 * ---------------------------------------
 * example.csv LOCATIONS CategroyX
 * 
 * ----------- EXAMPLE OUTPUT ------------
 * CATCOUNT:
 * ---------------------------------------
 * LOCATIONS for category: CategoryX
 * ---------------------------------------
 * Jonesboro, 1
 * Tel Aviv, 2 
 *
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Main Method
 * Method calls the function corresponding to what args[1] is. 
 */
public class PA2Main {
    public static void main(String[] args) {
        if (args[1].equals("CATCOUNT")) {
            extractFileCatcount(args[0]);
        } else if (args[1].equals("LOCATIONS")) {
            extractFileLocations(args[0], args[2]);
        } else {
            System.out.println("Invalid Command");
        }
    }

    /*
     * Method is only called if args[1] is CATCOUNT. This method
     * scans through the .csv file. It then adds each NEW category
     * as a key in the catcountMap HashMap and the Integer 1 as the value.
     * If the category is already a key in the Map, this method adds 1
     * to the Integer.
     * 
     * The end result will be every category found in the .csv as keys,
     * and a number specifying how many times that category was found in
     * the .csv as the value.
     * 
     * Finally, this method calls the print function
     * 
     * @param filename
     * this is the .csv filename given as args[0]
     * 
     */
    public static void extractFileCatcount(String filename) {
        Map<String, Integer> catcountMap = new HashMap<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            if (!catcountMap.containsKey(line[2])) {
                catcountMap.put(line[2], 1);
            } else {
                Integer valueCount = catcountMap.get(line[2]);
                valueCount++;
                catcountMap.put(line[2], valueCount);
            }
        }
        catcountMapPrinter(catcountMap);
    }

    /*
     * Method is only called if args[1] is LOCATIONS. This method scans though
     * the .csv file. It adds the location name, to locationsMap, for every line
     * that contains the category specified (args[2]). If the location IS NOT
     * already in the Map, it adds the location as a Key and the Integer 1 as
     * the
     * value. If the location IS in the Map already, the method adds 1 to the
     * corresponding value.
     * 
     * The end result will be every location found in the .csv, with the
     * specified category in the line, as keys, and a number specifying how many
     * times that location was found in the .csv as the value (only is the
     * category
     * was present in the line).
     * 
     * Finally, this method calls the print function
     * 
     * @param filename
     * this is the .csv filename given as args[0]
     * 
     * @param category
     * this is the category specified in args[2].
     * the method will use this string to add a location, if the category is
     * present
     * in the line being traversed
     */
    public static void extractFileLocations(String filename, String category) {
        Map<String, Integer> locationsMap = new HashMap<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            if (line[2].equals(category)) {
                if (!locationsMap.containsKey(line[3])) {
                    locationsMap.put(line[3], 1);
                } else {
                    Integer valueCount = locationsMap.get(line[3]);
                    valueCount++;
                    locationsMap.put(line[3], valueCount);
                }
            }
        }
        locationsMapPrinter(locationsMap, category);
    }

    /*
     * This is the print method for CATCOUNT. This method first,
     * prints out the title prompt, then it sorts the keys in the
     * catcountMap then prints "{key}, {value}" in that sorted order.
     * 
     * @param map
     * This is the catcountMap from the extractFileCatcount method.
     * It is type: HashMap with a String key and an Integer Value
     */
    public static void catcountMapPrinter(Map<String, Integer> map) {
        System.out.println("Number of positions for each category");
        System.out.println("-------------------------------------");
        List<String> sortedKeys = new ArrayList<String>(map.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            System.out.println(key + ", " + map.get(key));
        }
    }

    /*
     * This is the print method for LOCATIONS. This method first,
     * prints out the title prompt (using the category string),
     * then it sorts the keys in the catcountMap then prints
     * "{key}, {value}" in that sorted order.
     * 
     * @param map
     * This is the catcountMap from the extractFileCatcount method.
     * It is type: HashMap with a String key and an Integer Value
     * 
     * @param category
     * this is the category String (args[2]) used for printing
     * the category in the print prompt.
     */
    public static void locationsMapPrinter(Map<String, Integer> map,
            String category) {
        System.out.println("LOCATIONS for category: " + category);
        System.out.println("-------------------------------------");
        List<String> sortedKeys = new ArrayList<String>(map.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            System.out.println(key + ", " + map.get(key));
        }
    }
}


