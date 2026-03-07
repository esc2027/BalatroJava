import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private int chips, mult;
    private String name;

    public Hand(String name, int chips, int mult, ArrayList<Card> cards) {
        this.cards = cards;
        this.chips = chips;
        this.mult = mult;
        this.name = name;
    }

    public int getChips() {
        return chips;
    }

    public int getMult() {
        return mult;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}