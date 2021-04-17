package com.company;

import java.util.EmptyStackException;

public class LinkStack implements StringStack {

    Item head;


    private class Item {
        String value;
        Item next;

        Item(String value, Item next) {
            this.value = value;
            this.next = next;
        }

    }


    LinkStack(String value) {
        this.head = new Item(value, null);
    }

    LinkStack() {
        this.head = null;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    public void push(String s) {
        head = new Item(s, head);
    }

    public String top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.value;

    }

    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        head = head.next;
    }


    public static void main(String[] args) {
        LinkStack link = new LinkStack("123");

        link.push("test1");
        System.out.println(link.top());
    }
}
