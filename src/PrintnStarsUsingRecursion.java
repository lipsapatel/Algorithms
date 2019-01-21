/**
 * Print n starts using recursion
 */
public class PrintnStarsUsingRecursion {

    private static void printnStars(int n) {

        if (n == 1) {
            System.out.print("*");
        } else {
            System.out.print("*");
            printnStars(n - 1);
        }
    }

    public static void main(String[] args) {

        int n = 3;
        printnStars(n);
    }
}
