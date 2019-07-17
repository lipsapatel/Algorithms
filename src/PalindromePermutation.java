/**
 *
 Given a string, determine if a permutation of the string could form a palindrome.
 Example 1:
 Input: "code"
 Output: false

 Example 2:
 Input: "aab"
 Output: true

 Example 3:
 Input: "carerac"
 Output: true

 Solution
 Approach #1 Brute Force [Accepted]
 If a string with an even length is a palindrome, every character in the string must always occur an even number of times. If the string with an odd length is a palindrome, every character except one of the characters must always occur an even number of times. Thus, in case of a palindrome, the number of characters with odd number of occurences can't exceed 1(1 in case of odd length and 0 in case of even length).
 Based on the above observation, we can find the solution for the given problem. The given string could contain atmost all the ASCII characters from 0 to 127. Thus, we iterate over all the characters from 0 to 127. For every character chosen, we again iterate over the given string sss and find the number of occurences, chchch, of the current character in sss. We also keep a track of the number of characters in the given string sss with odd number of occurences in a variable countcountcount.
 If, for any character currently considered, its corresponding count, chchch, happens to be odd, we increment the value of countcountcount, to reflect the same. In case of even value of chchch for any character, the countcountcount remains unchanged.
 If, for any character, the countcountcount becomes greater than 1, it indicates that the given string sss can't lead to the formation of a palindromic permutation based on the reasoning discussed above. But, if the value of countcountcount remains lesser than 2 even when all the possible characters have been considered, it indicates that a palindromic permutation can be formed from the given string sss.

 Complexity Analysis
 Time complexity : O(128∗n)O(128*n)O(128∗n). We iterate constant number of times(128) over the string sss of length nnn giving a time complexity of 128n128n128n.
 Space complexity : O(1)O(1)O(1). Constant extra space is used.

 Approach #2 Using HashMap [Accepted]
 Algorithm

 1) From the discussion above, we know that to solve the given problem, we need to count the number of characters with odd number of occurrences
 in the given string s.
 2) To do so, we can also make use of a hashmap, map. This map takes the form (characteri,numberofoccurencesofcharacteri)
 3) We traverse over the given string s. For every new character found in s, we create a new entry in the map for this character
 with the number of occurrences as 1. Whenever we find the same character again, we update the number of occurrences appropriately.
 4) At the end, we traverse over the map created and find the number of characters with odd number of occurrences.
 If this count happens to exceed 1 at any step, we conclude that a palindromic permutation isn't possible for the string s.
 But, if we can reach the end of the string with count lesser than 2, we conclude that a palindromic permutation is possible for s.

 The following animation illustrates the process.
 Current
 1 / 13
 Complexity Analysis
 Time complexity : O(n). We traverse over the given string s with n characters once. We also traverse over the map which can grow
 upto a size of n in case all characters in s are distinct.
 Space complexity : O(n). The hashmap can grow upto a size of n, in case all the characters in s are distinct.

 Approach #3 Using Array [Accepted]
 Algorithm

 1) Instead of making use of the inbuilt Hashmap, we can make use of an array as a hashmap. For this, we make use of an array with length 128.
 2) Each index of this map corresponds to one of the 128 ASCII characters possible.
3) We traverse over the string s and put in the number of occurrences of each character in this map appropriately as done in the last case.
 Later on, we find the number of characters with odd number of occurrences to determine if a palindromic permutation is possible
 for the string s or not as done in previous approaches.

 Complexity Analysis
 Time complexity : O(n). We traverse once over the string s of length n. Then, we traverse over the map of length 128(constant).
 Space complexity : O(1). Constant extra space is used for map of size 128.

 Approach #4 Single Pass [Accepted]:
 Algorithm

 1) Instead of first traversing over the string s for finding the number of occurrences of each element and
 then determining the count of characters with odd number of occurrences in s, we can determine the value of count on the fly while
 traversing over s.
 2) For this, we traverse over s and update the number of occurrences of the character just encountered in the map.
 But, when ever we update any entry in map, we also check if its value becomes even or odd. We start of with a count value of 0.
 3) If the value of the entry just updated in map happens to be odd, we increment the value of count to indicate that one more character
 with odd number of occurrences has been found.
 4) But, if this entry happens to be even, we decrement the value of count to indicate that the number of characters with odd number of
 occurrences has reduced by one.
 But, in this case, we need to traverse till the end of the string to determine the final result, unlike the last approaches,
 where we could stop the traversal over map as soon as the count exceeded 1.
 This is because, even if the number of elements with odd number of occurrences may seem very large at the current moment,
 but their occurrences could turn out to be even when we traverse further in the string s.

 At the end, we again check if the value of count is lesser than 2 to conclude that a palindromic permutation is possible for the string s.

 Complexity Analysis
 Time complexity : O(n). We traverse over the string s of length n once only.
 Space complexity : O(128)O(128)O(128). A map of constant size(128) is used.
 */
public class PalindromePermutation {

    public static boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }
        return count <= 1;
    }

    public static void main(String[] args) {
        String s = /*"code";*/ "aab";
        System.out.println("Is any of the permutations palindrome: " + canPermutePalindrome(s));
    }
}
