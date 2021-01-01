package Jeu;

public class Niveau {
    public int difficulte;
    public int nbEtoile;
    public int score;
    public int argentSave;
    public int argentPerdu;
    public int nbrTour;
    public boolean resultat;
    public boolean dispo;
    public Plateau plateau;


    public Niveau(int difficulte) {
        this.difficulte = difficulte;
        this.nbEtoile = 0;
        this.resultat = false;
        this.dispo = false;
        if (difficulte == 1)
            this.dispo = true;
        this.nbrTour = 1;
        this.score = 0;
        this.argentSave = 0;
        this.argentPerdu = 0;
        this.plateau = new Plateau();
    }
}
