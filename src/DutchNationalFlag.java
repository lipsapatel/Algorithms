import java.util.Arrays;

/**
 * Dutch national flag sorting problem
 For this problem, your goal is to sort an array of 0, 1 and 2's but you must do this in place, in linear time
 and without any extra space (such as creating an extra array). This is called the Dutch national flag sorting problem.
 For example, if the input array is [2,0,0,1,2,1] then your program should output [0,0,1,1,2,2] and the algorithm should run in O(n) time.

 Algorithm

 The solution to this algorithm will require 3 pointers to iterate throughout the array, swapping the necessary elements.

 (1) Create a low pointer at the beginning of the array and a high pointer at the end of the array.
 (2) Create a mid pointer that starts at the beginning of the array and iterates through each element.
 (3) If the element at arr[mid] is a 2, then swap arr[mid] and arr[high] and decrease the high pointer by 1.
 (4) If the element at arr[mid] is a 0, then swap arr[mid] and arr[low] and increase the low and mid pointers by 1.
 (5) If the element at arr[mid] is a 1, don't swap anything and just increase the mid pointer by 1.

 This algorithm stops once the mid pointer passes the high pointer.

 Time Complexity: O(n)

 Hoare scheme - 3 way partition.

 **************************************************************************************

 Dutch National Flag
 Problem Statement:
 You are given n balls. Each of these balls are of one the three colors: Red, Green and Blue.
 They are arranged randomly in a line. Your task is to rearrange them such that all balls of the same color are together and
 their collective color groups are in this order: Red balls first, Green balls next and Blue balls last.

This combination of colors is similar to the Dutch National Flag, hence the problem name.
This is a popular sorting problem.

 Input/Output Format For The Function:
 Input Format:

 An array of characters named balls, consisting of letters from {‘R’, ‘G’, ‘B’}, where each letter represents a ball with color.
 'R' = Red Ball
 'G' = Green Ball
 'B' = Blue Ball

 Balls are arranged in the line, in the same order as they appear in balls.

 Output Format:
 Return type is void. You have to do it in-place i.e. you need to modify the input character array balls.

 Input/Output Format For The Custom Input:

 Input Format:
 The first line of input should contain an integer n, denoting no. of balls. In next n lines, ith line should contain one letter from {'R', 'G', 'B'}, balls[i], denoting color of ith ball.
 If balls = [G, B, G, G, R, B, R, G], then input should be:
 8
 G
 B
 G
 G
 R
 B
 R
 G

 Output Format:
 There will be n lines of output, where ith line contains a character balls[i], denoting character at index i of balls.
 Here, balls is the character array after the function that you are going to complete is called.
 For input balls = [G, B, G, G, R, B, R, G], output will be:
 R
 R
 G
 G
 G
 G
 B
 B

 Constraints:
 1 <= n <= 100000
 Do this in ONE pass over the array - NOT TWO passes, just one pass.
 Your solution can only use O(1) extra memory i.e. you have to do this in-place. Don't use any other memory for processing.

 Sample Test Case:
 Sample Input:
 balls = [G, B, G, G, R, B, R, G]
 Sample Output:
 [R, R, G, G, G, G, B, B]

 Explanation:
 In the input there are total 2 red balls, 4 green balls and 2 blue balls.
 In output red balls should come first, then green and then blue. So, [R, R, G, G, G, G, B, B] is the correct output.

Notes:
 Maximum time allowed in interview: 20 Minutes.
 A naive solution to this problem, is to simply count how many balls of each color, and then overwrite the array with that many balls in the expected order of colors. However, realize that that is not how it's ""practically"" feasible to do ""with actual balls"". i.e. you can overwrite variables in a program, but there is no way to "overwrite" a ball of a certain color with another color. i.e. that is an invalid solution.

 As mentioned in the notes:

 A naive solution to this problem, is to simply count how many balls of each color, and then overwrite the array
 with that many balls in the expected order of colors. However, realize that that is not how it's ""practically""
 feasible to do ""with actual balls"". i.e. you can overwrite variables in a program,
 but there is no way to "overwrite" a ball of a certain color with another color. i.e. that is an invalid solution.

 To solve the problem, taking one example will help.
 Try to play with:
 [R, G, G, R, G, B, G, B, R, R, R, G]

 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

 Suppose our initial char array is:
 [char array to process]
 ----
 Now ""suppose"" after processing some part of the given char array, we are able to maintain its structure like:
 [R, R, ..., R, R][G, G, ..., G, G][remaining char array to process][B, B, ..., B, B]
 Just assume that we are able to maintain this structure somehow, currently do not worry about how!
 ----
 If we can maintain the same structure after processing the first ball of "remaining char array to process" in O(1) time,
 then it means now we have to solve the same problem with reduced size of "remaining char array to process".
 After we have processed the first ball, we repeat the process for the next ball that is take O(1) time and maintain the same structure!
 So at the end we will be able to maintain the same structure and that will be the sorted char array!
 We are taking O(1) time to process each character hence the time complexity will also be n * O(1) that is O(n)!
 Let's think how can we maintain the same structure when first letter in the "remaining char array to process" is:
 ----
 'R', which means char array is:
 [R, R, ..., R, R]['G', G, ..., G, G]['R' + other remaining char array to process][B, B, ..., B, B]
 What should we do with 'R' to maintain the same structure?

 Swapping it with left most 'G'?
 Yes that is possible and that will maintain the structure with only one swap, ""assuming that we have the index of left most G"".
 So now our char array will look like:

 [R, R, ..., R, R, 'R'][G, ..., G, G, 'G'][other remaining char array to process][B, B, ..., B, B]

 ----

 'G' which means char array is:
 [R, R, ..., R, R][G, G, ..., G, G]['G' + other remaining char array to process][B, B, ..., B, B]
 What should we do with 'G' to maintain the same structure?
 Nothing to do, just go to the next character!
 So now our char array will look like:
 [R, R, ..., R, R][G, G, ..., G, G, 'G'][other remaining char array to process][B, B, ..., B, B]
 ----
 'B' which means char array is:
 [R, R, ..., R, R][G, G, ..., G, G]['B' + other remaining char array to process + 'last character'][B, B, ..., B, B]
 What should we do with 'B' to maintain the same structure?
 Swap 'B' with the 'last character' of the "remaining char array to process"!
 Yes that is possible and that will maintain the structure with only one swap ""assuming that we have the index of last character"".
 So now our char array will look like:
 [R, R, ..., R, R][G, G, ..., G, G]['last character' + other remaining char array to process]['B', B, B, ..., B, B]
 ----
 We have made some assumptions:
 1) When first character is 'R' we assumed that, ""we have the index of left most G"" and
 2) When first character is 'B' we assumed that, ""we have the index of last character"".
 So somehow we need to maintain these two indices and we will be able to solve the problem by following the steps above.
 Also when we are starting we can take,
 [R, R, ..., R, R], [G, G, ..., G, G] and [B, B, ..., B, B] parts = "". And then start our solution!
 The idea looks like complex but code is very simple!
 Have a look at the solution provided by us, it uses the same idea explained above.
 Time Complexity:
 O(length of the char array) as we are passing through the char array only once.

 Auxiliary Space Used:
 O(1) as we are using only constant extra space.

 Space Complexity:
 O(length of the char array) due to input char array balls.
 */
