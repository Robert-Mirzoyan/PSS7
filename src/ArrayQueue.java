public class ArrayQueue<E> implements Queue<E> {

    public static final int CAPACITY = 1000;

    private final E[] data;
    private int frontIndex;
    private int size;

    public ArrayQueue() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E element) {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }

        int available = (frontIndex + size) % data.length;
        data[available] = element;
        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }

        return data[frontIndex];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E answer = data[frontIndex];
        data[frontIndex] = null;
        frontIndex = (frontIndex + 1) % data.length;
        size--;
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }

            result.append(data[(frontIndex + i) % data.length]);
        }

        return result.append("]").toString();
    }
}
