/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package edu.greenriver.sdev333;

import java.util.Iterator;

/**
 * <p>A collection represents a group of objects, known as its items.</p>
 *
 * <p>Some collections allow duplicate elements and others do not.
 * Some collections are ordered and others are unordered.</p>
 *
 * <p>This is the root interface in the collection hierarchy.</p>
 *
 * <p>edu.greenriver.sdev333.Collection is a modified subset of
 * java.util.Collection used for teaching and learning purposes only</p>
 *
 * @param <ItemType> the data type of the individual items in this collection
 *
 * @author Josh Bloch
 * @author Neal Gafter
 */
public interface Collection<ItemType> extends Iterable<ItemType> {
    // Query Operations

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection
     */
    int size();

    /**
     * Returns true if this collection contains no items.
     *
     * @return true if this collection contains no items
     */
    boolean isEmpty();

    /**
     * Returns true if this collection contains the specified item.
     *
     * @param item items whose presence in this collection is to be tested
     * @return true if this collection contains the specified item
     * @throws NullPointerException if the specified item is null
     *         and this collection does not permit null items
     */
    boolean contains(ItemType item);

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an Iterator over the elements in this collection
     */
    Iterator<ItemType> iterator();

    // Modification Operations

    /**
     * Adds the specified item to the collection.
     *
     * @param item item to be added to the collection
     * @throws NullPointerException if the specified item is null
     *         and this collection does not permit null items
     */
    void add(ItemType item);

    /**
     * Removes a single instance of the specified item from this collection,
     * if it is present.
     * @param item item to be removed from this collection, if present
     * @throws NullPointerException if the specified item is null
     *         and this collection does not permit null items
     */
    void remove(ItemType item);


    // Bulk Operations
    /**
     * Removes all items from this collection.
     * The collection will be empty after this method returns.
     */
    void clear();

    /**
     * Returns true if this collection contains all the items
     * in the specified other collection.
     *
     * @param otherCollection collection to be checked for containment in this collection
     * @return true if this collection contains all the items
     *         in the specified other collection
     */
    boolean containsAll(Collection<? extends ItemType> otherCollection);

    /**
     * Adds all the items in this specified other collection to this collection.
     * @param otherCollection collection containing items to be added to this collection
     */
    void addAll(Collection<? extends ItemType> otherCollection);

    /**
     * Removes all of this collection's items that are also contained in the
     * specified other collection. After this call returns, this collection will
     * contain no elements in common with the specified other collection.
     * @param otherCollection collection containing elements to be removed
     *        from this collection
     */
    void removeAll(Collection<? extends ItemType> otherCollection);

    /**
     * Retains only the items in this collection that are contained in the
     * specified other collection. In other words, removes from this collection
     * all of its items that are not contained in the specified other collection
     *
     * @param otherCollection collection containing elements to be retained in
     *        this collection
     */
    void retainAll(Collection<? extends ItemType> otherCollection);

    // Comparison and hashing

    /**
     * Compares the specified object with this collection for equality.
     *
     * @param otherObj object to be compared for equality with this collection
     * @return true if the specified other object is equal to this collection
     */
    boolean equals(Object otherObj);

    /**
     * Returns the hash code value for this collection. Programmers should
     * take note that any class that overrides the Object.equals
     * method must also override the Object.hashCode method in order
     * to satisfy the general contract for the Object.hashCode method.
     * In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode().
     *
     * @return the hash code value for this collection
     */
    int hashCode();
}
