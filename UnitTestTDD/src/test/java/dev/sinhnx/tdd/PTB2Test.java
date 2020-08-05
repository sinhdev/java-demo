package dev.sinhnx.tdd;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import java.util.Arrays;

public class PTB2Test {
    @Test
    public void giaiPTB2Test1() {
        try {
            final PTB2 b21 = new PTB2(1, 2, 1);
            final double[] result = b21.giaiPT();
            final double[] expected = { -1 };
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void giaiPTB2Test2() {
        try {
            final PTB2 b21 = new PTB2(1, 1, 9);
            final double[] result = b21.giaiPT();
            Assert.assertNull(result);
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void giaiPTB2Test3() {
        try {
            final PTB2 b21 = new PTB2(1, -4, 3);
            final double[] result = b21.giaiPT();
            final double[] expected = { 1, 3 };
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void giaiPTB2Test4() {
        try {
            final PTB2 b21 = new PTB2(1, -6, 5);
            final double[] result = b21.giaiPT();
            final double[] expected = { 1, 5 };
            assertTrue(Arrays.equals(expected, result));
        } catch (final Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void validPTB2Test() {
        try {
            new PTB2(0, -6, 5);
        } catch (final Exception e) {
            assertTrue(e.getMessage().toString().equals("Khong phai la bac 2"));
        }
    }
}