public class Player {
    private Deck deck;
    private int score;
    private int money;

    public Player() {
        deck = new Deck();
    }

    public Deck getDeck() {
        return deck;
    }

    public int getScore() {
        return score;
    }

    public void changeMoney(int amount) {
        money += amount;
    }

    public void changeScore(int amount) {
        score += amount;
    }
}
