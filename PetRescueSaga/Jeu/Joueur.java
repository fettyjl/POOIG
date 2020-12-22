package Jeu;

public class Joueur {
        int vie,score,argent;
        int BonusFusee, BonusPeinture, BonusSauvatage;
        int niveau;
        Joueur(){
            this.vie=10;
            this.score=0;
            this.argent=500;
            this.BonusFusee=3;
            this.BonusPeinture=3;
            this.BonusSauvatage=3;
            this.niveau=0;
        }
}
