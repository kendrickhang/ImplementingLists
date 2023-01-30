package edu.greenriver.sdev333;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<ItemType> implements List<ItemType> {


    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList(){
        //an empty list has no nodes
        //which means it ahs no head, so set head to null
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**  returns hashcode for this object by hashing toString with Objects.hash
     *
     * @return int hashcode
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.toString());
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof DoublyLinkedList<?>) && ((DoublyLinkedList<?>) other).hashCode() == hashCode();
    }

    @Override
    public String toString(){
        String output = "[";
        Iterator<ItemType> itr = this.iterator();
        for(int i=0; i < size-1; i++){
            output = output + itr.next().toString() +", ";
        }
        output = output + itr.next().toString() +"]";
        return output;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param item items whose presence in this collection is to be tested
     * @return
     */
    @Override
    public boolean contains(ItemType item) {
        int index = indexOf(item);
        return index != -1;
    }

    /**
     * @return
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new myIterator();
    }

    /**
     * @param item item to be added to the collection
     */
    @Override
    public void add(ItemType item) {
        Node looseNode = new Node(item);
        if(this.head == null){
            this.head = looseNode;
            this.tail = looseNode;
            this.size++;
        }else{
            looseNode.prev = this.tail;
            this.tail.next = looseNode;
            this.tail = looseNode;
            this.size++;
        }
    }

    /**
     * @param item item to be removed from this collection, if present
     */
    @Override
    public void remove(ItemType item) {
        int index = indexOf(item);
        if(index != -1){
            remove(index);
        }
    }

    public int remove(ItemType item, boolean returnIndex) {
        int index = indexOf(item);
        if(index != -1){
            remove(index);
        }
        return index;
    }

    public void removeEvery(ItemType item){
        if(remove(item, true) != -1){
            removeEvery(item);
        }
    }

    /**
     *
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * @param otherCollection collection to be checked for containment in this collection
     * @return
     */
    @Override
    public boolean containsAll(Collection<? extends ItemType> otherCollection) {
        throw new UnsupportedOperationException("The containsAll() method is not implemented here.");
    }

    /**
     * @param otherCollection collection containing items to be added to this collection
     */
    @Override
    public void addAll(Collection<? extends ItemType> otherCollection) {
        for(ItemType other : otherCollection){
            add(other);
        }
    }

    /**
     * @param otherCollection collection containing elements to be removed
     *                        from this collection
     */
    @Override
    public void removeAll(Collection<? extends ItemType> otherCollection) {
        for(ItemType other : otherCollection){
            removeEvery(other);
        }
    }

    /**
     * @param otherCollection collection containing elements to be retained in
     *                        this collection
     */
    @Override
    public void retainAll(Collection<? extends ItemType> otherCollection) {
        throw new UnsupportedOperationException("The retailAll() method is not implemented here.");
    }

    /**
     * @param i index of the item to return
     * @return
     */
    @Override
    public ItemType get(int i) {
        if(i < 0 || i >= this.size) throw new IndexOutOfBoundsException("Index out of bounds in call to get(int).");
        Node current = this.head;
        for(int j = 0; j < i; j++){
            current = current.next;
        }
        return current.data;
    }

    /**
     * @param i index of the item to replace
     * @param item  item to be stored at the specified position
     */
    @Override
    public void set(int i, ItemType item) {
        if(i < 0 || i >= this.size) throw new IndexOutOfBoundsException("Index out of bounds in call to get(int).");
        Node current = this.head;
        for(int j = 0; j < i; j++){
            current = current.next;
        }
        current.data = item;
    }

    /**
     * @param index index at which the specified item is to be inserted
     * @param item  item to be inserted
     */
    @Override
    public void add(int index, ItemType item) {
        if(index < 0 || index > this.size) throw new IndexOutOfBoundsException("IndexOutOfBounds in call to add(int, ItemType)");
        Node looseNode = new Node(item);
        if(this.head == null){
            this.head = looseNode;
            this.tail = looseNode;
            this.size = 1;
        }else if(index == 0){
            looseNode.next = this.head;
            this.head = looseNode;
            this.size++;
        }else if(index == this.size){
            this.add(item);
        }else{
            Node current = this.head;
            for(int i = 0; i < index - 1; i++){
                current = current.next;
            }
            current.next.prev = looseNode;
            looseNode.next = current.next;
            looseNode.prev = current;
            current.next = looseNode;
            this.size++;
        }
    }

    /** This method removes the last element from the list, and returns it.
     *
     * @return The object removed from the list.
     * @throws IndexOutOfBoundsException if the list is empty.
     */
    public ItemType remove() throws IndexOutOfBoundsException {
        if(this.size == 0)throw new IndexOutOfBoundsException("The list is empty, but you called remove().");
        if(this.size == 1){
            Node lastOne = this.head;
            this.head = null;
            this.tail = null;
            this.size = 0;
            return lastOne.data;
        }else{
            Node end = this.tail;
            this.tail = this.tail.prev;     //There is no previous, if this was the only node
            this.tail.next = null;
            //end.prev = null;         //does not matter what it points at, there is nothing pointing at it, right?  (for garbage collection)
            this.size--;
            return end.data;
        }//end if else
    }//end remove

    /**
     * @param index the index of the item to be removed
     * @return
     */
    @Override
    public ItemType remove(int index) {
        if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException("Index out of bounds in call to remove(int).");
        if(index == this.size - 1){
            return this.remove();
        }else if(index == 0){
            Node start = this.head;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return start.data;
        }else{
            Node current = this.head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            this.size--;
            return current.data;
        }//end if else
    }

    /**
     * @param item the item to search for
     * @return
     */
    @Override
    public int indexOf(ItemType item) {
        Node current = this.head;
        for(int i = 0; i < this.size; i++){
            if(current.data.equals(item))return i;
            current = current.next;
        }
        return -1;
    }

    /**
     * @param item the item to search for
     * @return
     */
    @Override
    public int lastIndexOf(ItemType item) {
        Node current = this.tail;
        for(int i = this.size - 1; i >= 0; i--){
            if(current.data.equals(item))return i;
            current = current.prev;
        }
        return -1;
    }

    /**
     * @return
     */
    @Override
    public ListIterator<ItemType> listIterator() {
        return new myListIterator();
    }



    //****************  Private Subclass Node *************************************
    private class Node {
        public ItemType data;
        public Node next;
        public Node prev;

        public Node(ItemType d){
            this.data = d;
            this.next = null;
            this.prev = null;
        }
    }

    private class myIterator implements Iterator<ItemType> {
        private Node currentNode;
        private int currentPosition;
        public myIterator(){
            currentNode = head;
            currentPosition = 0;
        }

        /**
         * @return
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * @return
         */
        @Override
        public ItemType next() {
            ItemType data = currentNode.data;
            currentNode = currentNode.next;
            currentPosition++;
            return data;
        }

    }

    private class myListIterator implements ListIterator<ItemType>{

        private Node currentNode;
        private int currentPosition;
        private int lastIndex;

        public myListIterator(){
            currentNode = head;
            currentPosition = 0;
        }

        /**
         * @return
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * @return
         */
        @Override
        public ItemType next() {
            if(!hasNext()){throw new NoSuchElementException("You should check hasNext() before calling next()");}
            ItemType data = currentNode.data;
            currentNode = currentNode.next;
            lastIndex = currentPosition;
            currentPosition++;
            return data;
        }

        public boolean hasPrevious(){
            return currentPosition != 0;
        }

        public ItemType previous(){
            if(!hasPrevious()){
                throw new NoSuchElementException("Node at beginning of list has no previous.  Check hasPrevious() before calling previous()");
            }
            if(currentPosition == size){
                currentNode = tail;
                currentPosition--;
                lastIndex = currentPosition;
                return currentNode.data;
            } else{
                currentNode = currentNode.prev;
                currentPosition--;
                lastIndex = currentPosition;
                return currentNode.data;
            }
        }

        /**  The index-position of the element that you will get when you call next()
         * @return
         */
        @Override
        public int nextIndex() {
            return currentPosition;
        }

        /**
         * @return
         */
        @Override
        public int previousIndex() {
            return currentPosition - 1;
        }

        /**
         *
         */
        @Override
        public void remove() {
            if(lastIndex == currentPosition) {
                int remove = lastIndex;
                ItemType ignoreThis = previous();
                DoublyLinkedList.this.remove(remove);
            }else if(lastIndex == size - 1){
                int remove = lastIndex;
                ItemType ignoreThis = previous();
                //ItemType ignoreThisToo = previous();
                DoublyLinkedList.this.remove(remove);
            }else{
                DoublyLinkedList.this.remove(lastIndex);
            }
        }

        /**
         * @param item the element with which to replace the last element returned by
         *                 {@code next} or {@code previous}
         */
        @Override
        public void set(ItemType item) {
            DoublyLinkedList.this.set(lastIndex, item);
        }

        /**
         * @param item the element to insert
         */
        @Override
        public void add(ItemType item) {
            if(lastIndex == size - 1) {
                DoublyLinkedList.this.add(item);
                currentNode = tail.next;
                currentPosition = size;
            }else if(size == 0){
                DoublyLinkedList.this.add(item);
                currentNode = head;
                currentPosition = 0;
            }else{
                DoublyLinkedList.this.add(lastIndex, item);
            }
        }
    }

}
