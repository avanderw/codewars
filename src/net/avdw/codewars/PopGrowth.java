package net.avdw.codewars;

public class PopGrowth {
    public static int nbYear(int p0, double percent, int aug, int p) {
        percent /= 100;
        percent += 1;
        int count = 0;
        while (p0 < p) {
            p0 *= percent;
            p0 += aug;
            count++;
        }

        return count;
    }
}
