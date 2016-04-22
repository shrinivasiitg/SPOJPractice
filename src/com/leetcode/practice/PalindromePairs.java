package com.leetcode.practice;

import java.util.*;
import java.lang.*;

public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
        String[] reverseWords = new String[words.length];
        Map<String, Integer> map = new HashMap();
        for(int i=0; i<words.length; i++) {
            StringBuilder str = new StringBuilder(words[i]);
            reverseWords[i] = str.reverse().toString();
            map.put(words[i], i);
        }
        Arrays.sort(words);
        Arrays.sort(reverseWords);
        reverseWords = reverseSort(reverseWords);
        int wordsIterator = 0, reverseWordsIterator = reverseWords.length - 1;
//        while(wordsIterator <= words.length-1 && reverseWordsIterator >= 0) {
        while(wordsIterator <= reverseWordsIterator) {
            int stringCompare = words[wordsIterator].compareTo(reverseWords[reverseWordsIterator]);
            String reverse = new StringBuilder(reverseWords[reverseWordsIterator]).reverse().toString();
            if(isPalindrom(new StringBuilder(words[wordsIterator]).append(reverse).toString())) {
            	List<Integer> palindromePair = new ArrayList<>();
            	palindromePair.add(map.get(words[wordsIterator]));
            	palindromePair.add(map.get(new StringBuilder(words[wordsIterator]).reverse().toString()));
            	list.add(palindromePair);
            	reverseWordsIterator--;
            	wordsIterator++;            	
            } else if(stringCompare > 0) {
            	reverseWordsIterator--;
            } else {
            	wordsIterator++;            	
            }
        }
        return list;
    }

	public static void main(String[] args) {
//		String[] input = {"bat", "tab", "cat"};
		String[] input = {"abcd", "dcba", "lls", "s", "sssll"};
    	PalindromePairs pp = new PalindromePairs();
    	List<List<Integer>> list = pp.palindromePairs(input);
    	pp.printAnswer(list);
	}

	public void printPair(List<Integer> pair) {
		for(Iterator<Integer> iterator = pair.iterator(); iterator.hasNext(); ) {
			System.out.print(iterator.next() + ",");
		}
	}
	
	public void printAnswer(List<List<Integer>> palindromePairs) {
		for(Iterator<List<Integer>> iterator = palindromePairs.iterator(); iterator.hasNext(); ) {
			printPair(iterator.next());
			System.out.println();
		}
	}
	
	public String[] reverseSort(final String[] input){
        Arrays.sort(input);
        String[] reverseSortedString = new String[input.length];
        for(int i=0; i<=input.length-1; i++) {
        	reverseSortedString[i] = input[input.length-i-1];
        }
		return reverseSortedString;
	}
	
	boolean isPalindrom(String input) {
		String reverse = new StringBuilder(input).reverse().toString();
		return input.equals(reverse);
	}
	
}
