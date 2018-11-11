/**
 * Find median of two sorted arrays of same size.
 *
 * Given two sorted arrays of size n. Write an algorithm to find the median of
 * combined array (merger of both arrays, size = 2n)
 *
 * What is Median?
 *
 * The median is the value separating the higher half of data sample from lower half.
 * For data set it may be thought of middle value.
 *
 * If n = odd //Middle element
 * M = a[n+1/2]
 *
 * If n = even //Two middle element
 * M = a[n/2] + a[n/2 + 1] /2
 *
 * Example:
 *
 * A = 2, 6, 9
 * B = 1, 5, 7
 * Combined array = 1, 2, 5, 6, 7, 9
 * Median = 3rd element + 4th element/2
 * = (5+6)/2 = 5.5
 *
 * Binary Approach
 *
 * Time Complexity: O(logn)
 *
 * 1) Array a b
 * 2) Calculate median of a and b
 * 3) If m1 == m2 then return m1 or m2
 * 4) If m1 > m2
 * a = start, m1
 * b = m2, end
 * 5) If m1 < m2
 * a = m1, end
 * b = start, m2
 * 6) Repeat steps 1 to 5 until 2 elements are left in both the arrays
 * 7) Then apply the formula
 * Median = Math.max(a[0], b[0]) + Math.min(a[1], b[1])/2
 *
 * Example:
 *
 * int [] a = {2,6,9,10,11};
 int [] b = {1,5,7,12,15};

 m1 = 9 , m2 = 7
 We have m1 > m2
 Array1[] - from first element to m1, Array2[] – from m2 to last element.

 int [] a = {2,6,9};
 int [] b = {7,12,15};

 We have m1 < m2
 Array1[] - from m1 to last element, Array2[] – from first element to m2.

 int [] a = {6,9};
 int [] b = {7,12};
 Now we have 2 elements left in both the arrays so now apply the formula

 Median = (max(array[0],array[0]) + min(array[1],array[1]))/2
 =(max(6,7) + min(9,12))/2
 = (7+9)/2
 =8
 */
public class MedianOfTwoSortedArrayOfSameSize {

    private static float findMedianOfSortedArray(int[] a, int starta, int enda, int[] b, int startb, int endb) {

        //If there are only two elements left
        if (enda - starta + 1 == 2 && endb - startb + 1 ==2) {

            float x = Math.max(a[starta], b[startb]);
            float y = Math.min(a[enda], b[endb]);
            return (x+y)/2;
        }

        float mediana = findMedian(a, starta, enda);
        float medianb = findMedian(b, startb, endb);

        int mida = (starta + enda)/2;
        int midb = (startb + endb)/2;

        if (mediana > medianb) {
            return findMedianOfSortedArray(a, starta, mida, b, midb, endb);
        } else {
            return findMedianOfSortedArray(a, mida, enda, b, startb, midb);
        }
    }

    private static float findMedian(int[] x, int start, int end) {

        int size = end - start + 1;
        double median;

        //If even
        if (size % 2 == 0) { //Middle two elements
            float m = x[start + (size - 1)/2];
            float n = x[start + (size/2)];
            return (m + n)/2;
        } else { //odd middle element
            return x[start + (size - 1)/2];
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 6, 9, 10, 11};
        int[] b = {1, 5, 7, 12, 15};

        float median = findMedianOfSortedArray(a, 0, a.length - 1, b, 0, b.length - 1);
        System.out.println("Median of combined sorted array is: " + median);
    }
}
