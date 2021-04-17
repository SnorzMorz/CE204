//package linear;
//
//public class SinglyLinkedList {
//
//    private SinglyLinkedList.Item head = null;
//    private SinglyLinkedList.Item tail = null;
//    private int length = 0;
//
//    private class Item {
//        String value;
//        SinglyLinkedList.Item next;
//
//        Item (String value, SinglyLinkedList.Item next) {
//            this.value = value;
//            this.next = next;
//        }
//    }
//
//    void create(String value){
//        SinglyLinkedList.Item e = new SinglyLinkedList.Item(value, null);
//        head = e;
//    }
//
//    boolean isEmpty(){
//        if (length() == 0){
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    int length(){
//        return length;
//    }
//
//    public void insert (int index, String value) {
//        if (index <= 0)
//            addToHead(value);
//        else if (index >= length)
//            addToTail(value);
//        else {
//            SinglyLinkedList.Item cur = getItem(index-1);
//            DoublyLinkedList.Item e = new DoublyLinkedList.Item(value, cur, cur.next);
//            e.prev.next = e;
//            e.next.prev = e;
//
//            length++;
//        }
//    }
//
//
//
//
//    void addToHead(){
//        DoublyLinkedList.Item e = new DoublyLinkedList.Item(value, null, head);
//        head = e;
//        if (e.next == null)
//            tail = e;
//        else
//            e.next.prev = e;
//
//        length++;
//    }
//
//    public void addToTail (String value) {
//        DoublyLinkedList.Item e = new DoublyLinkedList.Item(value, tail, null);
//        tail = e;
//        if (e.prev == null)
//            head = e;
//        else
//            e.prev.next = e;
//
//        length++;
//    }
//
//}
