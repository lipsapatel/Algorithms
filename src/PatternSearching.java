import java.util.ArrayList;
import java.util.List;

/**
 * Naive algorithm for Pattern Searching
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function
 * search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[].
 * You may assume that n > m.
 *
 * Examples:
 *
 * Input:  txt[] = "THIS IS A TEST TEXT"
 *         pat[] = "TEST"
 * Output: Pattern found at index 10
 *
 * Input:  txt[] =  "AABAACAADAABAABA"
 *         pat[] =  "AABA"
 * Output: Pattern found at index 0
 *         Pattern found at index 9
 *         Pattern found at index 12
 *
 * pattern-searching
 * Pattern searching is an important problem in computer science.
 * When we do search for a string in notepad/word file or browser or database,
 * pattern searching algorithms are used to show the search results.
 *
 * Naive Pattern Searching:
 * Slide the pattern over text one by one and check for a match.
 * If a match is found, then slides by 1 again to check for subsequent matches.
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(1)
 *
 * Best Case Time Complexity: O(n)
 *
 * It occurs when first character of pattern is not present in text at all
 * For example:
 * text = "AABCCAADDEE"
 * pattern = "FAA"
 *
 * Worst Case Time Complexity: O(mn)
 *
 * When all characters in pattern and text are same.
 * For example:
 * text = "AAAAAAAAAAAAAAAAAAAAA"
 * pattern = "AAAA"
 *
 * Also occurs when last character is different
 * For Example:
 * text = "AAAAAAAAAAAAAAAAAAB"
 * pattern = "AAAB"
 *
 * KMP matching algorithm improves worst case to O(n)
 *
 * Example:
 * text = "AABAACAADAABAABA"
 * pattern = "AABA"
 * Output: 0, 9, 12 index at which pattern found.
 *
 * ******************************************************************
 * KMP Pattern Searching
 *
 * If we find the proper prefix which is also a proper suffix, then we can skip
 * unnecessary matches and improve the efficiency of our algorithm.
 *
 * 1) Create LPS table for pattern
 * 2) Whenever there is mismatch, move the position in pattern to that of lps
 * 3) If position becomes 0 in pattern then move the position in text by 1.
 * 4) If there is match, advance position in both text and pattern.
 *
 * Time Complexity: O(n + m) where m is the time taken to build the LPS table
 * and n is the KMP algorithm
 *
 * resources/NaiveApproachPatternSearching1.jpg
 * resources/NaiveApproachPatternSearching2.jpg
 * resources/KMPPatternSearching1.jpg
 * resources/KMPPatternSearching2.jpg
 * resources/KMPPatternSearching3.jpg
 * resources/KMPPatternSearching4.jpg
 * resources/KMPExample1.jpg
 * resources/KMPExample2.jpg
 * resources/KMPExample3.jpg
 *
 * **********************************************************************
 * Rabin Karp
 *
 * Approach
 *
 * 1) Calculate the hash of pattern and first m characters in text.
 * 2) We need hash function without collision or with less collision.
 * 3) Rolling hash: Update the hash in constant time when we slide the window.
 * 4) Match characters only if the hash of the text and the pattern matches
 * 5) When you calculate the hash for next window, subtract the first character and add
 * new character.
 * 6) To avoid integer overflow modulo with some large prime number, this results in
 * collision but it's probability is 1/P for each n.
 *
 * Formula = (A) * (size)^m - 1
 *
 * Time Complexity: Best Case and Average Case = O(m + n + 1/p * n * m)
 * Worst Case Time Complexity when all the characters in text and pattern are same
 * O(n * m)
 *
 * resources/RabinKarpPatternSearching1.jpg
 * resources/RabinKarpPatternSearching2.jpg
 */
public class PatternSearching {

    private static List<Integer> searchKMP(String txt, String pattern) {
        List<Integer> result = new ArrayList<>();

        int n = txt.length();
        int m = pattern.length();

        int[] lps = new int[m];
        computeLPSTable(lps, pattern);

        int i = 0;
        int j = 0;

        while(i < n) {

            if(txt.charAt(i) == pattern.charAt(j)) { //Match move forward
                i++;
                j++;
            }
            //There's a match
            if(j == m) {
                result.add(i - j); //found the substring match
                j = lps[j - 1];
            } else if (i < n && txt.charAt(i) != pattern.charAt(j)) {

                if(j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1; //If j = 0 starting from first, move i forward to next position
                }
            }
        }
        return result;
    }

    private static void computeLPSTable(int[] lps, String pattern) {

        for(int i = 1; i < pattern.length(); i++) {

            int j = lps[i - 1]; //Update prefix boundary

            //Move to the last prefix boundary match
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }

            //If prefix boundary matches the suffix boundary, then increase the prefix length
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
            }
        }
    }

    private static List<Integer> searchRabinKarp(String txt, String pattern) {
        int n = txt.length();
        int m = pattern.length();

        int prime = 101; //Choose the large enough prime so the chances of collision are negligible
        int size = 256;

        //Store the list of starting index which matched the pattern
        List<Integer> result = new ArrayList<>();

        //Calculate hash for pattern and first m characters of txt
        int hp = 0;
        int ht = 0;

        for(int i = 0; i < m; i++) {
            hp = (hp + (pattern.charAt(i) * calculatePower(size, m - i - 1, prime))) % prime;
            ht = (ht + (txt.charAt(i) * calculatePower(size, m - i - 1, prime))) % prime;
        }

        //Rolling hash - Slide the window and calculate hash.
        //if hp == ht then compare character one by one
        for(int i = 0; i <= n - m; i++) {
            //Check if the hash values are same, if the hash value match then
            //compare one character by character
            if(hp == ht) {

                int j;
                for(j = 0; j < m; j++) {
                    if(txt.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                if(j == m) {
                    result.add(i);
                }
            }

            if(i < n - m) { //because you do i + m
                //Calculate hash value of next window
                //Remove leading digit
                //Add trailing digit
                //Promote to higher position
                ht = (((ht - (txt.charAt(i) * calculatePower(size, m - 1, prime)) % prime) * size)
                        + txt.charAt(i + m)) % prime;

                //We might get negative value, converting it to +ve
                if(ht < 0) {
                    ht = ht + prime;
                }
            }
        }
        return result;
    }

    //To avoid integer overflow
    private static int calculatePower(int size, int m, int prime) {
        int ans = 1;
        for(int i = 0; i < m; i++) {
            ans = (ans * size) % prime;
        }
        return ans;
    }

    public static List<Integer> search(String txt, String pattern) {

        ArrayList<Integer> indexes = new ArrayList<>();

        int n = txt.length();
        int m = pattern.length();

        for(int i = 0; i <= n - m; i++) {
            int j;

            for(j = 0; j < m; j++) {
                if(txt.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if(j == m) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static void main(String[] args) {
        String txt = "AABAACAADAABAABA";
        String pattern = "AABA";

        List<Integer> indexes = search(txt, pattern);

        for(int index: indexes) {
            System.out.println(index);
        }

        System.out.println("KMP");

        indexes = searchKMP(txt, pattern);

        for(int index: indexes) {
            System.out.println(index);
        }

        System.out.println("Rabin Karp");

        indexes = searchRabinKarp(txt, pattern);

        for(int index: indexes) {
            System.out.println(index);
        }
    }
}
