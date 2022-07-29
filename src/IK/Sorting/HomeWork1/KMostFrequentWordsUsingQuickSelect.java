package IK.Sorting.HomeWork1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
 *
 * Approach
 * 1) Create HashMap of word and their frequencies - O(n)
 * 2) Create Word Class with String word and Integer freq
 * 3) Word class implements Comparable and overrides compareTo method
 * 4) compareTo method compares two objects and if their frequencies are not equal then greater frequency goes on left
 * 5) If their frequencies are equal then compare the word and word with lower lexicographical goes to left
 * 6) Create ArrayList of Word objects
 * 7) Do quickselect on Arraylist
 * 8) Partition the list and if pi == k - 1 then return
 * 9) If k - 1 < pi, then recurse on left (low, pi - 1)
 * 10) Recurse on right (pi + 1, high)
 * 11) In partition function, select random pivot and call compareTo method to compare two objects
 * 12) One the array has been partitioned, create sublist till k and then sort that sublist
 * 13) Create final result arraylist of the k most frequent words and return
 *
 * TC: O(n + klogk) - where n = to create hashmap and do quickselect and klogk is to sort sublist of size k
 * SC: O(n + logn) - where n = hashmap and arraylist of size n and logn is recursive call stack for quickselect.
 *
 */
public class KMostFrequentWordsUsingQuickSelect {

    private static ArrayList<String> kMostFrequentWords(ArrayList<String> words, int k) {

        //Create HashMap of word and their frequency - O(n)
        HashMap<String, Integer> map = new HashMap<>();

        for(String word: words) {
            if(map.containsKey(word)) {
                int freq = map.get(word);
                map.put(word, freq + 1);
            } else {
                map.put(word, 1);
            }
        }

        //Add to ArrayList of Word object
        ArrayList<Word> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            list.add(new Word(entry.getKey(), entry.getValue()));
        }

        //Do quickselect - O(n)
        quickSelect(list, 0, list.size() - 1, k);

        //Get the sublist from 0 to k. This returns List object and not ArrayList
        List<Word> sublist = list.subList(0, k);

        //Sort the sublist - O(klogk)
        Collections.sort(sublist);

        //Create list with only words
        ArrayList<String> result = new ArrayList<>();

        for(Word w: sublist) {
            result.add(w.word);
        }
        return result;
    }

    private static void quickSelect(ArrayList<Word> list, int low, int high, int k) {

        int pi = partition(list, low, high);

        if(pi ==  k - 1) {
            return;
        } else if (k - 1 < pi) {
            //Recurse Left
            quickSelect(list, low, pi - 1, k);
        } else {
            //Recurse right
            quickSelect(list, pi + 1, high, k);
        }
    }

    private static int partition(ArrayList<Word> list, int low, int high) {

        Random random = new Random(System.currentTimeMillis());
        int pi = low + random.nextInt(high - low + 1);

        swap(list, pi, high);
        Word pivot = list.get(high);

        int i = low - 1;

        for(int j = low; j < high; j++) {
            if(list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        i++;
        swap(list, i, high);
        return i;
    }

    private static void swap(ArrayList<Word> list, int i, int j) {
        Word temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static class Word implements Comparable<Word> {

        String word;
        Integer freq;

        public Word(String w, Integer f) {
            this.word = w;
            this.freq = f;
        }

        @Override
        public int compareTo(Word w) {
            if(this.freq != w.freq) {
                return -1 * this.freq.compareTo(w.freq); //This should go left if frequency is greater than pivot
            } else {
                return this.word.compareTo(w.word); //Order word lexicographically so lower will go left
            }
        }
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

        System.out.println("The k most frequent words are " + kMostFrequentWords(words, 4));
    }
}
