/**
 * Count and print all sub arrays with product less than k.
 *
 * Given an array of positive integers and integer k, write an algorithm to count all possible
 * sub arrays where product of all elements of subarray is less than k.
 *
 * int[] array = {10, 4, 2, 6}
 * int k = 100;
 *
 * Subarrays:
 *
 * {10}
 * {10, 4}
 * {10, 4, 2}
 * {4}
 * {4, 2}
 * {4, 2, 6}
 * {2}
 * {2, 6}
 * {6}
 *
 * Total 9
 *
 * Approach:
 *
 * Time Complexity: O(n3)
 *
 * 1) Take 3 nested loops
 * 2) Inner second loop is for groups size
 * 3) Inner third loop is taking the product of group
 * 4) Find the subarray with product less than k
 *
 *
 * Approach: Sliding Window Approach
 * Time Complexity: O(n)
 *
 * 1) Start with the first element.
 * 2) Keeping adding the element till the product is less than k.
 * 3) If the product is greater than k, divide it with the first element and increment start index.
 * 4) Repeat the above till we navigate the entire array
 *
 * int [] nums = {10, 4, 2, 6};
 K = 100
 count = 0 (will be our final result)
 __________________________________________________________
 start = 0, end = 1: [10], product = 10 <100,
 count = count + end-start = 0 + 1 = 1
 __________________________________________________________
 start = 0, end = 2: [10, 4], product = 10*4 = 40 <100,
 count = count + end-start = 1 + 2 = 3 (new ones are {10, 4}, {4})
 ___________________________________________________________
 start = 0, end = 3: [10, 4, 2], product = 40*2 = 80 <100,
 count = count + end-start = 3 + 3 = 6 (new ones are {10, 4, 2}, {4, 2}, {2})
 ___________________________________________________________
 start = 0, end = 4: [10, 4, 2, 6], product = 80*6 = 480 > 100 => remove 10 and
 product = 480/10 = 48, start  = 1
 ___________________________________________________________
 start = 1, end = 4: [4, 2, 6], product = 48<100,
 count = count + end-start =6 + 4 – 1 = 9

 */
public class PrintAllSubarraysWithProductLessThanK {

    private static int findAllSubarraysWithProductLessThanK(int[] array, int k) {

        int count = 0;
        int length = array.length;

        for (int startPoint = 0; startPoint < length; startPoint++) {

            for (int groups = startPoint + 1; groups <= length; groups++) {

                //group size = 1 - 10
                //group size = 2 - 10, 4
                //group size = 3 - 10, 4, 2

                int product = 1;
                int noOfElements = 0;
                String subarray = "";

                for (int j = startPoint; j < groups; j++) {

                    subarray = subarray + array[j] + " ";
                    product = product * array[j];
                    noOfElements++;
                }

                if (product < k && noOfElements > 0) {
                    System.out.println(subarray);
                    count++;
                }
            }
        }
        return count;
    }

    private static int findAllSubarrayWhoseProductLessThanKSlidingWindow(int[] array, int k) {

        int start = 0;
        int end = 1;
        int product = array[0];
        int count = 0;

        while(start <= end && end <= array.length) {

            if (product < k) {
                count = count + (end - start);

                if (end < array.length) {
                    product = product * array[end];
                }
                end++;
            } else {
                product = product/array[start];
                start++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int [] nums = {10,4,2,6};
        int k = 100;
        System.out.println("Sub arrays has sum less than k=100 are: " + findAllSubarraysWithProductLessThanK(nums, k));

        System.out.println("No of sub arrays has sum less than k="+k+" are: " +
            findAllSubarrayWhoseProductLessThanKSlidingWindow(nums, k));
    }
}
