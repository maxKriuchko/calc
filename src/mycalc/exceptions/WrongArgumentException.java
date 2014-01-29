package mycalc.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/28/14
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class WrongArgumentException extends RuntimeException {
    public WrongArgumentException() {}

    public WrongArgumentException(String message) {
        super(message);
    }
}

