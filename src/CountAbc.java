/**
 * Count recursively the total number of "abc" and "aba" substrings that appear in the given string.


 countAbc("abc") → 1
 countAbc("abcxxabc") → 2
 countAbc("abaxxaba") → 2
 */
public class CountAbc {

    private static int countAbc(String str) {
        //Base Case
        if (str.length() <= 2) {
            return 0;
        } else {
            if ((str.substring(0, 3)).equals("abc") || (str.substring(0, 3)).equals("aba")) {
                return 1 + countAbc(str.substring(1));
            } else {
                return countAbc(str.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("The total number of substrings abc and aba in given string abc are: " + countAbc("abc"));
        System.out.println("The total number of substrings abc and aba in given string abcxxabc are: " + countAbc("abcxxabc"));
        System.out.println("The total number of substrings abc and aba in given string  are: " + countAbc(""));
        System.out.println("The total number of substrings abc and aba in given string ababc are: " + countAbc("ababc"));
        System.out.println("The total number of substrings abc and aba in given string abaxxaba are: " + countAbc("abaxxaba"));
    }
}
