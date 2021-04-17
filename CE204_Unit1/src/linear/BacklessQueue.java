package linear;

/**
 *  This is a reimplementation of the Queue ADT. It is mostly the same as
 *  Queue.java except that, instead of storing a reference to the back of
 *  the queue, it uses the method getBack() to find the back by traversing
 *  the queue from the front. This is much more expensive, because you have
 *  to search through the whole queue on every add() operation; however, it
 *  does save (a tiny amount of) memory and it does make the add() and
 *  remove() methods simpler, as they no longer have to maintain the refer-
 *  ence to the back of the queue.
 */
public class BacklessQueue implements QueueADT{
    private class Item {
        String value;
        Item next;

        Item (String value) {
            this.value = value;
            this.next = null;
        }
    }

    private Item front = null;
    private int size = 0;

    /*
     *  add() no longer has to update the reference to the back of the queue.
     *  If the queue is empty, the new item is the front; otherwise, the new
     *  item becomes the last item's successor.
     */
    public void add (String s) {
        if (isEmpty())
            front = new Item(s);
        else
            getBack().next = new Item(s);
        size++;
    }

    /*
     *  remove() no longer needs a special case to deal with the case where
     *  we're removing the only item in the queue.
     */
    public String remove () {
        if (isEmpty())
            return null;

        String s = front.value;
        front = front.next;
        size--;
        return s;
    }

    public boolean isEmpty () { return front == null; }
    public int length ()      { return size;         }

    /**
     *  getBack() finds the back of the queue by starting at the front and
     *  following "next" references until it reaches an item that has no
     *  next item. That item must be the back of the queue.
     */
    private Item getBack () {
        if (isEmpty())
            return null;

        Item cur = front;
        while (cur.next != null)
            cur = cur.next;
        return cur;
    }
}
