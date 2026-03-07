import java.util.*;

public class Deck {
    ArrayList<Card> cards;
    ArrayList<Joker> jokers;
    int jokerSlots = 5;
    int handSize = 5;

    public Deck() {
        cards = new ArrayList<>();
        jokers = new ArrayList<>();
        for(int suit = 0; suit <= 3; suit++) {
            for(int value = 2; value <= 13; value++) {
                Card card = new Card(suit, value);
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
                System.out.print("|" + card.getSuit() + card.getValue());
            }
        }
        System.out.println("|");
    }

    public void printJokers() {
        System.out.println("Jokers:");
    }

    public void addJoker(Joker joker) {
        jokers.add(joker);
    }

    public void sellJoker(Joker joker, Player player) {
        player.changeMoney(joker.getSellPrice());
        jokers.remove(joker);
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

        int handTier = getHandTier(playedCards);

        return switch (handTier) {
            case 1 -> new Hand("Pair", 10, 2, playedCards);
            case 2 -> new Hand("Two Pair", 20, 2, playedCards);
            case 3 -> new Hand("Three of a Kind", 30, 3, playedCards);
            case 4 -> new Hand("Straight", 30, 4, playedCards);
            case 5 -> new Hand("Flush", 35, 4, playedCards);
            case 6 -> new Hand("Full House", 40, 4, playedCards);
            case 7 -> new Hand("Four of a Kind", 60, 7, playedCards);
            case 8 -> new Hand("Straight Flush", 100, 8, playedCards);
            default -> new Hand("High Card", 5, 1, playedCards);
        };
    }

    private static int getHandTier(ArrayList<Card> playedCards) {
        int handTier = 0;

//        for(Card card1 : playedCards) {
//            for(Card card2 : playedCards) {
//                if (card1.getValue() == card2.getValue()) {
//                    handTier = 1;
//                    break;
//                }
//            }
//        }
//        for(Card card1 : playedCards) {
//            for(Card card2 : playedCards) {
//                for(Card card3 : playedCards) {
//                    for(Card card4 : playedCards) {
//                        if (card1.getValue() == card2.getValue() && card3.getValue() == card4.getValue() && card1.getValue() != card3.getValue()) {
//                            handTier = 2;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        return handTier;
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

        return returnArray;
    }

    public void discard(Card card) {
        card.discard();
    }

    public void play(Card card) {
        card.discard();
    }
}
