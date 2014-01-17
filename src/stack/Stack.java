package stack;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */

public interface Stack {
    void push(Object element);
    Object pop();
    Object peek();
    int size();
    boolean isEmpty();
}

