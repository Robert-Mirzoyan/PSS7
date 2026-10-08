public class ArrayList<E> implements List<E> {

    public static final int CAPACITY = 16;

    private E[] data;
    private int size;

    public ArrayList() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
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
    public E get(int index) {
        checkIndex(index, size);
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, size);
        E previous = data[index];
        data[index] = element;
        return previous;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, size + 1);

        if (size == data.length) {
            resize(Math.max(1, 2 * data.length));
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);
        E removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    @SuppressWarnings("unchecked")
    protected void resize(int capacity) {
        E[] replacement = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            replacement[i] = data[i];
        }

        data = replacement;
    }

    protected void checkIndex(int index, int upperBound) {
        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(
                    "Illegal index: " + index);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }

            result.append(data[i]);
        }

        return result.append("]").toString();
    }
}
