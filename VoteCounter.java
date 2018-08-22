import java.util.*;

/**
* Challenge: Write a function that determines the winner of a voting contest
* Rules: If two or more candidates has the same number of votes, the winner is 
* determined by the last name after ordering it alphabetically.
* Input: Jane, Jane, Joe, Joe, Jeff, Jeff, Jane, Jeff
* Output: Jeff
* Explanation: Both Jave and Jeff have 3 votes each, but Jeff comes after Jane, so 
* he is declared as the winner
*/
public class VoteCounter {
	public static void main(String[] args) {
		System.out.println("Expected result: Jeff");

		String[] candidates = {"Jane", "Jane", "Joe", "Joe", "Jeff", "Jeff", "Jane", "Jeff"};
		String testingElectionWinner = electionWinner(candidates);
		System.out.println("Actual result: " + testingElectionWinner);
	}

	/**
	* Determine the winner from a voting system
	* @param votes array of strings with a list of the names of the candidates
	* @return the name of the candidate with the highest number of votes and with the last name in alphabetical order.
	*/
	static String electionWinner(String[] votes) {
        // To hold the output
        String winner = "";
        // Get the frequency of the votes for each candidate and store them
        // in a HashMap.
        HashMap<String,Integer> voteCounter = new HashMap<String,Integer>();
        // Iterate on the input array of strings
        for (int i = 0 ; i < votes.length ; i++) {
            // Add the candidate if it doesn't appear on the voteCounter object
            if (!voteCounter.containsKey(votes[i])) {
                voteCounter.put(votes[i], 1);
            } else {
                // If the candidate it's already on the voteCounter, increment
                // the number of votes
                int count = voteCounter.get(votes[i]);
                voteCounter.replace(votes[i], count, ++count);
            }
        }
        
        // Create another map to hold candidates sorted by values (using the helper function)
        HashMap<String,Integer> voteCounterSortedByValues = sortByValues(voteCounter);
        
        // Debug helper
        System.out.println("\nSorted by Values");
        voteCounterSortedByValues.forEach((k,v) -> System.out.println(k  + " " + v));
        
        // Iterate on the sorted vote counter object to determine the highest number of vote
        int maxPossibleVote = 0;
        for (Map.Entry<String, Integer> entry: voteCounterSortedByValues.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > maxPossibleVote) {
                maxPossibleVote = value;
            }
        }
    
        // Create another map to store the list of candidates who match the highest number of vote
        // Note: TreeMap naturally sorts by key
        TreeMap<String,Integer> voteCounterSortedByKeys = new TreeMap<String,Integer>();
        for (Map.Entry<String, Integer> entry: voteCounterSortedByValues.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value == maxPossibleVote) {
                voteCounterSortedByKeys.put(key, value);
            }
        }
        
        // Debug helper
        System.out.println("\nSorted by Keys");
        voteCounterSortedByKeys.forEach((k,v) -> System.out.println(k  + " " + v));
        
        // Finally, retrieve from the treemap the last key on the list previously sorted
        winner = voteCounterSortedByKeys.lastKey();
        return winner;
    }

    /**
    * Helper function that allows to sort the list of candidates by the number of votes
    * @param map input hashMap data structure that holds the list of candidates
    * @return A new hashMap with the list of candidates sorted by values
    */
    private static HashMap sortByValues(HashMap map) { 
       List list = new LinkedList(map.entrySet());
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });

       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
    }
}