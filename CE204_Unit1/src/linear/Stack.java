package linear;

/**
 *  A class implementing the stack ADT to provide a stack of strings. This
 *  implementation stores the data in an array, which initially has length
 *  10 and which is doubled every time the stack fills up.
 */
public class Stack implements StackADT {
    /*
     *  The entries of the stack and the number of items currently in the
     *  stack. Note that the fields are private, so the stack can only be
     *  accessed through the methods of the public interface. This prevents
     *  users from corrupting the data structure.
     */
    private String[] entries = new String[10];
    private int size = 0;

    /**
     *  push adds a string to the top of the stack.
     */
    public void push (String s) {
        /*
         *  Java arrays are indexed from zero so, if there are, e.g., five
         *  strings on the stack, they will be in positions 0-4. The next
         *  string to be added will go in position 5 and then the size will
         *  be 6.
         */
        entries[size] = s;
        size++;

        /*
         *  If the array is now full, we grow it so the next call to push()
         *  won't fail with an ArrayIndexOutOfBoundsException. We grow the
         *  array by doubling its length -- specifically, by making a new
         *  array that's twice as long, copying across the entries and then
         *  throwing away the old array. Instead of doubling the array, we
         *  could add, e.g., 10 more cells each time. This would use less
         *  memory but would result in much more copying: adding n strings
         *  to the stack would, on average, require copying each one about
         *  n/2 times; with doubling, each one is copied only once, on
         *  average. See the slides for details.
         *
         *  System.arraycopy(A, x, B, y, z) copies z entries from array A to
         *  array B, starting reading from A[x] and writing to B[y]. In this
         *  case, we copy the whole of entries to the first half of newEntries.
         */
        if (size >= entries.length) {
            String[] newEntries = new String[entries.length * 2];
            System.arraycopy (entries, 0, newEntries, 0, entries.length);
            entries = newEntries;
        }
    }

    /**
     *  pop() removes the top string from the stack and returns it. If the
     *  stack is empty, it returns null; it would probably be better to throw
     *  an exception, in production code, since trying to pop() from an empty
     *  stack usually indicates an error somewhere in the code.
     *
     *  Note that we don't actually remove the string from the array, but it
     *  will be overwritten the next time that array cell is used. Production
     *  code might want to set entries[size] to null so that the String
     *  object can be freed by the garbage collector.
     */
    public String pop () {
        if (size == 0)
            return null;
        else {
            size--;
            return entries[size];
        }
    }

    public boolean isEmpty () { return size == 0; }
    public int length ()      { return size;      }
}
