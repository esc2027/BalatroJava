import java.util.Scanner;

public class Game {
    static Game game;
    private Player player;
    int gameState = 0; //0 = blind select, 1 = game, 2 = shop
    int chips, mult, hands, discards;
    int ante = 1;
    int round = 0;
    Blind smallBlind, bigBlind, bossBlind;
    Scanner scanner;
    int[][] titleBanner = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 4, 4, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 5, 0, 3, 4, 4, 0, 0, 3, 4, 4, 4, 4, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 3, 3, 2, 3, 3, 4, 4, 4, 4, 3, 3, 4, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 4, 3, 5, 5, 5, 5, 4, 4, 4, 3, 4, 4, 4, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 6, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 4, 4, 1, 1, 1, 3, 0, 0},
            {0, 0, 0, 0, 1, 8, 8, 1, 1, 0, 1, 8, 8, 1, 1, 8, 6, 1, 8, 8, 8, 8, 8, 1, 8, 8, 8, 8, 8, 1, 1, 1, 1, 1, 8, 8, 8, 1, 4, 0},
            {0, 0, 6, 0, 1, 8, 1, 8, 1, 0, 1, 8, 1, 8, 1, 8, 6, 1, 8, 8, 1, 8, 8, 1, 1, 1, 8, 1, 1, 8, 8, 8, 1, 8, 1, 1, 8, 1, 4, 0},
            {0, 6, 6, 0, 1, 8, 1, 8, 1, 1, 8, 1, 1, 8, 1, 8, 5, 1, 8, 1, 1, 1, 8, 1, 3, 1, 8, 1, 1, 8, 1, 8, 1, 8, 1, 1, 8, 1, 4, 0},
            {0, 6, 3, 0, 1, 8, 8, 8, 1, 3, 8, 1, 8, 8, 1, 8, 3, 1, 8, 1, 1, 1, 8, 1, 2, 1, 8, 1, 1, 8, 1, 8, 1, 8, 1, 1, 8, 1, 4, 0},
            {0, 2, 3, 0, 1, 8, 1, 1, 8, 1, 8, 8, 1, 8, 1, 8, 5, 1, 8, 8, 1, 8, 8, 1, 1, 1, 8, 1, 1, 8, 8, 1, 1, 8, 1, 1, 8, 1, 4, 4},
            {0, 2, 3, 4, 1, 8, 1, 1, 8, 1, 8, 1, 1, 8, 1, 8, 2, 1, 8, 1, 1, 1, 8, 8, 1, 1, 8, 1, 1, 8, 1, 8, 1, 8, 1, 1, 8, 1, 3, 4},
            {0, 3, 3, 4, 1, 8, 1, 2, 8, 1, 8, 1, 2, 8, 1, 8, 1, 1, 8, 8, 8, 8, 8, 8, 1, 1, 8, 1, 1, 8, 1, 8, 1, 8, 1, 1, 8, 1, 4, 3},
            {2, 3, 4, 6, 1, 8, 1, 1, 8, 1, 8, 1, 2, 8, 1, 8, 8, 8, 1, 8, 8, 8, 8, 8, 1, 1, 8, 1, 1, 8, 1, 8, 1, 8, 8, 8, 1, 1, 4, 3},
            {2, 3, 4, 1, 8, 8, 8, 8, 1, 1, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 7, 5, 3, 1, 1, 3, 1, 8, 1, 8, 1, 1, 1, 1, 1, 4, 3, 0},
            {3, 5, 4, 4, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 6, 3, 1, 1, 2, 1, 3, 3, 3, 3, 3, 3, 0, 0},
            {2, 3, 7, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 5, 6, 6, 6, 7, 7, 3, 4, 3, 2, 0, 0, 0},
            {0, 5, 6, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 2, 3, 4, 3, 3, 2, 0, 0, 0, 0, 0},
            {0, 0, 6, 5, 6, 6, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 5, 5, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 6, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 5, 0, 0, 0, 0, 0}
    };

    static void main() {
        game = new Game();
    }

    public Game() {
        Image.draw(titleBanner);

        player = new Player();
        scanner = new Scanner(System.in);


        gameLoop();
    }

    public void gameLoop() {
        while(true) {
            switch(gameState) {
                case 0:
                    blindSelect();
                    break;
                case 1:
                    switch(round) {
                        case 0 -> game(smallBlind);
                        case 1 -> game(bigBlind);
                        case 2 -> game(bossBlind);
                    }
                    break;
                case 2:
                    shop();
                    break;
                default:
                    gameState = 0;
                    break;
            }
        }
    }

    public void blindSelect() {
        if(round == 3) {
            round = 0;
            ante++;
        }

        if(round == 0) {
            smallBlind = new Blind(ante, Color.blue("Small Blind"), 1, 3);
            bigBlind = new Blind(ante, Color.yellow("Big Blind"), 1.5, 4);
            bossBlind = new Blind(ante, Color.purple("The Wall"), 4, 5);
            System.out.println(Color.white("==================== ANTE " + ante + " ====================\n================================================"));
        }

        player.getDeck().reshuffle();
        hands = discards = 4;

        gameState = 1;
    }

    public void game(Blind blind) {

        while(player.getRoundScore() < blind.getTargetScore()) {

            if(hands == 0) printLose();

            player.getDeck().fillHand();

            printUI(blind);

            String input = getInput();
            char commandState = parse(input);

            while(commandState == '\0') {
                input = getInput();
                commandState = parse(input);
            }

            input = input.substring(1).replaceAll(" ", "");

            switch(commandState) {
                case 'p':
                    hands--;

                    Hand hand = player.getDeck().parseHand(input);

                    System.out.println("Played a " + Color.green(hand.getHandType().getName()));
                    Timer.sleep(100);

                    chips = tallyChips(hand);
                    mult = hand.getMult();

                    player.getJokerDeck().playJokers(hand, 4);

                    System.out.print(Color.blue(chips));
                    Timer.sleep(100);
                    System.out.print(" x " + Color.red(mult));
                    Timer.sleep(100);
                    System.out.println(" = " + Color.white(chips*mult));
                    Timer.sleep(100);

                    System.out.println(Color.white("------------------------------------------------"));
                    player.changeScore(chips*mult);

                    chips = mult = 0;
                    break;
                case 'd':
                    if(discards > 0) {
                        discards--;
                        player.getDeck().parseDiscard(input);
                    } else System.out.println(Color.red("Out of discards"));

                    System.out.println(Color.white("------------------------------------------------"));
                    break;
            }
        }

        Timer.sleep(500);

        cashout(blind);

        player.setScore(0);
        round++;
        gameState = 2;
    }

    public void shop() {
        Shop shop = new Shop(2);
        shop.print(player);
        String input = getInput();
        char commandState = parse(input);
        while(commandState != 'c') {
            if(commandState == 'b') {
                int shopIndex = Integer.parseInt(input.substring(1).replaceAll(" ", ""))-1;
                if(shopIndex >= 0 && shopIndex < shop.getSize()) {
                    shop.buyJoker(shopIndex, player);
                } else {
                    System.out.println("Please enter a valid joker index.");
                }
            }
            input = scanner.nextLine();
            commandState = parse(input);
        }
        shop.endShop();
        gameState = 0;
    }

    public int tallyChips(Hand hand) {
        int chipsTally = hand.getChips();

        System.out.print("Chips: " + Color.blue(hand.getChips()));
        for(Card card : hand.getCards()) {
            System.out.print(" + " + Color.blue(card.getRank().getValue()));
            chipsTally += card.getRank().getValue();
            Timer.sleep(100);
        }
        System.out.println(" = " + Color.blue(chipsTally));

        return chipsTally;
    }

    public char parse(String input) {
        char commandChar = input.toLowerCase().charAt(0);

        input = input.substring(1).replaceAll(" ", "");
        char[] cardIndexes = input.toCharArray();

        if(commandChar == 'q' || commandChar == 'e') {
            System.out.println("Goodbye.");
            System.exit(0);
        }

        boolean inputHasValidRange = true;
        if(input.length() > 5) inputHasValidRange = false;


        if(gameState == 1) {
            if(!input.matches("\\d+")) inputHasValidRange = false;
            for(char c : cardIndexes) {
                int cValue = c - '0';
                if(cValue == 0 || cValue > (char)player.getDeck().getHandSize()) inputHasValidRange = false;
            }
            if(inputHasValidRange) {
                if(commandChar == 'p' || commandChar == 'd') return commandChar;
            }
        }
        if(gameState == 2) {
            if(input.length() == 1 && commandChar == 'b') return commandChar;
            if(input.length() == 0 && commandChar == 'c') return commandChar;
        }

        if(input.length() == 1 && commandChar == 'i') {
            int index = Integer.parseInt(input) - 1;
            if(index >= 0 && index < player.getJokerDeck().jokers.size()) {
                player.getJokerDeck().printJokerInfo(index);
            } else System.out.println("Please enter a valid joker index.");
        }
        else if(input.length() == 1 && commandChar == 's') {
            int index = Integer.parseInt(input) - 1;
            if(index >= 0 && index < player.getJokerDeck().jokers.size()) {

                Joker joker = player.getJokerDeck().getJoker(index);
                System.out.println("Are you sure you want to sell " + joker.getName() + " for " + Color.yellow("$" + joker.getSellPrice()) + "? (y/n)");
                String response = scanner.nextLine().trim();
                if(response.equals("y") || response.equals("yes")) {
                    player.getJokerDeck().sellJoker(index, player);
                    System.out.println("Sold " + joker.getName() + " for " + Color.yellow("$" + joker.getSellPrice()));
                    System.out.println("New balance " + Color.yellow("$" + player.getMoney()));
                } else System.out.println("Cancelled selling joker " + joker.getName());
            } else System.out.println("Please enter a valid joker index.");
        }
        else if(input.length() == 2 && commandChar == 's') {
            int firstIndex = Integer.parseInt(input.substring(0,1)) - 1;
            int secondIndex = Integer.parseInt(input.substring(1)) - 1;

            if(firstIndex >= 0 && firstIndex < player.getJokerDeck().jokers.size() && secondIndex >= 0 && secondIndex < player.getJokerDeck().jokers.size()) {
                Joker firstJoker = player.getJokerDeck().getJoker(firstIndex);
                Joker secondJoker = player.getJokerDeck().getJoker(secondIndex);
                player.getJokerDeck().swapJokers(firstIndex, secondIndex);
                System.out.println("Swapped " + firstJoker.getName() + " and " + secondJoker.getName());
                player.getJokerDeck().printJokers();
            } else System.out.println("Please enter a valid joker index.");
        }
        else if(commandChar == 'h') {
            System.out.println(Color.white("Commands:"));
            System.out.println(Color.white("  During round:"));
            System.out.println(Color.white("    \"p(cards)\"") + "   Play up to 5 cards from your hand. Example: \"p3478\" plays cards 3, 4, 7, and 8.");
            System.out.println(Color.white("    \"d(cards)\"") + "   Discard up to 5 cards from your hand. Example: \"d145\" discards cards 1, 4, and 5.");
            System.out.println(Color.white("  In shop:"));
            System.out.println(Color.white("    \"d(joker)\"") + "   Buy a joker from the shop. Example: \"b2\" buys the second joker.");
            System.out.println(Color.white("    \"c\"") + "          Continue from the shop.");
            System.out.println(Color.white("  Anytime:"));
            System.out.println(Color.white("    \"i(joker)\"") + "   Gives info on a joker you own. Example: \"i3\" gives info on the third joker you own.");
            System.out.println(Color.white("    \"s(joker)\"") + "   Sell a joker you own, previewing the sell price in a confirm prompt. Example: \"i1\" sells the first joker you own.");
            System.out.println(Color.white("    \"s(jokers)\"") + "  Swap two jokers you own. Example: \"i25\" swaps your second and fifth jokers.");
        }
        else System.out.println("Please enter a valid command. Type \"help\" for help");

        return '\0';
    }

    public String getInput() {
        String input = "";
        while(input.trim().isEmpty()) {
            input = scanner.nextLine();
        }

        return input;
    }

    public void printLose() {
        System.out.println(Color.red("------------------ GAME OVER -------------------"));
        System.out.println("Ante: " + Color.white(ante));
        System.out.println("Round: " + Color.white(round));
        System.exit(0);
    }

    public void printUI(Blind blind) {
        blind.print();
        System.out.println("Round Score: " + Color.white(player.getRoundScore()));
        System.out.println("Hands " + Color.blue(hands) + ", Discards " + Color.red(discards) + ", " + Color.yellow("$" + player.getMoney()));
        player.getJokerDeck().printJokers();
        System.out.println();

        player.getDeck().printHand();
    }

    public void cashout(Blind blind) {
        System.out.println(blind.getName() + " defeated with score " + Color.white(player.getRoundScore()));
        System.out.println(Color.yellow("\n------------------- Cashout --------------------"));
        System.out.println(blind.getName() + " ---> " + Color.yellow("$" + blind.getRewardMoney()));

        if(hands > 0) {
            System.out.print(Color.blue(hands) + " Remaining Hands");
            Timer.type(".......");
            System.out.println(Color.yellow("$" + hands));
        }

        if(player.getMoney() >= 5) {
            System.out.print(Color.white(Math.min(player.getMoney()/5, 5)) + " Interest per $5");
            Timer.type(".......");
            System.out.println(Color.yellow("$" + Math.min(player.getMoney()/5, 5)));
        }
        Timer.sleep(100);
        int moneyTally = blind.getRewardMoney() + hands + Math.min(player.getMoney()/5, 5);

        player.changeMoney(moneyTally);
        System.out.println("\nPress " + Color.white("Enter") + " to cash out for " + Color.yellow("$" + moneyTally));
        scanner.nextLine();

        System.out.println(Color.white("================================================"));
    }

    public Player getPlayer() {
        return player;
    }

    public void addChips(int amount) {
        chips += amount;
    }
    public void addMult(int amount) {
        mult += amount;
    }
    public void multMult(int multiplier) {
        mult *= multiplier;
    }
}
