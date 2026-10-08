public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;

    private final E[] data;
    private int topIndex = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public void push(E element) {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }

        data[++topIndex] = element;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }

        return data[topIndex];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }

        E answer = data[topIndex];
        data[topIndex] = null;
        topIndex--;
        return answer;
    }
}
