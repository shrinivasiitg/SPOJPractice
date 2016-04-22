package com.geek4geeks.practice;

import java.util.Stack;
/**
 * 
 *                          | 
 *          |               |    
 *          |               | 
 *        | |       |       |
 *        | |     | |       |
 *        | | |   | | |     |
 *      | | | | | | | |   | |
 *    |_|_|_|_|_|_|_|_|___|_|
 *    1,2,5,7,3,2,4,5,3,0,2,8
 * 
 * Given this array, we can fill water like this(thin towers)               
 *           . . . . . . . .|    
 *          |. . . . . . . .| 
 *         .|. . . . . . . .|
 *        |.|. . . .|. . . .|
 *        |.|. . .|.|. . . .|
 *       .|.|.|. .|.|.|. . .|
 *     .|.|.|.|.|.|.|.|. .|.|
 *    |_|_|_|_|_|_|_|_|_ _|_|   
 *    1 square = 1 unit of water = 1 dot
 *    
 *   find number of units of water that this array can contain without spilling it out of the thin towers 
 *    
 * Given this array, we can also fill water like this(thick towers)
 *                           _
 *           _ . . . . . . .| | 
 *          | |. . . . . . .| |    
 *         _| |. . _ . . . .| | 
 *        | | |. .| |_ . . .| |
 *        | | |_ .| | |_ . .| |
 *       _| | | |_| | | |. _| |
 *     _| | | | | | | | |.| | |
 *    |_|_|_|_|_|_|_|_|_|_|_|_|
 *     1,2,5,7,3,2,4,5,3,0,2,8
 *     
 *    1 square = 1 unit of water = 1 dot
 *    
 *   find number of units of water that this array can contain without spilling it out of the thick towers 
 * 
 * 
 * @author acharys
 *
 */
public class RainWaterTowers {

	static int timeComplexityCounter = 0;
	
	public static void main(String[] args) {
		final int towerArray[] = new int[] { 1, 2, 5, 7, 3, 2, 4, 5, 3, 0, 2, 8};//, 1, 2, 5, 7, 3, 2, 4, 5, 3, 10, 10, 1, 2, 5, 7, 3, 2, 4, 5, 3, 2, 8};
//		final int towerArray[] = new int[] { 3, 4, 2, 5}; // 11,2
//		final int towerArray[] = new int[] { 1, 5, 3, 7, 2}; // 11,2
		boolean isThinTower = true;
		RainWaterTowers.timeComplexityCounter = 0;
		System.out.println(waterBetweenTowers(towerArray, towerArray.length,isThinTower));
		System.out.println(RainWaterTowers.timeComplexityCounter);
		isThinTower = false;
		RainWaterTowers.timeComplexityCounter = 0;
		System.out.println(waterBetweenTowers(towerArray, towerArray.length,isThinTower));
		System.out.println(RainWaterTowers.timeComplexityCounter);
	}

	static int waterBetweenTowers(final int[] towerArray, final int arraySize, boolean isThinTower) {
		Stack<Integer> supportingTowers = new Stack<Integer>();
		int currMax = 0;
		supportingTowers.push(0);
		for (int i = 1; i < arraySize; i++) {
			if (towerArray[i] > towerArray[i-1]) {
				while (supportingTowers.peek() != currMax && towerArray[supportingTowers.peek()] < towerArray[i] ) {
					supportingTowers.pop();
					RainWaterTowers.timeComplexityCounter++;
				}
				currMax = towerArray[currMax] > towerArray[i] ? currMax : i;
			}
			supportingTowers.push(i);
			RainWaterTowers.timeComplexityCounter++;
		}
		if(isThinTower){
			return waterBetweenThinSupportingTowers(towerArray, supportingTowers);
		} else{
			return waterBetweenThickSupportingTowers(towerArray, supportingTowers);			
		}
			
	}

	static int waterBetweenThinSupportingTowers(final int[] towerArray, Stack<Integer> supportingTowers) {
		int totalWater = 0;
		int prevTowerPosition = supportingTowers.peek();
		supportingTowers.pop();
		System.out.print("A[");		
		while (!supportingTowers.empty()) {
			System.out.print(prevTowerPosition + ",");		
			int currTowerPosition = supportingTowers.peek();
			supportingTowers.pop();
			int currentWater = towerArray[prevTowerPosition] < towerArray[currTowerPosition]
					? towerArray[prevTowerPosition] * (prevTowerPosition - currTowerPosition) 
					: towerArray[currTowerPosition] * (prevTowerPosition - currTowerPosition);
			totalWater += currentWater;
			prevTowerPosition = currTowerPosition;
		}
		System.out.println("]");		
		return totalWater;
	}
	static int waterBetweenThickSupportingTowers(final int[] towerArray, Stack<Integer> supportingTowers) {
		int totalWater = 0;
		int prevTowerPosition = supportingTowers.peek();
		supportingTowers.pop();
		System.out.print("A[");		
		while (!supportingTowers.empty()) {
			System.out.print(prevTowerPosition + ",");		
			int currTowerPosition = supportingTowers.peek();
			supportingTowers.pop();
			int currentWater = towerArray[prevTowerPosition] < towerArray[currTowerPosition]
					? towerArray[prevTowerPosition] * (prevTowerPosition - currTowerPosition - 1) 
					: towerArray[currTowerPosition] * (prevTowerPosition - currTowerPosition - 1);
			for(int j=prevTowerPosition-1; j>currTowerPosition; j--) {
				currentWater -= towerArray[j];
			}
			totalWater += currentWater;
			prevTowerPosition = currTowerPosition;
		}
		System.out.println("]");		
		return totalWater;
	}

}
