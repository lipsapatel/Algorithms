import java.util.ArrayList;

/**
 * Problem Statement
 You are given two s: N and K. Lun the dog is interested in strings that satisfy the following conditions:

 The string has exactly N characters, each of which is either 'A' or 'B'.
 The string s has exactly K pairs (i, j) (0 <= i < j <= N-1) such that s[i] = 'A' and s[j] = 'B'.
 If there exists a string that satisfies the conditions, find and return any such string. Otherwise, return an empty string.

 Definition
 Class: AB
 Method: createString
 Parameters: int, int
 Returns: String
 Method signature: String createString(int N, int K)
 (be sure your method is public)
 Limits
 Time limit (s): 2.000
 Memory limit (MB): 256

 Constraints

 N will be between 2 and 50, inclusive.
 - K will be between 0 and N(N-1)/2, inclusive.
 Examples
 0)
 3
 2
 Returns: "ABB"
 This string has exactly two pairs (i, j) mentioned in the statement: (0, 1) and (0, 2).
 1)
 2
 0
 Returns: "BA"
 Please note that there are valid test cases with K = 0.
 2)
 5
 8
 Returns: ""
 Five characters is too short for this value of K.
 3)
 10
 12
 Returns: "BAABBABAAB"

 Approach:

 1) Find all possible String of length n
 Time Complexity = O(setLength ^ n)

 2) For all possible strings, count the number of pairs
 Time Complexity = O(n)
 */
public class FindStringOfLengthNWithKPairs {



    public static String createStringOfLengthNWithKPairs(int n, int k) {

        char[] set = {'A', 'B'};
        int setLength = set.length;
        ArrayList<String> possibleStringList = new ArrayList<>();

        findAllPossibleStringOfLengthN(set, setLength, "", n, possibleStringList);

        //From all possible string list, find the string with exactly k pairs
        for (String eachString: possibleStringList) {

            int numOfAs = 0;
            int totalPairs = 0;

            //Iterate by each character
            for (int i = 0; i < eachString.length(); i++) {

                if (eachString.charAt(i) == 'A') {
                    numOfAs = numOfAs + 1;
                } else {
                    totalPairs = totalPairs + numOfAs;
                }
            }

            //Check if the total numbers of pairs are exactly k
            if (totalPairs == k) {
                return eachString;
            }
        }

        return "";
    }

    public static void findAllPossibleStringOfLengthN(char[] set, int setLength,
                                                      String prefix, int strLength,
                                                      ArrayList<String> possibleStringList) {

        //Base case
        if (strLength == 0) {
            //We found the string of length n
            possibleStringList.add(prefix);
            return;
        }

        //Iterate through the setLength
        for (int i = 0; i < setLength; i++) {

            String newPrefix = prefix + set[i];

            findAllPossibleStringOfLengthN(set, setLength, newPrefix, strLength - 1, possibleStringList);
        }
    }

    public static void main(String[] args) {

        System.out.println("The string with Length 2 and 2 pairs is : " +
                createStringOfLengthNWithKPairs(3, 2));
    }
}
