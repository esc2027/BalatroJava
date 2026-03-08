public enum Hand {

    HIGHCARD(0, 5, 1, "High Card"),
    PAIR(1, 10, 2, "Pair"),
    TWOPAIR(2, 20, 2, "Two Pair"),
    THREEOFAKIND(3, 30,3, "Three of a Kind"),
    STRAIGHT(4, 30, 4, "Straight"),
    FLUSH(5, 35, 4, "Flush"),
    FULLHOUSE(6, 40, 4, "Full House"),
    FOUROFAKIND(7, 60, 7, "Four of a Kind"),
    STRAIGHTFLUSH(8, 100,8, "Straight Flush");

    private final int handTier;
    private final int chips;
    private final int mult;
    private final String name;

    Hand(int handTier, int chips, int mult, String name) {
        this.handTier = handTier;
        this.chips = chips;
        this.mult = mult;
        this.name = name;
    }

    public int getHandTier() {
        return handTier;
    }

    public int getChips() {
        return chips;
    }

    public int getMult() {
        return mult;
    }

    public String getName() {
        return name;
    }
}
