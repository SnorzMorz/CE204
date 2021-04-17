package linear;

/**
 *  A class implementing the Queue ADT to provide a queue of strings. This
 *  implementation stores the data in objects of the Queue.Item inner
 *  class. Each of these objects stores a string and a reference to the
 *  object containing the next string in the queue.
 */
public class Queue implements QueueADT {

    private class Item {
        String value;
        Item next;

        /*
         *  The only time we create Items is to put them on the end of the
         *  queue so, at creation, next will always be null.
         */
        Item (String value) {
            this.value = value;
            this.next = null;
        }
    }

    /*
     *  We remove strings from the front of the queue and add them at the
     *  back, so we need to maintain references to these positions. If the
     *  queue is empty, we will have front = back = null; if it has one
     *  item, both front and back will refer to that.
     */
    private Item front = null;
    private Item back = null;
    private int size = 0;

    /**
     *  add() places a new string in the queue, behind the string that is
     *  currently the back. If the queue is empty, the new item will be
     *  both first and last in the queue. Otherwise, the new item will be
     *  the next item after the current back.
     */
    public void add (String s) {
        if (isEmpty()) {
            front = back = new Item(s);
        } else {
            back.next = new Item(s);
            back = back.next;
        }
        size++;
    }

    /**
     *  remove() removes the first string from the queue and returns it; if
     *  the queue is empty, it returns null. (Again, in production code, you
     *  would probably want to throw an exception.)
     */
    public String remove () {
        if (isEmpty())
            return null;

        String s = front.value;  /* Remember the first string.               */
        front = front.next;      /* The next item becomes the front.         */
        if (front == null)       /* If front is now null, the queue is empty */
            back = null;         /*     so back needs to be null, too.       */
        size--;
        return s;
    }

    public boolean isEmpty () { return front == null; }
    public int length ()      { return size;         }
}
