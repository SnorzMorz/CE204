package trees;

import linear.DoublyLinkedList;

/*
 *  A basic implementation of binary trees. When I implemented stacks,
 *  I created a class Stack, which had an inner class Stack.Item.  An
 *  object of the Stack class could represent any stack, empty or full.
 *  Here, I've taken a different approach: an object of the BinaryTree
 *  represents a tree with at least one node; an empty tree (one with
 *  no nodes) would be represented by just a null reference.  Each
 *  BinaryTree object represents a tree, with the value stored at its
 *  root and the left and right subtrees.
 */
public class BinaryTree {
    private String value;     /* Value stored at root. */
    private BinaryTree left;  /* Left subtree.         */
    private BinaryTree right; /* Right subtree.        */

    /*
     *  Constructor implementing the create operation: create a single-
     *  vertex tree that stores the given data.
     */
    BinaryTree (String value) { this (value, null, null); }

    /*
     *  Constructor implementing the join operator: create a tree with
     *  the given string stored at the root and with the givel left and
     *  right subtrees.
     */
    BinaryTree (String value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left  = left;
        this.right = right;
    }

    /*
     *  A node is a leaf exactly if it has no children.
     */
    boolean isLeaf () { return left == null && right == null; }

    /*
     *  Implementations of the other operators. We could just make the
     *  fields public, but that would allow them to be changed, which
     *  we only want to happen through methods of the class.
     */
    BinaryTree leftChild ()  { return left;  }
    BinaryTree rightChild () { return right; }
    String value () { return value; }

    /*
     *  An example of a recursive method on trees.  To determine if a
     *  tree containsWord the string s, we check if it's at the root. If not,
     *  we look in the left subtree by making a recursive call to
     *  containsWord(). If it's not in the left subtree, we look in the
     *  right subtree, the same way. Recall that the || and && operators
     *  use so-called short-circuit evaluation: that is, if the answer
     *  is known from looking at just the first operand, the second
     *  operand is not evaluated. (Using "true or anything = true" and
     *  "false and anything = false".) So if, e.g., value is null,
     *  value.equals(s) isn't called -- which is good, because it would
     *  throw a NullPointerException. Likewise, we only look in the
     *  left subtree if s isn't at the root, and only look in the right
     *  subtree if we've not already found s in the root or left sub-
     *  tree. This means we stop looking as soon as we find the string.
     */
    boolean contains (String s) {
        return (value != null && value.equals (s))
                || (left != null && left.contains (s))
                || (right != null && right.contains (s));
    }

    void preOrder () {
        System.out.println(value);
        if (left != null) left.preOrder();
        if (right != null) right.preOrder ();
    }

    void inOrder () {
        if (left != null) left.inOrder ();
        System.out.println(value);
        if (right != null) right.inOrder ();
    }

    void postOrder () {
        if (left != null) left.postOrder();
        if (right != null) right.postOrder ();
        System.out.println(value);
    }

    public static void main (String[] args) {

    }
}
