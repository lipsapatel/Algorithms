package IK.Sorting.HomeWork1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a number and a list of words, return the given number of most frequent words.
 *
 * Example
 * {
 * "k": 4,
 * "words": ["car", "bus", "taxi", "car", "driver", "candy", "race", "car", "driver", "fare", "taxi"]
 * }
 * Output:
 *
 * ["car", "driver", "taxi", "bus"]
 * Notes
 * Every word consists of only lowercase English letters.
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 * Constraints:
 *
 * 1 <= number of words <= 105
 * 1 <= size of each word <= 10
 * 1 <= the given number <= the number of unique words
 *
 * *****************************************************************************************************
 * Approach
 * 1) Create HashMap which stores words and their priorities.
 * 2) Create Priority Queue of Map.Entry and define comparator which compares by frequencies if they are not equal
 * 3) If frequency is same then compare by word lexicographically
 * 4) The size of priority queue is k
 * 5) Iterate map and offer elements to priority queue
 * 6) If size of priority queue is greater than k then poll.
 * 7) Iterate priority queue of k elements and add to result arraylist
 * 8) Return result array list
 *
 * TC: O(n + nlogk) where n = time to create hashmap and nlog to add elements to priority queue
 * SC: O(n + k) where n = size of hashmap and k = size of priority queue.
 * */
public class KMostFrequentWordsUsingHeap {

    private static ArrayList<String> kMostFrequentWordsUsingHeap(ArrayList<String> words, Integer k) {

        //Create HashMap of words and their frequencies
        HashMap<String, Integer> map = new HashMap<>();
        for(String w: words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        //Create Priority Queue of size k
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, (entry1, entry2) -> { //Min Priority Queue

            if(!entry1.getValue().equals(entry2.getValue())) { //Frequencies are not equal
                return entry1.getValue().compareTo(entry2.getValue());
            } else { //Compare words lexicographically
                return entry2.getKey().compareTo(entry1.getKey());
            }

        });

        //Add items to priority queue from hashmap
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            pq.offer(entry);

            if(pq.size() > k) {
                pq.poll();
            }
        }

        //Create final result array
        ArrayList<String> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }

        //This will return elements in increasing order because its min heap so reverse the result
        for(int i = 0; i < result.size()/2; i++) {
            swap(result, i, result.size() - 1 - i);
        }
        return result;
    }

    private static void swap(ArrayList<String> result, int i, int j) {
        String temp = result.get(i);
        result.set(i, result.get(j));
        result.set(j, temp);
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("car");
        words.add("bus");
        words.add("taxi");
        words.add("car");
        words.add("driver");
        words.add("candy");
        words.add("race");
        words.add("car");
        words.add("driver");
        words.add("fare");
        words.add("taxi");

        System.out.println("The k most frequent words are " + kMostFrequentWordsUsingHeap(words, 4));
    }
}
