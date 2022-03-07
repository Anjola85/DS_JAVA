package cracking_interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;


/**
 * Mostly String manipulation interview questions
 * @author muhammedadeyemi
 *
 */
public class fundamental_problems {	
	
	static void fizzbuzz() {
		for(int i = 1; i < 101; i++) {
			if(i%3==0 && i%5==0)
				System.out.println("FizzBuzz");
			else if(i%3==0)
				System.out.println("Fizz");
			else if(i%5==0)
				System.out.println("Buzz");
			else
				System.out.println(i);
		}
	}
	
	
	/**
	 * Using string builder - it a mutable sequence of characters
	 * @param str
	 * @return reversed string
	 */
	static String reverse(String str) {
		int length = str.length();
		StringBuilder out = new StringBuilder(length);
		for(int i = length-1; i >= 0; --i)
			out.append(str.charAt(i));
		return out.toString();
	}
	
	
	/**
	 * Given an unsorted array of integers, return a new array with all duplicates removed, do not use sets.
	 * @return sorted array without duplicates
	 */
	static int[] removeDuplicatesArray(int[] num) {
		//check edge cases
		//sortArray, loop through to find duplicate elements then mutate the array
		//added mutated array tp temp array
		Arrays.sort(num);
		int len = num.length;
	
		if(len == 0 || len == 1)//checking for edge case
			return num;
		
		int j = 0;
		for(int i = 0; i < len-1; i++) {
			if(num[i] != num[i+1])
				num[j++] = num[i];
		}
		num[j++] = num[len-1];
		
		//copy to new array
		int[] temp = new int[j];
		for(int i = 0; i < j; i++)
			temp[i] = num[i];
		return temp;
	}
	
	/** 
	 * @param s
	 * @return true if (()) or ()() 
	 */
	static boolean nestedParenthesis(String s) {
		if(s.length() % 2 != 0)
			return false;
		
		Stack<Character> stack = new Stack();
		
		for(char c : s.toCharArray()) {
			if(c == '(' || c == '[' || c == '{')
				stack.push(c);
			else if(c == ')' && !stack.isEmpty() && stack.peek() == '(')
				stack.pop();
			else if(c == ']' && !stack.isEmpty() && stack.peek() == '[')
				stack.pop();
			else if(c == '}' && !stack.isEmpty() && stack.peek() == '{')
				stack.pop();
		}
		return stack.isEmpty();
	}
	
	/**
	 * 
	 * @param nums
	 * @return greatest common divisor amongst number in an array
	 */
    public static int findGCD(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int max=nums[len-1], min=nums[0], divisor=1;        
        for(int i = 2; i <= max; i++) {
            if(min%i==0 && max%i==0) {
            	System.out.println("i: " + i);
                divisor = i;
            }
        }
        return divisor;
    }
    
    /**
     * Find the first non-repeated character in a string. for "total" return o
     * @param args
     */
    static Character firstNonRepeated(String str) {//takes O(2n)
    	HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
    	int len = str.length(), i;
    	Character c;
    	//build hashtable
    	for(i = 0; i < len; i++) {
    		c = str.charAt(i);
    		if(hash.containsKey(c)) 
    			hash.put(c, hash.get(c)+1);
    		else
    			hash.put(c, 1);
    	}
    	
    	//search for first non-repeated char
    	for(i = 0; i < len; i++) {
    		c = str.charAt(i);
    		if(hash.get(c) == 1)
    			return c;
    	}
    	return null;
    }
    
    /**
     * A function that deletes characters from a mutable ACII string.
     * @param src
     * @param remove
     */
    static void removeChars(StringBuilder str, String remove) {
    	boolean[] flags = new boolean[128]; //src is ASCII so we use lookup array.
    	
    	//flags = true for characters to be removed
    	for(char c: remove.toCharArray()) {
    		flags[c] = true;
    	}
    	
    	int i, j = 0; //pointers i = src and j = dest

    	//copy only elements that aren't flagged
    	for(i = 0; i < str.length(); ++i) {
    		char c = str.charAt(i);
    		if(!flags[c])
    			str.setCharAt(j++, c);
    	}
    	str.setLength(j);
    }
    
