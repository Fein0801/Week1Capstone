package co.grandcircus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PigLatinTranslator {

    // "List" of vowels in English
    private static final String[] VOWELS = { "a", "e", "i", "o", "u" };

    // "List" of indices of first occurence of each vowel
    private static int[] vowelIndices = new int[VOWELS.length];

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	String output = "";

	ArrayList<String> lineComponents = new ArrayList<String>();

	String[] testStrings = { "Hello my baby", "Hello my honey", "open the door" };
	String[] testWords = { "this", "hello", "WORD", "Benjamin" };

	findVowels(testWords[3].toLowerCase());

	System.out.println(Arrays.toString(vowelIndices));
	System.out.println(findFirstVowel(testWords[3].toLowerCase()));

	System.out.println("Please enter a sentence to translate.");
	String input = scan.nextLine();

	// If line contains spaces, split line and add to lineComponents
	if (input.contains(" ")) {
	    String[] arr = input.split(" ");
	    for (String s : arr) {
		lineComponents.add(s);
	    }
	} else {
	    lineComponents.add(input);
	}

	// Clear the list
	lineComponents.clear();

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

    // Finds the FIRST instance of each vowel in str //Never gets called in main
    private static void findVowels(String str) {
	for (int i = 0; i < VOWELS.length; i++) {
	    vowelIndices[i] = str.indexOf(VOWELS[i]);
	}
    }

    private static String translateWord(String str, int firstVowelIndex) {

	return ""; // FIXME actually do something
    }

}
