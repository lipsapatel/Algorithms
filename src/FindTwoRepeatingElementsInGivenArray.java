import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of n+2 elements.
 * All elements of array are in the range of 1 to n.
 * And all elements occur once except two numbers which occur twice.
 * Write an algorithm to find two repeating numbers.
 *
 * Example:
 *
 * int[] array = {1, 4, 5, 6, 3, 2, 5, 2};
 * int range = 6;
 * Output: Two repeated elements are 2 and 5
 *
 * Approach 1:
 *
 * 1) Solve using two nested loops.
 * 2) Take one element at time and compare with all other elements.
 * 3) If it appears twice print it.
 * 4) The solution will work even if all the numbers are not in the range 1 to n.
 *
 * Time Complexity: O(n2)
 *
 * Approach 2:
 *
 * HashMap
 *
 * 1) This approach will work even if all the numbers are not in the range of 1 to n.
 * 2) Keep the count of each element in the hashmap
 * 3) Print the elements which has count = 2.
 *
 * Time Complexity: O(n) Space Complexity: O(n)
 *
 * Approach 3:
 *
 * Math Formula
 *
 * 1) Find the sum of repeating elements a + b
 * 2) Find the product of repeating elements a * b
 * 3) a - b = sqrt((a + b)^2 - 4ab)
 * 4) a = (a + b) (a - b) /2
 * 5) a + b = sum
 * 6) b = sum - a
 *
 * 1) This solution will work only if all the numbers are in the range 1 to n and > 0
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Approach 4:
 *
 * Negate element by Index.
 *
 * 1) This solution works only if array has all positive integers and are in the range of 1 to n.
 * 2) Navigate the array
 * 3) Update the array ith index: array[Math.abs(array[i])] = array[Math.abs(array[i])] * -1;
 * 4) If it's already negative then we are visiting second time and it's duplicate.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Approach 5:
 *
 * Sorting:
 *
 * 1) This solution will work even if the numbers are not in the range of 1 to n.
 * 2) Sort the array. This will bring all repeated elements together.
 * 3) Now traverse the array and compare the adjacent elements. If they are same, then print it.
 *
 * Time Complexity: O(nlogn) Space Complexity: O(1)
 */
public class FindTwoRepeatingElementsInGivenArray {

    private static void findTwoRepeatingElements_UsingNestedLoop(int[] array) {

        System.out.println("The two repeating elements using nested loop are ");

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {

                if (array[i] == array[j]) {
                    System.out.print(array[i] + " ");
                }
            }
        }
        System.out.println();
    }

    private static void findTwoRepeatingElements_UsingHashMap(int[] array) {

        HashMap<Integer, Integer> countHashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < array.length; i++) {

            if (countHashMap.containsKey(array[i])) {
                countHashMap.put(array[i], countHashMap.get(array[i]) + 1);
            } else {
                countHashMap.put(array[i], 1);
            }
        }

        System.out.println("Two repeated elements using Hash Map are ");
        for (Map.Entry<Integer, Integer> entry: countHashMap.entrySet()) {
            if (entry.getValue() == 2) {
                System.out.print(entry.getKey() + " ");
            }
        }
        System.out.println();
    }

    private static void findTwoRepeatingElement_UsingMathFormula(int[] array, int range) {

        //find the sum of all elements in the range
       int rangeSum = range * (range + 1)/2;

        //Find array sum
        int arraySum = 0;

        //Find array product
        int arrayProduct = 1;

        for (int i = 0; i < array.length; i++) {
            arraySum = arraySum + array[i];
            arrayProduct = arrayProduct * array[i];
        }

        int sumOfRepeatedElements = arraySum - rangeSum;

        //Find the product of range
        int rangeProduct = findFactorial(range);

        int productOfRepeatedElements = arrayProduct/rangeProduct;

        int differenceOfRepeatedElements = (int)Math.sqrt((sumOfRepeatedElements * sumOfRepeatedElements)
                - 4 * productOfRepeatedElements);

        int a = (sumOfRepeatedElements + differenceOfRepeatedElements)/2;

        int b = sumOfRepeatedElements - a;

        System.out.println("The two repeated elements using Math formula are ");
        System.out.print(a + " " + b + " ");
        System.out.println();
    }

    private static int findFactorial(int range) {
        if (range == 0) {
            return 1;
        } else {
            return range * findFactorial(range - 1);
        }
    }

    private static void findTwoRepeatingElement_NegateIndex(int[] array) {

        System.out.println("The two repeating elements using negate index are ");

        for (int i = 0; i < array.length; i++) {

            if (array[Math.abs(array[i])] < 0) {
                System.out.print(Math.abs(array[i]) + " ");
            } else {
                array[Math.abs(array[i])] = array[Math.abs(array[i])] * -1;
            }
        }
        System.out.println();
    }

    private static void findTwoRepeatingElements_UsingSort(int[] array) {

        Arrays.sort(array);

        System.out.println("The two repeated elements using sorting are ");
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                System.out.print(array[i] + " ");
            }
        }
    }

    public static void main(String[] args) {

        int[] array = {1,5,2,4,8,9,3,1,4,0};

        findTwoRepeatingElements_UsingNestedLoop(array);
        findTwoRepeatingElements_UsingHashMap(array);

        int[] array1 = {1,4,5,6,3,2,5,2};
        findTwoRepeatingElement_UsingMathFormula(array1, 6);
        findTwoRepeatingElement_NegateIndex(array1);
        findTwoRepeatingElements_UsingSort(array);
    }
}
