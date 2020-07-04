import java.util.Random;

/**
 * Given a screen with a given width, height and supported min/max font size,
 * determine the max font a given string can be displayed in.
 *
 * Word or character can’t be broken. But here I am assuming that word can be broken.
 * Imagine a method getWidth(char c, int fontSize) and
 * getHeight(int fontSize) are given.
 *
 * Approach
 *
 * 1) Do the binary search to find the max font size
 * 2) For each max font size found, check if the string can fit the screen.
 * 3) If it fits, then go to right to find max font size
 * 4) If it don't fit then go to left to find the font size it fit.
 *
 * Time Complexity = O(nlog(fontSize))
 * where n = length of string
 * fontSize is the maxFontSize, if not given assume Integer.MAX_VALUE
 *
 * Space Complexity: O(1)
 *
 * resources/MaxFontSizeFitScreen1.jpg
 * resources/MaxFontSizeFitScreen2.jpg
 * resources/MaxFontSizeFitScreen3.jpg
 */
public class MaxFontSizeFitScreen {

    public static int getWidth(char c, int fontSize) {
        return new Random().nextInt();
    }

    public static int getHeight(int fontSize) {
        return new Random().nextInt();
    }

    //Screen Width and height
    public static int W = 23;
    public static int H = 40;

    public static int getMaxFont(String s) {
        int maxFont = 0;

        int startFont = 0;
        int endFont = Integer.MAX_VALUE;

        //Binary search
        while(startFont <= endFont) {
            int midFont = startFont + (endFont - startFont)/2;

            if(canFit(midFont, s)) {

                //Go right to find max
                startFont = midFont + 1;
                maxFont = midFont;
            } else {
                //Go left
                endFont = midFont - 1;
            }
        }
        return maxFont;
    }

    public static boolean canFit(int midFont, String s) {

        if(s == null || s.isEmpty()) {
            return true;
        }

        int rows = H/getHeight(midFont);
        int i = 0;

        for(int r = 0; r < rows; r++) {

            int remainingWidth = W;

            while(i < s.length()) {
                char ch = s.charAt(i);
                int width = getWidth(ch, midFont);
                if(remainingWidth >= width) {
                    remainingWidth -= width;
                    i++;
                } else {
                    break; //Start the next row
                }
            }
        }

        return i == s.length();
    }

    public static void main(String[] args) {
        System.out.println(getMaxFont("This string fit the screen"));
    }
}