    /**
     * A function that reverses the order of the words in a string.
     * @param word
     * @return
     */
    static String reverseWords(String str) {
    	//loop from the back and add to a string variable, maybe string builder.
    	int len = str.length();
		StringBuilder out = new StringBuilder(len);
		String word = "";
		int i;
		for(i = len-1; i >= 0; --i) {
			if(str.charAt(i) != ' ') {
				word = str.charAt(i) + word;
			}
			else {
				out.append(word + " ");
				word="";
			}
		}
		//add last word to StringBuilder since no space is encountered after it.
		if(str.charAt(0) != ' ')
			out.append(word);
		
    	return out.toString();
    }
    
    
    /**
     * Converts a string to a signed integer. You may assume that the string contains
     * only digits and the minus(-) character
     * @param args
     */
    static int strToInt(String str) {
    	int len = str.length();
    	int num=0, incre=1, condition;
    	if(str.charAt(0)=='-')
    		condition = 0;
    	else
    		condition = -1;
    	
		for(int i = len-1; i > condition; --i) {
			num += (str.charAt(i) - '0')*incre;
			incre*=10;
		}
    	
		if(condition==0)
			num*=-1;
		
    	return num;
    }
    
    /**
     * Converts a signed integer stored as an int back to a string
     * @param args
     */
    static String intToStr(int num) {
    	String str="";
    	int quotient = num, divisor = 10, dividend = num;
    	//loop
    	while(dividend != 0) {
    		quotient %= divisor;
        	str = Math.abs(quotient) + str;
        	quotient = dividend/=divisor;
    	}
    	if(num < 0)
    		str = "-"+str;
    	return str;
    }
    
    /**
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * @param s
     */
    static void reverseString(char[] s) {//Logarithmic running time
        Character temp;
        int len = s.length;
        int j = len -1;
        for(int i = 0; i < len/2; i++) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            --j;
        }
    }
    
    /**
     * Remove duplicate letters in a string
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        int len = s.length();
        
        int[] lastIndex = new int[26];  
        //store the position/index of the last occurence for each letter
        for(int i = 0; i < len; i++)
            lastIndex[s.charAt(i)-'a'] = i;
        
        boolean[] seen = new boolean[26];//if a letter has been seen in iteration, set to true
        Stack<Integer> st = new Stack<Integer>();//stack to keep track of letters
        
        for(int i = 0; i < len; i++) {
            int curr = s.charAt(i)-'a';
            
            if(seen[curr])//if letter has been seen before skip to next letter
                continue;
            
            //st.peek() > curr that is if letter in stack is lexicographically greater than element to be added
            //i < lastIndex[st.peek()] that is don't remove if that is the last occurence of the letter
            while(!st.isEmpty() && st.peek() > curr && i < lastIndex[st.peek()])
                seen[st.pop()] = false;
            
            st.push(curr);
            seen[curr]=true; //add to stack and set letter in boolean array to true-indicating seen
        }
            
        StringBuilder str = new StringBuilder(len);
        while(!st.isEmpty())
            str.append((char) (st.pop()+'a'));
        return str.reverse().toString();
    }
    
    /**
     * prints all permutations of a string with all distinct characters
     * @param args
     */
    
    //this uses recursion
    void permutation(String str) {
    	permutation(str, "");
    }
    
    void permutation(String str, String prefix) {
    	if(str.length() == 0)
    		System.out.println(prefix);
    	else {
    		for(int i = 0; i < str.length(); i++) {
    			String rem = str.substring(0, i) + str.substring(i+1);
    			permutation(rem, prefix+str.charAt(i));
    		}
    	}
    }
    
    static boolean check(String str) {//reimplement with stack
    	int s=0, e = 0;
    	for(int i = 0; i < str.length(); i++) {
    		char c = str.charAt(i);
    		
    		if(c == '(' || c == '{' || c == '[')
    			s++;
    		
    		if(c == ')' || c == '}' || c == ']')
    			e++;
    		
    	}
    	System.out.println("s: " + s + " e " + e);
    	if(s - e == 0)
    		return true;
    	
    	return false;
    }
    
    //largest sum of contiguous subArray
    //using DP - Kandane's Algorithm
    public int maxSubArray(int[] nums) {
        
        int currSub = nums[0];
        int maxSub = nums[0];
        
        for(int i = 0; i < nums.length; i++) {
        	
        	int num = nums[i];
        	
        	currSub = Math.max(num, currSub + num);
        	maxSub = Math.max(currSub, maxSub);
        	
        }
        
        return maxSub;
    }
    
    int binarySearch(int arr[], int l, int r, int x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;
 
            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;
 
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);
 
            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }
 
        // We reach here when element is not present
        // in array
        return -1;
    }
    
    //iterative approach
    int binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
 
            // Check if x is present at mid
            if (arr[m] == x)
                return m;
 
            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;
 
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
 
        // if we reach here, then element was
        // not present
        return -1;
    }
    
        
    
	public static void main(String[] args) {
//		double num = 3322.345;
//		String out = String.format("$%,.2f", num);
//		System.out.println(out);
		System.out.println(2 < -5);

	}
}
