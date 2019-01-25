import com.sun.xml.internal.fastinfoset.util.CharArray;

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
 */
public class PermutationOfString {

    private static void printStringPermutation(char[] A, int left) {

        //Base Case
        if (left == A.length) {
            System.out.println(Arrays.toString(A));
            return;
        }

        for (int i = left; i < A.length; i++) {
            swap(i, left, A);
            printStringPermutation(A, left + 1); //Keep increasing left for different position
            swap(i, left, A); //This is for backtrack
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
        printStringPermutation(input.toCharArray(), 0); //Swap

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
