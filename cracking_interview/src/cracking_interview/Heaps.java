package cracking_interview;

import java.util.Arrays;

/**
 * Using an array for implementation of minHeap
 * 
 * @author muhammedadeyemi
 * Insertion and deletion  runs in log(n)
 * look up runs in 0(1)
 */

public class Heaps {
	private int capacity = 10, size;
	int[] items;
	
	public Heaps() {
		items = new int[capacity];
		size = 0;
	}
	
	/**
	 * without removal
	 * @return first element in the array 
	 */
	public int peek() {
		if(size == 0) 
			throw new IllegalStateException();
		return items[0];
	}
	
	/**
	 * with removal
	 * @return first element of the array 
	 */
	public int poll() {
		if(size == 0) 
			throw new IllegalStateException();
		int oldVal = items[0];
		items[0] = items[size-1]; //replace top element with bottom
		size--; //shrink array by decrementing size
		heapifyDown(); //bubble down element at the top
		return oldVal; 
	}
	
	/**
	 * Add element
	 */
	public void add(int item) {
		ensureExtraCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}
	
	/**
	 * Starts with the very last element added 
	 * and walks up to its appropriate position
	 * if and only if there is a parent item 
	 */
	public void heapifyUp() {
		
		int index = size - 1; //current position[size - 1 because it is an array] of the node we want to bubble up
		
		while(hasParent(index) && parent(index) > items[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index); //if it is successful then the node we are trying to bubble up as taken position of the parent
		}
	}
	
	public void heapifyDown() {
		int index = 0; 
		
		while(hasLeftChild(index)) {
			int smallerChildIndex = getLeftChildIndex(index);
			
			//check if it has right child and if value of right child is smaller than left child
			if(hasRightChild(index) && rightChild(index) < leftChild(index))
				smallerChildIndex = getRightChildIndex(index);
			
			//check if element to be bubbled down is in right position first
			if(items[index] < items[smallerChildIndex])
				break;
			else {
				//still out of order so swap with the smaller child and move down
				swap(index, smallerChildIndex);
			}
			index = smallerChildIndex;  //take the position of the index it was swapped with. i.e. rightChildIndex or left and repeat
		}
	}
	
	
	
	
	
	
	
	/**BUNCH OF USEFUL/HELPER METHODS**/
	
	/**
	 * Gets the index of specified node
	 * @param  parent, parent, child respectively
	 * @returns the left, right and parent index respectively  
	 */
	private int getLeftChildIndex(int pIndex) {//always odd number
		return 2 * pIndex + 1;
	}
	
	
	private int getRightChildIndex(int pIndex) {//always even
		return 2 * pIndex + 2;
	}
	
	private int getParentIndex(int cIndex) {
		return (cIndex-1)/2;
	}
	
	/**
	 * Checks if the respective nodes exist
	 * @param  parent, parent, child respectively
	 * @returns true if left, right and parent node exists respectively  
	 */
	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}
	
	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}
	
	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}
	
	
	/**
	 * Gets the value of specified node
	 * @param  parent, parent, child respectively
	 * @returns the left, right and parent node respectively  
	 */
	private int leftChild(int index) {
		return items[getLeftChildIndex(index)];
	}
	
	private int rightChild(int index) {
		return items[getRightChildIndex(index)];
	}
	
	private int parent(int index) {
		return items[getParentIndex(index)];
	}

	
	private void swap(int indexOne, int indexTwo) {
		int temp = items[indexOne];
		items[indexOne] = items[indexTwo];
		items[indexTwo] = temp;
	}
	
	/**
	 * This methods doubles the size of the array if its full
	 */
	private void ensureExtraCapacity() {
		if(size == capacity) {
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heaps sort = new Heaps();
	
        int[] arr = new int[]{ 12, 11, 13, 5, 6, 7 };
        for(int i : arr) {
        	sort.add(i);
        }
        
        for(int i = 0; i < arr.length; i++)
        	arr[i] = sort.poll();
        
        System.out.println(Arrays.toString(arr));
	}

}
