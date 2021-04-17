package labs.lab5;

import java.util.*;
import sorting.Util;

/***********************************************************************
 *  Lab 5, exercise 1                                                  *
 **********************************************************************/

public class Mergesort {
    /*
     *  Mergesort for arrays. The overall structure is the same as the
     *  lists.
     */
    public static void sort (int[] ints) {
        /*
         *  If the array has zero or one items, it's already sorted.
         *  This is the base case of our recursion.
         */
        if (ints.length <= 1)
            return;

        /*
         *  Place the first half of the array in 'one' and the rest
         *  in 'two'.
         */
        int[] one = Arrays.copyOf (ints, ints.length/2);
        int[] two = Arrays.copyOfRange (ints, one.length, ints.length);

        /*
         *  Sort the subarrays recursively.
         */
        sort (one);
        sort (two);

        /*
         *  Merge the subarrays back into the original array. We
         *  maintain a current index in the two subarrays and the
         *  original array. Until we reach the end of a subarray, we
         *  copy the smallest item to the output; once one of the sub-
         *  arrays is exhausted, we copy the remainder of the other
         *  subarray all at once.
         */
        int onePos = 0;
        int twoPos = 0;
        int outPos = 0;

        while (onePos < one.length && twoPos < two.length) {
            if (one[onePos] < two[twoPos])
                ints[outPos++] = one[onePos++];
            else
                ints[outPos++] = two[twoPos++];
        }
        if (onePos < one.length)
            System.arraycopy (one, onePos, ints, outPos, one.length-onePos);
        else if (twoPos < two.length)
            System.arraycopy (two, twoPos, ints, outPos, two.length-twoPos);
    }

    /*
     *  Test code to generate an array of 20 random ints and sort them.
     *  Uses the Util class from lecture 5.
     */
    public static void main (String[] args) {
        int[] ints = Util.randomInts(20,100);

        System.out.println(Arrays.toString (ints));
        sort (ints);
        System.out.println(Arrays.toString (ints));
    }
}
