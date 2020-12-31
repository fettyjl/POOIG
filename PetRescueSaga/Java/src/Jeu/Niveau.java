package Jeu;

public class Niveau {
    public int niveau;
    int nbEtoile;
    public Plateau plateau;

    public Niveau(int niveau){
        this.niveau=niveau;
        this.nbEtoile=0;
        this.plateau=new Plateau();
    }
}
