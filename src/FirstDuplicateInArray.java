/**
 * Note: Write a solution with O(n) time complexity and O(1) additional space complexity,
 * since this is what you would be asked to do during a real interview.
 * Given an array a that contains only numbers in the range from 1 to a.length,
 * find the first duplicate number for which the second occurrence has the minimal index.
 * In other words, if there are more than 1 duplicated numbers,
 * return the number for which the second occurrence has a smaller index than the
 * second occurrence of the other number does.
 * If there are no such elements, return -1.
 * Example
 *
 * For a = [2, 3, 3, 1, 5, 2], the output should be
 * firstDuplicate(a) = 3.
 *
 * There are 2 duplicates: numbers 2 and 3. The second occurrence of 3 has a smaller index than
 * than second occurrence of 2 does,
 * so the answer is 3.
 *
 * For a = [2, 4, 3, 5, 1], the output should be
 * firstDuplicate(a) = -1.
 *
 * Input/Output
 *
 * [time limit] 3000ms (java
 *
 * [input] array.integer a
 *
 * Guaranteed constraints:
 * 1 ≤ a.length ≤ 105,
 * 1 ≤ a[i] ≤ a.length.
 *
 * [output] integer
 *
 * The element in a that occurs in the array more than once and has the minimal index for its second occurrence.
 * If there are no such elements, return -1.
 */
public class FirstDuplicateInArray {

    //We will negate by index
    //If 2 is occuring twice, then we will negate a[2] if the element is already negative
    // then it's duplicate
    //Since the range is 1 - length we a[length] will throw AOB exception so we will do -1
    public static int firstDuplicate(int[] a) {

        int duplicateNumberIndex = Integer.MAX_VALUE;
        int duplicateNumber = -1;

        for (int i = 0; i < a.length; i++) {

            if (a[Math.abs(a[i]) - 1] < 0) {
                if (i < duplicateNumberIndex) {
                    duplicateNumberIndex = i;
                    duplicateNumber = Math.abs(a[i]);
                }
            } else if (a[Math.abs(a[i]) - 1] == 0) {
                    a[Math.abs(a[i]) - 1] = Integer.MIN_VALUE;
            } else {
                    a[Math.abs(a[i]) - 1] = a[Math.abs(a[i]) - 1] * -1;
            }

        }
        return duplicateNumber;
    }

    public static void main(String[] args) {
        int[] arrayA = {4, 3, 2, 1, 2, 5};
        int duplicateNumber = firstDuplicate(arrayA);
        System.out.println("The duplicate number is " +  duplicateNumber);

        int[] arrayB = {1, 6, 5, 2, 3, 3, 2};
        int duplicateNumber1 = firstDuplicate(arrayB);
        System.out.println("The duplicate number is " +  duplicateNumber1);

        int[] arrayC = {2, 4, 3, 5, 1};
        int duplicateNumber2 = firstDuplicate(arrayC);
        System.out.println("The duplicate number is " +  duplicateNumber2);

        int[] arrayD = {2, 3, 3, 1, 5, 2};
        int duplicateNumber3 = firstDuplicate(arrayD);
        System.out.println("The duplicate number is " + duplicateNumber3);
    }
}
