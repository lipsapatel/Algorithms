package Java.Lambda;

interface MyInterface {

    //A method with no parameter
    public String sayHello();
}

public class LambdaExpressionWithNoParameter {

    public static void main(String[] args) {

        MyInterface myInterface = () -> "hello";

        System.out.println(myInterface.sayHello());
    }
}
