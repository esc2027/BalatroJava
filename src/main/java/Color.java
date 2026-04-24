public class Color {
    public static final String RESETCOLOR = "\u001B[0m";

    public static final String BLACK = "\u001b[30m";
    public static final String RED = "\u001b[38;2;255;66;63m";
    public static final String GREEN = "\u001b[32m";
    public static final String YELLOW = "\u001b[38;2;242;203;0m";
    public static final String BLUE = "\u001b[38;2;5;130;220m";
    public static final String PURPLE = "\u001b[35m";
    public static final String WHITE = "\u001b[97m";
    public static final String ORANGE = "\u001b[38;2;190;120;0m";
    public static final String GRAY = "\u001b[38;2;80;90;95m";

    public static final String REDBG = "\u001b[41m";
    public static final String YELLOWBG = "\u001b[43m";
    public static final String BLUEBG = "\u001b[44m";

    public static final String WHITEBG = "\u001b[107m";

    public static String red(Object input) {
        return RED + input + RESETCOLOR;
    }

    public static String orange(Object input) {
        return ORANGE + input + RESETCOLOR;
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

    public static String gray(Object input) {
        return GRAY + input + RESETCOLOR;
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
