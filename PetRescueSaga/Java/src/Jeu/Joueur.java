package Jeu;


public class Joueur {
    int scoreTot;
    Bonus bonus;

    //Initialise un Joueur avec un score Total et un Objet bonus contenant les bonus:
    Joueur() {
        this.scoreTot = 0;
        this.bonus = new Bonus();
    }

    //Rajoute un nombre de bonus au joueur en fonction du score totale:
    public void addBonus() {
        int a = scoreTot / 1500;
        while (a>0) {
            bonus.rajout();
            a--;
        }
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public int getScoreTot() {
        return scoreTot;
    }

    public void setScoreTot(int scoreTot) {
        this.scoreTot = scoreTot;
    }
}
