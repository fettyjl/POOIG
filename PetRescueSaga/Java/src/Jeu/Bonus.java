package Jeu;

public class Bonus {
    int fusee;
    int peinture;
    int sauvetage;

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

    public int getFusee() {
        return fusee;
    }

    public void setFusee(int fusee) {
        this.fusee = fusee;
    }

    public int getPeinture() {
        return peinture;
    }

    public void setPeinture(int peinture) {
        this.peinture = peinture;
    }

    public int getSauvetage() {
        return sauvetage;
    }

    public void setSauvetage(int sauvetage) {
        this.sauvetage = sauvetage;
    }
}
