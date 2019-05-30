/**
 * Find median of first array
 * Find median of 2nd array
 *
 * if m1 < m2
 * Then m1 - end
 * and start to m2 discard rest of the array
 *
 * Do it recursively
 * until we have k = 1.
 *
 * /*
 There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 Example 1:
 nums1 = [1, 3]
 nums2 = [2]
 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 The median is (2 + 3)/2 = 2.5
 LintCode examples:
 Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.
 Given A=[1,2,3] and B=[4,5], the median is 3.
 */


/*
    Thoughts:
    Trivial: merge and find median. NOPE: have to be in log(m+n) time
    http://www.jiuzhang.com/solutions/median-of-two-sorted-arrays/
    http://articles.leetcode.com/find-k-th-smallest-element-in-union-of

    Good one: http://blog.csdn.net/yutianzuijin/article/details/11499917
    http://blog.csdn.net/zxzxy1988/article/details/8587244
*/

/*
Thoughts:
A[1,2]
B[3,4,5]
Assume after merge: [1,2,3,4,5], we'll be looking for the item in the mid.
Math: consider A[mid/2] and B[mid/2]. If A[mid/2]<B[mid/2], then it's safe to cut off all items before mid/2 = n/4;
they won't be in range for the median.
Same applies to when A[mid/2]>B[mid/2]
Approach: find kth number of two sorted array, k ~= (m+n)/2
- use startA, startB indexes to track the partition location
- Cut off half of one list at a time, recursively process the rest 3/4 of overall content.
- when k = 1, since A/B lists are sorted, should return the min
- Be careful with index: it's all 0 based

We are always discarding smallest in the smallest median and biggest in the biggest median.

TC = O(log(m + n))
*/

public class MedianOfTwoSortedArray {

    public static double findMedianSortedArrays(int[] a, int[] b) {

        int n = a.length + b.length;

        if (n % 2 == 0) { //even
            return (findMedian(a, 0, b, 0, n/2) + findMedian(a, 0, b, 0, n/2 + 1))/2.0;
        } else { //odd
            return (double)findMedian(a, 0, b, 0, n / 2 + 1);
        }
    }

    /**
     *
     * @param a - First sorted array
     * @param sa - start A
     * @param b - Second sorted array
     * @param sb - start B
     * @param k - is size
     * @return
     */
    public static int findMedian(int[] a, int sa, int[] b, int sb, int k) {

        //edge cases
        //If a is exhausted, return kth in b
        if (sa >= a.length) {
            return b[sb + k - 1];
        }

        //If b is exhausted, return kth in a
        if (sb >= b.length) {
            return a[sa + k - 1];
        }

        //Handle final condition. If k = 1 The two elements is sorted and smaller one is k
        if (k == 1) {
            return Math.min(a[sa], b[sb]);
        }

        // compare and partition at each [x+(k/2-1)] position
        int halfKthOfA = sa + k/2 - 1 < a.length ? a[sa + k/2 - 1] : Integer.MAX_VALUE;
        int halfKthOfB = sb + k/2 - 1 < b.length ? b[sb + k/2 - 1] : Integer.MAX_VALUE;

        if (halfKthOfA < halfKthOfB) {
            return findMedian(a, sa + k/2, b, sb, k - k/2);
        } else {
            return findMedian(a, sa, b, sb + k/2, k - k/2);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = {3, 4, 5};

        System.out.println("The median is: " + findMedianSortedArrays(a, b));

        int[] a1 = {7, 8, 9, 12, 15};
        int[] b1 = {6, 10, 13, 20, 23};

        System.out.println("The median is: " + findMedianSortedArrays(a1, b1));
    }
}
