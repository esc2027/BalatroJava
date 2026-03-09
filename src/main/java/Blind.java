public class Blind {
    private String name;
    private int targetScore;

    public Blind(int ante, String name, double blindMult) {
        this.name = name;

        switch(ante) {
            case 1:
                targetScore = 300;
                break;
            case 2:
                targetScore = 800;
                break;
            case 3:
                targetScore = 2000;
                break;
            case 4:
                targetScore = 5000;
                break;
            case 5:
                targetScore = 11000;
                break;
            case 6:
                targetScore = 20000;
                break;
            case 7:
                targetScore = 35000;
                break;
            case 8:
                targetScore = 50000;
                break;
        }
        targetScore *= blindMult;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public void print() {
        System.out.println(name + ", target " + Color.white(targetScore));
    }

    public String getName() {
        return name;
    }
}
