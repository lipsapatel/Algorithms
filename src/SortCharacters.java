import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Sort All Characters

 Problem Statement:


 You have to sort an array of characters containing alphanumeric characters along with some other characters - ‘!’, ’@’, ’#’, ’$’, ’%’, ’^’, ’&’, ’*’, ’(‘, ’)’. You are given a character array named arr.


 Input/Output Format For The Function:


 Input Format:

 There is only one argument in input, a character array named arr.


 Output Format:

 Return a character array result, containing characters in sorted order of their ASCII values. You can overwrite the existing array.


 Input/Output Format For The Custom Input:


 Input Format:

 The first line contains the size, n, of the character array. Next n lines each contain the characters present in the array, with each character of the array in a new line.


 If arr = {a,z,i,#,&,l,c} then input should be:


 7

 a

 z

 i

 #

 &

 l

 c


 Output Format:


 Output each character of the array on a new line, in sorted order of their ASCII values.


 For above input - arr = {a,z,i,#,&,l,c}, output will be:


 #

 &

 a

 c

 i

 l

 z


 Constraints:

 1 <= length(arr) <= 100000


 Sample Test Case:


 Sample Input:


 10

 a

 s

 d

 f

 g

 *

 &

 !

 z

 y


 Sample Output:


 !

 &

 *

 a

 d

 f

 g

 s

 y

 z


 Explanation:

 Ascii values of the characters present in the character array are:

 a: 97, s: 115, d: 100, f: 102, g: 103, *: 42, &: 38, !: 33, z: 122, y: 121.

 Now sorting them according to their ascii values results in the given output.

 We have provided three solutions and all the solutions contain necessary comments to understand the approach used:


 1) brute_force solution.java


 Time Complexity (assuming the length of the given character array is “n”, input arguments are already given and excluding time used in the declaration of output):


 O(n ^ 2).

 The algorithm that we are using is selection sort. We are finding the minimum value of character present among remaining characters in each iteration. Since the size of the character array is n we will be iterating the entire array n times (that is O(n) iterations) to find n such minimum characters (that is O(n) time for finding minimum value during each iteration). Hence, it is O(n^2).


 Time Complexity:


 Time Complexity (assuming the length of the given character array is “n”, input arguments are already given and excluding time used in the declaration of output) is O(n ^ 2).


 Input is O(n).


 Output is also O(n).


 Hence, O(n ^ 2) + O(n) + O(n) -> O(n ^ 2).


 Auxiliary Space Used:


 O(n).

 As we are using a character array of the size of the input array to store and sort the characters.


 NOTE: In Java we have to convert from List<Character> to char ch[] = new char[n]; and then back to List<Character>, hence auxiliary space used is O(n), but in other languages this is not needed and aux space will be O(1).


 Space Complexity:


 O(n).

 Input is O(n).

 Auxiliary space used is O(n).

 Output is O(n).


 Hence, O(n) + O(n) + O(n) -> O(n).


 2) suboptimal_solution.java


 Time Complexity (assuming size of the character array is “n”, input arguments are already given and excluding time used in the declaration of output):


 O(nlogn).

 We are using inbuilt sorting function, Collections.sort() which uses randomized quicksort to sort an array. Since the average case time complexity of randomized quicksort is O(nlogn), hence it is O(nlogn).


 Time Complexity:


 Time Complexity (assuming size of the character array is “n”, input arguments are already given and excluding time used in the declaration of output) is O(nlogn).


 Input is O(n).


 Output is O(n).


 Hence, O(nlogn) + O(n) + O(n) -> O(nlogn).


 Auxiliary Space Used:


 O(1).

 Quicksort is an in place sorting algorithm and hence we do not use any extra space, so auxiliary space used is O(1).


 Space Complexity:


 O(n).

 Input is O(n).

 Auxiliary space used is O(1).

 Output is O(n).


 Hence, O(n) + O(1) + O(n) -> O(n).


 3) optimal_solution.java


 Time Complexity (assuming size of the character array is “n”, input arguments are already given and excluding time used in the declaration of output):


 O(n).

 The algorithm that we are using is counting sort. We create an array named frequency to keep a count of occurrence of each character in the input string. The maximum number of different characters possible is 128 so this will be an array of size 128. We traverse the character array once O(n) and update the frequency array for each character encountered during traversal. Finally after the complete iteration of the array, we traverse the frequency array from beginning to the end, that is from lowest ascii value to the highest (from index 0 to index 127), and add as many characters to the result (which is initially an empty string) as is the frequency of that character. Hence, it is O(n).


 NOTE:   We can even use array of length ‘72’ (with some mapping) instead of ‘128’ because in input we are given only ‘72’ different types of characters, but this is a general solution which works for the input string containing any ASCII characters.


 Time Complexity:
 Time Complexity (assuming size of the character array is “n”, input arguments are already given and excluding time used in the declaration of output) is O(n).
 Input is O(n).
 Output is O(n).
 Hence, O(n) + O(n) + O(n) -> O(n).

 Auxiliary Space Used:
 O(1).
 We create a frequency array of size 128, which uses extra space O(128). Hence it is O(1).

 Space Complexity:
 O(n).
 Input is O(n).
 Auxiliary space used is O(1).
 Output is O(n).

 Hence, O(n) + O(1) + O(n) -> O(n).
 */
public class SortCharacters {

    //TC = O(n)
    //SC = O(n)
    //Counting sort
    public static List<Character> sortCharacters(List<Character> arr) {

        int[] frequency = new int[128];

        for (char c: arr) {
            frequency[c]++;
        }

        arr.clear();

        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < frequency[i]; j++) {
                arr.add((char)i);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        List<Character> arr = new ArrayList<>();
        arr.add('!');
        arr.add('a');
        arr.add('s');
        arr.add('d');
        arr.add('f');
        arr.add('*');

        System.out.println("The sorted list: " + (sortCharacters(arr)).toString());

    }
}
