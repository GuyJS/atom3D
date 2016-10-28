package ui;

import java.util.Random;

public class RandomUtilities {

    private static Random random = new Random();

    public static double nextDouble(double max){
        return random.nextDouble() * max;
    }
}
