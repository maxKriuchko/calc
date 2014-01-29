package mycalc.polishNotation;

import mycalc.exceptions.WrongArgumentException;
import mycalc.operation.*;
import stack.LinkedStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Evaluate {
    private static LinkedStack operations = new LinkedStack();
    private static LinkedStack calculator = new LinkedStack();

    private static int currentPriority(String c) {
        if (c.equals("*") || c.equals("/")) {
            return 3;
        } else if (c.equals("+") || c.equals("-")) {
            return 2;
        } else if (c.equals("(")) {
            return 1;
        } else {
            return 0;
        }
    }

    public Operand stackOperation(String c, Operand left, Operand right) {
        if (c.equals("*")) {
            return new Multiply(left, right);
        } else if (c.equals("+")) {
            return new Plus(left, right);

        } else if (c.equals("/")) {
            return new Divide(left, right);

        } else if (c.equals("-")) {
            return new Minus(left, right);
        } else {
            throw new WrongArgumentException("Wrong argument");
        }
    }


    public boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") ||  c.equals("(") ||  c.equals(")");
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public List<String> split(String function) {
        List<String> list = new ArrayList<String>();
        String[] string = function.split("(?<=[-+*/()])|(?=[-+*/()])");
        for(String s : string) {
            if(s != null && s.length() > 0) {
                list.add(s);
            }
        }
        return list;
    }

    private int stackElementPriority() {
        int stackElementPriority = 0;
        if (operations.peek() != null) {
            return Evaluate.currentPriority((String) operations.peek());
        }
        return stackElementPriority;
    }


    public List<String> stringToPolish(String function){
        List<String> polishList = new ArrayList<String>();
        List<String> elements = split(function);
        for (String s: elements) {
            if (isOperator(s)) {
                if (s.equals("(")) {
                    operations.push(s);
                }
                else if (s.equals(")")) {
                    while (stackElementPriority() > 1) {
                        polishList.add(String.valueOf(operations.pop()));
                    }
                    operations.pop();
                }
                else if (stackElementPriority() <= currentPriority(s)) {
                    operations.push(s);
                }
                else if (stackElementPriority() > currentPriority(s)) {
                    while (stackElementPriority() >= currentPriority(s)) {
                        polishList.add(String.valueOf(operations.pop()));
                    }
                    operations.push(s);
                }
            } else {
                polishList.add(s);
            }
        }
        while (!operations.isEmpty()){
            polishList.add(String.valueOf(operations.pop()));
        }
        return polishList;
    }

    public Operand calculator(Iterable<String> a) {
        for (String token: a) {
            if (isInteger(token)) {
                calculator.push(new Constant(Integer.valueOf(token)));
            } else if (isOperator(token)) {
                Operand right = (Operand) calculator.pop();
                Operand left = (Operand) calculator.pop();
                calculator.push(stackOperation(token, left, right));
            } else {
                calculator.push(new Variable(token));
            }
        }
        return (Operand) calculator.peek();
    }
}
