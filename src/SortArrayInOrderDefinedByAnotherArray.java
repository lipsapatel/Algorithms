import java.util.*;

/**
 * Sort a given array in the order defined by another array.
 *
 * resources/SortArrayInOrderDefinedByAnotherArray.png
 *
 * Input Array: 2 6 9 1 4 4 2 1 10 13 5 7 8
 * Defined Array: 1 2 4 6
 * Output Array: 1 1 2 2 4 4 6 5 7 8 9 10 13
 *
 * Approach:
 *
 * Method 1: Sort an array and search
 *
 * 1) Create an auxiliaryArray and copy all elements of inputArray to auxiliaryArray
 * 2) Create boolean array visited of size inputArray and initialize it with false
 * 3) Sort auxiliaryArray using mergeSort - O(nlogn)
 * 4) Navigate through definedArray and take one element at a time say x
 * 5) Perform Binary Search on auxiliaryArray and find the first index of x
 * 6) Copy it into the inputArray and mark visited = true
 * 7) Do linear navigation on auxiliaryArray till you find x, copy it into the
 * inputArray and mark visited = true
 * 8) When definedArray is over, copy rest of the elements which are not visited from
 * auxiliaryArray to inputArray.
 *
 * Time Complexity: O(nlogn) to sort
 * Space Complexity: n
 *
 * Method 2: Use HashMap and then sort the remaining elements
 *
 * 1) Insert all the elements of inputArray in HashMap.
 * 2) Element as key and it's count as value.
 * 3) Navigate definedArray, check it's presence in HashMap
 * 4) If it's present, take it's value(count) and insert that many times in inputArray
 * 5) Once definedArray is completed, take the rest of the elements from HashMap, sort them
 * and insert it into inputArray
 *
 * p = number of elements in definedArray
 * We are sorting only the remaining elements
 * Time Complexity: O((n-p)log(n-p))
 * Space Complexity: n
 *
 * This one has little less time complexity
 */
public class SortArrayInOrderDefinedByAnotherArray {

    //Merge sort - Time Complexity O(nlogn)
    private static void mergeSort(int[] inputArray) {

        int[] auxiliaryArray = new int[inputArray.length];
        sort(0, inputArray.length - 1, auxiliaryArray, inputArray);
    }

    private static void sort(int low, int high, int[] auxiliaryArray, int[] inputArray) {

        if (low < high) {

            int mid = low + (high - low)/2;

            sort(low, mid, auxiliaryArray, inputArray);
            sort(mid + 1, high, auxiliaryArray, inputArray);
            merge(low, mid, high, auxiliaryArray, inputArray);
        }
    }

    private static void merge(int low, int mid, int high, int[] auxiliaryArray, int[] inputArray) {

        //Copy the input array in the auxiliaryArray
        for (int i = low; i <= high; i++) {
            auxiliaryArray[i] = inputArray[i];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while(i <= mid && j <= high) {

            if (auxiliaryArray[i] < auxiliaryArray[j]) {
                inputArray[k] = auxiliaryArray[i];
                i++;
            } else {
                inputArray[k] = auxiliaryArray[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            inputArray[k] = auxiliaryArray[i];
            i++;
            k++;
        }
    }

    private static int[] sortAndSearch(int[] inputArray, int[] definedArray) {

        //Boolean visited array and initialize with false
        boolean[] visistedArray = new boolean[inputArray.length];

        for (int i = 0; i < visistedArray.length; i++) {
            visistedArray[i] = false;
        }

        //auxiliaryArray and copy all the elements of inputArray
        int[] auxiliaryArray = new int[inputArray.length];

        for (int i = 0; i < auxiliaryArray.length; i++) {
            auxiliaryArray[i] = inputArray[i];
        }

        //Sort auxiliaryArray
        mergeSort(auxiliaryArray);

        int origIndex = -1;

        //Take one by one element from definedArray and perform Binary Search on auxiliaryArray
        //to find the first occurrence of element
        for (int i = 0; i < definedArray.length; i++) {

            int x = definedArray[i];

            int index = findFirstOccurrenceUsingBinarySearch(auxiliaryArray, x, 0, auxiliaryArray.length - 1);

            if (index >= 0) {

                //Update inputArray and mark visited true
                inputArray[++origIndex] = auxiliaryArray[index];
                visistedArray[index] = true;

                //Copy all duplicates too
                while (auxiliaryArray[++index] == x) {
                    inputArray[++origIndex] = auxiliaryArray[index];
                    visistedArray[index] = true;
                }
            }
        }

        //Copy rest of the elements from auxiliaryArray to inputArray whose visited = false
        for (int i = 0; i < auxiliaryArray.length; i++) {
            if (!visistedArray[i]) {
                inputArray[++origIndex] = auxiliaryArray[i];
            }
        }

        return inputArray;
    }

    private static int findFirstOccurrenceUsingBinarySearch(int[] auxiliaryArray, int x,
                                                            int start, int end) {

        if (start <= end) {

            int mid = (start + end)/2;

            //If mid is the element at index 0 return mid
            //Since we need to find the first occurrence check if the previous element is less
            //than mid
            if ((mid == 0 || auxiliaryArray[mid - 1] < x) &&
                    auxiliaryArray[mid] == x) {
                return mid;
            } else if (auxiliaryArray[mid] < x) {
                //search right
                return findFirstOccurrenceUsingBinarySearch(auxiliaryArray, x, mid + 1, end);
            } else {
                return findFirstOccurrenceUsingBinarySearch(auxiliaryArray, x, start, mid - 1);
            }
        }
        return -1;
    }

    private static int[] sortInOrderByHashMap(int[] inputArray, int[] definedArray) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        //Insert all elements and their count from inputArray to HashMap
        for (int i = 0; i < inputArray.length; i++) {

            if (hashMap.containsKey(inputArray[i])) {
                hashMap.put(inputArray[i], hashMap.get(inputArray[i]) + 1);
            } else {
                hashMap.put(inputArray[i], 1);
            }
        }

        int origIndex = -1;

        //Iterate definedArray and find it in hashmap and it's count and insert it in inputArray
        for (int i = 0; i < definedArray.length; i++) {
            if (hashMap.containsKey(definedArray[i])) {
                int count = hashMap.get(definedArray[i]);

                //For duplicates
                while (count > 0) {
                    inputArray[++origIndex] = definedArray[i];
                    count--;
                }

                //Remove from hashmap
                hashMap.remove(definedArray[i]);
            }
        }

        ArrayList<Integer> remainingValues = new ArrayList<>();

        Set<Integer> keys = hashMap.keySet();

        for (int x: keys) {
            remainingValues.add(x);
        }

        //Sort the list
        Collections.sort(remainingValues);

        //Insert it into inputArray
        for (int x: remainingValues) {
            inputArray[++origIndex] = x;
        }

        return inputArray;


    }

    public static void main(String[] args) {
        int [] inputArray = {2,6,9,1,4,4,2,1,10,13,5,7,8};
        //mergeSort(inputArray);

        //System.out.println("The array after sorting " + Arrays.toString(inputArray));

        int [] definedArray = {1,2,4,6};

        int[] outputArray = sortAndSearch(inputArray, definedArray);

        System.out.println("The array sorted in the order by definedArray: " +
                            Arrays.toString(outputArray));

        System.out.print("\n  Output Array using Hashing : ");

        int[] outputArray1 = sortInOrderByHashMap(inputArray, definedArray);

        System.out.println(Arrays.toString(outputArray1));
    }
}
