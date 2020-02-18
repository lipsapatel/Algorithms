/**
 * Find the max length word in the given string.
 *
 * TC = O(n)
 * SC = O(1)
 *
 * Special processing for the last word
 * If there are 2 words of the same length return the first word which is encounter.
 */
public class MaxLengthWord {

    private static int maxLength = 0;
    private static int startIndex = -1;
    private static int endIndex = -1;

    private static String findMaxLength(String ip, int s) {

        while (s < ip.length()) {

            for(int i = s; i < ip.length(); i++) {

                //Last character reached so update max and return
                if(i == ip.length() - 1) {

                    if (ip.length() - s > maxLength) {
                        maxLength = ip.length() - s;
                        startIndex = s;
                        endIndex = ip.length();
                    }
                    return ip.substring(startIndex, endIndex);

                } else if (ip.charAt(i) != ' ') {
                    //Do nothing
                } else {
                    //Update max
                    if(i - s > maxLength) {
                        maxLength = i - s;
                        startIndex = s;
                        endIndex = i;
                    }
                    s = i + 1;
                    break;
                }
            }

       }
        return ip.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {
        System.out.println(findMaxLength("The dog quick find quidk", 0));
    }
}
