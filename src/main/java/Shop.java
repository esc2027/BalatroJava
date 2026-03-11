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

    public void buyJoker(int index) {
        System.out.println("Bought " + jokers.get(index).getName());

    }
}
