package labs.lab5;

import linear.DoublyLinkedList;
import sorting.Util;
import java.util.*;

/***********************************************************************
 *  Lab 5, exercise 2                                                  *
 ***********************************************************************
 *  This code uncovered a bug in DoublyLinkedList.insert(). The correct-
 *  ed version is available for download in Unit 2. The change is to
 *  line 33: the correct version uses getItem (index-1), not
 *  getItem(index).
 */

public class InsertionSort {
    /*
     *  Insertion sort for lists. This closely follows the description
     *  in the lecture slides: we build up the sorted list item-by-
     *  item, with each one inserted into the correct place in the
     *  list built so far.
     */
    public static DoublyLinkedList sort (DoublyLinkedList list) {
        DoublyLinkedList output = new DoublyLinkedList();

        list.rewind();

        while (list.hasNext()) {
            String s = list.getNext();

            output.rewind();
            int pos = 0;
            while (true) {
                String next = output.getNext();
                if (next != null && s.compareTo (next) >= 0)
                    pos++;
                else
                    break;
            }
            output.insert (pos, s);
        }
        return output;
    }

    /*
     *  Insertion sort for arrays. For each element of the array, start-
     *  ing at index 1, we repeatedly swap it with elements to its left
     *  until we find the right place for it. Instead of doing literal
     *  swaps, we do something a little more efficient. We remember the
     *  item we're moving (in 'cur') and we move elements to the right
     *  and put 'cur' in the correct place once we find it.
     *
     *  Note that we could use binary search to find the correct posit-
     *  ion for 'cur' in the sorted part of the array in time O(log n).
     *  However, the only way to insert 'cur' into the correct position
     *  would be to move the items greater than it one cell to the
     *  right, and that still takes time Theta(n), so we probably
     *  wouldn't gain anything.
     */
    public static void sort (int[] ints) {
        for (int i = 1; i < ints.length; i++) {
//            System.out.println(Arrays.toString(ints));
            int cur = ints[i];
            int j = i;

            while (j > 0 && cur < ints[j-1]) {
                ints[j] = ints[j-1];
                j--;
            }
            ints[j] = cur;
        }
    }

    /*
     *  Helper method to print DoublyLinkedLists.
     */
    public static void printList (DoublyLinkedList list) {
        list.rewind();
        while (list.hasNext())
            System.out.print(list.getNext() + " ");
        System.out.println();
    }

    /*
     *  main() method to test the routines.
     */
    public static void main (String[] args) {
        String[] words = {"Attlee", "Churchill", "Eden", "MacMillan", "Douglas-Home",
                          "Wilson", "Heath", "Wilson", "Callaghan", "Thatcher", "Major",
                          "Blair", "Brown", "Cameron", "May", "Johnson"};

        DoublyLinkedList list = new DoublyLinkedList();
        for (String s : words)
            list.addToTail (s);

        printList (list);

        DoublyLinkedList sortedList = sort (list);
        sortedList.rewind();

        printList (sortedList);
        System.out.println("");


        int[] ints = Util.randomInts(20,100);
        System.out.println(Arrays.toString (ints));
        sort (ints);
        System.out.println(Arrays.toString (ints));
    }
}
