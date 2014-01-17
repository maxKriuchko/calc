package mycalc;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Plus extends BaseOperation implements Operand {

    public Plus(Operand left, Operand right) {
        super(left, right);
    }

    @Override
    public int value() {
        return left.value() + right.value();
    }
}