import java.util.*;

/**
* Challenge: Given an array of sentences and an array of queries, determine which 
* sentence contain all of the words of a query. The goal is to return a list of 
* indexes from the sentences that contain all the words in a query.
* Input: 
* - sentences = ["She likes him", "He likes her", "He doest not like Jane"]
* - queries = ["likes", "He Jane"]
* Output: 0 1 and 2
* Explanation: The index 0 and 1 from sentences contains all the words from query 0 (likes)
* The index 2 from sentences contains all the words from query 1 (He Jane);
*/
public class Phrases {
	public static void main(String[] args) {
		System.out.println("Expected result: \n0 1\n2");

		List<String> sentences = new ArrayList<String>();
		sentences.add("She likes him");
		sentences.add("He likes her");
		sentences.add("He does not like Jane");

		List<String> queries = new ArrayList<String>();
		queries.add("likes");
		queries.add("He Jane");

		System.out.println("Actual results:");
		textQueries(sentences, queries);
	}

	/**
	* Determine the index of the sentence that contain all the words of a query. 
	* If none of the sentences contain all words of a query, return -1. 
	* @param sentences an array of strings with sentences 
	* @param queries an array of strings with queries
	*/
	static void textQueries(List<String> sentences, List<String> queries) {
		for (String q : queries) {
            // Split the query into an array of words
            String[] splittedQuery = q.split(" ");
            int index = 0;
            int matchQueryCounter = 0;
    
            // Iterate on the list of sentences
            for(String s : sentences) {
                // Split the sentence into an array of words
                String[] splittedSentence = s.split(" ");
                
                // Set of Strings to add each word on a sentence
                Set<String> sentenceSet = new HashSet<String>();
                for (int i = 0 ; i < splittedSentence.length ; i++) {
                    sentenceSet.add(splittedSentence[i]);
                }

                int matchWordCounter = 0;
                // Attempt to add each word from the query to the previous set,
                // if the word is found duplicated, then the add function returns false.
                for (int i = 0 ; i < splittedQuery.length ; i++) {
                    if (!sentenceSet.add(splittedQuery[i])) {
                        matchWordCounter++;
                    }
                }
                
                // Verify if the counter matches the number of words in the query
                if (matchWordCounter == splittedQuery.length) {
                    System.out.print(index + " ");
                } else {
                    matchQueryCounter++;
                }
                ++index;
            }
            
            if (matchQueryCounter == sentences.size()) {
                System.out.println("-1");    
            } else {
                System.out.println();
            }
        }
	}
}