// Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int _capacity) {
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
    first = 0;
    last = 0;
    fillCount = 0;
    capacity = _capacity;
    rb = new double[_capacity];
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    // is there room?
    if (isFull()) {
        throw new RuntimeException("Ring buffer overflow");
    }
    rb[last] = x;
    last++;
    if (last == capacity) {
      last = 0;
    }
    fillCount++;
  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first.
    if (isEmpty()) {
        throw new RuntimeException("Ring buffer overflow");
    }
    double elementToReturn = rb[first];
    first++;
    fillCount--;
    if (first == capacity) {
        first = 0;
    }
    return elementToReturn;
}

  /** Return oldest item, but don't remove it. */
  public double peek() {
    // TODO: Return the first item. None of your instance variables should change.
    return rb[first];
  }

}
