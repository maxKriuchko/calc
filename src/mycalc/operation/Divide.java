package mycalc.operation;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Divide extends BaseOperation implements Operand {

    public Divide(Operand left, Operand right) {
        super(left, right);
    }

    @Override
    public long value() throws ArithmeticException{
        return left.value()/right.value();
    }
}