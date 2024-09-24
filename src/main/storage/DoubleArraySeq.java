package storage;

public class DoubleArraySeq implements Cloneable {

    public static final int DEFAULT_CAPACITY = 10;
    private double[] data;
    /** Normal value */
    private int manyItems = -1;
    /** Array index */
    private int currentIndex = -1;
    /** Normal value */
    private int size = -1;

    /**
     * Initialize an empty sequence with an initial capacity of 10. Note that
     * the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     * 
     * @postcondition
     *                This sequence is empty and has an initial capacity of 10.
     * @exception OutOfMemoryError
     *                             Indicates insufficient memory for:
     *                             new double[10].
     **/
    public DoubleArraySeq() {
        manyItems = DEFAULT_CAPACITY;
        data = new double[manyItems];
        size = 0;
    }

    /**
     * Initialize an empty sequence with a specified initial capacity. Note that
     * the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     * 
     * @param initialCapacity
     *                        the initial capacity of this sequence
     * @precondition
     *               initialCapacity is non-negative.
     * @postcondition
     *                This sequence is empty and has the given initial capacity.
     * @exception IllegalArgumentException
     *                                     Indicates that initialCapacity is
     *                                     negative.
     * @exception OutOfMemoryError
     *                                     Indicates insufficient memory for:
     *                                     new double[initialCapacity].
     **/
    public DoubleArraySeq(int initialCapacity) {
        manyItems = initialCapacity;
        data = new double[manyItems];
        size = 0;
    }

    /**
     * Add a new element to this sequence, after the current element.
     * If the new element would take this sequence beyond its current capacity,
     * then the capacity is increased before adding the new element.
     * 
     * @param element
     *                the new element that is being added
     * @postcondition
     *                A new copy of the element has been added to this sequence. If
     *                there was
     *                a current element, then the new element is placed after the
     *                current
     *                element. If there was no current element, then the new element
     *                is placed
     *                at the end of the sequence. In all cases, the new element
     *                becomes the
     *                new current element of this sequence.
     * @exception OutOfMemoryError
     *                             Indicates insufficient memory for increasing the
     *                             sequence's capacity.
     * @note
     *       An attempt to increase the capacity beyond
     *       Integer.MAX_VALUE will cause the sequence to fail with an
     *       arithmetic overflow.
     **/
    public void addAfter(double element) {
        if (!isCurrent() || size == currentIndex) {
            addElement(element, size);
        } else {
            addElement(element, currentIndex + 1);
        }
    }

    /**
     * Add a new element to this sequence, before the current element.
     * If the new element would take this sequence beyond its current capacity,
     * then the capacity is increased before adding the new element.
     * 
     * @param element
     *                the new element that is being added
     * @postcondition
     *                A new copy of the element has been added to this sequence. If
     *                there was
     *                a current element, then the new element is placed before the
     *                current
     *                element. If there was no current element, then the new element
     *                is placed
     *                at the start of the sequence. In all cases, the new element
     *                becomes the
     *                new current element of this sequence.
     * @exception OutOfMemoryError
     *                             Indicates insufficient memory for increasing the
     *                             sequence's capacity.
     * @note
     *       An attempt to increase the capacity beyond
     *       Integer.MAX_VALUE will cause the sequence to fail with an
     *       arithmetic overflow.
     **/
    public void addBefore(double element) {
        if (!isCurrent() || currentIndex == 0) {
            addElement(element, 0);
        }
        /*
         * else if(currentIndex == 0) {
         * addElement(element, currentIndex);
         * }
         */
        else {
            addElement(element, currentIndex);
        }
    }

    /**
     * Place the contents of another sequence at the end of this sequence.
     * 
     * @param addend
     *               a sequence whose contents will be placed at the end of this
     *               sequence
     * @precondition
     *               The parameter, addend, is not null.
     * @postcondition
     *                The elements from addend have been placed at the end of
     *                this sequence. The current element of this sequence remains
     *                where it
     *                was, and the addend is also unchanged.
     * @exception NullPointerException
     *                                 Indicates that addend is null.
     * @exception OutOfMemoryError
     *                                 Indicates insufficient memory to increase the
     *                                 size of this sequence.
     * @note
     *       An attempt to increase the capacity beyond
     *       Integer.MAX_VALUE will cause an arithmetic overflow
     *       that will cause the sequence to fail.
     **/
    public void addAll(DoubleArraySeq addend) {
        int element = currentIndex;
        DoubleArraySeq temp = addend.clone();
        for (int i = 0; i < temp.size; i++) {
            addElement(temp.data[i], size);
        }
        currentIndex = element;
    }

