package MajorityElementInArray;

/**
 * Use nested loops and increment the count
 * if count is > n/2 then break the loop
 *
 * Time complexity: O(n2)
 * Space complexity: O(1)
 */
public class MajorityElementNestedLoops {

    private static void findMajorityElement(int[] array) {

        boolean found = false;

        for (int i = 0; i < array.length; i++) {

            int count = 1;
            int currentElement = array[i];

            for (int j = i+1; j < array.length; j++) {
                if (array[j] == currentElement) {
                    count++;
                }
            }

            if (count > array.length/2) {
                System.out.println("Found the majority element " + currentElement);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("There is no majority element");
        }
    }

    public static void main(String[] args) {
        int [] array = {1,3,5,5,5,5,4,1,5};
        findMajorityElement(array);
    }
}
