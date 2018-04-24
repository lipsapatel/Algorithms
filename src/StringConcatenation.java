/**
 * String concatenation Example
 */
public class StringConcatenation {

    private static String process(String text) {

        text = text.concat("def");
        return text;
    }

    public static void main(String[] args) {
        String text = "abc";

        System.out.println(process(text)); //abcdef
        System.out.println(text); //abc
    }
}
