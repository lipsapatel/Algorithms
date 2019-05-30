import java.util.HashMap;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:

 Input: s = "anagram", t = "nagaram"
 Output: true
 Example 2:

 Input: s = "rat", t = "car"
 Output: false
 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?

 TC: O(n)
 SC: O(1) Although we use extra space, the space complexity is O(1) because table size stays constant no matter how large n is.

 For unicode characters, use hashmap
 */
public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] alphabets = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabets[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            alphabets[t.charAt(i) - 'a']--;
        }

        for (int i : alphabets) {
            if (i != 0) {
                return false;
            }
        }
        return true;

        //Using HashMap
        /*char[] arr1=s.toCharArray();
        char[] arr2=t.toCharArray();
        HashMap<Character,Integer> h=new HashMap<Character,Integer>();
        if(arr1.length != arr2.length)
            return false;
        for(int i=0;i<arr1.length;i++){
            if(h.get(arr1[i])!= null)
                h.put(arr1[i],(h.get(arr1[i]))+1);
            else
                h.put(arr1[i],1);
        }
        for(int i=0;i<arr2.length;i++){
            if(h.get(arr2[i]) ==null)
                return false;
            else if(h.get(arr2[i])==0)
                return false;
            else
                h.put(arr2[i],(h.get(arr2[i]))-1);
        }
        return true;*/
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println("Is anagram: " + isAnagram(s, t));
    }
}
