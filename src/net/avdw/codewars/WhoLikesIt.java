package net.avdw.codewars;

public class WhoLikesIt {
    public static String whoLikesIt(String... names) {
        if (names.length == 0) {
            return "no one likes this";
        }
        StringBuilder likes = new StringBuilder();
        switch (names.length) {
            case 1: likes.append(String.format("%s likes this", names[0])); break;
            case 2: likes.append(String.format("%s and %s like this", names[0], names[1])); break;
            case 3: likes.append(String.format("%s, %s and %s like this", names[0], names[1], names[2])); break;
            default:
                likes.append(String.format("%s, %s and %s others like this", names[0], names[1], names.length-2));
        }
        return likes.toString();
    }
}
