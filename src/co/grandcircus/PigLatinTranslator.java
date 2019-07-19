package co.grandcircus;

import java.util.ArrayList;
import java.util.Scanner;

public class PigLatinTranslator {

    // "List" of vowels in English
    private static final String[] VOWELS = { "a", "e", "i", "o", "u" };
    private static final String[] IGNORE = { "the", "@" };

    // "List" of indices of first occurence of each vowel
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
    private static int findFirstVowel(String str) {
	findVowels(str);
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
    private static void findVowels(String str) {
	for (int i = 0; i < VOWELS.length; i++) {
	    vowelIndices[i] = str.indexOf(VOWELS[i]);
	}
    }

    private static String translateWord(String str) {
	int firstVowelIndex = findFirstVowel(str);
	ArrayList<String> ignore = new ArrayList<String>();

	// ignore words like "the"
	for (String s : IGNORE) {
	    ignore.add(s);
	}

	// I want to ignore certain words like "the"
	if (!ignore.contains(str) && str.length() > 2) {
	    if ((firstVowelIndex == -1) || containsNumbers(str)) {
		return str;
	    } else if (firstVowelIndex == 0) {
		return str.concat("way");
	    } else {
		String firstConsonants = str.substring(0, firstVowelIndex);
		return str.substring(firstVowelIndex).concat(firstConsonants).concat("ay");
	    }
	}
	return str;
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

	return finalLine; // FIXME return something
    }

    // Finds numbers
    private static boolean containsNumbers(String str) {
	int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	for (int i : numbers) {
	    if (str.contains(Integer.toString(i))) {
		return true;
	    }
	}
	return false;
    }

}
