package sorting;

import linear.*;

public class Mergesort {
    /*
     *  Method to mergesort a linked list. It's not necessary to use a
     *  doubly linked list -- singly linked will work fine. I just used
     *  the doubly linked list class from earlier in the course because
     *  it was available. The method is static so you don't have to do
     *  "Mergesort ms = new Mergesort()" before your can sort anything.
     */
    public static void sort (DoublyLinkedList list) {
        /*
         *  The base-case of the recursion. If the input list has one
         *  element, or none at all, it is already sorted, so there is
         *  nothing to do. (Note that recursive calls will always be on
         *  lists of length at least one, but somebody might ask us to
         *  sort a list of length zero.)
         */
        if (list.length() <= 1)
            return;

        DoublyLinkedList one = new DoublyLinkedList();
        DoublyLinkedList two = new DoublyLinkedList();

        /*
         *  Split the list into sublists one and two. We just keep
         *  taking the first element off the input list and adding to
         *  the two sublists in turn.
         */
        while (!list.isEmpty()) {
            one.addToTail(extractHead (list));
            if (!list.isEmpty())
                two.addToTail(extractHead (list));
        }

        /*
         *  Recursively sort the sublists. Our input list had length
         *  at least two, so each sublist has at least one item.
         */
        sort (one);
        sort (two);

        /*
         *  Merge the sublists. Until both lists are empty, we keep
         *  taking the smaller of their two heads and appending to the
         *  sorted output. If either list is empty, we append the other
         *  one, item by item. Otherwise, both lists have a head and
         *  we append the head that has the smaller value.
         */
        while (!(one.isEmpty() && two.isEmpty())) {
            if (two.isEmpty())
                list.addToTail (extractHead (one));
            else if (one.isEmpty())
                list.addToTail (extractHead (two));
            else if (one.get(0).compareTo(two.get(0)) < 0)
                list.addToTail (extractHead (one));
            else
                list.addToTail (extractHead (two));
        }
    }

    /*
     *  extractHead() is a helper method that returns the head of the list
     *  and deletes it.
     */
    private static String extractHead (DoublyLinkedList list) {
        String s = list.get (0);
        list.delete (0);
        return s;
    }

    /*
     *  Test code that creates a list, prints it, sorts it and prints it
     *  again. Printing is done using the iterator methods.
     */
    public static void main (String[] args) {
        String[] words = {"rat", "ox", "tiger", "rabbit", "dragon", "snake",
                          "horse", "goat", "monkey", "rooster", "dog", "pig"};

        DoublyLinkedList list = new DoublyLinkedList();
        for (String s : words)
            list.addToTail (s);
        while (list.hasNext())
            System.out.print(list.getNext() + " ");
        System.out.println();

        sort (list);
        list.rewind();

        while (list.hasNext())
            System.out.print(list.getNext() + " ");
        System.out.println();
    }
}