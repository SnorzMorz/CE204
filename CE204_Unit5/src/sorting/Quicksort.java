package sorting;

import java.util.Arrays;

public class Quicksort {
    /*
     *  The main sorting routine calls a helper method on the whole
     *  array.
     */
    public static void sort (int[] ints) {
        qSort (ints, 0, ints.length-1);
    }

    /*
     *  The helper routine sorts the section of the array between
     *  ints[left] and ints[right] inclusive. It uses the mean of
     *  the first and last elements of the region as a pivot.
     *  Explained in lecture 6.
     */
    private static void qSort (int[] ints, int left, int right) {
        if (right - left < 1) return;

        int i = left-1;
        int j = right+1;
        int pivot = (ints[left] + ints[right])/2;

        while (i<j) {
            do { i++; } while (ints[i] < pivot);
            do { j--; } while (ints[j] > pivot);
            if (i < j) Util.swap (ints, i, j);
        }
        qSort (ints, left, j);
        qSort (ints, j+1, right);
    }

    /*
     *  Test code.
     */
    public static void main (String[] args) {
        int[] ints = Util.randomInts(20,100);

        System.out.println(Arrays.toString (ints));
        sort (ints);
        System.out.println(Arrays.toString (ints));

        for (int i = 1; i < ints.length; i++)
            if (ints[i-1] > ints[i]) {
                System.out.println("Error: ints["+(i-1)+"] > ints[" + i + "].");
            }
    }
}
