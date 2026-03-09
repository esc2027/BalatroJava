import java.util.HashMap;

public class Color {
    public static final String RESETCOLOR = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[97m";

    public static final String REDBG = "\u001B[41m";
    public static final String YELLOWBG = "\u001B[43m";
    public static final String BLUEBG = "\u001B[44m";

    public static final String WHITEBG = "\u001b[107m";

    public static String red(Object input) {
        return RED + input + RESETCOLOR;
    }

    public static String green(Object input) {
        return GREEN + input + RESETCOLOR;
    }

    public static String yellow(Object input) {
        return YELLOW + input + RESETCOLOR;
    }

    public static String blue(Object input) {
        return BLUE + input + RESETCOLOR;
    }

    public static String purple(Object input) {
        return PURPLE + input + RESETCOLOR;
    }

    public static String black(Object input) {
        return BLACK + input + RESETCOLOR;
    }

    public static String white(Object input) {
        return WHITE + input + RESETCOLOR;
    }

    public static String redbg(Object input) {
        return REDBG + input + RESETCOLOR;
    }

    public static String yellowbg(Object input) {
        return YELLOWBG + input + RESETCOLOR;
    }

    public static String bluebg(Object input) {
        return BLUEBG + input + RESETCOLOR;
    }

    public static String whitebg(Object input) {
        return WHITEBG + input + RESETCOLOR;
    }
}
