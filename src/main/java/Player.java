public class Player {
    private Deck deck;
    private JokerDeck jokerDeck;
    private int roundScore;
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

    public int getRoundScore() {
        return roundScore;
    }

    public int getMoney() {
        return money;
    }

    public void changeMoney(int amount) {
        money += amount;
    }

    public void changeScore(int amount) {
        roundScore += amount;
    }

    public void setScore(int value) {
        roundScore = value;
    }
}
