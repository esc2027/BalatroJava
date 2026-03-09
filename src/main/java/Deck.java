import java.util.*;

public class Deck {
    ArrayList<Card> cards;
    private int handSize = 8;

    public Deck() {
        cards = new ArrayList<>();

        for(int rank = 12; rank >= 0; rank--) {
            for(int suit = 0; suit <= 3; suit++) {
                Card card = new Card(Suit.values()[suit], Rank.values()[rank]);
                cards.add(card);
            }
        }
    }

    public void fillHand() {
        if(cardsInHand() >= handSize) return;

        int cardsInHand = cardsInHand();

        for(int i = 0; i < handSize - cardsInHand; i++) {
            draw();
        }
    }

    public void reshuffle() {
        for(Card card : cards) card.reshuffle();
    }

    public int cardsInHand() {
        int cardsInHand = 0;

        for(Card card : cards) {
            if(card.getState() == 1) cardsInHand++;
        }

        return cardsInHand;
    }

    public void printHand() {
        for(Card card : cards) {
            if(card.getState() == 1) {
                System.out.print(" " + card.getNameColor());
            }
        }
        System.out.println(" ");
    }

    public void draw() {
        ArrayList<Card> drawOptions = new ArrayList<>();

        for(Card card : cards) {
            if (card.getState() == 0) drawOptions.add(card);
        }

        if(drawOptions.isEmpty()) return;

        drawOptions.get((int)(Math.random() * drawOptions.size())).draw();
    }

    public Hand parseHand(String input) { //YOU CAN USE THE SAME CARD MULTIPLE TIMES (this is a problem obviously)
        ArrayList<Card> playedCards = stringToCards(input);

        for(Card card : playedCards) {
            card.discard();
        }

        return new Hand(getHandTier(playedCards), playedCards);
    }

    private static HandType getHandTier(ArrayList<Card> playedCards) {

        boolean flush = true;
        boolean straight = true;
        int[] rankCount = new int[15];
        Suit firstSuit = playedCards.get(0).getSuit();

        for(Card card : playedCards) {
            rankCount[card.getRank().getValue()]++;
            if(card.getSuit() != firstSuit) flush = false;
        }

        List<Integer> ranks = new ArrayList<>();
        for(Card card : playedCards) {
            ranks.add(card.getRank().getValue());
        }
        Collections.sort(ranks);


        for(int i = 1; i < ranks.size(); i++) {
            if(ranks.get(i) != ranks.get(i - 1) + 1) {
                straight = false;
                break;
            }
        }

        int pairs = 0;
        boolean three = false;
        boolean four = false;

        for(int count : rankCount) {
            if(count == 4) four = true;
            if(count == 3) three = true;
            if(count == 2) pairs++;
        }

//        for(Card card : playedCards) {
//            if(card == null) {
//                System.out.println("null");
//                break;
//            }
//            System.out.println(card.getRank());
//        }

        int size = playedCards.size();

        if(size == 5 && straight && flush) return HandType.STRAIGHTFLUSH;
        if(size >= 4 && four) return HandType.FOUROFAKIND;
        if(size == 5 && three && pairs == 1) return HandType.FULLHOUSE;
        if(size == 5 && flush) return HandType.FLUSH;
        if(size == 5 && straight) return HandType.STRAIGHT;
        if(size >= 3 && three) return HandType.THREEOFAKIND;
        if(size >= 4 && pairs == 2) return HandType.TWOPAIR;
        if(size >= 2 && pairs == 1) return HandType.PAIR;
        else return HandType.HIGHCARD;
    }

    public Hand parseDiscard(String input) {
        return null;
    }

    public ArrayList<Card> stringToCards(String input) {
        ArrayList<Card> handCards = new ArrayList<>();
        ArrayList<Card> returnArray = new ArrayList<>();

        for (Card card : cards) {
            if (card.getState() == 1) handCards.add(card);
        }

        for (char c : input.toCharArray()) {
            int index = Character.getNumericValue(c) - 1;
            returnArray.add(handCards.get(index));
        }

        return returnArray;
    }

    public void discard(Card card) {
        card.discard();
    }

    public void play(Card card) {
        card.discard();
    }

    public int getHandSize() {
        return handSize;
    }
}
