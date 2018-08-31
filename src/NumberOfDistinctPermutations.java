import java.util.HashMap;

/**
 * Find number of Distinct Permutations of a String
 *
 * Given a string, find the number of distinct permutations or anagrams of that string
 *
 * Example:
 *
 * String x = "abc"
 * Output: 6 (abc, acb, bac, bca, cab, cba)
 *
 * String x = "aab"
 * Output: 3 (aba, aab, baa)
 *
 * Approach:
 *
 * 1) Number of ways to arrange n distinct items are = n!
 * 2) If we have duplicate items, say one item repeated m1 times, then we need to divide n! by m1!. Since these items can be arranged
 * in m1! ways but that will not produce any different results
 * 3) For example: Let's say we have 6 items {a, b, a, c, d, c}, we have 'a' which is occurring twice. So in one of the permutation -
 * 'aabccd', if we replace 'a' at 0th index and 'a' at 1st index, it will not produce different result. 'aa' can be arranged in 2! ways. Same
 * with 'c' so our final result will be 6!/(2! * 2!)
 *
 * Let's drive a generic formula -
 *
 * 'N' elements are given. 'k' items are repeated.
 * One element is repeated m1 times
 * Second item is repeated m2 times
 * ...
 * ...
 * ...
 * kth item is repeated mk times
 *
 * So number of ways these N items are arranged will be
 *
 * = N! / m1! m2! .......mk!
 *
 * Implementation
 *
 * 1) Count all the characters in the given string, say its N.
 * 2) Calculate N!, this might be our final result if none of the characters are repeated.
 * 3) Count the occurrences of all the characters, store it. We are using HashMap in our implementation below.
 * 4) Find the factorial of all the counts and divide the result by these factorials (Non repeated characters will have count 1 and 1! = 1
 * so dividing by 1 will not affect our final result.
 */
public class NumberOfDistinctPermutations {

    private static void findNumberOfDistinctPermutations(String input) {

        //Convert String to char array
        char[] chars = input.toCharArray();

        //Create Hashmap and store the char and count
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {

            char x = chars[i];

            if (hashMap.containsKey(x)) {

                int count = hashMap.get(x);
                count++;
                hashMap.put(x, count);
            } else {
                hashMap.put(x, 1);
            }
        }

        int result = factorial(chars.length);

        //Handle the duplicates, divide the result by all the counts factorial in the hashmap
        for (char key: hashMap.keySet()) {

            int count = hashMap.get(key);

            int factorialCount = factorial(count);
            result = result/factorialCount;
        }

        System.out.println("Number of distinct permutations of String: " + input + " are: " + result);
    }

    private static int factorial(int number) {

        if (number == 0) {
            return 1;
        }

        int result = 1;

        for (int i = 2; i <= number; i++) {
            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {

        String input = "abc";
        findNumberOfDistinctPermutations(input);

        input = "aabc";
        findNumberOfDistinctPermutations(input);

        input = "aabccd";
        findNumberOfDistinctPermutations(input);
    }
}
