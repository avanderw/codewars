package net.avdw.codewars;

import java.util.HashMap;
import java.util.Map;

public class FindUniqueNumber {
    public static double findUniq(double arr[]) {
        Map<Double, Integer> freq = new HashMap<>();
        for (double item:arr) {
            freq.putIfAbsent(item, 0);
            freq.put(item, freq.get(item) +1);
        }
        return freq.entrySet().stream().filter(e-> e.getValue() == 1).findAny().map(Map.Entry::getKey).get();
    }
}
