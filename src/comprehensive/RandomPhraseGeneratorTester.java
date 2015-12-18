package comprehensive;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A class used to test the quality and functionality of the
 * RandomPhraseGenerator class. This class contains junit test cases to ensure
 * the quality of the program. Randomness has been tested by observing different
 * phrases being produced.
 * 
 * @author Jordan Hensley, Romney Doria, jHensley, doria CS 2420-fall 2015
 *         comprehensive Last Updated: 12/08/2015
 *
 */
public class RandomPhraseGeneratorTester {

	/**
	 * This test ensures that a simple terminal in the <start> definition is
	 * printed
	 */
	@Test
	public void testNoOtherNonTerminals() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest1.g");
		assertEquals("this is a test", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that a non-terminal inside of a terminal prints out
	 * correctly.
	 */
	@Test
	public void testMultipleNonTerminals() {
		// Tests comments
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest2.g");
		assertEquals("This is a test", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures the preservation of the lack of a space between a
	 * terminal and a period.
	 */
	@Test
	public void testEndingCharactersWithNonTerminals() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest3.g");
		assertEquals("This is to test this period.", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures the preservation of the lack of a space between a
	 * non-terminal and a period.
	 */
	@Test
	public void testPeriodFollowingNonTerminal() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest4.g");
		assertEquals("This period should come after this non-terminal.", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that numerous characters can be printed preserving the
	 * lack of space immediately following a non-terminal.
	 */
	@Test
	public void testMultipleEndingCharactersAfterNonTerminal() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest5.g");
		assertEquals("Here are multiple characters??!..!?", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that the rpg prints out the correct result when the
	 * definition for start is only one non-terminal.
	 */
	@Test
	public void testOnlyATerminal() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest6.g");
		assertEquals("This is a test.", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures the preservation of a lack of space between a terminal
	 * and non-terminal.
	 */
	@Test
	public void testNonTerminalBeforeTerminal() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest7.g");
		assertEquals("This is a test(should be no space between me).", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures the preservation of a lack of space when a non-terminal
	 * immediately after a terminal.
	 */
	@Test
	public void testNonTerminalAfterTerminal() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest8.g");
		assertEquals("nospace", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that a non-terminal surrounded by two terminals without
	 * a space before it or after it will return the correct phrase.
	 */
	@Test
	public void testNonTerminalBetweenTerminals() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest9.g");
		assertEquals("Testthisterminal", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that two non-terminals immediately next to one another
	 * will return the phrase in the correct format, maintaining a lack of
	 * space.
	 */
	@Test
	public void testAdjacentNonTerminals() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest10.g");
		assertEquals("A and test should have no space: Atest", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that non-terminals may appear in the terminals of other
	 * non-terminal declarations other than <start>.
	 */
	@Test
	public void testNonTerminalsInOtherTerminals() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest11.g");
		assertEquals("print this word.", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that a terminal in a declaration other than "start" can
	 * have multiple words and non-terminals.
	 */
	@Test
	public void testTerminalsWithManyTerminalsAndNonTerminals() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest12.g");
		assertEquals("Here is a statement: I am a string of words with a non-terminal in me.",
				RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that brackets can appear in terminals and not try to
	 * find a non-terminal definition.
	 */
	@Test
	public void testATerminalWithAOpeningBracket() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest13.g");
		assertEquals("This< >should be able< to> print like> this<.", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures that angle brackets may appear next to each other in a
	 * terminal so long that it doesn't take the form <>.
	 */
	@Test
	public void testNonTerminalWithBrackets() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest14.g");
		assertEquals("This should print these brackets: < > > < < >>><", RandomPhraseGenerator.printSinglePhrase());
	}

	/**
	 * This test ensures the rpg can handle when there is an open-bracket in the
	 * title of a non-terminal.
	 */
	@Test
	public void testOpenAngleBracketInNonTerminalName() {
		RandomPhraseGenerator.generateLibrary("RPG_Tests/RPGTest15.g");
		assertEquals("this is a test.", RandomPhraseGenerator.printSinglePhrase());
	}

}