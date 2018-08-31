/**
 * Determine if given year is Leap year
 *
 * Given a year, write a java program to find whether year is leap year or not
 *
 * Leap Year: A year usually occurring once every four years, that has 366 days including February 29 as an intercalary day is
 * called Leap year.
 *
 * Leap year is divisible by 4 and if it's divisible by 100 then it has to be divisible by 400 in order to be a leap year.
 *
 * Example:
 * Year: 2004, 2008, 2012 are leap years
 * Year: 1993, 2001, 2003, 1000 are not leap years
 *
 * Approach
 *
 * Pseudo code:
 *
 * If (year is not divisible by 4) then
 *  (its not a leap year)
 * else if (year is not divisible by 100) then it's a leap year
 * else if (year is not divisible by 400 then it's not a leap year
 * else it is a leap year
 */
public class FindLeapYear {

    private static void isLeapYear(int year) {

        if (year <= 0) {
            System.out.println("Invalid year entry");
            return;
        }

        //Check for leap year
        boolean isLeap;

        if (year % 4 == 0) {

            if (year % 100 == 0) {

                if (year % 400 == 0) {
                    isLeap = true;
                } else {
                    isLeap = false;
                }
            } else {
                isLeap = true;
            }
        } else {
            isLeap = false;
        }

        if (isLeap) {
            System.out.println("Given year " + year + " is a leap year");
        } else {
            System.out.println("Given year " + year + " is not a leap year, its a common year");
        }
    }

    public static void main(String[] args) {

        isLeapYear(1992);
        isLeapYear(2000);
        isLeapYear(1000);
        isLeapYear(2001);
        isLeapYear(2002);
        isLeapYear(2004);
    }
}
