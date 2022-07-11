package practice;

import getset.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

    @Test
    public void testSum() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.sum(10.00, 25.00), 35.00, "Incorrect result! Please check method implementation!");
    }

    @Test
    public void testDivide() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.divide(10.00, 2.00), 5.00);
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.multiply(10.00, 25.00), 250.00);
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.subtract(10.00, 25.00), -15.00);
    }
}
