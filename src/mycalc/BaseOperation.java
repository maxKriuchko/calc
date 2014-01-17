package mycalc;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseOperation implements Operand {
    protected final Operand right;
    protected final Operand left;

    protected BaseOperation(Operand left, Operand right) {
        this.right = right;
        this.left = left;
    }
}
