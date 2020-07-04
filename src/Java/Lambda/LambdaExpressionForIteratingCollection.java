package Java.Lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpressionForIteratingCollection {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Lipsa");
        list.add("apple");

        list.forEach(
                names -> System.out.println(names)
        );
    }
}