public class DutchNationalFlag {

    public static void dutchNationalFlag(int[] a) {

        int low = 0;
        int mid = 0;
        int high = a.length - 1;

        while (mid <= high) {
            if (a[mid] == 0) {
                swap(a, mid++, low++);
            } else if (a[mid] == 1) {
                mid++;
            } else if (a[mid] == 2) {
                swap(a, mid, high--);
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void dutchNationalFlagChar(char[] a) {

        int low = 0;
        int mid = 0;
        int high = a.length - 1;

        while (mid <= high) {
            if (a[mid] == 'R') {
                swap(a, mid, low);
                mid++;
                low++;
            } else if (a[mid] == 'G') {
                mid++;
            } else if (a[mid] == 'B') {
                swap(a, mid, high);
                high--;
            }
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 2, 1, 2, 0, 0, 2, 1, 2, 0, 2, 1, 0, 1, 2, 2, 1, 1,1, 0, 0, 0, 1, 2, 0, 1, 2, 1};

        long s = System.nanoTime();
        dutchNationalFlag(a);
        System.out.println(System.nanoTime() - s);
        System.out.println(Arrays.toString(a));

        char[] a1 = {'G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'};
        dutchNationalFlagChar(a1);
        System.out.println("After sorting: " + Arrays.toString(a1));
    }
}
