package Jeu;

import java.io.Serializable;

public class Bonus implements Serializable {
    int fusee;
    int peinture;
    int sauvetage;

    Bonus() {
        this.fusee = 2;
        this.peinture = 3;
        this.sauvetage = 1;
    }
    //Rajout de bonus :
    public void rajout() {
        this.fusee = fusee + 1;
        this.peinture = peinture + 2;
        this.sauvetage = sauvetage + 1;
    }

    //Verifie si il n'y a plus de bonus:
    public boolean plusDeBonus() {
        if (fusee == 0 && peinture == 0 && sauvetage == 0){
            return true;
        }
        return false;
    }
    //Verifie si il n'y a plus de bonus Fusée et Peinture:
    public boolean plusDeFetP(){
        if (fusee == 0 && peinture == 0){
            return true;
        }
        return false;
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
