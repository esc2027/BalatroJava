public class Joker {
    private String name;
    private String description;
    private int buyPrice;
    private int sellPrice;
    private int rarity;

    public Joker(String name, int buyPrice, int rarity) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.max(1, buyPrice / 2);
        this.rarity = rarity;
        this.description = "No description";
    }
    public Joker(String name, int buyPrice, int rarity, String description) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.max(1, buyPrice / 2);
        this.rarity = rarity;
        this.description = description;
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

    public void act(Game game, Hand hand) {
        switch(name) {
            case "Joker" ->
                game.addMult(4);
            case "Bull" ->
                game.addChips(game.getPlayer().getMoney());
            default ->
                game.addMult(1);
        }
    }

    //"When Scored" jokers
    public void act(Game game, Hand hand, int cardIndex) {
        Card card = hand.getCard(cardIndex);

        switch(name) {
            case "Greedy Joker" -> {
                if(card.getSuit() == Suit.DIAMONDS) game.addMult(3);
            }
            case "Lusty Joker" -> {
                if(card.getSuit() == Suit.HEARTS) game.addMult(3);
            }
            case "Wrathful Joker" -> {
                if(card.getSuit() == Suit.SPADES) game.addMult(3);
            }
            case "Gluttonous Joker" -> {
                if(card.getSuit() == Suit.CLUBS) game.addMult(3);
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
