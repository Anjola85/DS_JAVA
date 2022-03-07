package cracking_interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

import cracking_interview.BinarySearchTree.Node; 


/**
 * Binary Trees are complete trees
 * Main operations: insert, delete and search
 * @author muhammedadeyemi
 *
 */
public class BinarySearchTree {

	class Node {
		int data;
		Node left, right;
		
		Node(int v) {//constructor to set the value
			this.data = v;
			right = null;
			left = null;
		}
	}
	
	Node root; //starting node of the tree
	
	
	/**
	 * Inserting into the BST, NOTE: BST cannot have duplicates 
	 * @param curr - refers to the parent node(starting with the root node)
	 * @param v - value to be inserted
	 * 
	 * If the new nodes value is lower than the current nodes, we go to the left child.
	 * If the new nodes value is greater, we go right
	 * when the current node is null, we've reached a leaf node and we can insert the new node in that position
	 */
	public Node add(int value) {
		//balance tree when adding
		return root = addRecursive(root, value); //reseet root to whatever is returned??
	}
	
	private Node addRecursive(Node curr, int v) {
		if(curr == null)
			return new Node(v);  //if node is null, add to position.
		
		if(v < curr.data)
			curr.left = addRecursive(curr.left, v);
		else if(v > curr.data)
			curr.right = addRecursive(curr.right, v);
		
		return curr; //value already exists
	}
	

	/**
	 * To find an element we need to implement a search method
	 * @param value - what we are trying to find
	 */
	public boolean containsNode(int value) {
		return containsNodeRecursive(root, value);
	}
	
	private boolean containsNodeRecursive(Node curr, int value) {
		if(curr != null) {
			if(value == curr.data)
				return true;
			
			return value < curr.data ? containsNodeRecursive(curr.left, value) : containsNodeRecursive(curr.right, value);
		}
		return false;
	}
	
	
	/**
	 * Delete a node from binary search tree
	 * @param value
	 */
	public void deleteKey(int value) {
		root = delRecursive(root, value);
	}
	
	private Node delRecursive(Node curr, int value) {
		if(curr == null)
			return curr;
		
		if(value < curr.data)
			curr.left = delRecursive(curr.left, value);
		else if(value > curr.data)
			curr.right = delRecursive(curr.right, value);
		else {//found element to be deleted
			
			//case 1: no child
			if(curr.left == null && curr.right == null) {
				curr = null;
			}			
			//case 2: one child
			else if(curr.left == null){
				Node temp = curr; //store current node to be deleted
				curr = curr.right; //reset current node position to the next node
				temp = null;
			}
			else if(curr.right == null) {
				Node temp = curr; //store current node to be deleted
				curr = curr.left; //reset current node position to the next node
				temp = null;
			}
			//case 3: two children		
			else {
				//search for minimum element in right subtree of the node we are trying to delete
				Node temp = findMin(curr.right);
				curr.data = temp.data;
				curr.right = delRecursive(curr.right, curr.data); //delete the duplicate element from right subtree
			}
		}
		
		return curr;
	}
	
	/**
	 * returns the minimum value of a given tree
	 * @param curr - represents root element in tree
	 * @return minimum element
	 */
	private Node findMin(Node curr) {

		if(curr == null)
			return curr;
		
		else if(curr.left == null)
			return curr;
		
		return findMin(curr.left);
	}
	
	
	
	
	void preorder() {
		preOrderTraversal(root);
	}
	
	void inorder() {
		inOrderTraversal(root);
	}
	
	void postorder() {
		postOrder(root);
	}
	
	/**
	 * Traversal with recursion
	 * @param node
	 */
	
