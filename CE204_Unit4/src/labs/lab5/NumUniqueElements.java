package labs.lab5;

import java.util.*;
import trees.*;
import sorting.Util;

/***********************************************************************
 *  Lab 5, exercise 3                                                  *
 ***********************************************************************
 *  Many problems on arrays require us to consider all pairs of
 *  elements in the array. Since there are Theta(n^2) pairs of elements
 *  This will take time Omega(n^2) (i.e., at least time proportional to
 *  n^2) if we solve the problem by looking at all pairs. Often, we can
 *  solve the problem faster on a sorted array. Since we can sort the
 *  array in time Theta(n log n), this makes it faster overall to sort
 *  the array and work with that.
 *
 *  For example, one way to count the unique elements of an array is
 *  to look at each element in turn and increase a counter if the cur-
 *  rent element is different from all the previous ones. This looks at
 *  every pair of elements, so runs in time Theta(n^2). However, if the
 *  list is sorted, we just need to check if each element is different
 *  from the previous one. That takes time Theta(n), so the time taken
 *  to sort and then compare is Theta(n log n + n) = Theta(n log n),
 *  which is significantly faster than Theta(n^2), even for non-huge
 *  inputs.
 *
 *  There is a cost to using these techniques, though. Because we're
 *  sorting the array, we must either accept that the array will change
 *  or work with a copy of the array, which uses more memory.
 *
 *  If you're familiar with hash maps, you might be asking, "Why don't
 *  we just use a hash map and do the whole thing in time O(n)?" In
 *  more detail, this solution is to try to insert each element of the
 *  array into a hash map and increase a counter each time you have an
 *  element that wasn't already in the hash map. (See my binary search
 *  tree solution for a similar example.) Practically speaking, the
 *  hash map approach will run in time O(n) and is probably the one
 *  you should use. However, in the worst case, it can run in time
 *  Theta(n^2). This occurs if a large fraction of the array maps into
 *  the same entry in the hash table (i.e., there are a lot of "collis-
 *  ions"). In this case, finding elements in a hash map degenerates
 *  into finding them in a linked list or an unsorted array (depending
 *  on implementation), which is slow. In reality, this is extremely
 *  unlikely to happen, as long as your hash map has a decent amount
 *  of storage available to it, but it is a theoretical possibility.
 */
public class NumUniqueElements {
    /*
     *  Here is the method I described above: sort (a copy of) the
     *  array and count how many elements are different from the prev-
     *  ious.
     */
    public static int count (int[] ints) {
        /*
         *  Take a copy of the array and mergesort it. We take a copy
         *  because it might be important not to change the original
         *  array. Suppose it's a list of students' marks, for example.
         */
        int[] mycopy = Arrays.copyOf (ints, ints.length);
        Mergesort.sort (mycopy);

        /*
         *  The first element of the array is the first "new" value we
         *  see. Thereafter, each value in the array is "new" if it's
         *  different from the previous one.
         */
        int count = 1;
        for (int i = 1; i < mycopy.length; i++)
            if (mycopy[i] != mycopy[i-1])
                count++;

        return count;
    }

    /*
     *  Here's an alternative solution, which is Theta(n log n) for
     *  typical inputs. Instead of sorting the array, we maintain a
     *  binary search tree. For each array element in turn, we
     *  check if the element is in the array
     */
    public static int countWithBST (int[] ints) {
        BinarySearchTree bst = new BinarySearchTree();
        int count = 0;

        for (int i = 0; i < ints.length; i++) {
            if (!bst.contains(ints[i])) {
                bst.insert (ints[i]);
                count++;
            }
        }
        return count;
    }

    /*
     *  And here's the slow version, for comparison. For each
     *  entry of the array, we check all earlier entries to see
     *  if it's a value that's not been seen before; if so, we
     *  increase the count. This looks at n(n-1)/2 pairs, so has
     *  running time Theta(*)n^2).
     */
    public static int countSlowly (int[] ints) {
        int count = 0;
        for (int i = 0; i < ints.length; i++) {
            boolean newValue = true;
            for (int j = 0; j < i; j++)
                if (ints[j] == ints[i]) {
                    newValue = false;
                    break;
                }
            if (newValue)
                count++;
        }
        return count;
    }

    public static void main (String[] args) {
        /*
         *  Code to test the routines by generating an array of 20
         *  ints and computing the number of unique values. It prints
         *  the sorted array so you can check that the result is
         *  correct.
         */
//        int[] ints = Util.randomInts(20,100);
//        System.out.println("Array is: " + Arrays.toString (ints));
//        System.out.println("Array contains " + countSlowly (ints) + " unique values.");
//
//        Mergesort.sort(ints);
//        System.out.println("Sorted array: " + Arrays.toString (ints));

        /*
         *  Code to measure performance by running each method
         *  1000 times on arrays of length 20000.  On my computer,
         *  the results are:
         *      count() takes about 2300ms
         *      countWithBST is about 2500ms
         *      countSlowly is about 37000ms
         *
         *  For arrays of length 20000, we should expect that
         *  count and countWithBST will take a little more than
         *  twice as long, whereas countSlowly should take about
         *  four times as long. The actual results are:
         *
         */
        final int NUMBER_OF_RUNS = 1_000;
        final int ARRAY_SIZE = 20_000;
        long startTime, endTime;

        /*
         *  Set up some random arrays.
         */
        int[][] ints2d = new int[NUMBER_OF_RUNS][];
        for (int i = 0; i < ints2d.length; i++)
            ints2d[i] = Util.randomInts(ARRAY_SIZE,ARRAY_SIZE);


        startTime = System.currentTimeMillis();
        for (int i = 0; i < ints2d.length; i++)
            count (ints2d[i]);
        endTime = System.currentTimeMillis();
        System.out.println("count time:        " + (endTime-startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < ints2d.length; i++)
            countWithBST (ints2d[i]);
        endTime = System.currentTimeMillis();
        System.out.println("countWithBST time: " + (endTime-startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < ints2d.length; i++)
            countSlowly (ints2d[i]);
        endTime = System.currentTimeMillis();
        System.out.println("countSlowly time:  " + (endTime-startTime) + "ms");
    }
}
