package mycalc;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: mkruichko
 * Date: 1/14/14
 * Time: 12:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalculatorTest {

    @Test
    public void testDivideValue() throws Exception {
        Assert.assertEquals(5, new Divide(new Constant(10), new Constant(2)).value());
    }

    @Test
    public void testPlusValue() throws Exception {
        Assert.assertEquals(12, new Plus(new Constant(10), new Constant(2)).value());
    }

    @Test
    public void testMinusValue() throws Exception {
        Assert.assertEquals(10, new Minus(new Constant(12), new Constant(2)).value());
    }

    @Test
    public void testMultiplyValue() throws Exception {
        Assert.assertEquals(20, new Multiply(new Constant(10), new Constant(2)).value());
    }
}