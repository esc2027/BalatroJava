import java.util.Scanner;

public class Game {
    private Player player;
    int gameState = 0; //0 = blind select, 1 = game, 2 = shop
    int chips, mult, hands, discards;
    int ante = 1;
    int round = 0;
    Blind smallBlind, bigBlind, bossBlind;
    Scanner scanner;

    public static void main(String[] args) {
        //I can do a title banner here using color class and '█'
        Game game = new Game();
    }

    public Game() {
        player = new Player();
        scanner = new Scanner(System.in);

        System.out.println(Color.white("============== Welcome to Balatro!=============="));

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

            blind.print();
            System.out.println("Round Score: " + Color.white(player.getRoundScore()));
            System.out.println("Hands " + Color.blue(hands) + ", Discards " + Color.red(discards) + ", " + Color.yellow("$" + player.getMoney()));
            player.getJokerDeck().printJokers();
            System.out.println();

            player.getDeck().printHand();

            String input = getInput();
            char commandState = parse(input);
            input = input.substring(1).replaceAll(" ", "");


            switch(commandState) {
                case 'p':
                    hands--;

                    Hand hand = player.getDeck().parseHand(input);

                    System.out.println("Played a " + Color.green(hand.getHandType().getName()));

                    chips = tallyChips(hand);
                    mult = hand.getMult();
                    System.out.println(Color.blue(chips) + " x " + Color.red(mult) + " = " + Color.white(chips*mult));
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

        System.out.println(blind.getName() + " defeated with score " + Color.white(player.getRoundScore()));
        System.out.println(Color.yellow("\n------------------- Cashout --------------------"));
        System.out.println(blind.getName() + " ---> " + Color.yellow("$" + blind.getRewardMoney()));
        if(hands > 0) System.out.println(Color.blue(hands) + " Remaining Hands......." + Color.yellow("$" + hands));
        if(player.getMoney() >= 5) System.out.println(Color.white("1") + " interest per $5......." + Color.yellow("$" + Math.min(player.getMoney()/5, 5)));
        int moneyTally = blind.getRewardMoney() + hands + Math.min(player.getMoney()/5, 5);

        player.changeMoney(moneyTally);
        System.out.println("\nPress " + Color.white("Enter") + " to cash out for " + Color.yellow("$" + moneyTally));
        scanner.nextLine();

        System.out.println(Color.white("================================================"));
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

    public void changeChips(int amount) {
        chips += amount;
    }
    public void changeMult(int amount) {
        mult += amount;
    }
    public void multMult(int amount) {
        mult *= amount;
    }

    public int tallyChips(Hand hand) {
        int chipsTally = hand.getChips();

        System.out.print("Chips: " + Color.blue(hand.getChips()));
        for(Card card : hand.getCards()) {
            System.out.print(" + " + Color.blue(card.getRank().getValue()));
            chipsTally += card.getRank().getValue();
        }
        System.out.println(" = " + Color.blue(chipsTally));

        return chipsTally;
    }

    public char parse(String input) {
        while(true) {
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

            System.out.println("Please enter a valid command.");
            input = getInput();
        }
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
}
