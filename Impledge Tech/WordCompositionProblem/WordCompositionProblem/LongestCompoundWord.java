import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Name - Chitransh Varshney
// Branch - Information Technology
// College Name - Raj Kumar Goel Institute Of Technology
// Roll No. - 2000330130019

// Using Trie, which let words to share prefixes.
// This program calculate the First longest and the second longest words in a file
// that can be constructed by concatenating copies of shorter words 
// also found in the file. 
// total amount of compound words can also be calculated.

//   Steps : 
//   1. Firstly scan the file line by line and create a trie by using the words in the file.
//   Before inserting each word into the trie, it will check if the word has any prefixes. If yes, it will create
//   word-suffix pairs and add them into a queue. 
//   2. Then, the algorithm will pop a pair from the queue to see if the suffix in the word-suffix pair is a word 
//   in the file. If yes, the word is a compound word. Then, it will update the number of compound words, and
//   if it also is longer than the previous compound word, it will update the variables of longestCompoundWord
//   and secondLongestCompoundWord as well. 
//   3. If the suffix in the word-suffix pair is not a word in the file, it will check if the suffix has any prefixes.
//   If yes, it will create new word-suffix pairs and add them into the queue. Otherwise, it will just discard the pair
//   and pop a new pair from the queue and repeat the process until the queue is empty.
//   4. The algorithm runs in linear, O(kN),  where N is the number of words in the input file, and k is the maximum 
//   number of words in a compound word.
 

public class LongestCompoundWord {

	public static void main(String[] args) throws FileNotFoundException {
		//for calculating time ....
		long startTime = System.nanoTime();
		
		// change file name accordingly
		File file = new File("input2.txt");

		// Trie
		Trie trie = new Trie();
		LinkedList<Pair<String>> q = new LinkedList<Pair<String>>(); // Make a Queue
		
		// Calculate the total amount of valid or compound words 
		HashSet<String> compoundWords = new HashSet<String>();
		
		// scan the file
		@SuppressWarnings("resource")      // Use of @SuppressWarnings is to suppress or ignore warnings coming from the compiler.
		Scanner s = new Scanner(file);

		String word;				
		List<Integer> suffixIndices;	// indices of suffixes of a word
		
		//1) read words from the file.
		//2) insert each word in trie.
		//3) fill up the queue with words which have suffixes.

		while (s.hasNext()) {
			word = s.next();		
			suffixIndices = trie.getSuffixes(word); 

			trie.insert(word);

			for (int i : suffixIndices) {
				q.add(new Pair<String>(word, word.substring(i)));  // word.substring(i) - means suffix
			}
		}
		
		Pair<String> p;				// a pair of original word and its remaining suffix
		int maxLength = 0;			// longest compound word length
	
		String First_longest = "";		// longest compound word
		String Sec_longest = "";	// second longest compound word

		// start popping
		while (!q.isEmpty()) {
			p = q.removeFirst();
			word = p.second();
			
			suffixIndices = trie.getSuffixes(word);
			
			// if no suffixes found, which means no prefixes found
			// discard the pair and check the next pair
			if (suffixIndices.isEmpty()) {
				continue;
			}
			
			
			for (int i : suffixIndices) {
				if (i == word.length()) { // no suffix, means it is a compound word
					// check if the compound word is the longest
					// if it is update both longest and second longest
					// words records
					if (p.first().length() > maxLength) {
						Sec_longest = First_longest;
						maxLength = p.first().length();
						First_longest = p.first();
					}
			
					compoundWords.add(p.first());	// the word is compound word
					
				} else {
					q.add(new Pair<String>(p.first(), word.substring(i)));
				}
			}
		}
		   // Stop measuring execution time
		   long endTime = System.nanoTime();
 
		   // Calculate the execution time in milliseconds
		   long executionTime
			   = (endTime - startTime) / 1000000;
		

		// All Results......
		System.out.println("First Longest Compound Word: " + First_longest);
		System.out.println("Second Longest Compound Word: " + Sec_longest);
		System.out.println("Time taken to process file "+ file +": " + executionTime  + "  milli seconds");
		System.out.println("Total Number of Compound Words: " + compoundWords.size());
	}
}

