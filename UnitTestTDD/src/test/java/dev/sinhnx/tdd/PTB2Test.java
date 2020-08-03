package dev.sinhnx.tdd;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import java.util.Arrays;

public class PTB2Test {
    @Test
    public void giaiPTB2Test1() {
        try {
            PTB2 b21 = new PTB2(1, 2, 1);
            double[] result = b21.giaiPT();
            double[] expected = { -1 };
            assertTrue(Arrays.equals(expected, result));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void giaiPTB2Test2() {
        try {
            PTB2 b21 = new PTB2(1, 1, 9);
            double[] result = b21.giaiPT();
            Assert.assertNull(result);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void giaiPTB2Test3() {
        try {
            PTB2 b21 = new PTB2(1, -4, 3);
            double[] result = b21.giaiPT();
            double[] expected = { 1, 3 };
            assertTrue(Arrays.equals(expected, result));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void giaiPTB2Test4() {
        try {
            PTB2 b21 = new PTB2(1, -6, 5);
            double[] result = b21.giaiPT();
            double[] expected = { 1, 5 };
            assertTrue(Arrays.equals(expected, result));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void validPTB2Test() {
        try {
            PTB2 b21 = new PTB2(0, -6, 5);
        } catch (Exception e) {
            assertTrue(e.getMessage().toString().equals("Khong phai la bac 2"));
        }
    }
}