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
        if(suit.getSymbol() == '♠' || suit.getSymbol() == '♣') return Color.black(Color.whitebg(suit.getSymbol() + rank.getRankString()));
        else return Color.red(Color.whitebg(suit.getSymbol() + rank.getRankString()));
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
