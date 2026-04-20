import java.util.*;

public class JokerBank {
    private static ArrayList<Joker> jokers = new ArrayList<>(
            List.of(
                    new Joker("Joker", 2, 0, Color.red("+4 ") + "Mult"),
                    new Joker("Greedy Joker", 5, 0, "Played cards with " + Color.orange(Suit.DIAMONDS + " Diamond ") + "suit give " + Color.red("+3 ") + "Mult when scored"),
                    new Joker("Lusty Joker", 5, 0, "Played cards with " + Color.red(Suit.HEARTS + " Heart ") + "suit give " + Color.red("+3 ") + "Mult when scored"),
                    new Joker("Wrathful Joker", 5, 0, "Played cards with " + Color.gray(Suit.SPADES + " Spade ") + "suit give " + Color.red("+3 ") + "Mult when scored"),
                    new Joker("Gluttonous Joker", 5, 0, "Played cards with " + Color.blue(Suit.CLUBS + " Club ") + "suit give " + Color.red("+3 ") + "Mult when scored"),

                    new Joker("Joker Stencil", 8, 1, Color.redbg(Color.white("X1")) + " Mult for each empty " + Color.orange("Joker ") + "slot, " + Color.white("Joker Stencil ") + "included" + "\n(Currently " + Color.redbg(Color.white("X?"))),
                    new Joker("Four Fingers", 7, 1, "All " + Color.orange("Flushes ") + "and " + Color.orange("Straights ") + " can be made with 4 cards"),
                    new Joker("Mime", 5, 1, "Retrigger all card " + Color.orange(" held in hand ") + "abilities"),
                    new Joker("Bull", 6, 1, Color.blue("+2 ") + "Chips for each " + Color.yellow("$1 ") + "you have " + "\n(Currently " + Color.blue("+? ") + "Chips)"),

                    new Joker("Ancient Joker", 8, 2),
                    new Joker("Baseball Card", 8, 2),
                    new Joker("Baron", 8, 2)
            )
            );

    static {

    }

    public static Joker randomJoker() {
        double chance = Math.random();
        int chosenRarity = 0;

        if(chance < 0.3) chosenRarity = 1;
        if(chance < 0.05) chosenRarity = 2;

        ArrayList<Joker> jokerChoices = jokersOfRarity(chosenRarity);

        Collections.shuffle(jokerChoices);
        Joker chosenJoker = jokerChoices.get(0);

        removeJoker(chosenJoker);
        return chosenJoker;


    }

    public static void removeJoker(Joker joker) {
        jokers.remove(joker);
    }

    public static void addJoker(Joker joker) {
        jokers.add(joker);
    }

    public static ArrayList<Joker> jokersOfRarity(int rarity) {
        ArrayList<Joker> jokerChoices = new ArrayList<>();
        for(Joker joker : jokers) {
            if(joker.getRarity() == rarity) jokerChoices.add(joker);
        }

        return jokerChoices;
    }

}
