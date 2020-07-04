package Java.Lambda;

interface MyInterface1 {
    //A method with single parameter
    public int incrementByFive(int a);
}

public class LambdaExpressionWithSingleParameter {

    public static void main(String[] args) {
        MyInterface1 myInterface1 = num -> num + 5;

        System.out.println(myInterface1.incrementByFive(22));
    }
}
