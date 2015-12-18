package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * This is a class that is meant to be used in a static way. The main method
 * takes in two arguments: a string file name to a grammar file and an integer.
 * This class generates a library from which random phrases are generated. When
 * the class is ran, it will return a number of random phrases equivalent to the
 * number passed in and these phrases will be generated from the given grammar
 * file.
 * 
 * @author Jordan Hensley, Romney Doria, jHensley, doria CS 2420-fall 2015
 *         comprehensive Last Updated: 12/08/2015
 *
 */
public class RandomPhraseGenerator {

	private static boolean inComment = true;
	// This hashmap holds arraylists of character arrays that represent the
	// terminals in the grammar file. The keys are the non-terminal group to
	// which they belong to.
	public static HashMap<String, ArrayList<char[]>> library = new HashMap<String, ArrayList<char[]>>();

	/**
	 * This method prints a number of random phrases generated from the given
	 * grammar file correlating to the number that is passed in. If there are
	 * more or less arguments than two, the method will print an error. If the
	 * second parameter cannot be parsed to an integer, the method will print an
	 * error.
	 * 
	 * @param args
	 *            -The first item in the args array is the grammar file name and
	 *            the second is the number of phrases to print
	 */
	public static void main(String[] args) {
		// Ensure the correct number of arguments
		if (args.length != 2)
			System.err.println("Incorrect number of parameters");
		else {
			int x = Integer.parseInt(args[1]);
			// Ensure the second argument is a positive integer.
			if (x < 0)
				System.err.println("The integer parameter must be positive.");
			else {
				// Create the library using the filename and print the phrases
				generateLibrary(args[0]);
				for (int i = 0; i < x; i++) {
					System.out.println(printSinglePhrase() + "\n");
				}
			}
		}
	}

	/**
	 * This method takes in a filename correlating to a grammar file and creates
	 * the library needed to generate random phrases.
	 * 
	 * @param filename
	 *            - The name of the grammar file from which to generate the
	 *            phrases
	 */
	public static void generateLibrary(String filename) {
		library = new HashMap<String, ArrayList<char[]>>();
		try {
			Scanner scan = new Scanner(new File(filename));
			String tempString = "";
			ArrayList<char[]> tempList = new ArrayList<char[]>();

			while (scan.hasNextLine()) {
				String c = scan.nextLine();
				// When an open-bracket appears, the file is no longer in
				// comment and the next line is the non-terminal declaration.
				if (c.equals("{")) {
					if (!inComment)
						tempList.add(c.toCharArray());
					else {
						inComment = false;
						c = scan.nextLine();
						// holds the name of the non-terminal
						tempString = c;
						c = scan.nextLine();
					}
				}
				// Declaration is done, add it to the library.
				if (c.equals("}")) {
					if (!tempString.equals("")) {
						library.put(tempString, tempList);
						tempList = new ArrayList<char[]>();
					}
					inComment = true;
				}
				// Add the terminal as a character array to the library
				if (!inComment)
					tempList.add(c.toCharArray());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// print an error if the file is not found
			System.err.println("File could not be found.");
		}
	}

	/**
	 * This method returns a String containing a random phrase that was
	 * generated from the library.
	 */
	public static String printSinglePhrase() {

		// Holds the next thing to be read
		Stack<char[]> myStack = new Stack<char[]>();

		// Holds the phrase being built and a possible next non-terminal
		StringBuilder phrase = new StringBuilder("");
		StringBuilder nextNonTerminal = new StringBuilder("");
		Random rng = new Random();
		// Grab a random starting point and start building the phrase
		myStack.push(library.get("<start>").get(rng.nextInt(library.get("<start>").size())));

		stackLoop: while (!myStack.isEmpty()) {
			// Pop off the next item to be printed
			char[] tempArray = myStack.pop();
			for (int i = 0; i < tempArray.length; i++) {
				char c = tempArray[i];
				// a possible start to a non-terminal is found
				if (c == '<')
					nextNonTerminal.append(c);

				else if (c == '>') {
					// a non-terminal has been found
					if (nextNonTerminal.length() > 0) {
						nextNonTerminal.append(c);
						// push the remaining items in this array on the stack
						myStack.push(Arrays.copyOfRange(tempArray, i + 1, tempArray.length));

						// push a random terminal from the non-terminal
						// declaration on the stack
						myStack.push(library.get(nextNonTerminal.toString())
								.get(rng.nextInt(library.get(nextNonTerminal.toString()).size())));
						nextNonTerminal.setLength(0);

						// continue the outer loop
						continue stackLoop;
					} else
						phrase.append(c);

				} else if (c != ' ') {
					// if currently reading a potential non-terminal add the
					// character to it, else, just add it to the phrase
					if (nextNonTerminal.length() != 0)
						nextNonTerminal.append(c);
					else
						phrase.append(c);

				} else {
					// if a space is read and the program is reading a potential
					// non-terminal, clear the non-terminal and add its contents
					// to the phrase, else just add it.
					if (nextNonTerminal.length() != 0) {
						nextNonTerminal.append(c);
						phrase.append(nextNonTerminal);
						nextNonTerminal.setLength(0);
					} else
						phrase.append(c);
				}
			}
			// if the array is empty in a possible non-terminal, append to the
			// phrase
			if (nextNonTerminal.length() != 0) {
				phrase.append(nextNonTerminal);
				nextNonTerminal.setLength(0);
			}
		}
		return phrase.toString();
	}

}