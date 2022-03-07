package cracking_interview;

/**
 * Tree based data structure that stores letters as nodes.
 * Tries is a useful data-structure that are based on the prefix of a string
 * @author muhammedadeyemi
 *
 */
public class Trie {
	private Node root;
	
	public Trie() {
		root = new Node('\0'); //setting root as an empty character
	}
	
	/**
	 * Inserts a word into the trie
	 */
	public void insert(String word) {
		//keep track of a current node we are moving through the Trie data structure
		Node curr = root;
		for(int i = 0; i < word.length(); i++) {
			
			char c = word.charAt(i);
			
			//check if character exists in trie.
			if(curr.children[c-'a'] == null) //if there does not exists one of the character of the word in trie, create one and branch off 
				curr.children[c-'a'] = new Node(c);
			
			
			curr = curr.children[c-'a']; //moving down the trie
		}
		curr.isWord = true; //very last character inserted should be set to True representing a word
	}
	
	/**
	 * Returns if the word is in the trie or not
	 */
	public boolean search(String word) {
		Node node = getNode(word); //checking if the word is in trie
		return node != null && node.isWord;
	}
	
	/**
	 * @param prefix to search for
	 * @return if there is any word in the trie that starts with the given prefix
	 */
	public boolean startsWith(String prefix) {
		return getNode(prefix) != null;
	}
	
	/**
	 * Helper function(should be private)
	 * @param node we are looking for
	 * @return the very last node of the word that we are looking for
	 *
	 */
	private Node getNode(String word) {
		Node curr = root;
	
		//loops each character through the trie to make sure they all exist
		//and if so, return last node of the word.
		for(int i=0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			//edge case - if any of the character is not in trie, then word doesn't exist
			if(curr.children[c-'a'] == null) 
				return null;
			
			curr = curr.children[c-'a']; //assigned here to store last character of word.
		}
		return curr; //this will be the very last character in the word
	}
	
	class Node {
		public char c;
		public boolean isWord;
		public Node[] children;  
		
		public Node(char c) {
			this.c = c;
			isWord = false;
			children = new Node[26]; //array to store letters.
		}
	}
	
	
}
