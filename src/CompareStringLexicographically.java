/**
 * Compare two string lexicographically without using library function.
 *
 * Each character of both the strings are converted into a Unicode value for comparison.
 *
 * It returns the following values:
 *
 * 1) if (string1 > string2), it returns positive value
 * 2) if both strings are equal lexicographically, (string1 == string2) it returns 0
 * 3) if (string1 < string2) it returns a negative value.
 *
 * TC: O(Min(n, m)) - where n = String1 length and m = String2 length
 * SC: O(1)
 *
 */
public class CompareStringLexicographically {

    private static int compareStringLexicographically(String string1, String string2) {

        for (int i = 0; i < string1.length() && i < string2.length(); i++) {

            if ((int)string1.charAt(i) == (int)string2.charAt(i)) {
                continue;
            } else {

                return (int)string1.charAt(i) - (int)string2.charAt(i);
            }
        }

        //Edge cases for string like string1 = "Geeks"; string2 = "Geeksy";
        if (string1.length() != string2.length()) {

            return string1.length() - string2.length();

        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

        String string1 = "Geeks";
        String string2 = "Practice";
        String string3 = "Geeks";
        String string4 = "Geeksy";
        String string5 = "Geekb";

        System.out.println(string1 + " " + string2 + " " + compareStringLexicographically(string1, string2));
        System.out.println(string3 + " " + string3 + " " + compareStringLexicographically(string3, string3));
        System.out.println(string2 + " " + string1 + " " + compareStringLexicographically(string2, string1));
        System.out.println(string1 + " " + string4 + " " + compareStringLexicographically(string1, string4));
        System.out.println(string5 + " " + string1 + " " + compareStringLexicographically(string5, string1));
    }

}
