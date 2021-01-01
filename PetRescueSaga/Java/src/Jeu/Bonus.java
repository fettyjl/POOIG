package Jeu;

public class Bonus {
    public int fusee;
    public int peinture;
    public int sauvetage;

    Bonus() {
        this.fusee = 2;
        this.peinture = 3;
        this.sauvetage = 1;
    }

    public void rajout() {
        this.fusee = fusee + 2;
        this.peinture = peinture + 3;
        this.fusee = fusee + 1;
    }
}
