import java.util.Scanner;

public class Parser {
    Scanner scanner;

    public Parser() {
        scanner = new Scanner(System.in);
    }

    public Hand parse(Player player) {

        String input = "";
        while(input.equals("")) {
            input = scanner.nextLine();
        }

        char commandChar = input.toLowerCase().charAt(0);
        input = input.substring(1).replaceAll(" ", "");

        if(commandChar == 'q' || commandChar == 'e') {
            System.out.println("Goodbye.");
            System.exit(0);
        }

        if(input.matches("\\d+")) {
            if(input.length() <= 5) {
                boolean inputHasValidRange = true;
                for(char c : input.toCharArray()) {
                    int cValue = c - '0';
                    if(cValue == 0 || cValue > (char)player.getDeck().getHandSize()) inputHasValidRange = false;
                }
                if(inputHasValidRange) {
                    if(commandChar == 'p') return player.getDeck().parseHand(input);
                    if(commandChar == 'd') return player.getDeck().parseDiscard(input);
                }
            }
        }

        System.out.println("Please enter a valid command.");
        return parse(player);
    }
}
