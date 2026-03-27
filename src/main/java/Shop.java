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

    public void print(Player player) {
        System.out.println("--------------------- " + Color.yellow("SHOP") + " ---------------------");
        System.out.println("Balance: " + Color.yellow("$" + player.getMoney()) + "\n");
        for(Joker joker : jokers) {
            System.out.println(joker.getName() + Color.yellow(" $" + joker.getBuyPrice()));
        }

    }

    public void buyJoker(int index, Player player) {
        Joker jokerToBuy = jokers.get(index);

        if(player.getMoney() >= jokerToBuy.getBuyPrice()) {
            player.changeMoney(-jokerToBuy.getBuyPrice());
            System.out.println("Bought " + jokerToBuy.getName());

            player.getJokerDeck().addJoker(jokerToBuy);
            jokers.remove(jokerToBuy);

            System.out.println("\nRemaining balance: " + Color.yellow("$" + player.getMoney()));
            System.out.println("Left in shop: ");
            for(Joker joker : jokers) {
                System.out.println(joker.getName() + Color.yellow(" $" + joker.getBuyPrice()));
            }
        } else {
            System.out.println(Color.red("Not enough cash."));
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
