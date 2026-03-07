import java.util.Scanner;

public class Parser {
    Scanner scanner;

    public Parser() {
        scanner = new Scanner(System.in);
    }

    public Hand parse(Player player) {
        String input = scanner.nextLine();

        char commandChar = input.toLowerCase().charAt(0);
        input = input.substring(1).trim();

        if(commandChar == 'p') return player.getDeck().parseHand(input);
        if(commandChar == 'd') return player.getDeck().parseDiscard(input);

        if(commandChar == 'q') {
            System.out.println("Goodbye.");
            System.exit(0);
        }
        return null;
    }
}
