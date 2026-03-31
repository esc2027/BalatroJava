public class Card {
    private Suit suit;
    private Rank rank;
    private int state = 0; //0 = in deck, 1 = in hand, 2 = discard

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getNameColor() {
        return switch (suit.getSymbol()) {
            case '♠' -> Color.black(Color.whitebg(suit.getSymbol() + rank.getRankString()));
            case '♥' -> Color.red(Color.whitebg(suit.getSymbol() + rank.getRankString()));
            case '♣' -> Color.blue(Color.whitebg(suit.getSymbol() + rank.getRankString()));
            case '♦' -> Color.orange(Color.whitebg(suit.getSymbol() + rank.getRankString()));
            default -> Color.purple(Color.whitebg("??"));
        };
    }

    public Rank getRank() {
        return rank;
    }

    public int getState() {
        return state;
    }

    public void reshuffle() {
        state = 0;
    }

    public void draw() {
        state = 1;
    }

    public void discard() {
        state = 2;
    }
}
