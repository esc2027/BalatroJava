import java.util.*;

public class JokerBank {
    private static ArrayList<Joker> jokers = new ArrayList<>(
            List.of(
                    new Joker("Joker", 2, 0),
                    new Joker("Greedy Joker", 5, 0),
                    new Joker("Lusty Joker", 5, 0),
                    new Joker("Wrathful Joker", 5, 0),
                    new Joker("Gluttonous Joker", 5, 0),

                    new Joker("Joker Stencil", 8, 1),
                    new Joker("Four Fingers", 7, 1),
                    new Joker("Mime", 5, 1),
                    new Joker("Bull", 6, 1),

                    new Joker("Ancient Joker", 8, 2),
                    new Joker("Baseball Card", 8, 2),
                    new Joker("Baron", 8, 2)
            )
            );

    static {

    }

    public static Joker randomJoker() { //Maybe instead of removing the joker here, only remove the joker when it is bought from shop to prevent readding if joker is not bought
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

    public static ArrayList<Joker> jokersOfRarity(int rarity) {
        ArrayList<Joker> jokerChoices = new ArrayList<>();
        for(Joker joker : jokers) {
            if(joker.getRarity() == rarity) jokerChoices.add(joker);
        }

        return jokerChoices;
    }

}
