package IK.Recursion.PreClass;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Find all the permutations of a given string
 *
 * ABC
 * ABC BAC CBA
 * ABC ACB BAC BCA CBA CAB
 *
 * Swap the elements with the left position.
 * Left position will be 0, 1, 2
 *
 * resources/PermutationOfStringUsingSwap.png
 * resources/PermutationOfStringUsingBacktrack.png
 * resources/PermutationOfStringUsingSoFar.png
 *
 * Time Complexity: n! * n(n is for printing)
 * Branching factor is different at each level
 * For first level it's 3, then 2 and then 1 so n!
 *
 * TC = (height)^degree
 * Don't know the degree so can also count no of leaves which is n! because that's where all work is happening
 * height = O(n)
 *
 * Call Stack = order of height = O(n)
 *
 * Approach Using Swap - Optimal way
 *
 * 1) Given an input string convert it into char array
 * 2) Recursive function (char[], 0) where 0 is the left position
 * 3) Base Case: If left position is a.length - 1, print the char array after converting it to string
 * 4) Recursive Case: Iterate from i = left to a.length - 1
 * 5) Swap i and left
 * 6) Make recursive call with left + 1
 * 7) Swap i and left again to backtrack or revert to previous state
 *
 *  TC: O(n! * n) where n is the time to print the string and n! is all the leaf nodes
 *  SC: O(n) - Recursive Call stack space
 *
 *  Approach Using List
 *  1) Recursive function(list, chosen) - two list
 *  2) Base Case; If list is empty, print chosen
 *  3) Recursive Case: Iterate from i = 0 to list.size() - 1
 *  4) Choose - Add char at i from list to chosen
 *  5) Remove from list
 *  6) Make recursive call
 *  7) Unchoose - Remove from chosen and add back to list
 *
 *  TC: O(n * n!)
 *  SC: O(n)
 *
 *  Approach using soFar
 *  1) Recursive Case (soFar, rest)
 *  2) Base Case: If rest is empty print soFar
 *  3) Recursive Case: Iterate from i = 0 to rest.length() - 1
 *  4) Append char at i to soFar
 *  5) Remove that char from rest
 *  6) Make recursive call
 *
 *  TC: O(n * n!)
 *  SC: O(n)
 *
 */
public class PermutationOfString {

    private static void printStringPermutation(char[] A, int left) {

        //fix the element at position left
        //Every body from left till size get chance to be at position left

        //Base Case
        if (left == A.length - 1) { //or A.length is also fine
            System.out.println(new String(A));
        }

        //Recursive Case
        for (int i = left; i < A.length; i++) {
            swap(i, left, A);
            printStringPermutation(A, left + 1); //Keep increasing left for different position
            swap(i, left, A); //This is for backtrack; revert back to previous state
        }
    }

    private static void swap(int i, int left, char[] A) {
        char temp = A[i];
        A[i] = A[left];
        A[left] = temp;
    }

    private static void printArrayListPermutation(ArrayList<String> list, ArrayList<String> chosen) {

        //Base Case
        if (list.isEmpty()) {
            System.out.println(chosen);
        } else {
            //For each choice
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i);
                chosen.add(s);                           //Choose
                list.remove(i);

                printArrayListPermutation(list, chosen); //Explore

                chosen.remove(chosen.size() - 1);   //Unchoose
                list.add(i, s);
            }
        }
    }

    private static void printStringPermutation(String soFar, String rest) {

        if (rest.isEmpty()) {
            System.out.println(soFar);
        } else {

            for (int i = 0; i < rest.length(); i++) { //A, B, C for i = 0, 1, 2 rest length
                String next = soFar + rest.charAt(i); //A rem BC, i = 1 AC rem B
                String remaining = rest.substring(0, i) + rest.substring(i + 1); //subtracting
                printStringPermutation(next, remaining);
            }
        }
    }

    public static void main(String[] args) {
        String input = "ABC";
        System.out.println("Permutation using swap:");
        printStringPermutation(input.toCharArray(), 0); //Swap
        System.out.println("Done with Permutation using swap");

        printStringPermutation("", input);
        printStringPermutation("", "lipsa");

        ArrayList<String> list = new ArrayList<>();
        list.add("L");
        list.add("I");
        list.add("P");
        list.add("S");
        list.add("A");
        printArrayListPermutation(list, new ArrayList<String>());
    }
}
