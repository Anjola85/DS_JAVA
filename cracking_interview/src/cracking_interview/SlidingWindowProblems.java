package cracking_interview;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowProblems {
	
	/*QUESTION VARIANTS:
	 * 
	 * Commonalities?
	 * - Everything grouped sequentially
	 * - Longest/smallest/contains
	 * 
	 * 
	 * Fixed length: 
	 * - max sum of sub-array with size k
	 * 
	 * Dynamic variant: 
	 * - smallest sum >= to some value S
	 * 
	 * Dynamic variant with Auxiliary DS(HashMap/HashSet or additional array);
	 * - longest substring with no more than K distinct characters
	 * 
	 * String permutations
	 * - Given two strings, find if the second string exists as a permutation in the first string
	 * 
	 */
	
	
	
	
	
    /**
     * Find the max sum subarray of a fixed size K
     * @param array [4, 2, 1, 7, 8, 1, 2 ,8, 1, 0]
     * @param k fixed size
     * @return
     */
	static int findMaxSumSubArray(int[] arr, int k) {
		int max = Integer.MIN_VALUE, sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
			
			if(i >= k-1) {//as soon as fixed size is reached, check and assign max sum
				max = Math.max(max, sum);
				sum -= arr[i-(k-1)]; //remove far left value.
				
			}
		}
		return max;
	}
	
	
	/**
	 * Find the smallest subarray >= to a a target sum 
	 * @param k - target sum
	 * @param arraay
	 */
	static int findSmallestSubarray(int targetSum, int[] arr) {
		//need two pointers
		//pointer 1(s) to try and find the smallest sub array and pointer 2(i) that iterates to the end of array
		int size=Integer.MAX_VALUE, sum = 0, s=0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
			
			while(sum >= targetSum) {
				size = Math.min(size, i - s + 1); //get size of subarray
				sum -= arr[s];
				++s;
			}
		}
		return size;
	}
	
	/**k=2 
	 * Longest substring with K distinct characters
	 * @param args
	 */
	static int findLongestSubstring(int k, String str) {
		HashMap<Character, Integer> charHash = new  HashMap<Character, Integer>();
		int s = 0; //set to beginning of string
		int maxLen =Integer.MIN_VALUE;
		int len = str.length();
		
		for(int i = 0; i < len; i++) {
			char c = str.charAt(i);
			
			if(charHash.containsKey(c)) 
				charHash.put(c, charHash.get(c)+1); //increase no of occurence of character
			else 
				charHash.put(c, 1);
			
			
			while(charHash.size() > k) {
				char letter = str.charAt(s);
				
				charHash.put(letter, charHash.get(letter)-1);//reduce the occurrence of letter by 1 and shift s to the right 
				
				if(charHash.get(letter)==0)
					charHash.remove(letter);
				s++;
			}
			maxLen = Math.max(maxLen, i - s + 1);
		}
		
		return maxLen;
	}
	
	


	public static void main(String[] args) {
//		System.out.println(findLongestSubstring(2, "aaahhibc"));
		System.out.println(1>1);
	}
}
