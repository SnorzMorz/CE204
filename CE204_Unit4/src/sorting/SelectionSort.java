package sorting;

import java.util.*;

public class SelectionSort  {
    /*
     *  Method to sort an array of ints using selection sort. The method is
     *  static so you don't have to do "SelectionSort ss = new Selection-
     *  Sort() before you can sort anything.
     */

    public static void sort (int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            /*
             *  At this point, the first i elements of the array are sorted.
             *  Initially, i=0, corresponding to the whole array being
             *  unsorted.  First, we find the smallest element in the
             *  unsorted part of the array.
             */
            int minIndex = i;
            for (int j = i+1; j < ints.length; j++)
                if (ints[j] < ints[minIndex])
                    minIndex = j;

            /*
             *  If the smallest element of the unsorted part isn't the first
             *  one, swap with the first.
             */
            if (minIndex != i)
                Util.swap (ints, i, minIndex);
            /*
             *  Now, the  first i+1 elements of the array are sorted.
             */
        }
    }

    /*
     *  Test code to generate a random array of ints and sort it.
     */
    public static void main (String[] args) {
        int[] ints = Util.randomInts(20, 100);
        System.out.println(Arrays.toString (ints));
        sort (ints);
        System.out.println(Arrays.toString (ints));
    }
}
