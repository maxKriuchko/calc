package mycalc.operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/21/14
 * Time: 1:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class Variable implements Operand {
    private String variable;
    public static Map<String, Integer> myMap = new HashMap<String, Integer>();

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public long value() {
        return myMap.get(this.variable);
    }
}
