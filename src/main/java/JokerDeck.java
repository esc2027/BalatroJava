import java.util.*;
import java.util.stream.Collectors;

public class JokerDeck {
    ArrayList<Joker> jokers;
    int jokerSlots = 5;

    public JokerDeck() {
        jokers = new ArrayList<>();
    }

    public void printJokers() {
        StringBuilder returnString = new StringBuilder();

        returnString.append("Jokers: ");
        for(Joker joker : jokers) {
            returnString.append(joker.getName()).append(", ");
        }
        if(returnString.length() > 8) {
            returnString.delete(returnString.length() - 2, returnString.length());
        }
        System.out.println(returnString);
    }

    public void addJoker(Joker joker) {
        jokers.add(joker);
    }

    public void sellJoker(Joker joker, Player player) {
        player.changeMoney(joker.getSellPrice());
        jokers.remove(joker);
    }
}
