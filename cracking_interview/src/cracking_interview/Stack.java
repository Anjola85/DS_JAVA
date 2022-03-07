package cracking_interview;

/**
 * Stack supports the LIFO(last in first out) order or FILO(first in last out).
 * So we can easily implement this with an array, 
 * since we will only be removing from the end of the array by decrementing the specified SIZE variable
 * @author muhammedadeyemi
 *
 */
public class Stack {
	static final int MAX = 1000;
	int top; //top element in stack
	int a[]; //using an array to store elements in a stack
	
	
	Stack() {//default constructor creates an empty stack
		this.a = new int[MAX];
		top = -1;
	}
	
	boolean isEmpty() {
		return top > 0;
	}
	
	boolean push(int x) {
		
		if(top >= (MAX - 1)) //if there's no more space in stack
			return false;
		
		a[++top] = x;
		return true;
	}
	
	int pop() {//remove and return element at top of stack
		if(top < 0) //edge case. top has to be greater or equal to zero indicating there's at least an element present
			return 0;
		
		return a[top--];
	}
	
	int peek() {//retrieves the object at top of stack without removing it
		if(top < 0)
			return 0;
		
		return a[top];
	}
	
	void print() {
		for(int i = top; i >= 0; i--) {
			System.out.print(" " + a[i]);
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s = new Stack();
		s.push(10);
		s.push(20);
		s.push(30);
		System.out.println("Removed: " + s.pop());
		System.out.println("Top element is: " + s.peek());
		System.out.println("Elements present in stack are: ");
		s.print();
	}

}
