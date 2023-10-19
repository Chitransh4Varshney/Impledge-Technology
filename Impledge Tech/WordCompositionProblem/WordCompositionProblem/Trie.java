import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


// A Trie is a multiway tree data structure used for storing strings over an alphabet.
// It is used to store a large amount of strings.
// The Pattern matching can be done efficiently using tries.
// Preprocessing pattern improves the performance of pattern matching algorithm.
// But if a text is very large then it is better to preprocess text instead of pattern for efficient search.
//We use Trie as a helper data structure for class LongestCompoundWord
// each node, which contains a letter as its value,
// In trie may have a list of children nodes.
// Trie is also able to find all suffixes indices of a word


public class Trie {
	
	
	private class TrieNode {
		@SuppressWarnings("unused")    // Use of @SuppressWarnings is to suppress or ignore warnings coming from the compiler.
		private char val;			   // character stored in the node

		private HashMap<Character, TrieNode> children;	// map name of string to the node
														// which has the string as value

		private boolean endOfWord;		// if the node is at the end of a word i.e it give true or false
		
		// constructor
		public TrieNode(char val) {
			this.val = val;
			children = new HashMap<Character, TrieNode>();
			endOfWord = false;  // by default it is false
		}
		
		// add child to trieNode
		public void addChild(char child) {
			children.put(child, new TrieNode(child));
		}
		
		// get child of trieNode that has the same character as the give one
		public TrieNode getChild(char child) {
			if (!children.keySet().contains(child)) {
				return null;
			}
			
			return children.get(child);
		}
		
		// return true if child exists
		public boolean containsChild(char child) {
			return children.keySet().contains(child);
		}
	}
	
	// root node
	private TrieNode root;
	private TrieNode current;  // we use current node because in further places we use root then it will change the root value that's why we use current node .
	
	public Trie() {
		root = new TrieNode(' ');	// root
		current = root;
	}
	
	// insert a word to trie
	public void insert(String word) {
		char letter;
		current = root;
		
		// traverse every letter of a word
		// update trie if a letter is not in the structure
		for (int i = 0; i < word.length(); i++) {
			letter = word.charAt(i);
			
			if (!current.containsChild(letter)) {
				current.addChild(letter);
			} 
			
			// update means going next node
			current = current.getChild(letter);
		}
		
		// mark last letter as the end of a word
		current.endOfWord = true;
	}
	
	// return starting indices of all suffixes of a word
	public List<Integer> getSuffixes(String word) {
		List<Integer> indices = new LinkedList<Integer>();	// store indices
		char letter;
		current = root;	// start from root
		
		for (int i = 0; i < word.length(); i++) {
			letter = word.charAt(i);
			
			// if the current letter doesn't have one letter as child
			// which means trie currently doesn't have the relationship
			// returns indices of suffixes
			if (!current.containsChild(letter))
				return indices;
			
			// move on to the child node
			current = current.getChild(letter);
			
			// if the letter is an end to a word, it means after the letter is a suffix
			// update indices
			if (current.endOfWord)
				indices.add(i + 1);
		}
		
		return indices;
	}
	
}