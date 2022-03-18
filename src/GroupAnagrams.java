import java.util.*;

/*
Given list of words, group all the anagrams and print it.
For example:

later, alter, alert
angle, angel
allergy, gallery, largely
mean, name
aligned, dealing, leading

Approach1:

N = number of words
K = length of each word

1) Iterate all words O(N)
2) For each word, sort all the letters in the word. This will be the key in HashMap. O(KlogK)
3) Check if that sorted work exists in HashMap
4) If it does then add or append word
5) If it does not exist in hash map then create new entry with key and anagram(word)

TC: O(NKlogK)
SC: O(NK)

**************************************************************************************

Approach 2:

1) Iterate all words O(N)
2) For each word, create map of character and its count. This will be key in HashMap. O(K)
3) Check if that key exists in HashMap
4) If it does then add or append word
5) If it does not exist in hash map then create new entry with key and anagram (word)

TC: O(NK)
SC: O(NK)

 */
public class GroupAnagrams {

    private static String getSortedKey(String word) {

        //Convert work to lowercase
        String lowerCaseWord = word.toLowerCase(); //This makes it case insensitive

        //Convert it to char array and sort char
        char[] chars = lowerCaseWord.toCharArray();

        Arrays.sort(chars);

        return Arrays.toString(chars);
    }

    public static void main(String[] args) {

        String[] words = {"later", "alter", "alert", "angle", "angel", "allergy", "gallery", "largely"};

        //Approach 1 - Sorted Key
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            String signature = getSortedKey(words[i]);

            if (map.containsKey(signature)) {
                List<String> anagrams = map.get(signature);
                anagrams.add(words[i]);
            } else {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(words[i]);
                map.put(signature, anagrams);
            }
        }
        System.out.println("Approach1");
        for (List<String> entry: map.values()) {
            System.out.println(entry);
        }

        //Approach 2:
        HashMap<HashMap<Character, Integer>, ArrayList<String>> map1 = new HashMap<>();

        for(String word: words) {
            HashMap<Character, Integer> key = getKeyByCharCount(word);

            if(map1.containsKey(key)) {
                ArrayList<String> anagrams = map1.get(key);
                anagrams.add(word);
            } else {
                ArrayList<String> anagrams = new ArrayList<>();
                anagrams.add(word);
                map1.put(key, anagrams);
            }
        }

        System.out.println("Approach2");
        for(ArrayList<String> anagrams: map1.values()) {
            System.out.println(anagrams);
        }
    }

    private static HashMap<Character, Integer> getKeyByCharCount(String word) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if(map.containsKey(c)) {
                int count = map.get(c) + 1;
                map.put(c, count);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }
}
