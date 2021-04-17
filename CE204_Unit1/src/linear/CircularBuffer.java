package linear;

/**
 *  Circular buffers are a specialized implementation of queues used in, e.g.,
 *  network switches. The queue is a fixed size, which means that we can't add
 *  new items if it is full. In compensation for this, both add and remove are
 *  very fast.
 */
public class CircularBuffer {
    /*
     *  Data items (here, strings) are stored in an array, whose size is chosen
     *  when the buffer is created.
     */
    private String[] items;
    private int front;      /* Array index containing front item.           */
    private int back;       /* Array index where next "add" will be placed. */
    private int length;     /* Current size of the queue.                   */

    /**
     *  Create a queue of the specified size. The size is fixed, so we allow
     *  the user to choose it.
     */
    public CircularBuffer (int size) {
        items = new String[size];
        front = back = length = 0;
    }

    /**
     *  If there is space in the buffer, adds the string s and returns true; if
     *  the buffer is full, returns false and does not alter the buffer.
     */
    public boolean add (String s) {
        if (isFull())
            return false;

        /*
         *  When we add a string, we use the remainder operator so the "back"
         *  index will cycle around to the start of the array when necessary.
         *  For example, if the array has six entries (0-5) and we call add,
         *  when back == 5 the next call to add() will insert at position
         *  (5+1)%6 == 0, as required.
         */
        items[back] = s;
        back = (back + 1) % items.length;
        length++;
        return true;
    }

    /**
     *  Returns null if the buffer is empty (as usual, consider throwing an
     *  exception here); if the buffer is non-empty returns the string at the
     *  front of the queue and removes it. Operation is similar to add().
     */
    public String remove () {
        if (isEmpty())
            return null;

        String s = items[front];
        front = (front + 1) % items.length;
        length--;
        return s;
    }

    public boolean isFull() {
        return length == items.length;
    }

    public boolean isEmpty() {
        return length == 0;
    }
}
