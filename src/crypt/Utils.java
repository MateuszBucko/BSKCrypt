package crypt;

/**
 * Created by X on 03.03.2017.
 */
public class Utils {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean nwdOne(int a, int b) {
        int nwd = 1;
        for (int i = 1; i <= a; i++) {
            if (a % i == 0 && b % i == 0) nwd = i;
        }
        if (nwd == 1) return true;
        return false;
    }
}
