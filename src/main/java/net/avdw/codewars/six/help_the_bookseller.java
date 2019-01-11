package net.avdw.codewars.six;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class help_the_bookseller {
    public static class StockList {

        // 1st parameter is the stocklist (L in example),
        // 2nd parameter is list of categories (M in example)
        public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {
            if (lstOfArt.length == 0) {
                return "";
            }
            if (lstOf1stLetter.length == 0) {
                return "";
            }
            Map<String, Integer> map = new HashMap<>();
            for (String letter: lstOf1stLetter) {
                map.put(letter, 0);
                for (String art:lstOfArt) {
                    Scanner scanner = new Scanner(art);
                    if (scanner.next().startsWith(letter)) {
                        map.put(letter, map.get(letter) + scanner.nextInt());
                    }
                }
            }

            StringBuilder ret = new StringBuilder();
            for (String letter : lstOf1stLetter) {
                Integer value = 0;
                if (map.containsKey(letter)) {
                    value = map.get(letter);
                }
                ret.append(String.format("(%s : %s) - ", letter, map.get(letter)));
            }
            ret = new StringBuilder(ret.substring(0, ret.length() - 3));
            return ret.toString();
        }
    }
}
