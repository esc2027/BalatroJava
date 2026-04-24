public class Timer {

    public static final int DEFAULT_DELAY = 20;

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void typeln(String input) {
        boolean isInEscape = false;
        for(char c : input.toCharArray()) {

            if(c == '\u001b') isInEscape = true;

            if(!isInEscape) sleep(DEFAULT_DELAY);
            System.out.print(c);

            if(c == 'm' && isInEscape) isInEscape = false;
        }
        System.out.println();
    }

    public static void type(String input) {
        boolean isInEscape = false;
        for(char c : input.toCharArray()) {

            if(c == '\u001b') isInEscape = true;

            if(!isInEscape) sleep(DEFAULT_DELAY);
            System.out.print(c);

            if(c == 'm' && isInEscape) isInEscape = false;
        }
    }
}
