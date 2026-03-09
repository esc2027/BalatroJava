import java.util.ArrayList;

public class Hand {
    private HandType handType;
    private ArrayList<Card> cards;

    public Hand(HandType handType, ArrayList<Card> cards) {
        this.handType = handType;
        this.cards = cards;
    }

    public HandType getHandType() {
        return handType;
    }

    public int getChips() {
        return handType.getChips();
    }

    public int getMult() {
        return handType.getMult();
    }

    public String getName() {
        return handType.getName();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }
}
