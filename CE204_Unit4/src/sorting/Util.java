package sorting;

import java.util.*;

public class Util {
    static void swap (int[] ints, int index1, int index2) {
        int temp = ints[index1];
        ints[index1] = ints[index2];
        ints[index2] = temp;
    }

    public static int[] randomInts (int n, int range) {
        Random rand = new Random();
        int[] ints = new int[n];
        for (int i = 0; i < ints.length; i++)
            ints[i] = rand.nextInt (range);
        return ints;
    }
}
