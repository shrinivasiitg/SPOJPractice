package com.geek4geeks.practice;

import java.util.Scanner;

public class NextPermutation {

	static char input[];
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		for(int i=1; i<=testCases; i++) {
			input = new char[in.nextInt()];
			input = in.next().toCharArray();
			System.out.println(nextPerm());
		}
		in.close();
	}
	
	public static String nextPerm() {
		boolean doesNextPermExist = false;
		for(int i=input.length-2; i>=0;  i--) {
			if((int)input[i] < (int)input[i+1]) {
				for(int j=i+1; j<=input.length-1 && !doesNextPermExist; j++) {
					if((int)input[i] >= (int)input[j]) {
						swap(i, j-1);
						doesNextPermExist = true;
					}
				}
				if(!doesNextPermExist) {
					swap(i, input.length-1);
					doesNextPermExist = true;
				}
				int start = i+1, end = input.length-1;
				while(start < end){
					swap(start,end);
					start++;
					end--;
				}
				break;
			}
		}
		return doesNextPermExist ? new String(input) : "ERROR"; 
	}
	
	private static void swap(int a, int b) {
		char temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
}
