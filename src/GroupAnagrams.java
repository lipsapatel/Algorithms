import java.util.*;

/*
Given list of works, group all the anagrams and print it.
For example:

later, alter, alert
angle, angel
allergy, gallery, largely
mean, name
aligned, dealing, leading

Approach:

1) Create a signature for a word.
2) Store the signature and all the anagrams in the map
3) Print the anagrams

Time Complexity: nlogn to sort
For M words with N chars = MNlog(N) + M(to iterate words)
 */
public class GroupAnagrams {

    private static String getSignature(String word) {

        //Convert work to lowercase
        String lowerCaseWord = word.toLowerCase();
        //Convert it to char array and sort char
        char[] chars = lowerCaseWord.toCharArray();

        Arrays.sort(chars);

        return Arrays.toString(chars);
    }

    public static void main(String[] args) {

        String[] words = {"later", "alter", "alert", "angle", "angel", "allergy", "gallery", "largely"};

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            String signature = getSignature(words[i]);

            if (map.containsKey(signature)) {
                List<String> anagrams = map.get(signature);

                anagrams.add(words[i]);
                map.put(signature, anagrams);
            } else {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(words[i]);
                map.put(signature, anagrams);
            }
        }
        System.out.println("The anagrams are: ");
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
