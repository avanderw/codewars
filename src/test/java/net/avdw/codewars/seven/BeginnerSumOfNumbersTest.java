package net.avdw.codewars.seven;

import org.junit.Test;

import static org.junit.Assert.*;

public class BeginnerSumOfNumbersTest {
    BeginnerSumOfNumbers s = new BeginnerSumOfNumbers();

    @Test
    public void Test1()
    {
        assertEquals(-1, s.GetSum(0, -1));
        assertEquals(1, s.GetSum(0, 1));
    }
}