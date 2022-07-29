package IK.Sorting.PreClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Sort large set of floating numbers which are in the range of 0.1 to 1.0 and are uniformly distributed across the range
 * One bucket represents range of values. While in counting sort, one bucket is only for one value.
 *
 * Approach:
 *
 * 1) Create bucket of size n
 * 2) Do arr[i] * n to determine the bucket.
 * 3) Iterate array and add items to bucket
 * 4) Sort individual buckets using insertion sort
 * 5) Concatenate all buckets
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Not stable because individual sorting of bucket may not be stable.
 * Algorithm can be made stable by making individual sorting of buckets stable.
 *
 */
public class BucketSort {

    private static void bucketSort(float[] a) {
        int n = a.length;

        List<List<Float>> bucket = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            bucket.add(new ArrayList<>());
        }

        //Add items to bucket
        for(int i = 0; i < n; i++) {
            int index = (int)a[i] * n;

            bucket.get(index).add(a[i]);
        }

        //Sort Individual bucket
        for(List<Float> b: bucket) {
            Collections.sort(b);
        }

        //Concatenate bucket
        int index = 0;
        for(List<Float> b: bucket) {
            for(Float item: b) {
                a[index] = item;
                index++;
            }
        }
    }

    public static void main(String[] args) {
        float a[] = { 0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f };
        bucketSort(a);

        System.out.println(Arrays.toString(a));

    }
}