	static void preOrderTraversal(Node node) {
		if(node != null) {
			System.out.println(node.data);
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}
	
	static void inOrderTraversal(Node curr) {
		if(curr != null) {
			inOrderTraversal(curr.left);
			System.out.println(curr.data);
			inOrderTraversal(curr.right);
		}
	}
	
	static void postOrder(Node curr) {
		if(curr != null) {
			postOrder(curr.left);
			postOrder(curr.right);
			System.out.println(curr.data);
		}
	}
	
	
	/**
	 * Invert binary search tree
	 * @param args
	 */
    public Node invertTree(Node root) {

        if(root == null)
            return root;
        
        Node left = invertTree(root.left);
        Node right = invertTree(root.right);

        //swap
        root.right = left;
        root.left = right;
                   
        return root;
    }
    
    
    /**
     * Traversal(DFS) without recursion -> using stacks
     */
    
    public void inOrderIterate(Node root) {
    	//left -> root -> right
//        ArrayList<Integer> list = new ArrayList<Integer>();
        
        if(root == null)
            return;
        
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        
        //traverse tree, curr node will be null when it reaches a leafNode, that is when
        //it goes back and pops the parent node and then checks if the parent node has a right node.
        //if not pop again and go to parent
        while(curr != null || !s.isEmpty()) {
            
            //reach leftmost node of curr node first
            while(curr != null) {
                s.push(curr);
                curr = curr.left;
            }

            curr = s.pop();
            System.out.println(curr.data);
//            list.add(curr.data);
            
            //now do for right
            curr = curr.right;
        }

    }
    
    
    public void preOrderIterate(Node root) {
    	//root -> left -> right
    	if(root == null)//edge case
    		return;
    	
    	Stack<Node> s = new Stack<Node>();
    	s.push(root);
    	while(!s.isEmpty()) {
    		Node curr = s.pop();
    		System.out.println(curr.data);
    		
    		//push right elements in first
    		Node n = curr.right;
    		if(n != null)
    			s.push(n);
    		n = curr.left;
    		if(n != null)
    			s.push(n);
    	}
    }
    
    public void postOrderIterate(Node root) {
    	//left -> right -> root
    	if(root == null)
    		return;
    	
    	Stack<Node> s = new Stack<Node>();
    	
    	List<Integer> list = new ArrayList<Integer>();
    	//add root
    	s.push(root);
    	
    	while(!s.isEmpty()) {
    		Node top = s.pop();
    		list.add(0, top.data); //pushes the value to the right
    		if(top.left != null)
    			s.push(top.left);
    		if(top.right != null)
    			s.push(top.right);
    	}
    	
    	for(int i : list)
    		System.out.println(i);
//    	System.out.println(list.toString());
    }
    
    /**
     * Given an n-ary tree, find the maximum depth - leetcode 559
     */
    public int maxDepth(Node root) {
        if(root == null)
            return 0;
        
        int depth = 0;
        
        //using bfs. Hecne the use of queue
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root); //adding first element
        
        //evaluate levels
        while(!queue.isEmpty()) {
            
            int size = queue.size();       
            //loop through the size of the queue
            //since we need to check if each node has a child
            for(int i = 0; i < size; i++) {
                
                Node currNode = queue.poll();

                //if there are children, insert them as the next level 
//                for(Node child : currNode.children) //you will be given a Node class with children variable since it is an n-ary tree
//                    queue.offer(child);
            }
            
            //then increment depth since level has been added
            depth++;
        }
        return depth;
    }
    
    /**
     * Convert sorted array to a binary tree - Minimal Tree
     */
    //in a sorted array - think binary search
    //hence root is the middle element
    public Node sortedArrayToBST(int[] nums) {
        if(nums.length == 0)
            return null;
        
        return constructTreeFromArray(nums, 0, nums.length-1);
    }
    
    private Node constructTreeFromArray(int[] nums, int left, int right) {
        if(left > right) //checking boundaries
            return null;
        
        int midpoint = left + (right-left)/2;  //calculate midpoint
        Node node = new Node(nums[midpoint]); //create Node
        node.left = constructTreeFromArray(nums, left, midpoint-1); //set left boundary, so from 0 to the element b4 the middle element
        node.right = constructTreeFromArray(nums, midpoint+1, right); //set right bndary,from element after midpoint to last elmnt in arr
        return node; // first call will make the middle element the root node
    }
    
    
    /*Morris inorder traversal
     * 
     * Traversing a tree in O(1) space
     * finding predecessor -> go left from curr and keep going right, if only left exist? set as predecessor
     * now set curr to curr.left if it exists otherwise print curr and curr = curr.right 
    */
    
    

    
    
    /**
     * Merge two binary trees
     * @param args
     */
	
	public static void main(String[] args) {

//		String  str = "421";
//		int c = (str + '0');
//		System.out.println(c+1);
//        BinarySearchTree tree = new BinarySearchTree();
        
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
//        Node root = tree.add(50);
//        tree.add(30);
//        tree.add(20);
//        tree.add(40);
//        tree.add(70);
//        tree.add(60);
//        tree.add(80);
// 
//        System.out.println(
//            "Inorder traversal of the given tree");
//        tree.inOrderIterate(root);
// 
//        System.out.println("\nPreorder traversal of the given tree");
//        
//        tree.preOrderIterate(root);
//        
//        System.out.println("\nPostorder traversal of the given tree");
//        
//        tree.postOrderIterate(root);
//        System.out.println("\nDelete 20");
//        tree.deleteKey(20);
//        System.out.println(
//            "Inorder traversal of the modified tree");
//        tree.inorder();
// 
//        System.out.println("\nDelete 30");
//        tree.deleteKey(30);
//        System.out.println(
//            "Inorder traversal of the modified tree");
//        tree.inorder();
// 
//        System.out.println("\nDelete 50");
//        tree.deleteKey(50);
//        System.out.println(
//            "Inorder traversal of the modified tree");
//        tree.inorder();
		
	}

}
