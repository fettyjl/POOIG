package Jeu;


public class Joueur {
        int scoreTot, niveau;
        Bonus bonus;
        Joueur(){
            this.scoreTot=0;
            this.niveau=0;
            this.bonus=new Bonus();
        }
}
