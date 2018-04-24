import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Pair Class - Java Implementation
 *
 * Class Pair<Key, Value>
 *    Represents single entry
 *
 *    A convenient class to represent name:value pairs.
 *    Two pair objects are considered equal if the name and value of one pair object is matching
 *    with the second object.
 *    Generates hashcode using the key:value pair. If the key and value for two pairs are equal
 *    then hashcode will be the same.
 *
 *    public Pair(Key, Value) - Creates a new pair
 *
 *    Methods and Description
 *
 *    Return Type	Method	Description
1)  key for this pair	getKey()	Gets the key for this pair.

2)  value for this pair	getValue()	Gets the value for this pair.

 3) int (hash code for this Pair)	hashCode()

 Generate a hash code for this Pair.
 The hash code is calculated using both the name and the value of the Pair.

 4) String	toString()	String representation of this Pair.

5) boolean	equals(Object o)
 If the Object to be tested is not a Pair or is null, then this method returns false.
 Two Pairs are considered equal if and only if both the names and values are equal.
 Example:

 Pair<String,Integer> p1 = new Pair(1,5);
 Pair p2 = new Pair(1,5);
 Pair p3 = new Pair(2,6);
 System.out.println(p1.equals(p2) + " " + p2.equals(p3));
 Output: true false
 */
public class PairClass {

    public static void main(String[] args) {

        Pair<String, Integer> pair1 = new Pair<>("apple", 1);
        Pair<String, Integer> pair2 = new Pair<>("apple", 1);
        Pair<String, Integer> pair3 = new Pair<>("banana", 2);
        Pair<String, Integer> pair4 = new Pair<>("pineapple", 4);

        ArrayList<Pair<String, Integer>> pairList = new ArrayList<>();

        pairList.add(pair1);
        pairList.add(pair2);
        pairList.add(pair3);
        pairList.add(pair4);

        //toString function
        System.out.println("-------------------------toString function----------------------");
        for (Pair p: pairList) {

            System.out.println(p.toString());
        }

        //equals function
        System.out.println("------------------------equals function--------------------------");
        System.out.println("pair1 and pair2 are equal: " + pair1.equals(pair2));
        System.out.println("pair1 and pair3 are equal: " + pair1.equals(pair3));
        System.out.println("pair3 and pair4 are equal: " + pair3.equals(pair4));

        //getKey and getValue function
        System.out.println("----------------getKey and getValue function-----------------------");

        for(Pair p: pairList) {
            System.out.println("Key " + p.getKey() + " and its value: " + p.getValue());
        }

        //hashcode function
        System.out.println("---------------------------hashcode function----------------------");

        for(Pair p: pairList) {

            System.out.println("Pair: " + p.toString() + " and hashcode: " + p.hashCode());
        }
    }
}
