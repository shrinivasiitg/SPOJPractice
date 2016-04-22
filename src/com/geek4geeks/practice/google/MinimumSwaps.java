package com.geek4geeks.practice.google;

import java.util.*;
/**
 * http://www.geeksforgeeks.org/minimum-number-of-swaps-required-for-arranging-pairs-adjacent-to-each-other/
 * 
 * Input:
 * n = 3  
 * pairs[] = {1->3, 2->6, 4->5}  // 1 is partner of 3 and so on
 * arr[] = {3, 5, 6, 4, 1, 2}
 * 
 * Output: 2
 * We can get {3, 1, 5, 4, 6, 2} by swapping 5 & 6, and 6 & 1
 * 
 * @author acharys
 *
 */
public class MinimumSwaps {

	static int[] positionMap;
	static int[] pairMap;
	
	public static void main(String[] args) {	
		int[] pairs = {0,1,3,5,6,2,4};
		int[] array = {0,1,2,3,4,5,6};
		initializePositionMap(array);
		initializePairMap(pairs);
		System.out.println(swapsRequeired(array));
	}
	
	public static int swapsRequeired(int[] array) {
		int swapCount = 0;
		for(int i=1; i<=array.length-2; i+=2) {
			if(pairMap[array[i]] != array[i+1]) {
				int numberA = array[i+1];
				int numberB = array[positionMap[pairMap[i]]];
				int positionBeforeSwapA = positionMap[numberA];
				int positionBeforeSwapB = positionMap[numberB];
				
				int positionAfterSwapA = positionBeforeSwapB;
				int positionAfterSwapB = positionBeforeSwapA;
				
				array[positionAfterSwapA] = numberA;
				array[positionAfterSwapB] = numberB;
				
				positionMap[numberA] = positionAfterSwapA;
				positionMap[numberB] = positionAfterSwapB;
				
				swapCount++;
			}
		}
		return swapCount;
	}
	
	public static void initializePositionMap(int[] positionArray) {
		positionMap = new int[positionArray.length];
		positionMap[0] = 0;
		for(int i=1; i<=positionArray.length-1; i++) {
			positionMap[positionArray[i]] = i;
		}
	}

	public static void initializePairMap(int[] pairArray) {
		pairMap = new int[pairArray.length];
		pairMap[0] = 0;
		for(int i=1; i<=pairArray.length-2; i+=2) {
			pairMap[pairArray[i]] = pairArray[i+1];
			pairMap[pairArray[i+1]] = pairArray[i];
		}
	}
}
