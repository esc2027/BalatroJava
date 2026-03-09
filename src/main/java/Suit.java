public enum Suit {
    SPADES(0, '♠'), HEARTS(1, '♥'), CLUBS(2, '♣'), DIAMONDS(3, '♦');

    private final int index;
    private final char symbol;

    Suit(int index, char symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public int getIndex() {
        return index;
    }

    public char getSymbol() {
        return symbol;
    }
}
