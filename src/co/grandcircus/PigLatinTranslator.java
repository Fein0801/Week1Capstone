package co.grandcircus;

import java.util.ArrayList;
import java.util.Scanner;

public class PigLatinTranslator {

    // "List" of vowels in English
    private static final String[] VOWELS = { "a", "e", "i", "o", "u" };
    private static final String[] IGNORE = { "the", "and" };

    // "List" of indices of first occurrence of each vowel
    private static int[] vowelIndices = new int[VOWELS.length];

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	String output = "";

	String cont = "yes";
	while (cont.equalsIgnoreCase("yes")) {
	    System.out.println("Please enter a sentence to translate.");
	    String input = scan.nextLine();

	    // If line contains spaces, split line and add to lineComponents
	    output = translateLine(input);
	    System.out.println(output);

	    // Exit point
	    System.out.println("Translate another line? (yes/no)");
	    cont = scan.next();

	    scan.nextLine();
	}

	// Prints "Goodbye friend" in pig latin
	System.out.print(translateLine("Goodbye friend"));
	System.out.println("(Goodbye friend)");

	scan.close();
    }

    // Finds the first vowel in the given string
    private static int findFirstVowel(String word) {
	findVowels(word);
	int firstVowelIndex = -1;
	for (int i = 0; i < vowelIndices.length; i++) {
	    int currIndex = vowelIndices[i];

	    if ((firstVowelIndex == -1 && currIndex != -1)) {
		firstVowelIndex = currIndex;
	    }
	    if (currIndex != -1 && currIndex < firstVowelIndex) {
		firstVowelIndex = currIndex;
	    }
	}
	return firstVowelIndex;
    }

    // Finds the FIRST instance of each vowel in str (Never gets called in main)
    private static void findVowels(String word) {
	for (int i = 0; i < VOWELS.length; i++) {
	    vowelIndices[i] = word.indexOf(VOWELS[i]);
	}
    }

    // translates one word into pig latin
    private static String translateWord(String word) {
	int firstVowelIndex = findFirstVowel(word);
	ArrayList<String> ignore = new ArrayList<String>();

	// ignore words like "the"
	for (String s : IGNORE) {
	    ignore.add(s);
	}

	// I want to ignore certain words like "the"
	if (!ignore.contains(word) && word.length() > 2) {
	    if ((firstVowelIndex == -1) || containsNumbersOrSymbols(word)) {
		return word;
	    } else if (firstVowelIndex == 0) {
		return word.concat("way");
	    } else {
		String firstConsonants = word.substring(0, firstVowelIndex);
		return word.substring(firstVowelIndex).concat(firstConsonants).concat("ay");
	    }
	}
	return word;
    }

    // Determines whether a string is a line or one word and calls translateWord()
    // accordingly
    private static String translateLine(String str) {
	ArrayList<String> lineComponents = new ArrayList<String>();

	String finalLine = "";

	str = str.toLowerCase();
	// If the string is more than one word, change to a list of words
	if (str.contains(" ")) {
	    String[] arr = str.split(" ");
	    for (String s : arr) {
		lineComponents.add(s);
	    }

	    // Otherwise add the string to the list
	} else {
	    lineComponents.add(str);
	}

	// Translates each word and then adds spaces
	for (String s : lineComponents) {
	    finalLine = finalLine.concat(translateWord(s)).concat(" ");
	}

	return finalLine;
    }

    // Finds numbers or symbols
    private static boolean containsNumbersOrSymbols(String word) {
	for (int i = 0; i < word.length(); i++) {
	    // If the character is not a letter or an apostrophe
	    if (!Character.isLetter(word.charAt(i)) && (word.charAt(i) != '\'')) {
		return true;
	    }
	}
	return false;
    }

}
