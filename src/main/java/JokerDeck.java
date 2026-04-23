import java.util.*;

public class JokerDeck {
    ArrayList<Joker> jokers;
    private final int JOKER_SLOTS = 5;

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

    public void printJokerInfo(int index) {
        jokers.get(index).printInfo();
    }

    public Joker getJoker(int index) {
        return jokers.get(index);
    }

    public void swapJokers(int firstIndex, int secondIndex) {
        Joker firstJoker = jokers.get(firstIndex);
        Joker secondJoker = jokers.get(secondIndex);

        jokers.set(firstIndex, secondJoker);
        jokers.set(secondIndex, firstJoker);
    }

    public void addJoker(Joker joker) {
        jokers.add(joker);
    }

    public int getJokerSlots() {
        return JOKER_SLOTS;
    }

    public void sellJoker(int index, Player player) {
        Joker joker = jokers.get(index);
        player.changeMoney(joker.getSellPrice());
        jokers.remove(joker);
    }
}
