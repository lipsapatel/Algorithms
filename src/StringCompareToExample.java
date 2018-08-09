/**
 * How to compare two strings in Java program
 *
 * Java implements Comparable interface and it has two variants of compareTo() methods.

 1.	compareTo(String string2) – This string compareTo method compares the String object with the String argument passed lexicographically.

 If string1 is greater than string2, it returns positive integer. If string1 is smaller than string2 then it returns negative integer.

 It returns 0 when both String have same value.
 In the above case, equals(String string) method will return true.

 The comparison is based on the Unicode value of each character in the strings.

 2.	compareToIgnoreCase(String string2) – This compareTo method is similar to the first one except that it ignores the case.

 It uses CASE_INSENSITIVE_ORDER Comparator for case insensitive comparison.

 If the return value of this method is 0, then equalsIgnoreCase(String string2) will return true,

 The negative output above is because “ABC” is lexicographically less than “DEF”.
 */
public class StringCompareToExample {

    public static void main(String[] args) {

        String string1 = "ABC";

        System.out.println(string1.compareTo("DEF"));
        System.out.println(string1.compareToIgnoreCase("abc"));
    }
}
