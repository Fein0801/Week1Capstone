package co.grandcircus;

import java.util.ArrayList;
import java.util.Scanner;

public class PigLatinTranslator {

    // "List" of vowels in English
    private static final String[] VOWELS = { "a", "e", "i", "o", "u" };
    private static final String[] IGNORE = {};

    // "List" of indices of first occurence of each vowel
    private static int[] vowelIndices = new int[VOWELS.length];

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	String output = "";

	System.out.println("Please enter a sentence to translate.");
	String input = scan.nextLine();

	// If line contains spaces, split line and add to lineComponents
	output = translateLine(input);

	System.out.println(output);

	scan.close();
    }

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

	for (String s : IGNORE) { // TODO may help me implement certain things, I don't know though
	    ignore.add(s);
	}

	// I want to ignore certain words
	if (!ignore.contains(str) && str.length() > 2) {
	    if (firstVowelIndex == -1) {
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

    private static String translateLine(String str) {
	ArrayList<String> lineComponents = new ArrayList<String>();

	String finalLine = "";
	if (str.contains(" ")) {
	    String[] arr = str.split(" ");
	    for (String s : arr) {
		lineComponents.add(s);
	    }
	} else {
	    lineComponents.add(str);
	}

	for (String s : lineComponents) {
	    finalLine = finalLine.concat(translateWord(s)).concat(" ");
	}
	// Clear the list
	lineComponents.clear();

	return finalLine; // FIXME return something
    }

}
