package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

public class SinglyLinkedList<ItemType> implements List<ItemType> {

    //Fields
    private Node head;
    private int size;

    //helper/inner classes
    private class Node{

        ItemType data;
        Node next;
    }


    //constructor
    public SinglyLinkedList(){
        size = 0;
        head = null;
    }
    //helper method
    private void checkIndex(int index){
        if(index<0 || index>= size){
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no items.
     *
     * @return true if this collection contains no items
     */
    @Override
    public boolean isEmpty() {

        return size() == 0||head==null;

    }

    /**
     * Returns true if this collection contains the specified item.
     *
     * @param item items whose presence in this collection is to be tested
     * @return true if this collection contains the specified item
     * @throws NullPointerException if the specified item is null
     *                              and this collection does not permit null items
     */

    @Override
    public boolean contains(ItemType item) {
        // assume indexOf is working...
        int position = indexOf(item);
        if (position == -1) {
            return false;
        }
        return true;

    }

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an Iterator over the elements in this collection
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new OurCustomIterator();
    }

    /**
     * Adds the specified item to the collection.
     *
     * @param item item to be added to the collection
     * @throws NullPointerException if the specified item is null
     *                              and this collection does not permit null items
     */
    @Override
    public void add(ItemType item) {

        Node newNode = new Node();
        newNode.data = item;
        newNode.next = null;
        if(head == null){
            head = newNode;
        }else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            //current is pointing to last node
            current.next = newNode;
        }
        size++;

    }

    /**
     * Removes a single instance of the specified item from this collection,
     * if it is present.
     *
     * @param item item to be removed from this collection, if present
     * @throws NullPointerException if the specified item is null
     *                              and this collection does not permit null items
     */
    @Override
    public void remove(ItemType item) {

        if(item == null){
            throw new NullPointerException();
        }

        /*
        alternative slower method with less code:
        int position = indexOf(item);
        if(position != -1){
        remove(position);
        }
         */

        if(head.data == item){
            head = head.next;
        }else{
            Node current = head;
            Node previous;
            while (current.next != null){
                previous = current;
                current = current.next;

                if(current.data.equals(item)){
                    previous.next = current.next;
                }
            }
        }size -=1;

    }

