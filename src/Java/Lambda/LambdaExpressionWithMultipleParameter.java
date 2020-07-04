package Java.Lambda;

interface MyInterface2 {
    public String strConcat(String a, String b);
}

public class LambdaExpressionWithMultipleParameter {
    public static void main(String[] args) {
        MyInterface2 myInterface2 = (str1, str2) -> str1 + str2;

        System.out.println(myInterface2.strConcat("Hello", "World"));
    }
}
