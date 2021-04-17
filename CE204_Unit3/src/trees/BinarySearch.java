package trees;

public class BinarySearch {
    /*
     *  This method uses binary search to determine whether the array
     *  arr contains the integer i. IMPORTANT: arr must be sorted in
     *  increasing order.
     */
    static boolean contains (int[] arr, int i) {
        int left = 0;
        int right = arr.length-1;

        if (arr[left] == i || arr[right] == i)
            return true;
        while (right > left+1) {
            int middle = (left + right)/2;
            if (arr[middle] == i)
                return true;
            else if (i < arr[middle])
                right = middle;
            else
                left = middle;
        }
        return false;
    }

    /*
     *  Test code creates lists of lengths up to 50, containing 1, ..., n
     *  and verifies that each number is in the list. It's clear that the
     *  binary search routine can only return true if it finds the item
     *  and false if it doesn't but it's easy to slip up and get stuck in
     *  an infinite loop when writing binary search.
     */
    public static void main (String[] args) {
        for (int size = 1; size < 50; size++) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++)
                arr[i] = i;

            for (int i = 0; i < size; i++)
                if (!contains (arr, i))
                    System.out.println("size="+size+", i="+i);
        }
    }
}
