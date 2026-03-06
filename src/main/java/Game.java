public class Game {
    private Player player;
    int gameState = 0; //0 = blind select, 1 = game, 2 = shop
    int ante = 1;
    int round = 0;
    Blind smallBlind, bigBlind, bossBlind;

    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game() {
        player = new Player();
        System.out.println("===Welcome to Balatro!===");
        gameLoop();
    }

    public void gameLoop() {
        while(true) {
            switch(gameState) {
                case 0:
                    blindSelect();
                    break;
                case 1:
                    game(smallBlind);
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
        if(round == 0) {
            smallBlind = new Blind(ante, "Small Blind", 1);
            bigBlind = new Blind(ante, "Big Blind", 1.5);
            bossBlind = new Blind(ante, "The Wall", 4);
        }
        gameState = 1;
    }

    public void game(Blind blind) {
        while(player.getScore() < blind.getTargetScore()) {
            player.getDeck().fillHand();

            blind.print();
            player.getDeck().printJokers();
            player.getDeck().printHand();

            player.changeScore(blind.getTargetScore());

        }
        System.out.println("BLIND DEFEATED with score " + player.getScore());
        round++;
        gameState = 2;
    }

    public void shop() {
        Shop shop = new Shop();
    }
}
