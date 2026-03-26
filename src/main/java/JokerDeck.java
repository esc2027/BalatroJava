import java.util.*;

public class JokerDeck {
    ArrayList<Joker> jokers;
    int jokerSlots = 5;

    public JokerDeck() {
        jokers = new ArrayList<>();
    }

    public void printJokers() {
        System.out.print("Jokers:");
        for(Joker joker : jokers) {
            System.out.print(" " + joker.getName() + Color.RESETCOLOR);
        }
        System.out.println();
    }

    public void addJoker(Joker joker) {
        jokers.add(joker);
    }

    public void sellJoker(Joker joker, Player player) {
        player.changeMoney(joker.getSellPrice());
        jokers.remove(joker);
    }
}
