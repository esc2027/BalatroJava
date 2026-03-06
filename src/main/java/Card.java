public class Card {
    private int suit;
    private int value;
    private int state = 0; //0 = in deck, 1 = in hand, 2 = discard

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        switch(suit) {
            case 0: //spades
                return '♠';
            case 1: //hearts
                return '♡';
            case 2: //clubs
                return '♣';
            case 3: //diamonds
                return '♢';
            default:
                return 'X';
        }
    }

    public int getState() {
        return state;
    }

    public void draw() {
        state = 1;
    }

    public void discard() {
        state = 2;
    }
}
