/**
 * We have a number of bunnies and each bunny has two big floppy ears.
 * We want to compute the total number of ears across all the bunnies recursively (without loops or multiplication).


 bunnyEars(0) → 0
 bunnyEars(1) → 2
 bunnyEars(2) → 4
 */
public class BunnyEars {

    private static int bunnyEars(int bunnies) {

        //Base Case
        if (bunnies == 0) {
            return 0;
        } else { //Recursive Case
            return 2 + bunnyEars(bunnies - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("No of ears 0 bunnies have: " + bunnyEars(0));
        System.out.println("No of ears 1 bunnies have: " + bunnyEars(1));
        System.out.println("No of ears 2 bunnies have: " + bunnyEars(2));
    }
}
