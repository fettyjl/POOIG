package Jeu;


public class Joueur {
        public int scoreTot;
    int niveau;
        public Bonus bonus;
        Joueur(){
            this.scoreTot=0;
            this.niveau=0;
            this.bonus=new Bonus();
        }
        public void addBonus(){
            int a=scoreTot/200;
            bonus.rajout(a);
        }
}
