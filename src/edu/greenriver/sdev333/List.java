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

import java.util.ListIterator;

/**
 * <p>A list is a collection where the order of the items is controlled
 * by the client that uses this interface. The client can access items
 * by their integer index (position in the list), and search for items
 * in the list.</p>
 *
 * <p>Lists (like Java arrays) are zero based.</p>
 *
 * <p>Unlike sets, lists typically allow duplicate elements.</p>
 *
 * <p>The List interface provides methods for position-based (indexed) access
 * to list items.</p>
 *
 * <p>The List interface provides methods for modification of the list
 * (add and remove) at a given position (index).</p>
 *
 * <p>The List interface provides two methods to search for a specified item.
 * Warning: the performance of these operations are dependent on the
 * implementation.</p>
 *
 * <p>In addition to providing an Iterator (as required by Collection),
 * the List interface provides a special iterator, called a ListIterator
 * that allows element insertion and replacement, and bidirectional access
 * in addition to the normal operations that Iterator provides.</p>
 *
 * <p>edu.greenriver.sdev333.List is a modified subset of java.util.List
 * used for teaching and learning purposes only</p>
 *
 * @param <ItemType> the data type of the individual items in this list
 *
 * @author Josh Bloch
 * @author Neal Gafter
 */
public interface List<ItemType> extends Collection<ItemType> {

    // Positional Access Operations

    /**
     * Returns the item at the specified position in this list
     *
     * @param index index of the item to return
     * @return the item at the specified position in this list
     * @throws IndexOutOfBoundsException if this index is out of range
     *         (index < 0 || index >= size())
     */
    ItemType get(int index);

    /**
     * Replaces the item at the specified position in this list
     * with the specified item
     *
     * @param index index of the item to replace
     * @param item item to be stored at the specified position
     * @throws NullPointerException if the specified item is null
     *         and this list does not permit null elements
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     */
    void set(int index, ItemType item);

    /**
     * Inserts the specified item at the specified position in this list.
     * Shifts the item currently at that position (if any) and any subsequent
     * items to the right.
     *
     * @param index index at which the specified item is to be inserted
     * @param item item to be inserted
     * @throws NullPointerException if the specified item is null
     *         and this list does not permit null elements
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     */
    void add(int index, ItemType item);

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent items to the left.
     *
     * @param index the index of the item to be removed
     * @return
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    ItemType remove(int index);

    /**
     * Returns the index of the first occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     * @param item the item to search for
     * @return the index of the first occurrence of the specified item
     *         in this list, or -1 if this list does not contain the item
     * @throws NullPointerException if the specified item is null and this
     *         list does not permit null items
     */
    int indexOf(ItemType item);

    /**
     * Returns the index of the last occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     * @param item the item to search for
     * @return the index of the first occurrence of the specified item
     *         in this list, or -1 if this list does not contain the item
     * @throws NullPointerException if the specified item is null and this
     *         list does not permit null items
     */
    int lastIndexOf(ItemType item);

    // List Iterators

    /**
     * Returns a list iterator over the elements in this list
     * (in proper sequence).
     *
     * @return a list iterator over the elements in this list
     * (in proper sequence)
     */
    ListIterator<ItemType> listIterator();
}
