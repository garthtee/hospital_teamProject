package core;


/**
 * Created by Garth Toland on 17/04/2016.
 * Description: Validates a time.
 */
public class TimeValidator {

    public static boolean validateTime(String time) {

        if (time.length() < 6) {
            char c0 = time.charAt(0);

            char c1 = time.charAt(1);
            char c2 = time.charAt(2);
            char c3 = time.charAt(3);
            char c4 = time.charAt(4);

            int i0 = Character.getNumericValue(c0);
            int i1 = Character.getNumericValue(c1);
            int i2 = Character.getNumericValue(c2);
            int i3 = Character.getNumericValue(c3);
            int i4 = Character.getNumericValue(c4);

            try {
                if (!isCorrectFormat(time.charAt(0), time.charAt(1), time.charAt(2), time.charAt(3), time.charAt(4))) {
                    return false;
                } else if (i0 < 0 || i0 > 2) {
                    return false;
                } else if (i0 == 2 && i1 > 3) {
                    return false;
                } else if (time.charAt(2) != ':') {
                    return false;
                } else if (i3 > 5) {
                    return false;
                } else
                    return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

    }

    private static boolean isCorrectFormat(char c0, char c1, char c2, char c3, char c4) {

        if (c2 != ':') {
            return false;
        }
        try {
            Integer.parseInt(String.valueOf(c0));
            Integer.parseInt(String.valueOf(c1));
            Integer.parseInt(String.valueOf(c3));
            Integer.parseInt(String.valueOf(c4));
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
