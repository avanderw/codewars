package net.avdw.codewars;

public class BeginnerSumOfNumbers {
    public int GetSum(int a, int b)
    {
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        if (min == max) {
            return a;
        }

        int sum = 0;
        for (int i =min ; i <= max;i++)
        sum += i;

        return sum;
    }
}