    /**
     * Move forward, so that the current element is now the next element in
     * this sequence.
     * 
     * @precondition
     *               isCurrent() returns true.
     * @postcondition
     *                If the current element was already the end element of this
     *                sequence
     *                (with nothing after it), then there is no longer any current
     *                element.
     *                Otherwise, the new element is the element immediately after
     *                the
     *                original current element.
     * @exception IllegalStateException
     *                                  Indicates that there is no current element,
     *                                  so
     *                                  advance may not be called.
     **/
    public void advance() {
        if (isCurrent()) {
            if (currentIndex == size) {
                currentIndex = 0;
            } else {
                currentIndex++;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Generate a copy of this sequence.
     * 
     * @return
     *         The return value is a copy of this sequence. Subsequent changes to
     *         the
     *         copy will not affect the original, nor vice versa.
     * @exception OutOfMemoryError
     *                             Indicates insufficient memory for creating the
     *                             clone.
     **/
    public DoubleArraySeq clone() {
        DoubleArraySeq clone = new DoubleArraySeq(this.manyItems);
        clone.currentIndex = this.currentIndex;
        clone.data = this.data.clone();
        clone.size = this.size;
        return clone;
    }

    /**
     * Create a new sequence that contains all the elements from one sequence
     * followed by another.
     * 
     * @param s1
     *           the first of two sequences
     * @param s2
     *           the second of two sequences
     * @precondition
     *               Neither s1 nor s2 is null.
     * @return
     *         a new sequence that has the elements of s1 followed by the
     *         elements of s2 (with no current element)
     * @exception NullPointerException
     *                                 Indicates that one of the arguments is null.
     * @exception OutOfMemoryError
     *                                 Indicates insufficient memory for the new
     *                                 sequence.
     * @note
     *       An attempt to create a sequence with a capacity beyond
     *       Integer.MAX_VALUE will cause an arithmetic overflow
     *       that will cause the sequence to fail.
     **/
    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) {
        DoubleArraySeq merge = new DoubleArraySeq(s1.size + s2.size);
        for (int i = 0; i < s1.size + s2.size; i++) {
            if (i < s1.size) {
                merge.addAfter(s1.data[i]);
            } else {
                merge.addAfter(s2.data[i - s1.size]);
            }
        }
        merge.currentIndex = -1;
        return merge;
    }

    /**
     * Change the current capacity of this sequence.
     * 
     * @param minimumCapacity
     *                        the new capacity for this sequence
     * @postcondition
     *                This sequence's capacity has been changed to at least
     *                minimumCapacity.
     *                If the capacity was already at or greater than
     *                minimumCapacity,
     *                then the capacity is left unchanged.
     * @exception OutOfMemoryError
     *                             Indicates insufficient memory for: new
     *                             int[minimumCapacity].
     **/
    public void ensureCapacity(int minimumCapacity) {
        if (manyItems < minimumCapacity) {
            resize(minimumCapacity);
        }
    }

    /**
     * Accessor method to get the current capacity of this sequence.
     * The add method works efficiently (without needing
     * more memory) until this capacity is reached.
     * 
     * @return
     *         the current capacity of this sequence
     **/
    public int getCapacity() {
        return manyItems;
    }

    /**
     * Accessor method to get the current element of this sequence.
     * 
     * @precondition
     *               isCurrent() returns true.
     * @return
     *         the current element of this sequence
     * @exception IllegalStateException
     *                                  Indicates that there is no current element,
     *                                  so
     *                                  getCurrent may not be called.
     **/
    public double getCurrent() {
        if (isCurrent()) {
            return data[currentIndex];
        }
        else {
            throw new IllegalStateException();
        }
    }

    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the
     * getCurrent method.
     * 
     * @return
     *         true (there is a current element) or false (there is no current
     *         element at the moment)
     **/
    public boolean isCurrent() {
        if (currentIndex >= 0) {
            if (data[currentIndex] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the current element from this sequence.
     * 
     * @precondition
     *               isCurrent() returns true.
     * @postcondition
     *                The current element has been removed from this sequence, and
     *                the
     *                following element (if there is one) is now the new current
     *                element.
     *                If there was no following element, then there is now no
     *                current
     *                element.
     * @exception IllegalStateException
     *                                  Indicates that there is no current element,
     *                                  so
     *                                  removeCurrent may not be called.
     **/
    public void removeCurrent() {
        if (isCurrent()) {
            double[] temp = data.clone();
            for (int i = 0; i < manyItems; i++) {
                if (i == manyItems - 1) {

                } else if (i >= currentIndex) {
                    temp[i] = data[i + 1];
                } else {
                    temp[i] = data[i];
                }
            }
            data = temp.clone();
            size--;
        }
        else {
            throw new IllegalStateException();
        }
    }

    /**
     * Determine the number of elements in this sequence.
     * 
     * @return
     *         the number of elements in this sequence
     **/

    public int size() {
        if (size >= 0) {
            return size;
        }
        return 0;
    }

    /**
     * Set the current element at the front of this sequence.
     * 
     * @postcondition
     *                The front element of this sequence is now the current element
     *                (but
     *                if this sequence has no elements at all, then there is no
     *                current
     *                element).
     **/
    public void start() {
        if (size >= 0) {
            currentIndex = 0;
        }
    }

    /**
     * Reduce the current capacity of this sequence to its actual size (i.e., the
     * number of elements it contains).
     * 
     * @postcondition
     *                This sequence's capacity has been changed to its current size.
     * @exception OutOfMemoryError
     *                             Indicates insufficient memory for altering the
     *                             capacity.
     **/
    public void trimToSize() {
        if (size < manyItems) {
            resize(size);
        }
    }

    public String toString() {
        String array = "<";
        for (int i = 0; i < size; i++) {
            if (currentIndex == i) {
                array += "[";
            }
            if (data[i] != 0) {
                array += data[i];
            }
            if (currentIndex == i) {
                array += "]";
            }
            if (i < manyItems - 1 && data[i + 1] != 0) {
                array += ", ";
            }
        }
        array += ">";
        return array;
    }

    public boolean equals(Object equals) {
        boolean equal = false;
        DoubleArraySeq temp = (DoubleArraySeq) equals;
        if (this.size == temp.size && temp.size == 0) {
            return true;
        }
        if (this.size == temp.size) {
            for (int i = 0; i < temp.size; i++) {
                equal = this.data[i] == temp.data[i];
                if (!equal) {
                    return equal;
                }
            }
        }
        if (this.currentIndex != temp.currentIndex) {
            equal = false;
        }
        return equal;
    }

    /**
     * Increase the capacity of this sequence by one.
     *
     * @postcondition
     *                The sequence is recreated with the capacity of one more
     *                element.
     */
    public void addCapacity() {
        double[] temp = data;
        data = new double[++manyItems];
        for (int i = 0; i < temp.length; i++) {
            data[i] = temp[i];
        }
    }

    /**
     * Change the capacity of this sequence to the specified value.
     *
     * @postcondition
     *                The sequence is recreated with the capacity specified by the
     *                parameter. If there are values that no longer fit in the
     *                resized sequence, they are deleted.
     * 
     * @param cap
     *            the new capacity of the sequence.
     */
    public void resize(int cap) {
        double[] temp = data;
        data = new double[cap];
        if (cap != 0) {
            for (int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
        manyItems = cap;
        if (size > cap) {
            size = cap;
        }
    }

    /**
     * Add a specified element to the sequence at a specified index.
     * 
     * @param value
     *              value to be added to the sequence.
     * @param index
     *              index of the new value.
     */
    public void addElement(double value, int index) {
        ensureCapacity(++size);
        double[] temp = data.clone();
        for (int i = 0; i < manyItems; i++) {
            if (i == index) {
                temp[i] = value;
            } else if (i > index) {
                temp[i] = data[i - 1];
            } else {
                temp[i] = data[i];
            }
        }
        data = temp.clone();
        currentIndex = index;
        if (manyItems == 11 && size == 11) {
            ensureCapacity(size + 1);
        }
    }
}