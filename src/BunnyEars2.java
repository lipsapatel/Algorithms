/**
 * We have bunnies standing in a line, numbered 1, 2, ...
 * The odd bunnies (1, 3, ..) have the normal 2 ears.
 * The even bunnies (2, 4, ..) we'll say have 3 ears, because they each have a raised foot.
 * Recursively return the number of "ears" in the bunny line 1, 2, ... n (without loops or multiplication).


 bunnyEars2(0) → 0
 bunnyEars2(1) → 2
 bunnyEars2(2) → 5
 */
public class BunnyEars2 {

    private static int bunnyEars2(int bunnies) {
        //Base Case
        if (bunnies == 0) {
            return 0;
        } else {
            //Recursive Case
            if (bunnies % 2 == 0) { //even case
                return 3 + bunnyEars2(bunnies - 1);
            } else { //odd case
                return 2 + bunnyEars2(bunnies - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("0 Bunnies in line: " + bunnyEars2(0));
        System.out.println("1 Bunnies in line: " + bunnyEars2(1));
        System.out.println("2 Bunnies in line: " + bunnyEars2(2));
    }
}
