package net.avdw.codewars.seven;

import org.junit.Test;

import static org.junit.Assert.*;

public class PopGrowthTest {
    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        System.out.println("Fixed Tests: nbYear");
        testing(PopGrowth.nbYear(1500, 5, 100, 5000),15);
        testing(PopGrowth.nbYear(1500000, 2.5, 10000, 2000000), 10);
        testing(PopGrowth.nbYear(1500000, 0.25, 1000, 2000000), 94);
    }
}