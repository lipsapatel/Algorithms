package IK.SystemDesign.PreClass;

import java.util.HashMap;

/**
 * Convert decimal to any base
 *
 * 1) Create HashMap with integer number from 0 to 63 and string value of 0 - 9, a - z, A - Z, _ and -
 * 2) Method takes integer n which is decimal number and base
 * 3) While n > 0, rem = n % base
 * 4) Append map.get(rem) to string builder
 * 5) n = n/base
 * 6) Return string in reverse order
 *
 * TC: O(n) where n = length of string
 * SC: O(1)
 */
public class ConvertDecimalToAnyBase {

    private static HashMap<Integer, String> map = new HashMap<>();

    private static String decimalToAnyBase(int n, int base) {

        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            int rem = n % base;
            sb.append(map.get(rem));

            n = n/base;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        //Store mapping for 0 to 63.
        for(int i = 0; i <= 9; i++) {
            map.put(i, ""+i);
        }

        int j = 65;
        for(int i = 10; i <= 35; i++) {
            map.put(i, "" + (char)j);
            j++;
        }

        j = 97;
        for(int i = 36; i <= 61; i++) {
            map.put(i, "" + (char)j);
            j++;
        }

        map.put(62, "-");
        map.put(63, "_");

        System.out.println("The string in base 64 for 7974 is " + decimalToAnyBase(7974, 64).toString());
        System.out.println("The string in base 7 for 23 is " + decimalToAnyBase(23, 7).toString());
    }
}
