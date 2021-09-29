/* 
 * AUTHOR: Nate Patanjo
 * FILE: WordSearch.java
 * ASSIGHNMENT: PA1 - WordSearch
 * COURSE: CSC210 - Spring 2020
 * PURPOSE: This program uses two arguments: a dictionary of  words,
 * and a word search board. It prints out each word from your dictionary 
 * that are found in the word search. The order these words are printed:
 * First, each row - left to right.
 * Second, each row - right to left
 * Third, Each column - top to bottom
 * Last, Each column - bottom to top
 * 
 * USAGE:
 * Dictionary file
 * Word Search file
 * 
 * ----------- EXAMPLE INPUT -------------
 * Dictionary file:
 * ---------------------------------------
 * the
 * cool
 * Happy
 * wow
 * ----------- EXAMPLE INPUT -------------
 * Word Search file:
 * ---------------------------------------
 * t h e h k s
 * w a f j d k
 * o p c o o l
 * w p f j d k
 * y y e i x p
 * ----------- EXAMPLE OUTPUT -------------
 * 
 * ----------------------------------------
 * the
 * cool
 * wow
 * happy
 * wow
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSearch {
    /*
     * This is how you declare constants in Java. You can now type
     * 'MIN_WORD_LENGTH' anywhere you want to refer to it.
     */
    private static final int MIN_WORD_LENGTH = 3;

    /*
     * main method
     */
    public static void main(String[] args) {
        List<String> dictionaryArr = findDictArr(args[0]);
        String[] searchArr = readFileName(args[1]);
        searchDispatcher(searchArr, dictionaryArr);
        }

        /*
         * Method creates an ArrayList consisting of all the values in the
         * given dictionary
         * 
         * @param filename
         * This is the name of the file given through the command line
         * for the dictionary
         * 
         * @return dictArrList
         * this is an ArrayList consisting of all the words in the
         * dictionary
         */
    public static List<String> findDictArr(String filename) {
            List<String> dictArrList = new ArrayList<String>();
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                dictArrList.add(scanner.nextLine().toLowerCase());
            }
            return dictArrList;
        }

        /*
         * Method creates an array with each index representing a line from
         * the file.
         * 
         * @param filename
         * this is the name of the file given through the command line
         * for the word search
         * 
         * @return searchArr
         * this is the an array containing the rows
         */
    public static String[] readFileName(String filename) {
        Scanner scanner = null;
        int lineCounter = 0;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int rowInt = Integer.valueOf(scanner.nextLine());
        scanner.nextLine();
        String[] searchArr = new String[rowInt];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase();
            searchArr[lineCounter] = line;
            lineCounter++;
        }
        scanner.close();
        return spaceDelete(searchArr);
    }
    
    /*
     * method deletes the space from the searchArr Array.
     * we can't have a space in the array for the lines to be iterated
     * through properly
     * 
     * @param searchArr
     * this is the array consisting of each line of the file
     * including spaces
     * 
     * @return unspacedSearchArr
     * this is the same as the searchArr param, but without spaces.
     */
    public static String[] spaceDelete(String[] searchArr) {
        String[] unspacedSearchArr = new String[searchArr.length];
        for (int i = 0 ; i < searchArr.length ; i++) {
            String line = searchArr[i].replaceAll(" ", "");
            unspacedSearchArr[i] = line;
        }
        return unspacedSearchArr;
    }
    
    /*
     * method calls functions in the correct printing order. It calls
     * the deliverLR to find words from each line left to right.
     * DeliverRL to find words right to left. deliverTB to find
     * words from each column top down. deliverBT to find words from every
     * column bottom up. It adds the given ArrayLists to a single one
     * called foundWordsList
     * This is also where the words are printed once the method calls
     * are complete.
     * 
     * @param seachArr
     * this is an array consisting of each line of the word search
     * 
     * @param dictArrList
     * this is an ArrayList that is contains all the words in the dictionary
     * used for the word search
     */
    public static void searchDispatcher(String[] searchArr,
            List<String> dictArrList) {

        List<String> foundWordsList = new ArrayList<String>();
        foundWordsList.addAll(deliverLR(searchArr, dictArrList));
        foundWordsList.addAll(deliverRL(searchArr, dictArrList));
        // an array of the columns similar too searchArr
        String[] colSearchArr = colGenerator(searchArr);
        foundWordsList.addAll(deliverTB(colSearchArr, dictArrList));
        foundWordsList.addAll(deliverBT(colSearchArr, dictArrList));
        for (String i : foundWordsList) {
            System.out.println(i);
        }
    }
    
    /*
     * method iterates through the searchArr array and calls the
     * wordSearchOporation method with each index. each index represents
     * one line of the word search. This one iterates from left to right.
     * 
     * @param searchArr
     * this is an array consisting of each line of the word search
     * 
     * @param dictArrList
     * this is an ArrayList that is contains all the words in the dictionary
     * used for the word search
     * 
     * @return localFoundWordsList
     * this is an ArrayList containing all the words found with the iteration
     * direction specific to this method. (left -> right)
     */
    public static List<String> deliverLR(String[] searchArr,
            List<String> dictArrList) {
        List<String> localFoundWordsList = new ArrayList<String>();
        for (int i = 0; i < searchArr.length; i++) {
            localFoundWordsList
                    .addAll(wordSearchOperation(searchArr[i], dictArrList));
        }
        return localFoundWordsList;
    }

    /*
     * method iterates through the searchArr array and calls the
     * wordSearchOporation method with each index. each index represents
     * one line of the word search. This one iterates from right to left.
     * 
     * @param searchArr
     * this is an array consisting of each line of the word search
     * 
     * @param dictArrList
     * this is an ArrayList that is contains all the words in the dictionary
     * used for the word search
     * 
     * @return localFoundWordsList
     * this is an ArrayList containing all the words found with the iteration
     * direction specific to this method. (right -> left)
     */
    public static List<String> deliverRL(String[] searchArr,
            List<String> dictArrList) {
        List<String> localFoundWordsList = new ArrayList<String>();
        for (int line = 0; line < searchArr.length; line++) {
            String passStr = "";
            for (int i = searchArr[line].length() - 1; i >= 0; i--) {
                passStr += searchArr[line].charAt(i);
            }
            localFoundWordsList
                    .addAll(wordSearchOperation(passStr, dictArrList));
        }
        return localFoundWordsList;
    }

    /*
     * method takes the searchArr array and makes a new array that
     * is arranged in order for each column from top to bottom. It
     * first creates a new array, then iterates through searchArr creating
     * new strings at every given index. It then adds these strings to the
     * colSearchArr array.
     * 
     * @param searchArr
     * this is an array consisting of each line of the word search
     * 
     * @return
     * this is a new array containing each column of the word search
     * from top to bottom
     */
    public static String[] colGenerator(String[] searchArr) {
        int colCount = searchArr[0].length();
        int counter = 0;
        String[] colSearchArr = new String[colCount];
        for (int i = 0; i < colCount; i++) {
            String tmp = "";
            for (String line : searchArr) {
                tmp += line.charAt(counter);
            }
            colSearchArr[counter] = tmp;
            counter++;
        }
        return colSearchArr;
    }

    /*
     * method iterates through the column modified searchArr array and calls the
     * wordSearchOporation method with each index. each index represents
     * one column of the word search. This one iterates from left to right.
     * 
     * @param searchArr
     * this is an array consisting of each line of the word search
     * 
     * @param dictArrList
     * this is an ArrayList that is contains all the words in the dictionary
     * used for the word search
     * 
     * @return localFoundWordsList
     * this is an ArrayList containing all the words found with the iteration
     * direction specific to this method. (left -> right) visually however,
     * from top to bottom on the word search.
     */
    public static List<String> deliverTB(String[] searchArr,
            List<String> dictArrList) {
        List<String> localFoundWordsList = new ArrayList<String>();
        for (int i = 0; i < searchArr.length; i++) {
            localFoundWordsList
                    .addAll(wordSearchOperation(searchArr[i], dictArrList));
        }
        return localFoundWordsList;
    }

    /*
     * method iterates through the column modified searchArr array and calls the
     * wordSearchOporation method with each index. each index represents
     * one column of the word search. This one iterates from left to right.
     * 
     * @param searchArr
     * this is an array consisting of each line of the word search
     * 
     * @param dictArrList
     * this is an ArrayList that is contains all the words in the dictionary
     * used for the word search
     * 
     * @return localFoundWordsList
     * this is an ArrayList containing all the words found with the iteration
     * direction specific to this method. (right -> left) visually however,
     * from bottom to top on the word search.
     */
    public static List<String> deliverBT(String[] searchArr,
            List<String> dictArrList) {
        List<String> localFoundWordsList = new ArrayList<String>();
        for (int line = 0; line < searchArr.length; line++) {
            String passStr = "";
            for (int i = searchArr[line].length() - 1; i >= 0; i--) {
                passStr += searchArr[line].charAt(i);
            }
            localFoundWordsList
                    .addAll(wordSearchOperation(passStr, dictArrList));
        }
        return localFoundWordsList;
    }
    
    /*
     * method creates an ArrayList of of the words present in the
     * given string. It checks each possible letter variation to see
     * if it is present in the dictionary. If if the word is present
     * the word is added to the ArrayList.
     * 
     * @param searchLine
     * this is a line of letters that the function will use to search.
     * each on of these is a set of letters from the previously used
     * "searchArr" or "colSearchArr"
     * 
     * @param dictArrList
     * this is an ArrayList that is contains all the words in the dictionary
     * used for the word search
     * 
     * @return
     * this is an ArrayList containing all the words found in the given
     * line.
     */
    public static List<String> wordSearchOperation(String searchLine,
            List<String> dictionary) {
        List<String> retWordsList = new ArrayList<String>();
        // the length of the row
        int rowLen = searchLine.length();
        // the start of the word being checked in dict
        int checkStart = 0;
        // the end of the word being checking in dict
        int checkEnd = (checkStart + MIN_WORD_LENGTH);
        while (checkStart < rowLen - (MIN_WORD_LENGTH - 1)) {
            String tmpStr = searchLine.substring(checkStart, checkEnd);
            if (dictionary.contains(tmpStr)) {
                retWordsList.add(tmpStr);
            }
            if (checkEnd == rowLen) {
                checkStart++;
                checkEnd = (checkStart + MIN_WORD_LENGTH) - 1;
            }
            checkEnd++;
        }
        return retWordsList;
    }
}

























