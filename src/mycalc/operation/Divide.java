package mycalc.operation;

import mycalc.exceptions.DivisonByZeroException;

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
    public long value() throws DivisonByZeroException{
        long divisor = right.value();
        if (divisor == 0) {
            throw new DivisonByZeroException("Division by zero");
        }
        return left.value()/divisor;
    }
}