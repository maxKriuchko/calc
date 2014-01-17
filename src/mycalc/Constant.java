package mycalc;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Constant implements Operand {
    private final int value;

    public Constant(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }
}
