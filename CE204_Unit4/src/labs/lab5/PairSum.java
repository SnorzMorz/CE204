package labs.lab5;

import sorting.Util;

import java.util.*;

/***********************************************************************
 *  Lab 5, exercise 4                                                  *
 **********************************************************************/

public class PairSum {

    /*******************************************************************
     *  Exercise 4a                                                    *
     *******************************************************************
     *  The simplest way is to consider all possible pairs of elements
     *  of the array to see if any has the sum we're looking for. This
     *  takes time O(n^2), since there are n(n-1)/2 pairs to consider,
     *  in the worst case.
     */
     public static boolean pairSumA (int[] ints, int target) {
         for (int i = 0; i < ints.length; i++)
             for (int j = 0; j < i; j++)
                 if (ints[i] + ints[j] == target)
                     return true;

         return false;
     }

    /*******************************************************************
     *  Exercise 4b                                                    *
     *******************************************************************
     *  Here is a more efficient algorithm. Since we're trying to beat
     *  n^2, we can take time O(n log n) to sort the array and this will
     *  result in an asymptotically faster algorithm, as long as we can
     *  solve the problem on sorted arrays in time better than O(n^2).
     *
     *  In this case, we can solve the problem faster on sorted arrays.
     *  For each integer x in the array, we can use binary search to see
     *  if target-x is also in the array. This involves doing n binary
     *  searches, so takes time O(n log n). We optimize this by consid-
     *  ering only values x<=target/2, which means that target-x >= x,
     *  so we do the binary search on elements greater than x. This
     *  doesn't improve the asymptotic running time but it does reduce
     *  the constant factors.
     */
    public static boolean pairSumB (int[] ints, int target) {
        int[] intsSorted = Arrays.copyOf (ints, ints.length);
        Mergesort.sort (intsSorted);

        for (int i = 0; i < intsSorted.length; i++) {
            if (intsSorted[i] > target/2)
                return false;
            if (Arrays.binarySearch (intsSorted, i+1,
                    intsSorted.length, target-intsSorted[i]) >= 0)
                return true;
        }
        return false;
    }

    /*
     *  Here is another method. Again, we sort the list but now we
     *  use an algorithm on the sorted list that runs in time Theta(n).
     *  This gives a total running time of O(n log n), because of the
     *  sorting; again, we're improving constant factors over method B.
     *
     *  We consider elements i and j of the sorted array A, initially
     *  with i = 0 and j = n-1. If A[0] + A[n-1] = target, we're done.
     *  If A[0] + A[n-1] < target then we know that A[0] + A[something]
     *  can never be the answer, as A[0] plus the biggest number in the
     *  array is too small. Therefore, we set i = i+1. Similarly, if
     *  A[0] + A[n-1] is too big, the answer cannot be A[something] +
     *  A[n-1], so we set j = j-1. We keep increasing i and/or decreas-
     *  ing j until either we find the answer or we've searched the
     *  whole array.
     */
    public static boolean pairSumC (int[] ints, int target) {
        int[] A = Arrays.copyOf (ints, ints.length);
        Mergesort.sort (A);

        int i = 0;
        int j = A.length-1;
        while (i < j) {
            int sum = A[i] + A[j];
            if (sum == target)
                return true;
            else if (sum > target)
                j--;
            else
                i++;
        }
        return false;
    }

    /*
     *  As with exercise 2, there is another possible solution using
     *  a hash map. As you see each element x of the array, put it in
     *  the hash map and ask if the hash map already contains
     *  target-x. As explained in exercise 2, this runs in time
     *  proportional to n^2 in the worst case but, in practical terms,
     *  is very likely to run in time O(n).
     */

    /*
     *  main() method to test the methods above. We have to be careful
     *  here, as method A can look very fast with certain inputs. My
     *  initial test was to create an array of length n, containing
     *  random integers in the range 0 to n-1. I then selected a random
     *  target in the range 0 to 2n-1, and method A was so fast that I
     *  I had a bug in the code and it wasn't even checking the answer.
     *  The reason that method A appeared fast is that the array will
     *  have contained most of the numbers from 0 to n-1. Half of the
     *  time, the target will have also been in the range 0 to n-1 and,
     *  in that case, there will nearly always be a solution of the
     *  form A[0] + A[j], which is very quick to find. Even for targets
     *  up to 1.5*n, there's a 50/50 chance that A[0]>=n/2 so there's
     *  still very likely to be a solution of the form A[0] + A[j].
     *
     *  To get a better test, I've set the target to be a random number
     *  in the range 0 to 3n. This means that, at least one third of
     *  the time, there is no solution, and method A has to search the
     *  whole array, which makes it much slower than the other methods.
     *  However, this practical testing does suggest that, if you were
     *  in a situation where you had good reason to believe that most
     *  targets were achievable, it might be better to use the simple
     *   O(n^2) method.
     */
    public static void main (String[] args) {
        final int NUMBER_OF_RUNS = 1_000;
        final int ARRAY_SIZE = 10_000;
        Random rand = new Random ();

        long startTime, endTime;

        /*
         *  Set up some random arrays and targets.
         */
        int[][] ints2d = new int[NUMBER_OF_RUNS][];
        for (int i = 0; i < ints2d.length; i++)
            ints2d[i] = Util.randomInts(ARRAY_SIZE, ARRAY_SIZE);
        int[] target = Util.randomInts (ARRAY_SIZE, 3*ARRAY_SIZE);

        /*
         *  Test the three methods.
         */
        int count = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < ints2d.length; i++)
            if (pairSumA (ints2d[i], target[i]))
                count++;
        endTime = System.currentTimeMillis();
        System.out.println("Method A: " + (endTime-startTime) + "ms (" + count + " hits)");

        count = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < ints2d.length; i++)
            if (pairSumB (ints2d[i], target[i]))
                count++;
        endTime = System.currentTimeMillis();
        System.out.println("Method B: " + (endTime-startTime) + "ms (" + count + " hits)");

        count = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < ints2d.length; i++)
            if (pairSumC (ints2d[i], target[i]))
                count++;
        endTime = System.currentTimeMillis();
        System.out.println("Method C: " + (endTime-startTime) + "ms (" + count + " hits)");

    }
}
