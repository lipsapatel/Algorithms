package IK.Sorting.Class;

/**
 * Given two sets of integers, find if one is subset of another
 *
 * Two Approaches
 *
 * Approach1:
 *
 * Binary Search: O(mlogn)
 * Use this approach if there's difference in size of both arrays = m >>>>>n
 *
 * Approach2:
 *
 * Merge Sort Approach: O(max(m,n))
 *
 * 1) Find the smaller array and larger array
 * 2) Take 2 pointers
 * 3) If elements matches, increment both the pointers.
 * 4) If element doesn't matches, check if the element in larger array is bigger than element in smaller array, return false
 * 5) If element in larger array is smaller, increment larger array pointer.
 * 6) If both arrays are finished, return true
 * 7) If smaller one is finished, return true
 * 8) If bigger one is finished and smaller one is not finished, return false
 *
 * TC: O(max(m, n))
 * SC: O(1)
 * m == n
 *
 * Binary Search
 *
 * 1) Iterate smaller array.
 * 2) Binary search in larger array.
 * 3) If all elements of smaller array found, return true
 * 4) Else return false
 *
 * Use this when m >>>>n
 * TC: O(nlogm)
 * SC: O(1) *
 *
 * Binary Search: mlogn
 Mergesort: Max( m, n)
 M = 1B
 N = 10
 10 * 30 = 300 Binary – m >>>>>n
 1B = Mergesort – same size – good

 If both the arrays are not sorted, sort them and then do this but then it doesn't matter which approach you choose
 Because the time complexity of sorting is O(nlogn) + O(mlogm)

 */
public class IsArraySubsetOfAnother {

    public static boolean isSubset(int[] a, int m, int[] b, int n) {

        int i = 0;
        int j = 0;

        while (i < m && j < n) {

            if (a[i] == b[j]) {
                i++;
                j++;
            } else {
                if (b[j] > a[i]) {
                    return false;
                } else {
                    j++;
                }
            }
        }

        if (i == m && j == n) {
            return true;
        }

        if (j == n) {
            return false;
        }

        if (i == m) {
            return true;
        }
        return false;
    }

    //Time Complexity= O(m + n) Trading off the time complexity because of code readability
    // This does not return if a[i] < b[j]
    public static boolean isSubsetApproach(int[] a, int m, int[] b, int n) {

        int i = 0;
        int j = 0;
        int matches = 0;

        while (i < m && j < n) {
            if (a[i] == b[j]) {
                i++;
                j++;
                matches++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Math.min(m, n) == matches;
    }

    private static boolean binarySearch(int[] a, int k, int low, int high) {

        if(low <= high) {
            int mid = low + (high - low)/2;

            if(a[mid] == k) {
                return true;
            } else if (k < a[mid]) {
                return binarySearch(a, k, low, mid - 1);
            } else {
                return binarySearch(a, k, mid + 1, high);
            }
        } else {
            return false;
        }
    }

    private static boolean isArraySubnetBinarySearch(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if(!binarySearch(b, a[i], 0, b.length - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 3, 4};
        boolean isSubset1 = false;

        if (a.length <= b.length) {
             isSubset1 = isSubset(a, a.length, b, b.length);
        }
        System.out.println("Is Subset: " + isSubset1);
        System.out.println("Is subset: " + isArraySubnetBinarySearch(a, b));

        int[] a1 = {4, 8};
        int[] b1 = {1, 2, 3, 4, 5, 6, 7, 8};

        if (a1.length < b1.length) {
            isSubset1 = isSubset(a1, a1.length, b1, b1.length);
        }
        System.out.println("Is Subset: " + isSubset1);

        System.out.println("Is subset: " + isArraySubnetBinarySearch(a1, b1));
    }
}
