import java.util.ArrayList;

/**
 * You are given "Pictures of cats"
 *
 * You need to crawl all billion of documents on web and find only those documents which has pictures and cats both.
 *
 * You are given
 *
 * cats = {13, 15, 17, 19, 20, 24, 36}; Documents containing word "cats", this is sorted order = m size
 * pictures = {2, 5, 6, 7, 8, 12, 16, 18, 23, 25, 28}; Documents containing word "pictures", this is also in sorted order = n size
 *
 * There are 3 approaches:
 *
 * Brute force approach
 * Time Complexity: O(m * n)
 *
 * 1) Two for loops and construct result array
 *
 * Merge Approach
 * Time Complexity: O(m + n)
 *
 * 1) Take two pointer, both of them are pointing to 0th element in both array.
 * 2) Increment the pointer which has less value.
 * 3) If value are equal, add to result and increment both the pointer
 *
 * This approach is good, if size of both the array are equal
 *
 * For example: m = 1B and n = 1B
 * Using Merge approach: m + n = 2B
 *
 * Using Binary Search approach = mlogn
 *  = 1B * 30 = 30B
 *
 *  Binary Search:
 *  Time Complexity: O(mlogn)
 *
 *  1) Take log of smaller array
 *  2) Take first element in cats and binary search in pictures. If found add to result
 *
 *  Binary search is good if the input size vary very largely
 *
 *  For example: m = 10k
 *  n = 1B
 *
 *  Merge approach: m + n ~ 1B
 *  Binary Approach: 10k * 30 = 300k
 *
 */
public class SearchWordsInBillionOfDocuments {

    //Time Complexity: O(m + n)
    public static ArrayList<Integer> merge(int[] cats, int[] pictures) {
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < cats.length && j < pictures.length) {
            if (cats[i] < pictures[j]) {
                i++;
            } else if (pictures[j] < cats[i]) {
                j++;
            } else if (cats[i] == pictures[j]) {
                result.add(cats[i]);
                i++;
                j++;
            }
        }
        return result;
    }

    public static ArrayList<Integer> binarySearchApproach(int[] cats, int[] pictures) {

        ArrayList<Integer> result = new ArrayList<>();

        //Time Complexity: O(mlogn)
        if (cats.length < pictures.length) {
            for (int i = 0; i < cats.length; i++) {

                if (binarySearch(pictures, cats[i], 0, pictures.length - 1)) {
                    result.add(cats[i]);
                }
            }
        } else { //Time Complexity: O(nlogm)

            for (int i = 0; i < pictures.length; i++) {

                if (binarySearch(cats, pictures[i], 0, cats.length - 1)) {
                    result.add(pictures[i]);
                }
            }
        }
        return result;
    }

    public static boolean binarySearch(int[] array, int k, int low, int high) {
        if (low > high) {
            return false;
        } else {

            int mid = low + (high - low)/2;

            if (k < array[mid]) {
                return binarySearch(array, k, low, mid - 1);
            } else if (k > array[mid]) {
                return binarySearch(array, k, mid + 1, high);
            } else {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        int[] cats = {13, 15, 17, 19, 20, 24, 36};
        int[] pictures = {2, 5, 6, 7, 8, 12, 15, 16, 17, 18, 23, 25, 28};

        System.out.println("Documents which contains both cats and pictures are: " + merge(cats, pictures));
        System.out.println("Documents which contains both cats and pictures using binary search: " + binarySearchApproach(cats, pictures));
    }
}
