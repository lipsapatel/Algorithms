/**
 * Write a function to find the longest common prefix string amongst an array of strings.

 If there is no common prefix, return an empty string "".

 Example 1:

 Input: ["flower","flow","flight"]
 Output: "fl"
 Example 2:

 Input: ["dog","racecar","car"]
 Output: ""
 Explanation: There is no common prefix among the input strings.
 Note:

 All given inputs are in lowercase letters a-z.
 */
class LongestCommonPrefixString {
    public String longestCommonPrefix(String[] strs) {
        
        /*
        1) Write helper function which takes (String[] strs, int index)
        2) Call helper function with (strs, 0)
        3) If all 3 characters are equal, make recursive call with index + 1
        3) Base case: If any string finishes, return the longest prefix
        */
        if (strs.length == 0) {
            return "";
        }

        //Find the min length string
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].isEmpty()) {
                return "";
            }
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }
        return longestCommonPrefix(strs, minLength);
    }

    public String longestCommonPrefix(String[] strs, int minLength) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            char c = strs[0].charAt(i);

            for(int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return result.toString();
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        LongestCommonPrefixString longestCommonPrefixString = new LongestCommonPrefixString();
        System.out.println(longestCommonPrefixString.longestCommonPrefix(strs));
    }
}