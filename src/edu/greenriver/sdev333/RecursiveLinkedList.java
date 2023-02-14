package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

public class RecursiveLinkedList <ItemType> implements List<ItemType> {
    // private helper class
    private class Node {
        ItemType data;
        Node next;
    }

    // fields
    private Node head;

    public RecursiveLinkedList() {
        head = null;
    }

    @Override
    public int size() {
        return helpSize(head);
    }

    // private helper method that does the actual recursive work
    private int helpSize(Node theHead){
        if (theHead == null) {
            // base case (the bottom / the end)
            return 0;
        }
        else {
            //          VV "the rest"
            return helpSize(theHead.next) + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(ItemType item) {
        return false;
    }

    @Override
    public Iterator<ItemType> iterator() {
        return null;
    }

    @Override
    public void add(ItemType item) {
        // adds an item to back
        // loopy way:
        //  - created a current variable, set it to be same value as head
        //  - wrote a loop while (current.next != null) { current = current.next; }
        //  - once we are at the last node, create new node and attach it
        head = helpAdd(head, item);
    }

    private Node helpAdd(Node theHead, ItemType item) {
        if (theHead == null) {
            // hit bottom (at the end)
            Node theNewNode = new Node();
            theNewNode.data = item;
            theNewNode.next = null;
            return theNewNode;
        }
        else {
            // not at end/bottom
            theHead.next = helpAdd(theHead.next, item);
            return theHead;
        }
    }

    @Override
    public void remove(ItemType item) {

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsAll(Collection<? extends ItemType> otherCollection) {
        return false;
    }

    @Override
    public void addAll(Collection<? extends ItemType> otherCollection) {

    }

    @Override
    public void removeAll(Collection<? extends ItemType> otherCollection) {

    }

    @Override
    public void retainAll(Collection<? extends ItemType> otherCollection) {

    }

    @Override
    public ItemType get(int index) {
        return null;
    }

    @Override
    public void set(int index, ItemType item) {

    }

    @Override
    public void add(int index, ItemType item) {
        // add(0, item)
        if (index == 0) {
            // add to the front - list may or may not be empty
            Node theNewOne = new Node();
            theNewOne.data = item;
            theNewOne.next = head;
            head = theNewOne;
        }
        else {
            // anywhere else other than 0 (front)
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            Node theNewOne = new Node();
            theNewOne.data = item;
            theNewOne.next = current.next;
            current.next = theNewOne;
        }
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public int indexOf(ItemType item) {
        return 0;
    }

    @Override
    public int lastIndexOf(ItemType item) {
        return 0;
    }

    @Override
    public ListIterator<ItemType> listIterator() {
        return null;
    }
}
