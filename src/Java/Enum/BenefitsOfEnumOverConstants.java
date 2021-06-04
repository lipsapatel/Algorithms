package Java.Enum;

/**
 * Both enum and constants in usage
 * This method shows the benefit of using Enum over Constants
 */
public class BenefitsOfEnumOverConstants {

    public static void main(String[] args) {

        //Enum values are fixed
        simpleEnumExample(ThreadStates.START);
        simpleEnumExample(ThreadStates.WAITING);
        simpleEnumExample(ThreadStates.RUNNING);
        simpleEnumExample(ThreadStates.DEAD);
        simpleEnumExample(null);

        simpleConstantsExample(1);
        simpleConstantsExample(2);
        simpleConstantsExample(3);
        simpleConstantsExample(4);
        //We can pass any int constant
        simpleConstantsExample(5);

        String detail = ThreadStatesEnum.START.getDetail();
        System.out.println("Thread State Enum details: " + detail);
    }

    private static void simpleEnumExample(ThreadStates threadStates) {

        if (threadStates == ThreadStates.START) {
            System.out.println("Thread started");
        } else if (threadStates == ThreadStates.WAITING) {
            System.out.println("Thread is waiting");
        } else if (threadStates == ThreadStates.RUNNING) {
            System.out.println("Thread is running");
        } else {
            System.out.println("Thread is dead");
        }
    }

    private static void simpleConstantsExample(int i) {

        if (i == ThreadStatesConstant.START) {
            System.out.println("Thread started");
        } else if (i == ThreadStatesConstant.WAITING) {
            System.out.println("Thread is waiting");
        } else if (i == ThreadStatesConstant.RUNNING) {
            System.out.println("Thread is running");
        } else {
            System.out.println("Thread is dead");
        }
    }
}
