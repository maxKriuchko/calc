package stack;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */

public class LinkedStack implements Stack {
    private Node head;
    private int size;

    private static class Node {
        private Object element;
        private Node reference;

        private Node(Object element, Node reference) {
            this.element = element;
            this.reference = reference;
        }
    }

    @Override
    public void push(Object element) {
        head = new Node(element, head);
        size ++;
    }

    @Override
    public Object pop() {
        if (head == null){
            return null;
        }
        Object result = head.element;
        head = head.reference;
        size --;
        return result;
    }

    @Override
    public Object peek() {
        if (head == null){
            return null;
        }
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}

