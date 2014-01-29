package mycalc.polishNotation;

import mycalc.exceptions.WrongArgumentException;
import mycalc.operation.Constant;
import mycalc.operation.Operand;
import mycalc.operation.Variable;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class EvaluateTest {
    @Test
    public void testIsOperator() throws Exception {
        Evaluate evaluate = new Evaluate();
        Assert.assertFalse(evaluate.isOperator("a"));
        Assert.assertFalse(evaluate.isOperator("$%#$"));
        Assert.assertTrue(evaluate.isOperator("+"));
        Assert.assertTrue(evaluate.isOperator("-"));
        Assert.assertTrue(evaluate.isOperator("/"));
        Assert.assertTrue(evaluate.isOperator("("));
        Assert.assertTrue(evaluate.isOperator(")"));
        Assert.assertTrue(evaluate.isOperator("*"));

    }

    @Test
    public void testIsInteger() throws Exception {
        Evaluate evaluate = new Evaluate();
        Assert.assertFalse(evaluate.isInteger("wer"));
        Assert.assertFalse(evaluate.isInteger("#%r"));
        Assert.assertFalse(evaluate.isInteger("x"));
        Assert.assertTrue(evaluate.isInteger("1"));
        Assert.assertTrue(evaluate.isInteger("123"));
    }

    @Test
    public void testSplit() throws Exception {
        String function = "100000*x*x/(x-1)";
        Evaluate evaluate = new Evaluate();
        List<String> splitter = evaluate.split(function);
        Assert.assertEquals(splitter.get(0), "100000");
        Assert.assertEquals(splitter.get(1), "*");
        Assert.assertEquals(splitter.get(2), "x");
        Assert.assertEquals(splitter.get(3), "*");
        Assert.assertEquals(splitter.get(4), "x");
        Assert.assertEquals(splitter.get(5), "/");
        Assert.assertEquals(splitter.get(6), "(");
        Assert.assertEquals(splitter.get(7), "x");
        Assert.assertEquals(splitter.get(8), "-");
        Assert.assertEquals(splitter.get(9), "1");
        Assert.assertEquals(splitter.get(10), ")");
    }

    @Test
    public void testStringToPolish() throws Exception {
        Evaluate evaluate = new Evaluate();
        List<String> listOfStrings= evaluate.stringToPolish("(8+2*5*x)");
        Assert.assertEquals(listOfStrings.get(0), "8");
        Assert.assertEquals(listOfStrings.get(1), "2");
        Assert.assertEquals(listOfStrings.get(2), "5");
        Assert.assertEquals(listOfStrings.get(3), "x");
        Assert.assertEquals(listOfStrings.get(4), "*");
        Assert.assertEquals(listOfStrings.get(5), "*");
        Assert.assertEquals(listOfStrings.get(6), "+");
    }

    @Test
    public void testCalculator() throws Exception {
        List<String> elements =  Arrays.asList("8","2", "5", "*", "+", "1", "3", "2", "*", "+", "4", "-", "/");
        Operand operation = new Evaluate().calculator(elements);
        Assert.assertEquals(6, operation.value());
    }

    @Test
    public void testCalculatorVariable() throws Exception {
        List<String> elements =  Arrays.asList("8","2", "5", "x", "*", "*", "+");
        Operand operation = new Evaluate().calculator(elements);
        Variable.myMap.put("x", 1);
        Assert.assertEquals(18, operation.value());
    }

    @Test
    public void testStackOperation () throws Exception{
        Evaluate evaluate = new Evaluate();
        Operand sumResult = evaluate.stackOperation("+", new Constant(5), new Constant(2));
        Assert.assertEquals(7, sumResult.value());
        Operand minResult = evaluate.stackOperation("-", new Constant(5), new Constant(2));
        Assert.assertEquals(3, minResult.value());
        Operand divideResult = evaluate.stackOperation("/", new Constant(6), new Constant(2));
        Assert.assertEquals(3, divideResult.value());
        Operand multipleResult = evaluate.stackOperation("*", new Constant(6), new Constant(2));
        Assert.assertEquals(12, multipleResult.value());
        try {
            evaluate.stackOperation("%", new Constant(6), new Constant(2));
            Assert.fail("Exception expected but not thrown");
        } catch (WrongArgumentException e) {
            Assert.assertEquals("Wrong argument", e.getMessage() );
        }
    }
}
