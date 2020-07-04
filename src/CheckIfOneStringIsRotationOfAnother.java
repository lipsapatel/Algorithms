/**
 * Check if one string is rotation of another string
 *
 * Input String: lipsa patel
 * and psapatelli
 *
 * true
 *
 * Input String lipsa patel
 * and pspatellia
 *
 * false
 *
 * Approach:
 *
 * 1) stringAdd = s1 + s1;
 * Make a new string by appending first string to itself
 * 2) stringAdd.contains(s2);
 * Check if second string is substring of new string.
 *
 * The time complexity depends on the contains method.
 * Java contains method called indexOf which uses brute force approach.
 * Java chose this to brute force approach since this is usually used for smaller strings.
 *
 * But the time complexity can be reduce to O(m + n) using Rabin karp rolling hash algorithm.
 *
 * Space Complexity: O(2n)
 * Time Complexity: O(2n * n) = where 2n is the length stringAdd and n is the length of s2
 * = O(n ^ 2) = O(mn) where m = length of added string and n = s2 length
 */
public class CheckIfOneStringIsRotationOfAnother {

    private static boolean checkIfOneStringIsRotationOfAnother(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        String stringAdd = s1 + s1;

        if (stringAdd.contains(s2)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkRotation(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        String addedString = s1 + s1;

        return searchPattern(addedString, s2);
    }

    private static int calculatePower(int size, int m, int prime) {
        int ans = 1;
        for(int i = 0; i < m; i++) {
            ans = (ans * size) % prime;
        }
        return ans;
    }

    private static boolean searchPattern(String txt, String pattern) {
        int n = txt.length();
        int m = pattern.length();

        int prime = 101;
        int size = 256;

        //Calculate hash of pattern and first m characters of text
        int hp = 0;
        int ht = 0;

        for(int i = 0; i < m; i++) {
            hp = (hp + (pattern.charAt(i) * calculatePower(size, m - i - 1, prime))) % prime;
            ht = (ht + (txt.charAt(i) * calculatePower(size, m - i - 1, prime))) % prime;
        }

        //Rolling hash - Slide the window and calculate hash
        //If hp == ht, then compare character one by one
        for (int i = 0; i <= n - m; i++) {
            //Check if the hash values are same, if hash values match
            //then compare one character by character
            if(hp == ht) {

                int j;
                for(j = 0; j < m; j++) {
                    if(txt.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                if(j == m) {
                    return true;
                }
            }

            if(i < n - m) {
                ht = (((ht - (txt.charAt(i) * calculatePower(size, m - 1, prime))) * size)
                        + txt.charAt(i + m)) % prime;

                if(ht < 0) {
                   ht = ht + prime;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        String s1 = "lipsapatel";
        String s2 = "psapatelli";

        System.out.println("The strings are rotation of each other: " + checkIfOneStringIsRotationOfAnother(s1, s2));
        System.out.println("The strings are rotation of each other rolling hash: " + checkRotation(s1, s2));

        String s3 = "pspatellia";

        System.out.println("The strings are rotation of each other: " + checkIfOneStringIsRotationOfAnother(s1, s3));
        System.out.println("The strings are rotation of each other rolling hash: " + checkRotation(s1, s3));
    }
}
