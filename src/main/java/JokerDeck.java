import java.util.*;

public class JokerDeck {
    ArrayList<Joker> jokers;
    int jokerSlots = 5;

    public JokerDeck() {
        jokers = new ArrayList<>();
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
}
