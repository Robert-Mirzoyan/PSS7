public class DoublyLinkedBase<E> {

    private static class Node<E> {
        private E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(E element, Node<E> previous, Node<E> next) {
            this.element = element;
            this.previous = previous;
            this.next = next;
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size;

    public DoublyLinkedBase() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }

        return header.next.element;
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }

        return trailer.previous.element;
    }

    public void addFirst(E element) {
        addBetween(element, header, header.next);
    }

    public void addLast(E element) {
        addBetween(element, trailer.previous, trailer);
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        return remove(header.next);
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        return remove(trailer.previous);
    }

    private void addBetween(
            E element,
            Node<E> predecessor,
            Node<E> successor) {

        Node<E> newest =
                new Node<>(element, predecessor, successor);

        predecessor.next = newest;
        successor.previous = newest;
        size++;
    }

    private E remove(Node<E> node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        size--;

        E answer = node.element;
        node.element = null;
        node.previous = null;
        node.next = null;
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = header.next;

        while (current != trailer) {
            result.append(current.element);

            if (current.next != trailer) {
                result.append(", ");
            }

            current = current.next;
        }

        return result.append("]").toString();
    }
}
