import java.util.*;

/**
* Challenge: Given two Strings, return a list of words that are missing from the 
* first sentence:
* Input: 
* - s: I like to program in Java
* - t: program Java
* Output:
* - I, like, to, in
*/
public class MissingWords {
	public static void main(String[] args) {
		System.out.println("Expected results: [I, like, to,in]");

		String sentence1 = "I like to program in Java";
		String sentence2 = "program Java";
		List<String> testingMissingWords = missingWords(sentence1, sentence2);
		System.out.println("Actual results: " + testingMissingWords);
	}

	/**
	* Determine the list of missing words from list2 not in list 1 
	* @param s first sentence input
	* @param t second sentence input
	* @return List of missing words
	*/
	static List<String> missingWords(String s, String t) {
        // Define the returned List of the missing words
        List<String> missing = new ArrayList<String>();
        // Split both string with the spac as the delimiter
        String[] splittedS = s.split(" ");
        String[] splittedT = t.split(" ");
        int runner = 0; // Runner variable to move along t
        
        // Loop through each word in s
        for (int i = 0; i < splittedS.length ; i++) {
            System.out.println("Query word: " + splittedS[i] + " compared to: " + splittedT[runner]);
            // Compare word in s with word in t, if they are not 
            // equal, add such word to the output list
            if (!(splittedS[i].equals(splittedT[runner]))){
                missing.add(splittedS[i]);
            } else {
                // For corner-case: delete the word from t
                // Example: 
                // s: i play play play
                // t: play
                // expected output: i play play
                splittedT[runner] = "";
                runner++;
                // This condition avoids to going out of bound in t
                if (runner == splittedT.length) {
                    runner = 0;
                }
            }
        }
        return missing;
    }
}