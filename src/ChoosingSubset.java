/**
 * Given a dorm of 60 people, how many different group of 4 people can go to flick.
 * nCK = n choose k
 * n!/(n - k)! k!
 *
 * Number of subsets that include bob = n-1 C k - 1
 *  + Number of subsets that don't include bob = n - 1 C k
 */
public class ChoosingSubset {

    private static int chooseK(int n, int k) {

        if (k == 0 || k == n) {
            return 1;
        } else {
            return chooseK(n - 1, k - 1) + chooseK(n - 1, k);
        }
    }

    public static void main(String[] args) {

        int k = 4;
        int n = 60;

        System.out.println("The different group of 4 people are: " + chooseK(n, k));
    }
}
