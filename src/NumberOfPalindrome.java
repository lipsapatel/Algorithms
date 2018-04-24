import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Number of Palindrome for string "apple" is
 *  'a', 'p', 'l', 'e', 'pp' = 5
 */
public class NumberOfPalindrome {

    private static int palindrome(String str) {

        Set<String> distinctPln = new HashSet<String>();
        for (int i=0; i<str.length();i++) {
            distinctPln.add(String.valueOf(str.charAt(i)));
            for (int j=i-1, k=i+1; j>=0 && k<str.length(); j--, k++) {
                // String of length 2 as palindrome
                if ( (new Character(str.charAt(i))).equals(new Character(str.charAt(j)))) {
                    distinctPln.add(str.substring(j,i+1));
                }
                // String of length 2 as palindrome
                if ( (new Character(str.charAt(i))).equals(new Character(str.charAt(k)))) {
                    distinctPln.add(str.substring(i,k+1));
                }
                if ( (new Character(str.charAt(j))).equals(new Character(str.charAt(k)))) {
                    distinctPln.add(str.substring(j,k+1));
                } else {
                    continue;
                }
            }
        }

        Iterator<String> distinctPlnItr = distinctPln.iterator();
        while ( distinctPlnItr.hasNext()) {
            System.out.println(distinctPlnItr.next()+ ",");
        }
        return distinctPln.size();

    }

    public static void main(String[] args) {
        System.out.println("Number of palindrome is " + palindrome("apple"));
    }
}
