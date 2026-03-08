public class Player {
    private Deck deck;
    private JokerDeck jokerDeck;
    private int score;
    private int money;

    public Player() {
        deck = new Deck();
        jokerDeck = new JokerDeck();
    }

    public Deck getDeck() {
        return deck;
    }

    public JokerDeck getJokerDeck() {
        return jokerDeck;
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
