public class Joker {
    private String name;
    private String description;
    private int buyPrice;
    private int sellPrice;
    private int rarity;
    private int[] priorities;

    public Joker(String name, int buyPrice, int rarity, int[] priorities) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.max(1, buyPrice / 2);
        this.rarity = rarity;
        this.description = "No description";
        this.priorities = priorities;
    }
    public Joker(String name, int buyPrice, int rarity, int[] priorities, String description) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.max(1, buyPrice / 2);
        this.rarity = rarity;
        this.description = description;
        this.priorities = priorities;
    }


    public void printInfo() {
        System.out.println(getName() + "\n" + description);
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getRarity() {
        return rarity;
    }

    public String getName() {
        return switch(rarity) {
            case 0 -> Color.blue(name);
            case 1 -> Color.green(name);
            case 2 -> Color.red(name);
            case 3 -> Color.purple(name);
            default -> name;
        };
    }

    public boolean hasPriority(int priority) {
        for(int i : priorities) {
            if(i == priority) return true;
        }
        return false;
    }

    public void act(Hand hand) {
        System.out.println(getName() + ": ");

        switch(name) {
            case "Joker" ->
                Game.game.addMult(4);
            case "Bull" ->
                Game.game.addChips(Game.game.getPlayer().getMoney());
            default ->
                Game.game.addMult(1);
        }
    }

    //"On Scored" jokers
    public void act(Hand hand, int cardIndex) {

        System.out.println(getName() + ": ");

        Card card = hand.getCard(cardIndex);

        switch(name) {
            case "Greedy Joker" -> {
                if(card.getSuit() == Suit.DIAMONDS) Game.game.addMult(3);
            }
            case "Lusty Joker" -> {
                if(card.getSuit() == Suit.HEARTS) Game.game.addMult(3);
            }
            case "Wrathful Joker" -> {
                if(card.getSuit() == Suit.SPADES) Game.game.addMult(3);
            }
            case "Gluttonous Joker" -> {
                if(card.getSuit() == Suit.CLUBS) Game.game.addMult(3);
            }
        }
    }

    //probably instead play jokers based on when during a round they are used with a method in JokerDeck.java

//    public void play() {
//        switch(name) {
//            case "":
//                break;
//        }
//    }
}
