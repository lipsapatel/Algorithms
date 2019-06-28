import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Lexicographical Order


 Problem Statement:


 You are given a string array named arr, of size N, containing KEYS and VALUES separated by a space.


 Your task is to find, for each unique KEY, the number of VALUES with that key and the VALUE with the highest lexicographical order (also called alphabetical order OR dictionary order).


 (Have a look at the sample test cases for more clarity.)


 Input/Output Format For The Function:


 Input Format:


 There is only one argument: String array arr.


 Output Format:


 Return a string array res, with an entry for each unique key. Each entry should contain key, number of values corresponding to that key and value with the highest lexicographical order in the below format:


 "<KEY>:<COUNT>,<HIGHEST_LEXICOGRAPHICAL_VALUE>"


 Order of the output does not matter.


 Input/Output Format For The Custom Input:


 Input Format:


 The first line of input should contain an integer N, denoting size of input array arr. In next N lines, ith line should contain a string arr[i], denoting a value at index i of arr.


 If N = 5 and arr = [“key1 abcd”, “key2 zzz”, “key1 hello”,

 “key3 world”, "key1 hello"], then input should be:


 5

 key1 abcd

 key2 zzz

 key1 hello

 key3 world

 key1 hello


 Output Format:


 There will be N lines of output, where ith line contains a string res[i], denoting value at index i of res.

 Here, res is the result array returned by solution function.


 For input N = 5 and arr = [“key1 abcd”, “key2 zzz”, “key1 hello”,

 “key3 world”, "key1 hello"], output will be:


 key3:1,world

 key2:1,zzz

 key1:3,hello


 Constraints:

 1 <= N <= 10^4
 1 <= Length(KEYS) <= 256
 1 <= Length(VALUES) <= 800
 KEYS can repeat.
 VALUES can repeat.
 Keys and values will contain only lower case letters and numerics.


 Sample Test Case:


 Sample Test Case 1:


 Sample Input 1:


 arr = [

 “key1 abcd”,

 “key2 zzz”,

 “key1 hello”,

 “key3 world”,

 "key1 hello"

 ]


 Sample Output 1:


 One possible output (you can return strings in any order):


 [

 "key1:3,hello",

 "key2:1,zzz",

 "key3:1,world"

 ]


 Sample Test Case 2:


 Sample Input 2:


 arr = [

 “mark zuckerberg”,

 “tim cook”,

 “mark twain”

 ]


 Sample Output 2:


 One possible output (you can return strings in any order):


 [

 "mark:2,zuckerberg",

 "tim:1,cook"

 ]

 Approach:

 1) Create Map of String keys and Object class Lexi which has count and highest string lexicographically. //n
 2) Iterate Map and create result string array
 3) Use compareTo to compare the string for lexicographical order - length of string

 Comparing two string with length N is O(N) operation.


 Space Complexity: O(n*(MAX_LENGTH(KEYS)+MAX_LENGTH(VALUES)))

 Time Complexity: O(n*(MAX_LENGTH(VALUE))
 */

public class LexicographicalOrder {

    private static class Lexi {
        int count;
        String highestLexi;

        public Lexi(int c, String s) {
            count = c;
            highestLexi = s;
        }
    }

    private static String[] getLexicographicalHighestString(String[] arr) {
        HashMap<String, Lexi> map = new HashMap<>(); //SC = n * (Max(keys) + Max(values))

        for (String s: arr) { //Tc = n
            //Split the string by space
            String[] values = s.split(" ");
            String key = values[0];
            String value = values[1];

            //If map doesn't contain entry, add it
            if (!map.containsKey(key)) {
                map.put(key, new Lexi(1, value));
            } else {
                Lexi lexi = map.get(key);
                lexi.count++;

                if (lexi.highestLexi.compareTo(value) < 0) { //TC = Length of String
                    lexi.highestLexi = value;
                }
            }
        }

        String[] result = new String[map.size()];
        int i = 0;

        for (Map.Entry<String, Lexi> entry: map.entrySet()) {
            Lexi lexi = entry.getValue();
            result[i] = entry.getKey() + ":" + lexi.count + "," + lexi.highestLexi;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] array = {"key1 abcd","key2 zzz","key1 hello","key3 world","key1 hello"};

        System.out.println(Arrays.toString(getLexicographicalHighestString(array)));
    }

}
