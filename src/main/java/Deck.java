import java.util.*;

public class Deck {
    ArrayList<Card> cards;
    int handSize = 8;

    public Deck() {
        cards = new ArrayList<>();

        for(int suit = 0; suit <= 3; suit++) {
            for(int rank = 2; rank <= 12; rank++) {
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
                System.out.print("|" + card.getSuitEnum().getSymbol() + card.getRank().getRankString());
            }
        }
        System.out.println("|");
    }

    public void draw() {
        ArrayList<Card> drawOptions = new ArrayList<>();

        for(Card card : cards) {
            if (card.getState() == 0) drawOptions.add(card);
        }

        drawOptions.get((int)(Math.random() * drawOptions.size())).draw();
    }

    public Hand parseHand(String input) {
        ArrayList<Card> playedCards = stringToCards(input);

        Hand handTier = getHandTier(playedCards);

        return handTier;
    }

    private static Hand getHandTier(ArrayList<Card> playedCards) {

        boolean flush = true;
        boolean straight = true;
        int[] rankCount = new int[15];
        Suit firstSuit = playedCards.get(0).getSuitEnum();

        for(Card card : playedCards) {
            if(card == null) {
                flush = false;
                straight = false;
                continue;
            }
            rankCount[card.getRank().getValue()]++;
            if(card.getSuitEnum() != firstSuit) flush = false;
        }

        List<Integer> ranks = new ArrayList<>();
        for(Card card : playedCards) {
            if(card == null) continue;
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

        if(straight && flush) return Hand.STRAIGHTFLUSH;
        if(four) return Hand.FOUROFAKIND;
        if(three && pairs == 1) return Hand.FULLHOUSE;
        if(flush) return Hand.FLUSH;
        if(straight) return Hand.STRAIGHT;
        if(three) return Hand.THREEOFAKIND;
        if(pairs == 2) return Hand.TWOPAIR;
        if(pairs == 1) return Hand.PAIR;
        else return Hand.HIGHCARD;
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
            int index = Character.getNumericValue(c);
            returnArray.add(handCards.get(index));
        }

        while(returnArray.size() < 5) {
            returnArray.add(null);
        }

        return returnArray;
    }

    public void discard(Card card) {
        card.discard();
    }

    public void play(Card card) {
        card.discard();
    }
}
