package mycalc.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/28/14
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DivisonByZeroException extends RuntimeException {
    public DivisonByZeroException(String s) {
        super(s);
    }

    public DivisonByZeroException() {}
}
