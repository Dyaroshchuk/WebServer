package utils;

import java.util.Random;

public class RandomHelper {

    public static String getRandomCode() {
        Random r = new Random();
        return String.valueOf(r.nextInt(9000) + 1000);
    }
}
