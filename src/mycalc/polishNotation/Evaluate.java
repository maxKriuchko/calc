package mycalc.polishNotation;

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

    private static int priority (char c) {
        switch (c) {
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(':
                return 1;
            default:
                return 0;
        }
    }

    private static int getStackElemPriority() {
        int priority = 0;
        try {
            priority = Evaluate.priority((Character) operations.peek());
        }catch (Exception e) {
        }
        return priority;
    }

    private void stackOperation(String c) {
        Operand right = (Operand) calculator.pop();
        Operand left = (Operand) calculator.pop();
        if (c.equals("*")) {
            calculator.push(new Multiply(left, right));

        } else if (c.equals("+")) {
            calculator.push(new Plus(left, right));

        } else if (c.equals("/")) {
            calculator.push(new Divide(left, right));

        } else if (c.equals("-")) {
            calculator.push(new Minus(left, right));
        }
    }

    public List<Object> stringToPolish(String function){
        List<Object> polishList = new ArrayList<Object>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < function.length(); i++) {
            char c = function.charAt(i);
            if (Character.isDigit(c)) {
                for (int j = i; j < function.length(); j++) {
                    char c1 = function.charAt(j);
                    if (Character.isDigit(c1)) {
                        sb.append(c1);
                    } else {
                        String fullToken = sb.toString();
                        Integer tokenNumeric = Integer.parseInt(fullToken);
                        polishList.add(tokenNumeric);
                        sb.delete(0, sb.length());
                        i = j - 1;
                        break;
                    }
                }
            } else if (Character.isLetter(c)){
                polishList.add(String.valueOf(c));
            }
            else {
                if (c == '(') {
                    operations.push(c);
                }
                else if (c == ')') {
                    while (this.getStackElemPriority() > 1) {
                        polishList.add(String.valueOf(operations.pop()));
                    }
                    operations.pop();
                }
                else if (operations.size() >= 0 &&
                        this.getStackElemPriority() <= priority(c)) {
                    operations.push(c);
                }
                else if (this.getStackElemPriority() > priority(c)) {
                    while (this.getStackElemPriority() > priority(c)) {
                        polishList.add(String.valueOf(operations.pop()));
                    }
                    operations.push(c);
                }
            }
        }
        while (!operations.isEmpty()){
            polishList.add(String.valueOf(operations.pop()));
        }
        return polishList;
    }

    public Operand calculator(Iterable<Object> a) {
        for (Object token : a) {
            if (token instanceof Integer) {
                calculator.push(new Constant((Integer)token));
            } else if(token instanceof String) {
                String castedToken = (String) token;
                if (castedToken.matches("[A-Za-z]")) {
                    calculator.push(new Variable(castedToken));
                }
                else {
                    stackOperation(castedToken);
                }
            }
        }
        return (Operand) calculator.peek();
    }
}
