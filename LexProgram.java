import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Program with help from textbook Ch 4 in iCollege (edited version of front.c)
 */

public class LexProgram {

	public static char lexeme[] = new char[100];
	public static char nextChar;
	public static int lexLen;
	public static int token;
	public static int nextToken;

	public static String text;

	// charClass (Character Classes)
	public static int charClass;
	public static final int LETTER = 100;
	public static final int DIGIT = 101;
	public static final int UNKNOWN = 99;
	public static final char EOF = '&';

	// Token Codes
	public static int ADD_OP = 1;
	public static int SUB_OP = 2;
	public static int MULT_OP = 3;
	public static int DIV_OP = 4;
	public static int MOD = 5;
	public static int LESSTHAN = 6;
	public static int GREATERTHAN = 7;
	public static int LESSTHANOREQUAL = 8;
	public static int GREATERTHANOREQUAL = 9;
	public static int EQUALS_OP = 10;
	public static int NOTEQUAL_OP = 11;
	public static int ASSIGN_OP = 12;
	public static int LEFT_PAREN = 13;
	public static int RIGHT_PAREN = 14;
	public static int SEMICOLON = 15;

	public static int INT_LIT = 20;
	public static int IDENT = 21;

	public static void main(String[] args) throws IOException {

		// try out the 4 test files
		File testFile = new File("TestFile.txt");
		Scanner scanner = new Scanner(testFile);

		int lineNum = 0;
		// Error if test file is empty
		if (testFile.length() == 0) {
			System.out.println("Error, cannot read empty file");
		} else {
			// Read each line, print it, and analyze it
			while (scanner.hasNext()) {
				text = scanner.nextLine();
				System.out.println("Line " + lineNum + ": " + text);
				text = text + '&';
				lineNum++;

				getChar();

				do {
					lex();
				} while (nextToken != '&');

				System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}
		scanner.close();
	} // end of main

	/***
	 * lookup(): Computes the token code for single-character tokens and arithmetic
	 * operators || look up operators and () and returns the token
	 */
	public static int lookup(char ch) {
		switch (ch) {

		case '(':
			addChar();
			nextToken = LEFT_PAREN;
			break;

		case ')':
			addChar();
			nextToken = RIGHT_PAREN;
			break;

		case '+':
			addChar();
			nextToken = ADD_OP;
			break;

		case '-':
			addChar();
			nextToken = SUB_OP;
			break;

		case '*':
			addChar();
			nextToken = MULT_OP;
			break;

		case '/':
			addChar();
			nextToken = DIV_OP;
			break;

		case '=':
			addChar();
			nextToken = EQUALS_OP;
			break;

		case ';':
			addChar();
			nextToken = SEMICOLON;
			break;

		case '<':
			addChar();
			nextToken = LESSTHAN;
			break;

		case '>':
			addChar();
			nextToken = GREATERTHAN;
			break;

		default:
			addChar();
			nextToken = EOF;
			break;
		}
		return nextToken;
	}

	/*** addChar(): Adds the character in nextChar to the end of Array lexeme */
	public static void addChar() {
		if (lexLen <= 98) {
			lexeme[lexLen++] = nextChar;
			lexeme[lexLen] = '\0';
		} else {
			System.out.println("Error, lexeme is too long");
		}
	}

	/***
	 * getChar(): gets next input character and puts it in a Class variable named
	 * nextChar
	 */
	public static void getChar() throws IOException {

		// Getting character will get the first character of the text string
		nextChar = text.charAt(0);
		// Update the text string
		text = text.substring(1);

		// Assign the character to the class it belongs to
		if (nextChar != EOF) {

			if (Character.isLetter(nextChar)) {
				charClass = LETTER;
			} else if (Character.isDigit(nextChar)) {
				charClass = DIGIT;
			} else {
				charClass = UNKNOWN;
			}
		} else {
			charClass = EOF;
		}
	}

	/***
	 * getNonBlank() - function to call getChar until it returns a non-whitespace
	 * character
	 * 
	 * @throws IOException
	 */
	public static void getNonBlank() throws IOException {
		while (nextChar == ' ') {
			getChar();
		}
	}

	/***
	 * lex(): lexical analyzer for arithmetic expressions
	 * 
	 * @return nextToken
	 * @throws IOException
	 */
	public static int lex() throws IOException {
		lexLen = 0;
		getNonBlank();

		switch (charClass) {

		case LETTER:
			addChar();
			getChar();
			while (charClass == LETTER || charClass == DIGIT) {
				addChar();
				getChar();
			}
			nextToken = IDENT;
			break;

		case DIGIT:
			addChar();
			getChar();
			while (charClass == DIGIT) {
				addChar();
				getChar();
			}
			nextToken = INT_LIT;
			break;

		case UNKNOWN:
			// Find the unknown character
			lookup(nextChar);
			getChar();
			break;

		case EOF:
			nextToken = EOF;
			lexeme[0] = 'E';
			lexeme[1] = 'O';
			lexeme[2] = 'F';
			lexeme[3] = '\0';
			break;
		}

		System.out.print("Next token is: " + String.valueOf(nextToken) + "--> " + tokenCodetoName(nextToken));
		printLexeme();
		return nextToken;
	}

	// Prints the lexeme contained in the array
	public static void printLexeme() {
		System.out.print(" || next lexeme: ");

		for (int i = 0; i < lexeme.length; i++) {
			if (lexeme[i] == '\0') {
				break;
			}
			System.out.print(lexeme[i]);
		}
		System.out.println();
	}

	// Convert token code to its token name
	public static String tokenCodetoName(int nextToken) {

		String nextTokenName = "";
		switch (nextToken) {

		case 1:
			nextTokenName = "ADD_OP";
			break;

		case 2:
			nextTokenName = "SUB_OP";
			break;

		case 3:
			nextTokenName = "MULT_OP";
			break;

		case 4:
			nextTokenName = "DIV_OP";
			break;

		case 5:
			nextTokenName = "MOD";
			break;

		case 6:
			nextTokenName = "LESSTHAN";
			break;

		case 7:
			nextTokenName = "GREATERTHAN";
			break;

		case 10:
			nextTokenName = "EQUALS_OP";
			break;

		case 13:
			nextTokenName = "LEFT_PAREN";
			break;

		case 14:
			nextTokenName = "RIGHT_PAREN";
			break;

		case 15:
			nextTokenName = "SEMICOLON";
			break;

		case '&':
			nextTokenName = "EOF";
			break;

		case 20:
			nextTokenName = "INT_LIT";
			break;

		case 21:
			nextTokenName = "IDENT";
			break;
		}
		return nextTokenName;
	}

}
