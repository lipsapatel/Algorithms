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
 * Binary Search: mlogn
 Mergesort: Max( m, n)
 M = 1B
 N = 10
 10 * 30 = 300 Binary – m >>>>>n
 1B = Mergesort – same size – good

 If both the arrays are not sorted, sort them and then do this but then it doesn't matter which approach you choose
 Because the time complexity of sorting is O(mlogn)

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

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 3, 4};
        boolean isSubset1 = false;

        if (a.length <= b.length) {
             isSubset1 = isSubset(a, a.length, b, b.length);
        }
        System.out.println("Is Subset: " + isSubsetApproach(a, a.length, b, b.length));

        int[] a1 = {4, 8};
        int[] b1 = {1, 2, 3, 4, 5, 6, 7, 8};

        if (a1.length < b1.length) {
            isSubset1 = isSubset(a1, a1.length, b1, b1.length);
        }
        System.out.println("Is Subset: " + isSubset1);
    }
}
