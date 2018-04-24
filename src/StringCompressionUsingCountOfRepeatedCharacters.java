/**
 * String compression using count of repeated characters.
 * Also run length encoding.
 *
 * Write an algorithm to compress the string by using count
 * of repeated characters and if the new compressed string length is not
 * smaller than the original string then return the original string.
 *
 * Example:
 *
 * Input String: lliipsapaateel
 * Compressed String: l2i2p1s1a1p1a2t1e2l1
 * Output String: lliipsapaateel
 *
 * Input String: lliiippsssaaa
 * Compressed String: l2i3p2s3a3
 * Output String: l2i3p2s3a3
 *
 * Input: String
 * Output: Compressed String or Original String whichever is smaller
 *
 * Difference between StringBuffer and StringBuilder
 *
 * String Buffer;
 *
 * 1) Synchronized
 * 2) less efficient
 *
 * String Builder
 *
 * 1) Not Synchronized
 * 2) More efficient
 *
 * Approach
 *
 * 1) Create StringBuilder sb and int count
 * 2) Navigate the string taking each character at a time
 * 3) If you find the same character, increase the count
 * 4) If you find different character, append the character and count to StringBuilder sb
 * 5) Reset count
 * 6) Compare the length of compressed string and original string, return whichever is smaller.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class StringCompressionUsingCountOfRepeatedCharacters {

    private static String getCompressedStringUsingCountOfRepeatedCharacters(String originalString) {

        StringBuilder sb = new StringBuilder();
        int count = 1;
        char previousCharacter = originalString.charAt(0);

        for (int i = 1; i < originalString.length(); i++) {

            char currentCharacter = originalString.charAt(i);

            if (currentCharacter == previousCharacter) {
                count++;
            } else {
                sb.append(previousCharacter);
                sb.append(count);
                previousCharacter = currentCharacter;
                count = 1;
            }
        }

        //Append the last character
        sb.append(previousCharacter);
        sb.append(count);

        if (sb.length() < originalString.length()) {
            return sb.toString();
        } else {
            return originalString;
        }
    }

    public static void main(String[] args) {

        String s1 = "lliipsapaateel";

        System.out.println("The compression of string " + s1 + " is " + getCompressedStringUsingCountOfRepeatedCharacters(s1));

        String s2 = "lliiippsssaaa";

        System.out.println("The compression of string " + s2 + " is " + getCompressedStringUsingCountOfRepeatedCharacters(s2));
    }
}
