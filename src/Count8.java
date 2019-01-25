/**
 *
 Given a non-negative int n, compute recursively (no loops) the count of the occurrences of 8 as a digit,
 except that an 8 with another 8 immediately to its left counts double, so 8818 yields 4.
 Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6),
 while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).


 count8(8) → 1
 count8(818) → 2
 count8(8818) → 4
 */
public class Count8 {

    private static int count8(int n) {
        //Base Case
        if (n == 0) {
            return 0;
        } else { //Recursive case
            if (n % 10 == 8) {

                //check if there's 8 on it's left
                int left = n / 10;
                if (left % 10 == 8) {
                    return 2 + count8(n / 10);
                } else {
                    return 1 + count8(n / 10);
                }
            } else {
                return count8(n / 10);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("No of 8 in 8: " + count8(8));
        System.out.println("No of 8 in 818: " + count8(818));
        System.out.println("No of 8 in 8818: " + count8(8818));
    }
}
