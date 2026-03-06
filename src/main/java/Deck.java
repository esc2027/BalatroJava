import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    ArrayList<Card> cards;
    ArrayList<Joker> jokers;
    int jokerSlots;
    int handSize;

    public Deck() {
        cards = new ArrayList<>();
        jokers = new ArrayList<>();
        handSize = 5;

        for(int suit = 0; suit <= 3; suit++) {
            for(int value = 2; value <= 13; value++) {
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }

    public void fillHand() {

        if(cardsInHand() >= handSize) return;

        for(int i = 0; i < handSize - cardsInHand(); i++) {
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

    public void discard(Card card) {
        card.discard();
    }

    public void play(Card card) {
        card.discard();
    }
}
