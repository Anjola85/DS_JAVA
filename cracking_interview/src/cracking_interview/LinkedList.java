package cracking_interview;


/***
 * This contains complete implementation of a LinkedList and other possible methods that could be asked of a LinkedList 
 * @author muhammedadeyemi
 *
 */
public class LinkedList {
	static Node head;
	static Node tail;
	
	class Node {
		Node next;
		int data;
		
		Node(int d) {//constructor
			data = d;
			next = null;
		}
	}
	
	/**
	 * Appends the specified element to the end of this list.
	 */
	boolean add(int data) {
		
		if (head == null) {
			head = new Node(data);
			return true;
		}
		
		Node curr = head;
		while(curr.next != null) 
			curr = curr.next;
		curr.next = new Node(data);
		tail = new Node(data);
		return true;
	}
	
	boolean addAfter(Node e, int data) {
		
		if(e.next == null) {
			e.next = new Node(data);
			tail = new Node(data);
		} else {
			Node temp = e.next;
			e.next = new Node(data);
			e.next.next = temp;				
		}
		
		
		return false;
	}
	
	/**
	 * Insert specified element at the beginning of the list
	 */
	boolean addFirst(int data) {		
		if(head == null) {
			head.data = data;
			return true;
		}
		
		//there exists a head
		Node new_node = new Node(data);
		new_node.next = head;
		head = new_node;
		return true;
	}
	
	/**
	 * Delete's element based on data provided
	 */
	boolean delete(int data) {
		Node curr = head;
		
		if(head == null)
			throw new NullPointerException();
		
		
		//if data is head.data
		if(data == head.data) {
			head = head.next;
			return true;
		}
		
		//for the other elements in list
		while(curr.next != null) {
			if(data == curr.next.data) {
				curr.next = curr.next.next; //delete node carrying data
				System.gc();
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	
	
	/**
	 * Possible algorithmic questions that could be asked in an interview.
	 */
	
	/**
	 * Function to remove duplicates from an unsorted linked list 
	 * use System.gc after deleting element to run the garbage collector to recycle unused objects
	 */
	void remove_duplicates() {
		Node ptr1 = head, ptr2 = null;
		
		/*Pick elements one by one*/
		while(ptr1 != null && ptr1.next != null) {//check if there exists an element or more than one element in the LinkedList
			ptr2 = ptr1;
			while(ptr2.next != null) {//loop to end of linked list
				if(ptr1.data == ptr2.next.data) {
					//if duplicate is found delete duplicate
					ptr2.next = ptr2.next.next;
					System.gc(); //recycle unused object to make the memory free.
				} else {
					ptr2 = ptr2.next;
				}
			}
			ptr1 = ptr1.next;
		}
	}
	
	/**
	 * Print all elements of a linked list
	 * @param node
	 */
	void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println("\n");
	}
	
	
	/**
	 * Reverse a linkedlist
	 * You'll need three pointers for this; next, prev and curr
	 * @param args
	 */
    Node reverseList(Node head) {
    	
    	if(head == null)
    		return null;
    	
        Node curr = head, prev = null, next;
        while(curr!=null) {
        	next = curr.next;
        	curr.next = prev;
        	prev = curr;   
        	curr = curr.next;
        }
        head=curr; //when it is done, set head to tail element since it is in reverse
        return head;
    }
	
    
    Node reverseBetween(Node head, int left, int right) {
        
    	if(head == null)
    		return null;
    	
    	Node prev = null, connection, curr=head;
    	
    	//shift position to start
    	while(left > 1) {
    		prev = curr;
    		curr = curr.next;
    		--left;
    		--right;
    	}
    	
    	connection = prev;//set connection to the prev element before the node we'll reverse
    	Node next, tail = curr;
    	
    	while(right > 0) {//reverse elements within the left and right points
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    		--right;
    	}
    	
    	
    	//we have 1 -> 2 -> 3 -> 4 -> 5
    	//start is at position (left-1) which is 2 and right is at position (right-3) which is 4.
    	//reversed is 1 -> 4 -> 3 -> 2 -> 5
    	/**
    	 * At this point. curr = next = 5 
    	 * connection = prev = 1
    	 * tail = 2 at line tail = curr which was 2.
    	 * 
    	 * after the reversed elements loop have been run, then
    	 * 4 -> 3 -> 2
    	 * 
    	 * hence suppose there exists a previous element from the left position, set the next of the prev element to the last 
    	 * reversed element node, which is 4. hence 1 -> 4 -> 3 -> 2.
    	 * 
    	 * But when the reversed loop first runs, 2 still points to 1, 2 -> 1.
    	 * At the end of the loop we have curr = 5 and tail = 2. 
    	 * Hence we can just set tail.next = curr. making;
    	 * 
    	 * 1 -> 4 -> 3 -> 2 -> 5
    	 */
    	
    	
    	if(connection != null)//reset prev element before start
    		connection.next = prev;
    	else
    		head = prev; //incase reversal started from the head itself then change the head to the tail
    	
    	tail.next = curr; //reset former starting point to point to tail.
    	
        return head;
    }
    
    //merget two sorted lists
    Node mergeTwoLists(Node list1, Node list2) {
        
    	Node tempNode = new Node(0);
    	Node curr = tempNode;
        
        while(list1 != null && list2 != null) {
            
            if(list1.data < list2.data) {
                curr.next = list1;
                list1 = list1.next;
            }
            else {
                curr.next = list2;
                list2 = list2.next;
            }
            
            curr = curr.next;
        }
        
        //checking for edge cases where one list reaches the end before the other
        if(list1 != null) 
            curr.next = list1; 
        else if(list2 != null)
            curr.next = list2;
        
        return tempNode.next; //returns the head
    }
    
    

	public static void main(String[] args) {
//		Input: head = [1,2,3,4,5], left = 2, right = 4
//				Output: [1,4,3,2,5]
		
//		[1,2,3,4,5]
//		2
//		4
		LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        System.out.println("Linked List before reversal: \n ");
        list.printList(head);
 
        list.reverseBetween(head, 2, 4);
        
        System.out.println("\nLinked List after reversal: \n ");
        list.printList(head);
        
//        System.out.println("Reversed :\n" );
//        list.reverseList(head);
//        list.printList(head);
	}

}
