package Jeu;

public class Bonus {
    public int fusee;
    public int peinture;
    public int sauvetage;

    Bonus() {
        this.fusee = 3;
        this.peinture = 3;
        this.sauvetage = 3;
    }

    public void rajout(int a) {
        this.fusee = fusee + a;
        this.peinture = peinture + a;
        this.fusee = fusee + 1;
    }
}
