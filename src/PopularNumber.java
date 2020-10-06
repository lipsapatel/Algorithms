import java.util.*;

/**
 * a = {1, 5, 7, 7, 7, 1}
 * A number is popular if it occurs greater than > 25% of the time.
 * If we have 100 numbers, a number is popular if it occurs 26 or more time.
 * 5 appears 1/6 times = 18 %
 * 1 appears 2/6 = 33.33 %
 * 7 appears 3/6 = 50%
 * Output: {1, 7}
 * If the array is unsorted, create map of numbers and their count.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * If the array is sorted then can we do better than O(n)
 * For 25%, the candidates number are at n/4, n/2 and 3n/4
 *
 * And for each candidate number, find the left and right
 * (right - left) * 100 / size > 25
 *
 * Candidate numbers = 100/percent
 * So the Time Complexity = O((100/p - 1) * log(n))
 * Space Complexity: O(1)
 */
public class PopularNumber {

    private static int[] popularNumberUnsortedArray(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        for(int n: a) {
            if(map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            double count = entry.getValue();
            if(((count / a.length) * 100) > 25) {
                result.add(entry.getKey());
            }
        }

        int[] array = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        return array;
    }

    //Sorted array
    private static int[] popularNumber(int[] a) {
        //For 25%, the candidate number are n/4, n/2(n/4 + n/4), 3n/4(n/2 + n/4)
        int n = a.length;
        ArrayList<Integer> result = new ArrayList<>();

        //There could be duplicate numbers, for example whole array contains same number
        Set<Integer> candidate = new HashSet<>();
        candidate.add(a[n/4]);
        candidate.add(a[n/2]);
        candidate.add(a[(3 * n)/4]);

        for(int x: candidate) {
            double left = findLeft(a, x, 0, n - 1);
            double right = findRight(a, x, 0, n - 1);

            if((right - left + 1)/n * 100 > 25) {
                result.add(x);
            }
        }

        int[] array = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        return array;
    }

    private static int findLeft(int[] a, int x, int left, int right) {
        while(left <= right) {
            int mid = left + (right - left)/2;

            if(a[mid] == x && (mid == 0 || a[mid] > a[mid - 1])) {
                return mid;
            } else if(a[mid] >= x) {
                return findLeft(a, x, left, mid - 1);
            } else {
                return findLeft(a, x, mid + 1, right);
            }
        }
        return -1;
    }

    private static int findRight(int[] a, int x, int left, int right) {
        while(left <= right) {
            int mid = left + (right - left)/2;

            if(a[mid] == x && (mid == a.length - 1 || a[mid] < a[mid + 1])) {
                return mid;
            } else if(a[mid] <= x) {
                return findRight(a, x, mid + 1, right);
            } else {
                return findRight(a, x, left, mid - 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,5, 7, 7, 7, 1};
        System.out.println(Arrays.toString(popularNumberUnsortedArray(a)));

        int[] a1 = {1, 1, 5, 7, 7, 7};
        System.out.println(Arrays.toString(popularNumber(a1)));
    }
}
