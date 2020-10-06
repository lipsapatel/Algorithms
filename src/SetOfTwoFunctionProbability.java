import java.util.HashMap;
import java.util.Random;

/**
 * Set of two functions given.
 * G3()  {A, B, C} randomly
 * G7() = {D, E, F, G H, I, J} randomly
 * G20() = {1, 2, 3, …, 20} randomly with equal probability
 * G20() returns number between 1 to 20 with equal probability – 1/20
 * The only function you can call in our implementation of G20() is G3() and G7().
 */
public class SetOfTwoFunctionProbability {

    private static char G3() {
        char[] set = {'A', 'B', 'C'};
        Random random = new Random();
        int i = random.nextInt(set.length);

        return set[i];
    }

    private static char G7() {
        char[] set = {'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        Random random = new Random();
        int i = random.nextInt(set.length);

        return set[i];
    }

    private static HashMap<String, Integer> map = new HashMap<>();

    private static int G20() {
        //You keep the mapping

        /*
            D  E  F  G  H  I  J
         -------------------------
          A 1  2  3  4  5  6  7
          B 8  9  10 11 12 13 14
          C 15 16 17 18 19 20 21
         */

        char x = G3();
        char y = G7();

        if(x == 'C' && y == 'J') {
            return G20();
        }

        String key = String.valueOf(x) + String.valueOf(y);
        return map.get(key);
    }

    private static int G7Size = 7;

    private static int G3WithoutMapping() {
        Random random = new Random();
        return random.nextInt(3);
    }

    private static int G7WithoutMapping() {
        Random random = new Random();
        return random.nextInt(7);
    }

    private static int G20WithoutMapping() {
        int x = G3WithoutMapping();
        int y = G7WithoutMapping();

        //Formula (x + 1) * y + (x + 1) * (G7Size - y)
        //For G3() = 0, it will multiply (0)
        //For G3() = 1, we need to add 7, 6, 5, 4, 3, 2, 1
        //For G3() = 2, it will use G7Size - y //12,10, 8, 6, 4, 2, 0

        int result;
        if(x == 0) {
            result = (x + 1) * y + 1; //(x + 1) * 0
        } else if(x == 1) {
            result = (x + 1) * y + (x + G7Size + 1 - y);
        } else {
            result = (x + 1) * y + (x) * (G7Size - y);
        }
        if(result == 21) {
            return G20WithoutMapping();
        }
        return result;
    }

    public static void main(String[] args) {
        map.put("AD", 1);
        map.put("AE", 2);
        map.put("AF", 3);
        map.put("AG", 4);
        map.put("AH", 5);
        map.put("AI", 6);
        map.put("AJ", 7);

        map.put("BD", 8);
        map.put("BE", 9);
        map.put("BF", 10);
        map.put("BG", 11);
        map.put("BH", 12);
        map.put("BI", 13);
        map.put("BJ", 14);

        map.put("CD", 15);
        map.put("CE", 16);
        map.put("CF", 17);
        map.put("CG", 18);
        map.put("CH", 19);
        map.put("CI", 20);

        System.out.println(G20());
        System.out.println(G20WithoutMapping());
    }
}
