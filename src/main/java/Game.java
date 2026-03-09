public class Game {
    private Player player;
    private Parser parser;
    int gameState = 0; //0 = blind select, 1 = game, 2 = shop
    int chips, mult;
    int ante = 1;
    int round = 0;
    Blind smallBlind, bigBlind, bossBlind;

    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game() {
        player = new Player();
        parser = new Parser();
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
            smallBlind = new Blind(ante, Color.blue("Small Blind"), 1);
            bigBlind = new Blind(ante, Color.yellow("Big Blind"), 1.5);
            bossBlind = new Blind(ante, Color.purple("The Wall"), 4);
            System.out.println(Color.white("==================== ANTE " + ante + " ====================\n================================================"));
        }
        gameState = 1;
    }

    public void game(Blind blind) {

        player.getDeck().reshuffle();

        while(player.getRoundScore() < blind.getTargetScore()) {
            player.getDeck().fillHand();

            blind.print();
            System.out.println("Round Score: " + Color.white(player.getRoundScore()));
            player.getJokerDeck().printJokers();
            player.getDeck().printHand();

            Hand hand = parser.parse(player);

            System.out.println(Color.green(hand.getHandType().getName()));

            chips = tallyChips(hand);
            mult = hand.getMult();
            System.out.println(Color.blue(chips) + " x " + Color.red(mult) + " = " + Color.white(chips*mult));
            System.out.println(Color.white("------------------------------------------------"));
            player.changeScore(chips*mult);

            chips = mult = 0;

        }

        System.out.println(blind.getName() + " defeated with score " + Color.white(player.getRoundScore()));
        System.out.println(Color.white("================================================"));
        player.setScore(0);
        round++;
        gameState = 2;
    }

    public void shop() {
        Shop shop = new Shop();
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
}
