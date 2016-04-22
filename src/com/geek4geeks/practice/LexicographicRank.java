package com.geek4geeks.practice;

/**
 * http://www.geeksforgeeks.org/lexicographic-rank-of-a-string/
 * 
 * @author acharys
 *
 */
public class LexicographicRank {

	static int rank[] = new int[256];
	static long factorial[];

	public static void main(String[] args) {
		String input = "bacd";
		System.out.println(rank(input));
		
//		while(true){
//			Scanner scanner = new Scanner(System.in);
//			input  = scanner.next();
//			System.out.println(rank(input));
//			scanner.close();
//		}
		
	}

	static long rank(String input) {		
		initializeArray();
		initializeFactorial(input.length());
		assignRanksFromInput(input);
//		printRanks(input);
		return rankOfString(input,0);
	}
	static long rankOfString(final String input, final int currCharIndex) {
		if (input.length()-1 == currCharIndex) {
			return 1;
		} else {
			int rankOfCurrChar = getRank((int)input.charAt(currCharIndex));
			decreseRankByOne((int)input.charAt(currCharIndex));
			return factorial(input.length()-currCharIndex-1)*(rankOfCurrChar-1) + rankOfString(input, currCharIndex+1);
		}
	}

	static void initializeArray() {
		for (int i = 0; i < rank.length; i++) {
			rank[i] = 0;
		}
	}
	
	static void initializeFactorial(final int size) {
		factorial = new long[size];
		for(int i=0; i<size; i++) {
			factorial[i] = 1; 
		}
	}

	static void assignRanksFromInput(final String input) {
		for (int i = 0; i < input.length(); i++) {
			increseRankByOne((int) input.charAt(i));
		}
	}

	static void increseRankByOne(final int charIndex) {
		changeRankByOne(charIndex, true);
	}

	static void decreseRankByOne(final int charIndex) {
		changeRankByOne(charIndex, false);
	}

	static void changeRankByOne(final int charIndex, final boolean increse) {
		for (int i = charIndex; i < rank.length; i++) {
			rank[i] += increse ? 1 : -1;
		}
	}
	
	static void printRanks(final String input) {
		for(int i=0; i<input.length(); i++) {
			System.out.println(input.charAt(i) + "-" + getRank((int) input.charAt(i)));
		}
	}
	
	static int getRank(final int charIndex) {
		return rank[charIndex] != rank[charIndex-1] ? rank[charIndex] : 0;
	}
	
	static long factorial(final int number) {
		if(number >= 2 && factorial[number] == 1) {
			factorial[number] = number*factorial(number-1);			
		}
		return factorial[number];
	}
}
