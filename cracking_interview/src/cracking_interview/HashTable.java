package cracking_interview;


/**
 * HashTable is a syncronized version of hashMap that ensures thread safety (i.e. can be shared with multiple threads)
 * hence it is slower and allows no null key
 * Becasue of this reason;
 * 
 * HashMap is considered faster since it is single threaded and unsynchronized, it is faster and allows one null key
 * 
 * Synchronization is the capability to control the access of multiple threads to any shared resource.
 * @author muhammedadeyemi
 *
 *Implementation:
 *Create two arrays/arraylist representing value and id respectively and find a way to map
 *or an array of linkedlist of the values to prevent collision
 */

/**
 * Hashing Function
 * 
 * This converts the original key -> hash code -> index
 * in the index a[index] it stores the original value
 * 
 * But for an array with linkedlist 
 * in the index it stores the original key and then value
 */


public class HashTable {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
