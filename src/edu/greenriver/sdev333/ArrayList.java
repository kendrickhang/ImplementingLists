package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class implements the List interface in the Java collections
 * package.
 *
 * @author Jack Ruiz
 *
 * @param <ItemType>
 */
public class ArrayList<ItemType> implements List<ItemType> {

    // WE NEED FIELDS

    // one plain Java array
    private ItemType[] data;

    // one int to keep track of size
    // size is the number of spots used in the data array
    // size is different than length
    private int size;

    public ArrayList() {
        size = 0;
        data = (ItemType[]) new Object[10];
    }

    public ArrayList(int initial) {
        size = 0;
        data = (ItemType[]) new Object[initial];
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
        if (item == null) {
            throw new NullPointerException();
        }

        return (indexOf(item) != -1);
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
        data[size++] = item;

        // all of the above works until I run out of room when size becomes
        // the same as length, I'm out of room
        checkSize();

    }

    /*
     * check for size increase requirement, and double-array size
     * if necessary
     */
    private void checkSize() {
        if (size == data.length) {
            // resize up ....

            // Step 1 - create a temp array 2x size
            ItemType[] temp = (ItemType[]) new Object[data.length * 2];;

            // Step 2 - copy arrayElements from data to temp
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }

            // Step 3 - point 'data' at temp array
            data = temp;
        }
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
        if (item == null) {
            throw new NullPointerException();
        }
        // IN CLASS
        int i = indexOf(item);
        if (i != -1) {
            remove(i);
        }
    }

    /**
     * Removes all items from this collection.
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        // data = (ItemType[]) new Object[data.length];
        size = 0;
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

        for (ItemType i: otherCollection) {
            if (!contains(i))
                return false;
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
        if (otherCollection != null) {
            for (ItemType i: otherCollection) {
                this.add(i);
            }
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
        for (ItemType i: otherCollection) {
            remove(i);
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
        // Create a value-copy of current list
        List<ItemType> listCopy = new ArrayList<>();
        for (ItemType i: this) {
            listCopy.add(i);
        }

        // remove items in otherCollection from listCopy
        // this leaves us with an inverted view of the list
        listCopy.removeAll(otherCollection);

        // now remove listCopy items from originalList
        this.removeAll(listCopy);
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
        if (validIndex(index)) {
            return data[index];
        }
        else {
            throw new IndexOutOfBoundsException("index is beyond size");
        }
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
        if (validIndex(index)) {
            data[index] = item;
        } else {
            throw new IndexOutOfBoundsException("index is beyond size");
        }
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
        if (validIndex(index)) {
            // Add the last item to the end of list, and shift everything behind it to the right
            this.add(data[size-1]);

            for (int i = size-2; i > index; i--) {
                data[i] = data[i-1];
            }

            // finally, set the 'added' item to the specified location
            set(index, item);
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
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
        // DID AT HOME
        for (int i = 0; i < size; i++) {
            if (data[i].equals(item))
                return i;
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
        for (int i = size-1; i >= 0; i--) {
            if (data[i].equals(item))
                return i;
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
        return new SecondCustomIterator();
    }

    /**
     * Per the Oracle javadocs, "... two lists are defined to be equal
     * if they contain the same elements in the same order."
     * @param obj object to be compared for equality with this collection
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // Must first ensure these two objects are of the same type ...
        if (obj != null && obj.getClass() == this.getClass()) {

            // create temporary ArrayList from obj
            ArrayList<ItemType> temp = (ArrayList<ItemType>) obj;

            // confirm lists are of equal size, if not, return false
            if (this.size() != temp.size())
                return false;

            // cycle through individual items, testing one by one
            // return false if one of pair is not equal
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) != temp.get(i))
                    return false;
            }
        }

        return true;
    }

    /**
     * This method tests a given index value to determine if it
     * is valid.  Any index out of range, i.e. smaller than 0
     * or larger than size, is invalid and does not point to
     * a valid element.
     * @param index
     * @return
     */
    private boolean validIndex(int index) {
        return ((index < this.size) && (index >= 0));
    }

    /**
     * This method resizes the array, doubling its previous value, to
     * make way for new elements.  Called as required by various methods.
     */
    private void resize() {
        // Step 1 - create a temp array 2x size
        ItemType[] temp = (ItemType[]) new Object[data.length * 2];;

        // Step 2 - copy arrayElements from data to temp
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }

        // Step 3 - point 'data' at temp array
        data = temp;
    }

    private class OurCustomIterator implements Iterator<ItemType> {

        // fields
        private int currentPosition;

        public OurCustomIterator() {
            currentPosition = 0;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size();
        }

        @Override
        public ItemType next() {
            ItemType result = get(currentPosition);
            currentPosition++;
            return result;
        }

    }

    /**
     * Implemented methods using primarily same logic as CustomerIterator
     * above, though the extra methods were completed using documentation found
     * for the JDK at
     * https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html#previousIndex--
     */
    private class SecondCustomIterator implements ListIterator<ItemType> {

        // fancier Iterator that lets us go forwards and backwards
        private int currentPosition;
        private int lastIndexReturned;

        public SecondCustomIterator() {
            currentPosition = 0;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size();
        }

        @Override
        public ItemType next() {
            ItemType result = get(currentPosition);
            lastIndexReturned = currentPosition;
            currentPosition++;
            return result;
        }

        @Override
        public boolean hasPrevious() {
            return currentPosition > 0;
        }

        @Override
        public ItemType previous() {
            if (currentPosition > 0) {
                ItemType result = get(currentPosition - 1);
                lastIndexReturned = currentPosition - 1;
                return result;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public int nextIndex() {
            if (currentPosition == size() - 1)
                return currentPosition;
            else
                return currentPosition + 1;
        }

        @Override
        public int previousIndex() {
            if (currentPosition == 0)
                return -1;
            else
                return currentPosition - 1;
        }

        @Override
        public void remove() {
            ArrayList.this.remove(lastIndexReturned);
        }

        @Override
        public void set(ItemType itemType) {
            ArrayList.this.set(lastIndexReturned, itemType);
        }

        @Override
        public void add(ItemType itemType) {

        }
    }

} // end of class ArrayList
Footer
© 2023 GitHub, Inc.
Footer navigation
Terms
Privacy
Security
Status
Docs
Contact GitHub
Pricing
API
Training
Blog
About
