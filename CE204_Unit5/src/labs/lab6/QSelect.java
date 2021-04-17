package labs.lab6;

import sorting.*;

import java.util.Arrays;

/***********************************************************************
 *  Lab 6, exercise 2                                                  *
 **********************************************************************/

public class QSelect {
    /*
     *  Method to find the target-th smallest value in the array ints.
     *  This could be done in time Theta(n log n) by sorting the array
     *  and returning ints[target-1]. However, we can do it in time O(n),
     *  as in the helper method.
     */
    public static int qSelect (int[] ints, int target) {
        return qSelect (ints, 0, ints.length-1, target-1);
    }

    /*
     *  Recursive helper method. We proceed as in quicksort but we only
     *  recurse on the partition of the array that includes the element
     *  we're looking for. Elements in the other part of the array are
     *  either too big or too small to be the answer, so we don't care
     *  what order they actually occur. Therefore, we don't need to sort
     *  that part.
     */
    private static int qSelect (int[] ints, int left, int right, int target) {
        /*
         *  If we've got down to a single element, return it.
         */
        if (right - left < 1) return ints[target];

        /*
         *  The loop here is identical to quicksort.
         */
        int i = left-1;
        int j = right+1;
        int pivot = (ints[left] + ints[right])/2;

        while (i<j) {
            do { i++; } while (ints[i] < pivot);
            do { j--; } while (ints[j] > pivot);
            if (i < j) Util.swap (ints, i, j);
        }

        /*
         *  Recurse on the partition that contains the target.
         */
        if (target <= j)
            return qSelect (ints, left, j, target);
        else
            return qSelect (ints, j+1, right, target);
    }

    /*
     *  Test code.
     */
    public static void main (String[] args) {
        int[] ints = Util.randomInts(20, 100);

        System.out.println(Arrays.toString(ints));
        System.out.println(qSelect(ints, 4));
    }
}