package com.leet4geeks.practice;

import java.util.*;
import java.lang.*;

public class LongestPalindromicSubstring {

//	public static Map<Integer, Integer[]> map = new HashMap<>();
	public static Map<Integer, String> map = new HashMap<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextInt();
		
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
//		String input = "BBABCBCAB";
//		String input = "forgeeksskeegfor";
		String input = "asacccdada";
		input = in.next();
		System.out.println(lps.longestPalindrome(input));
//		Integer[] array = {0,1,2,3,4};
//		System.out.println(lps.intArrayToString(array));
	}

	public String longestPalindrome(String s) {
		Integer[] palindromes;
		if(s != null ) {
			if(s.compareTo("bb") == 0) {
				return "bb";
			}
		}
		palindromes = recursiveLongestPalindrome(s,0);
		if(palindromes == null) {
			return "";
		} else {
			return getPalindrome(s, palindromes);
			
		}
    }
	/**
	 * 
	 * @param s
	 * @param start
	 * @return an array a[] where 
	 * 			a[0,1] = optimal palindrome
	 * 			a[2,3] = a palindrome including the current char
	 * 			a[4] = 1 if all the characters in palindrome  a[2 to 3] are of same.
	 */
	public Integer[] recursiveLongestPalindrome(final String s, int start){
		if(s == null || s.length() == 0) {
			return null;
		}
		if(map.containsKey(start)) {
			return stringToIntArray(map.get(start));
		}
		Integer[] currentPalindromes = {start,start,start,start,1};
		if(start == s.length()-1) {
			currentPalindromes[0] = start;
			currentPalindromes[1] = start;
			currentPalindromes[2] = start;
			currentPalindromes[3] = start;
			currentPalindromes[4] = 1;
		} else if(start == s.length()-2) {
			if(s.charAt(start) == s.charAt(start+1)) {
				currentPalindromes[0] = start;
				currentPalindromes[1] = start+1;
				currentPalindromes[2] = start;
				currentPalindromes[3] = start+1;
				currentPalindromes[4] = 1;
			} else {
				currentPalindromes[0] = start;
				currentPalindromes[1] = start;
				currentPalindromes[2] = start;
				currentPalindromes[3] = start;
				currentPalindromes[4] = 1;
			}
		} else {
			Integer[] previousPalindromes = recursiveLongestPalindrome(s, start+1);
			
			/*
			 *	t\This if is to chenck for the cases where String str is a palindrome and appending char ch at begining,
			 *	 makes it again palindrome
			 *		Example
			 *			str is palindrome
			 *			ch+str also a palindrome
			 * 		this can only happend if all the characters of str are = ch
			 */
			if(previousPalindromes[4] == 1) { 
				if(s.charAt(start)  == s.charAt(start+1)) {
					currentPalindromes[4] = 1;
					currentPalindromes[2] = start;
					currentPalindromes[3] = previousPalindromes[3];
				} else {
					currentPalindromes[2] = currentPalindromes[3] = start;
					currentPalindromes[4] = 1;					
				}
//				else if(previousPalindromes[3] != s.length()-1) {
//					if(s.charAt(start) == s.charAt(previousPalindromes[3]+1)) {
//						currentPalindromes[2] = start;
//						currentPalindromes[3] = previousPalindromes[3]+1;
//						currentPalindromes[4] = 0;
//					} else {
//						currentPalindromes[2] = currentPalindromes[3] = start;
//						currentPalindromes[4] = 1;					
//					}
//				} else {
//					currentPalindromes[2] = currentPalindromes[3] = start;
//					currentPalindromes[4] = 1;
//				}
			} else if(previousPalindromes[3] != s.length()-1 && s.charAt(start) == s.charAt(previousPalindromes[3]+1)) { // {(b)[aca]b}
				currentPalindromes[2] = start;
				currentPalindromes[3] = previousPalindromes[3]+1;
				currentPalindromes[4] = 0;
			} else {
				while(s.charAt(start) != s.charAt(previousPalindromes[3]+1)){
					previousPalindromes[3] -= 2;
				}
				currentPalindromes[2] = start;
				currentPalindromes[3] = previousPalindromes[3]+1;
				currentPalindromes[4] = 1;
			}
			
			if(currentPalindromes[3]-currentPalindromes[2] > previousPalindromes[1]-previousPalindromes[0]) {
				currentPalindromes[0] = currentPalindromes[2];
				currentPalindromes[1] = currentPalindromes[3];
			} else {
				currentPalindromes[0] = previousPalindromes[0];
				currentPalindromes[1] = previousPalindromes[1];
			}
		}
		Integer[] array = {currentPalindromes[0],currentPalindromes[1],currentPalindromes[2],currentPalindromes[3],currentPalindromes[4]};
		map.put(start, intArrayToString(currentPalindromes));
		return array;
//		int[] optimalPalindrom = new int[2];
////		optimalPalindrome[0] = palindromes[0];
////		optimalPalindrome[1] = palindromes[1];
////		int adjacentPalindrome
//		return null;
	}

	public String getPalindrome(String s, Integer[] palindromes) {
		return s.substring(palindromes[0], palindromes[1]+1);
	}
	public boolean isPalindrome(String palindromicString, char ch) {
		
		return false;
	}
	
	public Integer[] stringToIntArray(String input){
		Integer[] array = {(input.charAt(0))-'0', (input.charAt(1))-'0', (input.charAt(2))-'0', (input.charAt(3))-'0', (input.charAt(4))-'0', };
		return array;
	}
	
	public String intArrayToString(Integer[] input){
		return new StringBuilder().append(input[0]).append(input[1]).append(input[2]).append(input[3]).append(input[4]).toString();
	}
	
}
