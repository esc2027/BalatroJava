public class Joker {
    private String name;
    private int buyPrice;
    private int sellPrice;
    private int rarity;

    public Joker(String name, int buyPrice, int rarity) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.max(1, buyPrice / 2);
        this.rarity = rarity;
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

    //probably instead play jokers based on when during a round they are used with a method in JokerDeck.java

//    public void play() {
//        switch(name) {
//            case "":
//                break;
//        }
//    }
}
