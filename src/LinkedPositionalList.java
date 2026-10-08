public class LinkedPositionalList<E>
        implements PositionalList<E> {

    private static class Node<E>
            implements Position<E> {

        private E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(
                E element,
                Node<E> previous,
                Node<E> next) {

            this.element = element;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public E getElement() {
            if (next == null) {
                throw new IllegalStateException(
                        "Position is no longer valid");
            }

            return element;
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    @SuppressWarnings("unchecked")
    private Node<E> validate(Position<E> position) {
        if (!(position instanceof Node<?>)) {
            throw new IllegalArgumentException(
                    "Invalid position");
        }

        Node<E> node = (Node<E>) position;

        if (node.next == null) {
            throw new IllegalArgumentException(
                    "Position is no longer valid");
        }

        return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }

        return node;
    }

    private Position<E> addBetween(
            E element,
            Node<E> previous,
            Node<E> next) {

        Node<E> newest =
                new Node<>(element, previous, next);

        previous.next = newest;
        next.previous = newest;
        size++;

        return newest;
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
    public Position<E> first() {
        return position(header.next);
    }

    @Override
    public Position<E> last() {
        return position(trailer.previous);
    }

    @Override
    public Position<E> before(Position<E> position) {
        Node<E> node = validate(position);
        return this.position(node.previous);
    }

    @Override
    public Position<E> after(Position<E> position) {
        Node<E> node = validate(position);
        return this.position(node.next);
    }

    @Override
    public Position<E> addFirst(E element) {
        return addBetween(
                element,
                header,
                header.next);
    }

    @Override
    public Position<E> addLast(E element) {
        return addBetween(
                element,
                trailer.previous,
                trailer);
    }

    @Override
    public Position<E> addBefore(
            Position<E> position,
            E element) {

        Node<E> node = validate(position);

        return addBetween(
                element,
                node.previous,
                node);
    }

    @Override
    public Position<E> addAfter(
            Position<E> position,
            E element) {

        Node<E> node = validate(position);

        return addBetween(
                element,
                node,
                node.next);
    }

    @Override
    public E set(
            Position<E> position,
            E element) {

        Node<E> node = validate(position);
        E previousElement = node.element;
        node.element = element;

        return previousElement;
    }

    @Override
    public E remove(Position<E> position) {
        Node<E> node = validate(position);

        node.previous.next = node.next;
        node.next.previous = node.previous;
        size--;

        E removedElement = node.element;

        node.element = null;
        node.previous = null;
        node.next = null;

        return removedElement;
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

        result.append("]");
        return result.toString();
    }
}
