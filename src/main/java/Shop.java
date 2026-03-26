import java.util.ArrayList;

public class Shop {

    private ArrayList<Joker> jokers;
    private int size;

    public Shop(int size) {
        jokers = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            jokers.add(JokerBank.randomJoker());
        }
    }

    public void print() {
        System.out.println("--------------------- " + Color.yellow("SHOP") + " ---------------------\n");
        for(Joker joker : jokers) {
            System.out.println(joker.getName() + Color.yellow(" $" + joker.getBuyPrice()));
        }

    }

    public void buyJoker(int index, JokerDeck jokerDeck) {
        System.out.println("Bought " + jokers.get(index).getName());
        
        jokerDeck.addJoker(jokers.get(index));
        jokers.remove(jokers.get(index));

        System.out.println("\nLeft in shop: ");
        for(Joker joker : jokers) {
            System.out.println(joker.getName() + Color.yellow(" $" + joker.getBuyPrice()));
        }

    }

    public int getSize() {
        return jokers.size();

    }

    public void endShop() {
        for(Joker joker : jokers) {
            JokerBank.addJoker(joker);
        }
        System.out.println("------------------------------------------------");
    }
}
