package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    ArrayList<String> testListOne = new ArrayList<>(5);
    ArrayList<String> testListTwo = new ArrayList<>(2);

    @org.junit.jupiter.api.Test
    void testEquals() {
        testListOne.add("foo");
        testListTwo.add("foo");
        assertEquals(true, testListOne.equals(testListTwo));
        testListOne.add("bar");
        assertEquals(false, testListOne.equals(testListTwo));
    }

    @org.junit.jupiter.api.Test
    void size() {
        testListOne.add("bar");
        testListOne.add("foo");
        assertEquals(2, testListOne.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertEquals(true, testListOne.isEmpty());
        testListOne.add("one");
        assertEquals(false, testListOne.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        assertEquals(true, testListOne.contains("foo"));
        assertEquals(false, testListOne.contains("nope"));
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        Iterator<String> itr = testListOne.iterator();
        assertEquals(true, itr instanceof Iterator<String>);
        assertEquals("one", itr.next());
        assertEquals("bar", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("foo", itr.next());
        assertEquals(false, itr.hasNext());
    }

    @org.junit.jupiter.api.Test
    void add() {
        //trivial : included with other tests
    }

    @org.junit.jupiter.api.Test
    void remove() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.remove(1);
        assertEquals("foo", testListOne.get(1));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        assertEquals("[one, bar, foo]", testListOne.toString());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.clear();
        assertEquals(true, testListOne.isEmpty());
        assertEquals(0, testListOne.size());
    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.add("more");
        testListOne.add("stuff");
        testListOne.add("now");

        testListTwo.add("bar");
        testListTwo.add("foo");

        assertEquals(true, testListOne.containsAll(testListTwo));

        testListTwo.add("nope");
        assertEquals(false, testListOne.containsAll(testListTwo));

    }

    @org.junit.jupiter.api.Test
    void addAll() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");

        testListTwo.add("more");
        testListTwo.add("stuff");

        testListOne.addAll(testListTwo);
        assertEquals("[one, bar, foo, more, stuff]", testListOne.toString());
    }

    @org.junit.jupiter.api.Test
    void get() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        assertEquals("foo", testListOne.get(2));
    }

    @org.junit.jupiter.api.Test
    void set() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        assertEquals("foo", testListOne.get(2));
        testListOne.set(2, "new");
        assertEquals("new", testListOne.get(2));
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.add("new");
        assertEquals(3, testListOne.indexOf("new"));
    }

    @org.junit.jupiter.api.Test
    void lastIndexOf() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.add("new");
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.add("new");
        assertEquals(2, testListOne.indexOf("foo"));
        assertEquals(6, testListOne.lastIndexOf("foo"));
    }

    @org.junit.jupiter.api.Test
    void listIterator() {
        testListOne.add("one");
        testListOne.add("bar");
        testListOne.add("foo");
        testListOne.add("more");
        testListOne.add("stuff");

        ListIterator itr = testListOne.listIterator();
        assertEquals("one", itr.next());
        assertEquals("bar", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("foo", itr.next());
        assertEquals("foo", itr.previous());
        assertEquals("bar", itr.previous());
        assertEquals(true, itr.hasPrevious());
        assertEquals("one", itr.previous());
        assertEquals(false, itr.hasPrevious());
        assertEquals("one", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("bar", itr.next());
        assertEquals("foo", itr.next());
        assertEquals("more", itr.next());
        assertEquals("stuff", itr.next());
        assertEquals(false, itr.hasNext());
        assertEquals("stuff", itr.previous());
        assertEquals(4, itr.nextIndex());
        assertEquals(3, itr.previousIndex());
        assertEquals("stuff", itr.next());
        itr.remove();
        assertEquals("more", itr.previous());
        itr.set("less");
        assertEquals("less", itr.next());
        assertEquals(false, itr.hasNext());
        itr.add("time");
        assertEquals(false, itr.hasNext());
        assertEquals("time", itr.previous());
        assertEquals("foo", itr.previous());


    }
}