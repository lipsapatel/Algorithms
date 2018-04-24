package MajorityElementInArray;

/**
 * Boyer Moore majority vote algorithm
 *
 * Objective:  Given an array of integer write an algorithm to find the
 * majority element in it (if exist).

 Majority Element: If an element appears more than n/2 times
 in array where n is the size of the array.

 int [] arrA = {1,3,5,5,5,5,4,1,5};
 Output: Element appearing more than n/2 times: 5

 int []arrA = {1,2,3,4};
 Output: No element appearing more than n/2 time

 As per above algorithm we can divide out implementation into two parts

 First iteration — Find the element which could be a majority element.
 Second iteration – check the element(found in first iteration)
 count is greater than n/2

 Time Complexity: O(n),
 Space Complexity: O(1)

 At first count = 1 and majorityElement is first element

 if majorityElement == currentElement then increment the count
 if count = 0 then majorityElement = currentElement count = 1
 if majorityElement != currentElement then decrement the count

 Iterate the array and count the majorityElement and check if the count is
 greater than n/2

 */
public class MajorityElementInArrayByBoyerMoore {

    public static void findMajorityElement(int[] array) {

        //Step 1

        int majorityElement = array[0];
        int count = 1;

        for (int i = 1; i < array.length; i++) {

            //If majorityElement == current Element increment the count
            if (majorityElement == array[i]) {
                count++;
            } else if (count == 0) { //If count == 0 then make current element as majority element and make count = 1
                majorityElement = array[i];
                count = 1;
            } else { //If majority element != current element, decrement the count
                count--;
            }
        }

        //Step 2
        //Iterate and find the count of majority element
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == majorityElement) {
                count++;
            }
        }

        if (count > array.length/2) {
            System.out.println("Boyer Moore Element appearing more than n/2 times is " + majorityElement);
        } else {
            System.out.println("No element found appearing more than n/2 times");
        }
    }

    public static void main(String[] args) {
        int [] array = {1,3,5,5,5,5,4,1,5};
        findMajorityElement(array);
    }

}
