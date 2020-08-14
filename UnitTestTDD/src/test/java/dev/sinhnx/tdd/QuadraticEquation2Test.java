package dev.sinhnx.tdd;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import java.util.Arrays;

public class QuadraticEquation2Test {
    @Test
    public void solveEquationTest1() {
        try {
            final QuadraticEquation2 b21 = new QuadraticEquation2(1, 2, 1);
            final double[] result = b21.giaiPT();
            final double[] expected = { -1 };
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void solveEquationTest2() {
        try {
            final QuadraticEquation2 b21 = new QuadraticEquation2(1, 1, 9);
            final double[] result = b21.giaiPT();
            Assert.assertNull(result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void solveEquationTest3() {
        try {
            final QuadraticEquation2 b21 = new QuadraticEquation2(1, -4, 3);
            final double[] result = b21.giaiPT();
            final double[] expected = { 1, 3 };
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void solveEquationTest4() {
        try {
            final QuadraticEquation2 b21 = new QuadraticEquation2(1, -6, 5);
            final double[] result = b21.giaiPT();
            final double[] expected = { 1, 5 };
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void validQuadraticEquation2Test() {
        try {
            new QuadraticEquation2(0, -6, 5);
        } catch (final Exception e) {
            assertTrue(e.getMessage().toString().equals("Invalid Quadratic Equation 2"));
        }
    }
}