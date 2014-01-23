package mycalc;

import mycalc.operation.Operand;
import mycalc.operation.Variable;
import mycalc.polishNotation.Evaluate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/21/14
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Runner {
    public static int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20};
//    private static String function = "(8+2*5)/(1+3*2-4)";
    private static String function1 = "100000*x*x*x*x*x*x/(x-1)";
    public static void main(String[] args) {
        Evaluate eval = new Evaluate();
        List<Object> result = eval.stringToPolish(function1);
        Operand operand = eval.calculator(result);
        for (int item: values) {
            Variable.myMap.put("x", item);
            try {
                long equationResult = operand.value();
                if (equationResult < 2147483647) {
                    System.out.printf("x=" + item + " f(x)=" + equationResult + "\n");
                } else {
                    System.out.printf("x=" + item + " f(x)=" + "overloading" + "\n");
                }
            } catch (ArithmeticException e) {
                System.out.printf("x=" + item + " f(x)=" + "Divided by zero" + "\n");
            }
        }
    }
}

