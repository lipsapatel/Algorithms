/**
 * Given an array and element return YES if element is present in array
 * else return NO
 */
public class FindElementInArray {

    private static String findNumber(int[] array, int element) {

        for (int arrayElement : array) {
            if (arrayElement == element) {
                return "YES";
            }
        }

        return "NO";
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1};
        int element = 5;
        System.out.println("Element " + element + " present: " + findNumber(array, element));

        int[] array1 = {1, 2, 5, 3, 4, 5};
        int element1 = 5;
        System.out.println("Element " + element1 + " present: " + findNumber(array1, element1));

        int[] array2 = {5, 1, 2, 3, 4, 5, 1};
        int element2 = 6;
        System.out.println("Element " + element2 + " present: " + findNumber(array2, element2));
    }
}
