/**
 * Clock Angle Problem
 *
 * Find the Angle between hour hand and minute hand at the given time.
 *
 * Example:
 * Time: 12:45
 * Input: hour = 12 and minute = 45
 *
 * 360 degree angle for hour hand = 12 hours
 * 360/12 = 30 degrees in 1 hours
 * 1 hour has 60 min = 30/60 = 0.5 degree in 1 min
 *
 * 360 degree angle for minute hand = 60 minutes
 * 360/60 = 6 degree in 1 minute
 *
 * (h * 60 + min) * 0.5
 * (12 * 60 + 45) = 765 * 0.5 = 382.5 degree
 *
 * (m * 6) = 45 * 6 = 270
 *
 * Angle between hour and minute hand = Math.abs(382.5 - 270) = 112.5
 *
 * min (360 - 112.5, 112.5) = 112.5
 *
 * Time: 3.30
 *
 * (3 * 60 + 30) = 210 * 0.5 = 105 degree hour hand
 *
 * (30 * 6) = 180 degree for minute hand
 *
 * difference is 75 and that's the minimum
 *
 * Approach:
 *
 * 1) Angle between hour and minute hand = angle of hour hand - angle of minute hand
 * 2) return minimum (360 - angle, angle)
 * 3) hour hand moves 12 hours and each hour has 60 minutes so 360/720 = 0.5 degree in 1 min
 *    (h * 60 + m) * 0.5
 * 4) Minute hand moves 60 minutes so 360/60 = 6 degree in 1 min
 *    m * 6
 */
public class AngleBetweenHourAndMinuteHand {

    private static double angleBetweenHourAndMinuteHand(int hour, int minute) {

        if (hour < 0 || minute < 0) {
            return -1;
        }

        if (hour == 12) {
            hour = 0;
        }

        if (minute == 60) {
            minute = 0;
            hour = hour + 1;
        }

        double hourAngle = (hour * 60 + minute) * 0.5;

        double minuteAngle = minute * 6;

        double angleBetweenHourAndMinuteHand = Math.abs(hourAngle - minuteAngle);

        return Math.min(360 - angleBetweenHourAndMinuteHand, angleBetweenHourAndMinuteHand);

    }

    public static void main(String[] args) {

        System.out.println("The angle between hour and minute hand is " + angleBetweenHourAndMinuteHand(12, 45));

        System.out.println("The angle between hour and minute hand is " + angleBetweenHourAndMinuteHand(3, 30));

        System.out.println("The angle between hour and minute hand is " + angleBetweenHourAndMinuteHand(2, 23));
    }
}
