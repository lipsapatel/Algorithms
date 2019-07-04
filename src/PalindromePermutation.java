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
 From the discussion above, we know that to solve the given problem, we need to count the number of characters with odd number of occurences in the given string sss. To do so, we can also make use of a hashmap, mapmapmap. This mapmapmap takes the form (characteri,numberofoccurencesofcharacteri)(character_i, number of occurences of character_i)(characteri​,numberofoccurencesofcharacteri​).
 We traverse over the given string sss. For every new character found in sss, we create a new entry in the mapmapmap for this character with the number of occurences as 1. Whenever we find the same character again, we update the number of occurences appropriately.
 At the end, we traverse over the mapmapmap created and find the number of characters with odd number of occurences. If this countcountcount happens to exceed 1 at any step, we conclude that a palindromic permutation isn't possible for the string sss. But, if we can reach the end of the string with countcountcount lesser than 2, we conclude that a palindromic permutation is possible for sss.
 The following animation illustrates the process.
 Current
 1 / 13
 Complexity Analysis
 Time complexity : O(n)O(n)O(n). We traverse over the given string sss with nnn characters once. We also traverse over the mapmapmap which can grow upto a size of nnn in case all characters in sss are distinct.
 Space complexity : O(n)O(n)O(n). The hashmap can grow upto a size of nnn, in case all the characters in sss are distinct.

 Approach #3 Using Array [Accepted]
 Algorithm
 Instead of making use of the inbuilt Hashmap, we can make use of an array as a hashmap. For this, we make use of an array mapmapmap with length 128. Each index of this mapmapmap corresponds to one of the 128 ASCII characters possible.
 We traverse over the string sss and put in the number of occurences of each character in this mapmapmap appropriately as done in the last case. Later on, we find the number of characters with odd number of occurences to determine if a palindromic permutation is possible for the string sss or not as done in previous approaches.
 Complexity Analysis
 Time complexity : O(n)O(n)O(n). We traverse once over the string sss of length nnn. Then, we traverse over the mapmapmap of length 128(constant).
 Space complexity : O(1)O(1)O(1). Constant extra space is used for mapmapmap of size 128.

 Approach #4 Single Pass [Accepted]:
 Algorithm
 Instead of first traversing over the string sss for finding the number of occurences of each element and then determining the countcountcount of characters with odd number of occurences in sss, we can determine the value of countcountcount on the fly while traversing over sss.
 For this, we traverse over sss and update the number of occurences of the character just encountered in the mapmapmap. But, whevenever we update any entry in mapmapmap, we also check if its value becomes even or odd. We start of with a countcountcount value of 0. If the value of the entry just updated in mapmapmap happens to be odd, we increment the value of countcountcount to indicate that one more character with odd number of occurences has been found. But, if this entry happens to be even, we decrement the value of countcountcount to indicate that the number of characters with odd number of occurences has reduced by one.
 But, in this case, we need to traverse till the end of the string to determine the final result, unlike the last approaches, where we could stop the traversal over mapmapmap as soon as the countcountcount exceeded 1. This is because, even if the number of elements with odd number of occurences may seem very large at the current moment, but their occurences could turn out to be even when we traverse further in the string sss.
 At the end, we again check if the value of countcountcount is lesser than 2 to conclude that a palindromic permutation is possible for the string sss.

 Complexity Analysis
 Time complexity : O(n)O(n)O(n). We traverse over the string sss of length nnn once only.
 Space complexity : O(128)O(128)O(128). A mapmapmap of constant size(128) is used.
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