    /**
     * Removes all items from this collection.
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {

        size = 0;
        head = null;

    }

    /**
     * Returns true if this collection contains all the items
     * in the specified other collection.
     *
     * @param otherCollection collection to be checked for containment in this collection
     * @return true if this collection contains all the items
     * in the specified other collection
     */
    @Override
    public boolean containsAll(Collection<? extends ItemType> otherCollection) {

        for(ItemType item: otherCollection) {

            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all the items in this specified other collection to this collection.
     *
     * @param otherCollection collection containing items to be added to this collection
     */
    @Override


    public void addAll(Collection<? extends ItemType> otherCollection) {
        // walk through the other collection
        //  for-each loop
        //  or use Iterator

        Iterator<ItemType> itr = (Iterator<ItemType>)otherCollection.iterator();
        while (itr.hasNext()) {
            ItemType currentItem = itr.next();
            add(currentItem);
        }

        /*
        // alternative implementation using a for-each loop
        for (ItemType currentItem : otherCollection) {
            add(currentItem);
        }
        */

    }

    /**
     * Removes all of this collection's items that are also contained in the
     * specified other collection. After this call returns, this collection will
     * contain no elements in common with the specified other collection.
     *
     * @param otherCollection collection containing elements to be removed
     *                        from this collection
     */
    @Override
    public void removeAll(Collection<? extends ItemType> otherCollection) {

        Iterator<ItemType> itr = (Iterator<ItemType>)otherCollection.iterator();
        while(itr.hasNext()) {
            ItemType currentItem = itr.next();
            remove(currentItem);
        }

    }

    /**
     * Retains only the items in this collection that are contained in the
     * specified other collection. In other words, removes from this collection
     * all of its items that are not contained in the specified other collection
     *
     * @param otherCollection collection containing elements to be retained in
     *                        this collection
     */
    @Override
    public void retainAll(Collection<? extends ItemType> otherCollection) {

    }

    /**
     * Returns the item at the specified position in this list
     *
     * @param index index of the item to return
     * @return the item at the specified position in this list
     * @throws IndexOutOfBoundsException if this index is out of range
     *                                   (index < 0 || index >= size())
     */
    @Override
    public ItemType get(int index) {

        checkIndex(index);
        Node current = head;
        int counter = 0;
        while(counter != index){

            current = current.next;
            counter++;
        }
        return current.data;


    }

    /**
     * Replaces the item at the specified position in this list
     * with the specified item
     *
     * @param index index of the item to replace
     * @param item  item to be stored at the specified position
     * @throws NullPointerException      if the specified item is null
     *                                   and this list does not permit null elements
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    @Override
    public void set(int index, ItemType item) {

        checkIndex(index);
        Node current = head;
        int counter = 0;
        while(counter!= index){
            current = current.next;
            counter++;
        }
        current.data = item;

    }

    /**
     * Inserts the specified item at the specified position in this list.
     * Shifts the item currently at that position (if any) and any subsequent
     * items to the right.
     *
     * @param index index at which the specified item is to be inserted
     * @param item  item to be inserted
     * @throws NullPointerException      if the specified item is null
     *                                   and this list does not permit null elements
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    @Override
    public void add(int index, ItemType item) {
        checkIndex(index);
        //if the head is null
        if (index == 0) {
            Node theNewOne = new Node();
            theNewOne.data = item;
            theNewOne.next = head;
            head = theNewOne;

        }
        else {
            Node current = head;

            // stop one before the position I want to insert at
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            // when I get here, current is pointing the node *BEFORE* the node at the index
            Node theNewOne = new Node();
            theNewOne.data = item;
            theNewOne.next = current.next;

            current.next = theNewOne;
        }

        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();

        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent items to the left.
     *
     * @param index the index of the item to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    @Override
    public void remove(int index) {
        checkIndex(index);

        if(index == 0){
            head = head.next;
        }
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
        }
        size--;

    }

    /**
     * Returns the index of the first occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     *
     * @param item the item to search for
     * @return the index of the first occurrence of the specified item
     * in this list, or -1 if this list does not contain the item
     * @throws NullPointerException if the specified item is null and this
     *                              list does not permit null items
     */
    @Override
    public int indexOf(ItemType item) {
        int counter = 0;
        Node current = head;
        while (current != null) {
            if (current.data.equals(item)) {

                return counter;
            }
            counter++;
            current = current.next;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     *
     * @param item the item to search for
     * @return the index of the first occurrence of the specified item
     * in this list, or -1 if this list does not contain the item
     * @throws NullPointerException if the specified item is null and this
     *                              list does not permit null items
     */
    @Override
    public int lastIndexOf(ItemType item) {
        return 0;
    }

    /**
     * Returns a list iterator over the elements in this list
     * (in proper sequence).
     *
     * @return a list iterator over the elements in this list
     * (in proper sequence)
     */
    @Override
    public ListIterator<ItemType> listIterator() {

        return new OurEnhancedIterator();
    }

    private class OurCustomIterator implements Iterator<ItemType> {

        // field
        private Node currentPosition;

        public OurCustomIterator() {
            currentPosition = head;
        }

        @Override
        public boolean hasNext() {
            // see if I'm on the last node: if (current.next == null)
            // see if I made it past the last node: if (current == null)
            if (currentPosition != null) {

                return true;
            }
            return false;
        }

        @Override
        public ItemType next() {
            ItemType result = currentPosition.data;
            currentPosition = currentPosition.next;
            return result;
        }
    }


    private class OurEnhancedIterator implements ListIterator<ItemType> {

        private Node currentPosition;
        private int currentIndex;

        public OurEnhancedIterator() {
            currentPosition = head;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentPosition != null;
        }

        @Override
        public ItemType next() {
            ItemType result = currentPosition.data;
            currentPosition = currentPosition.next;
            return result;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public ItemType previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(ItemType itemType) {

        }

        @Override
        public void add(ItemType itemType) {

        }
    }

}

