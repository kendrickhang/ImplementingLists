package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayList<ItemType> implements List<ItemType>{

    // one plain old java array
    private ItemType[] data;

    // one int to keep track of size
    // size is the spots that are used in the data array
    // size is different than lenth of the array
    private int size;

    //Constructor
    public ArrayList() {
        this.data = (ItemType[]) new Object[10];
        this.size = 0;
    }

    //Constructor
    public ArrayList(int capacity) {
        this.data = (ItemType[]) new Object[capacity];
        this.size = 0;
    }


    /**  returns hashcode for this object by hashing toString with Objects.hash
     *
     * @return int hashcode
     */
    public int hashcode(){
       return Objects.hash(this.toString());
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof ArrayList<?>) && ((ArrayList<?>) other).hashcode() == hashcode();
    }


    private void trimArray(){
        ItemType[] tempArray = (ItemType[]) new Object[size];
        for(int i = 0; i < size; i++){
            tempArray[i] = data[i];
        }
        data = tempArray;
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
        return size == 0;
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
        int index = indexOf(item);
        return isValidIndex(index);
    }

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an Iterator over the elements in this collection
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new MyCustomIterator();
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
        if(!hasRoom()){
            expand();
        }
        data[size] = item;
        size++;

    }

    private boolean hasRoom(){
        return size != data.length;
    }

    private void expand(){
        //resize up : double array size
        ItemType[] temp = (ItemType[]) new Object[size * 2];
        for(int i=0; i < this.size; i++){
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    private boolean isValidIndex(int index){
        return index >= 0 && index < size && size !=0;
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
        int tempSize = 0;
        ItemType[] tempData = (ItemType[]) new Object[size];
        for(int i =0; i < size; i++){
            if(!item.equals(data[i])){
                tempData[tempSize] = data[i];
                tempSize++;
            }
        }
        data = tempData;
        size = tempSize;
    }

    /**
     * Removes all items from this collection.
     * The collection will be empty after this method returns.
     */

    @Override
    public String toString(){
        String output = "[";
        for(int i=0; i < size-1; i++){
            output = output + data[i].toString() +", ";
        }
        output = output + data[size-1].toString() +"]";
        return output;
    }
    @Override
    public void clear() {
        this.data = (ItemType[]) new Object[10];
        this.size = 0;
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
        //fail fast (fail loud)
        //throw new UnsupportedOperationException("containsAll method is not supported in this implementation");

        Iterator<ItemType> itr = (Iterator<ItemType>)otherCollection.iterator();
        while(itr.hasNext()){
            ItemType itemToCheck = itr.next();
            if(!contains(itemToCheck)){
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
        //fail fast (fail loud)
        //throw new UnsupportedOperationException("addAll method is not supported in this implementation");
        for(ItemType other : otherCollection){
            add(other);
        }
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
        //fail fast (fail loud)
        throw new UnsupportedOperationException("addAll method is not supported in this implementation");
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
        //fail fast (fail loud)
        throw new UnsupportedOperationException("addAll method is not supported in this implementation");
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
        if(!isValidIndex(index)){
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        return data[index];
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
        if(!isValidIndex(index)){
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        data[index] = item;
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
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        if(!hasRoom()){expand();}
        for(int i = size; i>index; i--){
            data[i] = data[i-1];
        }
        data[index] = item;
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
        if(!isValidIndex(index)){throw new IndexOutOfBoundsException("Index out of bounds");}
        //shift values left to overwrite the item at index
        for(int i = index; i<size-1; i++){
            data[i] = data[i+1];
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
        for(int i = 0; i < size; i++){
            if(item.equals(data[i])){
                return i;
            }
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
        for(int i = size -1; i >= 0; i--){
            if(item.equals(data[i])){
                return i;
            }
        }
        return -1;
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
        return new MyCustomListIterator();
    }

    private class MyCustomIterator implements Iterator<ItemType>{

        private int currentPosition;
        public MyCustomIterator(){
            currentPosition = 0;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentPosition < size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public ItemType next() {
            return get(currentPosition++);
        }
    }

    private class MyCustomListIterator implements ListIterator<ItemType>{
        private int currentPosition;
        public MyCustomListIterator(){
            currentPosition = 0;
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the forward direction. (In other words,
         * returns {@code true} if {@link #next} would return an element rather
         * than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the forward direction
         */
        @Override
        public boolean hasNext() {
            return currentPosition < size();
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to {@link #previous} to go back and forth.
         * (Note that alternating calls to {@code next} and {@code previous}
         * will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        @Override
        public ItemType next() {
            return get(currentPosition++);
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the reverse direction.  (In other words,
         * returns {@code true} if {@link #previous} would return an element
         * rather than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the reverse direction
         */
        @Override
        public boolean hasPrevious() {
            return currentPosition > 0;
        }

        /**
         * Returns the previous element in the list and moves the cursor
         * position backwards.  This method may be called repeatedly to
         * iterate through the list backwards, or intermixed with calls to
         * {@link #next} to go back and forth.  (Note that alternating calls
         * to {@code next} and {@code previous} will return the same
         * element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous
         *                                element
         */
        @Override
        public ItemType previous() {
            ItemType returnThis = get(currentPosition -1);
            currentPosition--;
            return returnThis;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #next}. (Returns list size if the list
         * iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code next}, or list size if the list
         * iterator is at the end of the list
         */
        @Override
        public int nextIndex() {
            return currentPosition;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #previous}. (Returns -1 if the list
         * iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code previous}, or -1 if the list
         * iterator is at the beginning of the list
         */
        @Override
        public int previousIndex() {
            return currentPosition - 1;
        }

        /**
         * Removes from the list the last element that was returned by {@link
         * #next} or {@link #previous} (optional operation).  This call can
         * only be made once per call to {@code next} or {@code previous}.
         * It can be made only if {@link #add} has not been
         * called after the last call to {@code next} or {@code previous}.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this list iterator
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void remove() {

        }

        /**
         * Replaces the last element returned by {@link #next} or
         * {@link #previous} with the specified element (optional operation).
         * This call can be made only if neither {@link #remove} nor {@link
         * #add} have been called after the last call to {@code next} or
         * {@code previous}.
         *
         * @param itemType the element with which to replace the last element returned by
         *                 {@code next} or {@code previous}
         * @throws UnsupportedOperationException if the {@code set} operation
         *                                       is not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of the specified
         *                                       element prevents it from being added to this list
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void set(ItemType itemType) {

        }

        /**
         * Inserts the specified element into the list (optional operation).
         * The element is inserted immediately before the element that
         * would be returned by {@link #next}, if any, and after the element
         * that would be returned by {@link #previous}, if any.  (If the
         * list contains no elements, the new element becomes the sole element
         * on the list.)  The new element is inserted before the implicit
         * cursor: a subsequent call to {@code next} would be unaffected, and a
         * subsequent call to {@code previous} would return the new element.
         * (This call increases by one the value that would be returned by a
         * call to {@code nextIndex} or {@code previousIndex}.)
         *
         * @param itemType the element to insert
         * @throws UnsupportedOperationException if the {@code add} method is
         *                                       not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of this element
         *                                       prevents it from being added to this list
         */
        @Override
        public void add(ItemType itemType) {

        }
    }
}
