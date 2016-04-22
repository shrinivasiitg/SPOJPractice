package com.leetcode.practice;

public class MultiplyStrings {

	public static final int SIZE_OF_LONG = 15;
	public static void main(String[] args) {
		MultiplyStrings mps = new MultiplyStrings();
		String str1 = "99999999999999999999";	//20  9's
		String str2 = "99999999999999999999";	//"10";
		Long ll = Long.valueOf("9999999999");
		System.out.println(ll);		
//		System.out.println(Long.valueOf(str1).longValue());		
		System.out.println(mps.add(str1, str2));
		System.out.println(mps.times(str1, ll));
	}
    
	public String multiply(String num1, String num2) {
        if(num1.length() + num2.length() <= SIZE_OF_LONG ) {
        	return String.valueOf((Long.valueOf(num1).longValue()*Long.valueOf(num2).longValue()));
        } else if(num2.length() <= SIZE_OF_LONG) {
        	return times(num1, Long.valueOf(num2));
        } else {
        	for(int i=num2.length()-SIZE_OF_LONG; i>=0; i-=SIZE_OF_LONG) {
        		num2.substring(i, i+SIZE_OF_LONG-1);
        	}
        }
        return null;

    }
	
	/**
	 * 
	 * @param num
	 * @param times (a long value)
	 * @return num*times
	 */
	public String times(String num, long times) {
		if(num.length() <= SIZE_OF_LONG) {
        	return String.valueOf((Long.valueOf(num).longValue()+Long.valueOf(times).longValue()));
		} else {
			char[] timesArray = new char[num.length()];
			for(int i=0; i<timesArray.length; i++) {
				timesArray[i] = num.charAt(i);
			}
			long carry = 0;
			int numIterator = num.length()-1;
			while(numIterator >= 0){
				long digitSum = Character.getNumericValue(num.charAt(numIterator))*times + carry;
				carry = digitSum/10;
				timesArray[numIterator] = Long.toString(digitSum%10).charAt(0);
				numIterator--;
			}
			if(carry == 0) {
				return String.valueOf(timesArray);
			} else {
				return String.valueOf(carry)+String.valueOf(timesArray);
			}
		}
	}
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return num1+num2
	 */
	public String add(String num1, String num2) {
		if(num1.length() <= SIZE_OF_LONG) {
        	return String.valueOf((Long.valueOf(num1).longValue()+Long.valueOf(num2).longValue()));
		} else {
			char[] sum = new char[num1.length()];
//			sum[0] = '0';
			for(int i=0; i<sum.length; i++) {
				sum[i] = num1.charAt(i);
			}
			int carry = 0;
			int num1Iterator = num1.length()-1;
			int num2Iterator = num2.length()-1;
			while(num2Iterator >= 0){
				int digitSum = Character.getNumericValue(num1.charAt(num1Iterator)) + Character.getNumericValue(num2.charAt(num2Iterator)) + carry;
				carry = digitSum/10;
				sum[num1Iterator] = Integer.toString(digitSum%10).charAt(0);
				num1Iterator--;
				num2Iterator--;	
			}
			while(carry !=0 && num1Iterator >= 0){
				int digitSum = Character.getNumericValue(num1.charAt(num1Iterator)) + carry;
				carry = digitSum/10;
				sum[num1Iterator] = Integer.toString(digitSum%10).charAt(0);
				num1Iterator--;
			}
			if(carry == 0) {
				return String.valueOf(sum);
			} else {
				return String.valueOf(carry)+String.valueOf(sum);
			}
		}
	}
}
