import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Bob’s fruit company is running a promotion in which customers receive prizes for purchasing
a secret combination of items. The combination will change each day, so Bob wants to use a code
list to make it easy to change the combination. The code list contains groups of fruits.
Both the order of the groups within the code list and the order of the fruits within the groups
matter. However, between the groups of fruits, any number, and type of fruit is allowable.
The term “anything” is used to allow for any type of fruit to appear in that location
within the group.

        Consider the following secret code list: [[apple, apple], [banana, anything, banana]]

        Based on the above secret code list, a customer who made either of the following
        purchases would win the prize:

        orange, apple, apple, banana, orange, banana

        apple, apple, orange, orange, banana, apple, banana, banana

        Write an algorithm to output 1 if the customer is a winner else output 0.

        Input: The input to the function/method consists of two arguments:codeList,
        a list of lists representing the order and grouping of specific fruits that must be
        purchased in order to win the prize for the day.

        shoppingCart, a list representing the order in which a customer purchases fruit.

        Output: Return an integer 1 if the customer is a winner else return 0.

        Note: ‘anything’ in the codeList represents that any fruit can be ordered
        in place of ‘anything’ in the group.‘anything’ has to be something,
        it cannot be “nothing.”‘anything’ must represent one and only one fruit.

        Examples

        Example 1: Input:codeList =[[apple, apple], [banana, anything, banana]]
        shoppingCart=[orange, apple, apple, banana, orange, banana]
        Output: 1
        Explanation: codeList contains two groups - [apple, apple] and [banana, anything, banana].
        The second group contains ‘anything’ so any fruit can be ordered in place of ‘anything’
        in the shoppingCart. The customer is a winner as the customer has added fruits
        in the order of fruits in the groups and the order of groups in the codeList is
        also maintained in the shoppingCart.

        Example 2: Input:codeList=[[apple, apple],[banana, anything, banana]]
        shoppingCart=[banana, orange, banana, apple, apple]
        Output: 0
        Explanation:The customer is not a winner as the customer has added the fruits in
        order of groups but group [banana, orange, banana] is not following the group
        [apple, apple] in the codeList.

        Example3: Input:codeList =[[apple, apple],[banana, anything, banana]]
        shoppingCart=[apple, banana, apple, banana, orange, banana]
        Output: 0
        Explanation:The customer is not a winner as the customer has added the fruits
        in an order which is not following the order of fruit names in the first group.
*/
public class FruitWinner
{
    private static int checkWinner(List<String> shoppingCart, List<List<String>> codeList) {

        int lastIndex = 0;
        List<String> shoppingList = shoppingCart;

        //Iterate the codeList
        for (int i = 0; i < codeList.size(); i++) {
            List<String> groupList = codeList.get(i);
            shoppingList = shoppingList.subList(lastIndex, shoppingList.size());

            if (!groupList.contains("anything")) {
                int firstIndex = Collections.indexOfSubList(shoppingList, groupList);

                if (firstIndex != -1) {
                    lastIndex = firstIndex + groupList.size();
                } else {
                    return 0;
                }
            } else {
                int anythingIndex = groupList.indexOf("anything");

                List<String> beforeAnythingList = groupList.subList(0, anythingIndex);
                List<String> afterAnythingList = groupList.subList(anythingIndex+1, groupList.size());

                int firstIndexOfBeforeList = Collections.indexOfSubList(shoppingList, beforeAnythingList);

                if (firstIndexOfBeforeList != -1) {
                    int firstIndexOfAfterList = Collections.indexOfSubList(shoppingList, afterAnythingList);

                    if (firstIndexOfAfterList != -1) {
                        System.out.println("Group List matched");
                        lastIndex = firstIndexOfBeforeList + groupList.size();
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
        return 1;
    }
    public static void main(String[] args) {
        List<String> shoppingCart = Arrays.asList("apple", "banana", "apple", "banana", "orange", "banana");

        List<List<String>> codeList = Arrays.asList(Arrays.asList("apple", "apple"),
                                                    Arrays.asList("banana", "anything", "banana"));


        if(checkWinner(shoppingCart,codeList) == 1) {
            System.out.println("The given shopping cart is winner");
        } else {
            System.out.println("The given shopping cart is not winner");
        }

        List<String> shoppingCart1 = Arrays.asList("banana", "orange", "banana", "apple", "apple");

        List<List<String>> codeList1 = Arrays.asList(Arrays.asList("apple", "apple"),
                Arrays.asList("banana", "anything", "banana"));


        if(checkWinner(shoppingCart1,codeList1) == 1) {
            System.out.println("The given shopping cart is winner");
        } else {
            System.out.println("The given shopping cart is not winner");
        }

        List<String> shoppingCart2 = Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana");

        List<List<String>> codeList2 = Arrays.asList(Arrays.asList("apple", "apple"),
                Arrays.asList("banana", "anything", "banana"));


        if(checkWinner(shoppingCart2,codeList2) == 1) {
            System.out.println("The given shopping cart is winner");
        } else {
            System.out.println("The given shopping cart is not winner");
        }
    }
}
