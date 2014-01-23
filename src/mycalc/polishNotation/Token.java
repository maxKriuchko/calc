package mycalc.polishNotation;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/22/14
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Token {
    private Object value;
    private boolean isNumber;

    public Token(Number value) {
        this.value = value;
        this.isNumber = true;
    }

    public Token(Character c) {
        this.value = c;
        this.isNumber = false;
    }

    public boolean isNumber() {
        return isNumber;
    }
}
