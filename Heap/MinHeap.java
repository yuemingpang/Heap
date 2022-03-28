import java.util.NoSuchElementException;

/**
 * The implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

  public static final int INITIAL_CAPACITY = 13;

  private T[] backingArray;
  private int size;

  /**
  * This is the constructor that constructs a new MinHeap.
  *
  * Java does not allow for regular generic array creation,
  * so instead we cast a Comparable[] to a T[] to get the generic typing.
  */
  public MinHeap() {
    backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
  }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */  
  public void add(T data) {
    if (data == null) {
        throw new IllegalArgumentException();
    } else {
      if (size+1 < backingArray.length) {
        backingArray[size+1] = data;
        size++;
      } else if (size+1 == backingArray.length) {
        T[] backingArray2 = (T[]) new Comparable[2*(size+1)];
        for (int i = 1; i<size+1; i++) {
          backingArray2[i] = backingArray[i];
        }
        backingArray2[size+1] = data;
        size++;
        backingArray = backingArray2;
      }
      //reordering:
      upHeap(size);
      }     
  }
    
  private void upHeap(int child) {
    int parent = child/2;
    if (child>1 && backingArray[child].compareTo(backingArray[parent])<0) {
        swap(child, parent);
        upHeap(parent);
    }
  } 


    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
  public T remove() {
    if (size == 0) {
      throw new NoSuchElementException();
    } else {
      T remove = backingArray[1];
      backingArray[1] = backingArray[size];
      backingArray[size] = null;
      size--;
      downHeap(1);
      return remove;
    }
  }
    
  private void downHeap(int parent) {
    int child1 = 2*parent;
    int child2 = 2*parent+1;
    if (parent < size/2) {
      if (backingArray[parent].compareTo(backingArray[child1])>0 || backingArray[parent].compareTo(backingArray[child2])>0) {
        if (backingArray[child1].compareTo(backingArray[child2])<0) {
          swap(child1, parent);
          downHeap(child1);
        } else {
          swap(child2, parent);
          downHeap(child2);
        }
      }
    } else if (parent == size/2) {
      if (child2>backingArray.length || backingArray[child2] == null) {
        if (backingArray[parent].compareTo(backingArray[child1])>0) {
          swap(child1, parent);
        }
      } else {
        if (backingArray[child1].compareTo(backingArray[child2])<0) {
          swap(child1, parent);
        } else {
          swap(child2, parent);
        }
      }
    }
  }


    /**
     * Returns the backing array of the heap.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * @return The size of the list
     */
  public int size() {
    return size;
  }

   /**
     * Helper method to swap 2 elemnts.
     */
  private void swap(int i, int j) {
    T temp = backingArray[i];
    backingArray[i] = backingArray[j];
    backingArray[j] = temp;
  }
   
}