package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class implements the List interface. 
 *
 * @author Jack Ruiz
 */
public class DoublyLinkedList<ItemType> implements List<ItemType>{

    private Node head;
    private int size;

    // helper/inner class
    private class Node {
        ItemType data;
        Node next;
        Node previous;
    }

    /*
     * Test w/ for-each statemenet
     */
    private class OurCustomIterator<ItemType> implements Iterator<ItemType> {

        private Node iHead;

        public OurCustomIterator() {
            //iHead = DoublyLinkedList.this.head;
            iHead = head;
        }
        @Override
        public boolean hasNext() {
            // Ken tested for if (currentPostition != null) ... but he had reversed the two lines in next() as well
            //return iHead.next != null;
            if ((iHead != null) && iHead.next != null)
                return true;
            return false;
        }

        /*
         * Ken had iHead.next and result = lines reversed
         */
        @Override
        public ItemType next() {
            iHead = iHead.next;
            ItemType result = (ItemType) iHead.data;
            return result;
        }
    }

    // geeksforgeeks.org/difference-between-an-iterator-and-listiterator-in-java/
    private class OurListIterator<ItemType> implements ListIterator<ItemType> {

        private Node iHead;
        private Node priorNode;

        public OurListIterator() {
            iHead = head;
        }
        @Override
        public boolean hasNext() {
            return iHead.next != null;
        }

        @Override
        public ItemType next() {
            iHead = iHead.next;
            ItemType result = (ItemType) iHead.data;
            priorNode = iHead;
            return result;
        }

        @Override
        public boolean hasPrevious() {
            //return iHead.previous != null;
            //return iHead.previous != null;
            return iHead != null;
        }

        @Override
        public ItemType previous() {
            //iHead = iHead.previous;
            //ItemType result = (ItemType) iHead.data;
            ItemType result = (ItemType) iHead.data;
            //if (iHead.previous != null)
            iHead = iHead.previous;
            priorNode = iHead;
            return result;
        }

        @Override
        public int nextIndex() {
            if (iHead.next == null)
                return size();

            ItemType next = next();
            Node node = head;
            int i = 0;

            while (node.next != null) {
                node = node.next;
                i++;
                if (node.next.data.equals(next)) {
                    return i;
                }
            }

            return 0;
        }

        @Override
        public int previousIndex() {
            if (iHead.previous == null)
                return -1;

            ItemType prev = (ItemType) iHead.previous.data;
            Node node = head;
            int i = -1;

            while (node.next != null) {
                node = node.next;               // will be head node w/ index 0 to start
                i++;
                if (node.data.equals(prev)) {
                    return i;
                }
            }

            return -1;
        }

        @Override
        public void remove() {
            // both prior and next nodes ARE NOT null
            if (priorNode.previous != null && priorNode.next != null) {
                priorNode.previous.next = priorNode.next;   // prior nodes' next value = next node
                priorNode.next.previous = priorNode.previous;
            }
            // both prior and next nodes ARE null (single item in list)
            else if (priorNode.previous == null && priorNode.next == null) {
                iHead.next = null;
            }
            // prior only IS NULL (1st item in list)
            else if (priorNode.previous == null) {
                iHead = priorNode.next;
            }
            // next only IS NULL (last item in list)
            else if (priorNode.next == null) {
                priorNode.next = null;
            }
        }

        @Override
        public void set(ItemType itemType) {

        }

        @Override
        public void add(ItemType itemType) {

        }
    }

    /*
     * Return a pointer to the last element in the linked-list
     */
    private Node getLastElement() {
        if (size == 0) {
            return null;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            return n;
        }
    }

    /*
     * Return element at index n (not the data in that element)
     */
    private Node getElementN(int index) {

        if ((index >= size) || (index < 0))
            throw new IndexOutOfBoundsException();

        Node n = head;
        int i = -1;

        while (n.next != null) {
            i++;
            n = n.next;
            if (i == index) {
                //System.out.println("data, " + n.data);
                return n;
            }
        }

        // we shouldn't be here ...
        throw new NullPointerException();
    }

    public DoublyLinkedList() {
        // A new list contains no elemenets, so its head is null
        head = null;
        size = 0;
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

        if (size == 0)
            return false;

        return indexOf(item) != -1;
    }

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an Iterator over the elements in this collection
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new OurCustomIterator<>();
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

        if (head == null) {         // Add new 1st item, item.previous = null
            head = new Node();
            head.next = new Node();
            head.next.data = item;
        } else {
            Node currentLast = getLastElement();

            currentLast.next = new Node();
            currentLast.next.data = item;
            currentLast.next.previous = currentLast;
        }

        ++size;
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
        if (item == null)
            throw new NullPointerException();

        Node n = head;

        // 1st Node is treated differently as it has no valid previous
        if (n.next.data.equals(item)) {
            n.next = n.next.next;
            n.next.previous = null;
            --size;
        }
        else {
            while (n.next != null) {
                n = n.next;
                if (n.data.equals(item)) {
                    if (n.next != null)
                        n.next.previous = n.previous;   // assign 'previous' for following node to prior node
                    n.previous.next = n.next;       // assign 'next' for prior node to n.next
                    --size;
                    return;
                }
            }
        }

    }

    /**
     * Removes all items from this collection.
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        head = null;
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
        // Cycle through otherCollection, and when find an element from that
        // collection here, remove it. If at some point we do not find one of those
        // elements, return false.  If we make it through the list, return true
        for (ItemType i: otherCollection) {
            if (!this.contains(i))
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
        if (otherCollection != null) {
            for (ItemType i: otherCollection) {
                this.remove(i);
            }
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
        return getElementN(index).data;
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
        if (item == null)
            throw new NullPointerException();

        Node i = getElementN(index);
        i.data = item;
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
        if (item == null)
            throw new NullPointerException();

        Node i = getElementN(index);    // current element at index
        Node n = new Node();            // new node to be inserted
        n.data = item;
        if (index != 0)
            n.previous = i.previous;    // previous value for new node
        n.next = i;                     // next value for new node
        i.previous.next = n;            // next value for old prior node
        i.previous = n;                 // previous value for old node

        ++size;
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
        Node i = getElementN(index);

        if (index == 0) {
            head.next = i.next;
            i.next.previous = null;
        } else {
            i.previous.next = i.next;
            i.next.previous = i.previous;
        }

        --size;
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
        if (item == null)
            throw new NullPointerException();

        Node n = head;
        int i = -1;

        while (n.next != null) {
            i++;
            n = n.next;
            if (n.data.equals(item))
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

        if (item == null)
            throw new NullPointerException();

        Node n = getLastElement();
        int index = size - 1;

        while (true) {

            if (n.data.equals(item))
                return index;

            if (n.previous != null) {
                n = n.previous;
                //System.out.println("cycling through last-index, looking for " + item + ", have " + n.data);
                --index;
            } else {
                break;
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
        return new OurListIterator<>();
    }
}
