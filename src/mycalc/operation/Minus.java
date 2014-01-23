package mycalc.operation;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Minus extends BaseOperation implements Operand {

    public Minus(Operand left, Operand right) {
        super(left, right);
    }

    @Override
    public long value() {
        return left.value() - right.value();
    }
}