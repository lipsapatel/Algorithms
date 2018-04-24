/**
 * String Builder Example
 */
public class StringBuilderExample {

    private static StringBuilder process(StringBuilder text) {

        text = text.append("def");
        return text;
    }

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("abc");

        System.out.println(process(text)); //abcdef
        System.out.println(text); //abcdef
    }
}
