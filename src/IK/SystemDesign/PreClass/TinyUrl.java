package IK.SystemDesign.PreClass;

import java.util.HashMap;

/**
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 *
 *
 * Example 1:
 *
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 *
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after deconding it.
 *
 *
 * Constraints:
 *
 * 1 <= url.length <= 104
 * url is guranteed to be a valid URL.
 *
 * Global Counter Approach
 * 1) Create a global counter and increment it for every long url encountered
 * 2) Create hashMap to store mapping between short url and long url
 * 3) Encode: Increment the counter and save the string counter value and long url in hashmap and return short url
 * 4) Decode: Split the string by "/". Get the counter string. If the hashmap contains counter string, return the corresponding long url
 * 5) else return -1
 *
 * Pros: The short url generated this way is unique. There are no collisions.
 * Cons: The short url are preditable
 * It's not that short because for 1 billion the length of short url is 10 characters long(1000000000)
 *
 * TC: O(1) For insert and search
 * SC: O(n) where n = number of long urls
 *
 * Global Counter in base 64
 * 1) Create mapping for all integer values mapped to 0 - 9, a - z, A - Z, _, -
 * 2) For encode, increment the counter, convert counter to base 64
 * 3) Add the short url and long url to hashmap
 * 4) Return short url
 * 5) For decode, split the short url by /
 * 6) Check if the hash map contains short url, if yes then return the long url
 * 7) Else return -1
 *
 * TC: O(length(counter)) for converting decimal counter to base 64
 * SC: O(1)
 *
 * Pros: The short url is unique and short.
 * Cons: The short url is predictable
 */
public class TinyUrl {

    private static int counter = 7973;
    private static HashMap<String, String> map = new HashMap<>();

    private static HashMap<Integer, String> mapping = new HashMap<>();

    private static String encodeCounter(String longUrl) {

        counter++;

        //If we are converting counter to base 64
        String counterBase64 = decimalToAnyBase(counter, 64);

        String shortUrl = "bit.ly/" + counterBase64;

        map.put(counterBase64, longUrl);

        //If counter is int
        //map.put(String.valueOf(counter), longUrl);

        return shortUrl;
    }

    private static String decodeCounter(String shortUrl) {
        String[] values = shortUrl.split("/");

        if(map.containsKey(values[1])) {
            return map.get(values[1]);
        }
        return "-1";
    }

    private static String decimalToAnyBase(int n, int base) {

        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            int rem = n % base;

            sb.append(mapping.get(rem));

            n = n/base;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {

        for(int i = 0; i <= 9; i++) {
            mapping.put(i, String.valueOf(i));
        }

        int j = 65;
        for(int i = 10; i <= 35; i++) {
            mapping.put(i, String.valueOf((char)j));
            j++;
        }

        j = 97;
        for(int i = 36; i <= 61; i++) {
            mapping.put(i, String.valueOf((char)j));
            j++;
        }

        mapping.put(62, "-");
        mapping.put(63, "_");

        String shortUrl = encodeCounter("www.google.com");

        System.out.println("Encode - Get Short Url: " + shortUrl);
        System.out.println("Decode - Get long Url: " + decodeCounter(shortUrl));
    }
}
