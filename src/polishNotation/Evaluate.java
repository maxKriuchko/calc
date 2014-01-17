package polishNotation;

import stack.LinkedStack;

import java.util.ArrayList;

import mycalc.*;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Evaluate {
    public static int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static String function = "(8+2*5)/(1+3*2-4)";
    private static String function1 = "100000*x*x*x*x*x*x/(x-1)";
//    private static String function2 = "100000*2*2*2*2*2*2/(1-1)";
    private static LinkedStack operations = new LinkedStack();
    private static ArrayList<String> polishList = new ArrayList<String>();
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
            priority = priority((Character) operations.peek());
        }catch (Exception e) {
//            System.out.println("Stack is null");
        }
        return priority;
    }

    private static void stackOperation(String c) {
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

    private static String concatenate(ArrayList<Character> list) {
        String a = "";
        for (int item =0; item < list.size(); item ++) {
            a +=list.get(item);
        }
        return a;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static int StringEvaluation(String function){
        ArrayList<Character> newChar = new ArrayList();
        for (int i = 0; i < function.length(); i++) {
            char c = function.charAt(i);
            if (Character.isDigit(c)) {
                for (int j = i; j < function.length(); j++) {
                    char c1 = function.charAt(j);
                    if (Character.isDigit(c1)) {
                        newChar.add(c1);
                    } else {
                        polishList.add(concatenate(newChar));
                        newChar.clear();
                        i = j - 1;
                        break;
                    }
                }
            }
            else {
                if (c == '(') {
                    operations.push(c);
                }
                else if (c == ')') {
                    while (getStackElemPriority() > 1) {
                        polishList.add(String.valueOf(operations.pop()));
                    }
                    operations.pop();
                }
                else if (operations.size() >= 0 &&
                        getStackElemPriority() <= priority(c)) {
                    operations.push(c);
                }
                else if (getStackElemPriority() >= priority(c)) {
                    while (getStackElemPriority() >= priority(c)) {
                        polishList.add(String.valueOf(operations.pop()));
                    }
                    operations.push(c);
                }
            }
        }
        while (!operations.isEmpty()){
            polishList.add(String.valueOf(operations.pop()));
        }
        for (int item=0; item < polishList.size(); item ++) {
            if (isInteger(polishList.get(item))) {
                calculator.push(new Constant(Integer.parseInt(polishList.get(item))));
            }
            else {
                stackOperation(polishList.get(item));
            }
        }

        return ((Operand) calculator.peek()).value();
    }



    public static void main(String[] args){
//        int result = StringEvaluation(function2);
//        System.out.println(result);
        for (int item = 0; item < values.length; item ++) {
            String newString = function1.replace("x", String.valueOf(values[item]));
//            System.out.println(newString);
            int result = StringEvaluation(newString);
            System.out.println("x=" + values[item] + " f(x)=" + result);
        }
    }
}

