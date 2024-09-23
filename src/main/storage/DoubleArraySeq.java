package storage;

public class DoubleArraySeq implements Cloneable  {

    public static final int DEFAULT_CAPACITY = 10;
    private double[] data;
    private int manyItems;
    private int currentIndex;
    private int size;

    public DoubleArraySeq() {
        manyItems = 10;
        data = new double[manyItems];
        currentIndex = 0;
        size = 0;
    }
    public DoubleArraySeq(int capacity) {
        manyItems = capacity;
        data = new double[manyItems];
        currentIndex = 0;
        size = 0;
    }

    public void addAfter(double element) {}
    public void addBefore(double element) {}
    public void addAll(DoubleArraySeq add) {
        for(double i : add.data) {
            if(i != 0) {addAfter(i);}
        }
    }
    public void advance() {currentIndex++;}
    public DoubleArraySeq clone() {return this;}
    public static DoubleArraySeq concatenation(DoubleArraySeq first, DoubleArraySeq second) {return new DoubleArraySeq();}
    public void ensureCapacity(int capacity) {
        if(manyItems < capacity) {
            resize(capacity);
        }
    }
    public int getCapacity() {return manyItems;}
    public double getCurrent() {return data[currentIndex];}
    public boolean isCurrent() {return data[currentIndex] != 0;}
    public void removeCurrent() {}
    public int size() {return size;}
    public void start() {if(size > 0) {currentIndex = 0;}}
    public void trimToSize() {
        if(size < manyItems) {
            resize(size);
        }
    }
    public String toString() {
        String array = "<";
        for(int i = 0; i < size; i++) {
            if(currentIndex == i) {array += "[";}
            if(data[i] != 0) {array += data[i];}
            if(currentIndex == i) {array += "]";}
            array += ", ";
        }
        array += ">";
        return array;
    }
    public boolean equals(Object equals) {
        DoubleArraySeq temp = (DoubleArraySeq) equals;
        return this.data == temp.data;
    }

    public void addCapacity() {
        double[] temp = data;
        data = new double[++manyItems];
        for(int i = 0; i < temp.length; i++) {
            data[i] = temp[i];
        }
    }
    public void resize(int cap) {
        double[] temp = data;
        data = new double[cap];
        if(cap != 0) {
            for(int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
        manyItems = cap;
        if(size > cap) {size = cap;}
    }
    
}