package Jeu;

public class Niveau {
    int difficulte;
    int nbEtoile;
    int score;
    int argentSave;
    int argentPerdu;
    int nbrTour;
    boolean resultat;
    boolean dispo;
    Plateau plateau;

    /*
    Initialise un niveau avec une difficultée, un nombre d'etoile en fonction de la réussite, un score, un nombre d'argent gagner et perdu, un nombre de tour
    un boolean qui vérifie si le niveau est disponible au joueur et un boolean qui indique si le joueur a réussit le niveau, un plateau de bloc et sac d'argent:
     */

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

    public boolean isResultat() {
        return resultat;
    }

    public void setResultat(boolean resultat) {
        this.resultat = resultat;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getNbrTour() {
        return nbrTour;
    }

    public void setNbrTour(int nbrTour) {
        this.nbrTour = nbrTour;
    }

    public int getNbEtoile() {
        return nbEtoile;
    }

    public void setNbEtoile(int nbEtoile) {
        this.nbEtoile = nbEtoile;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getArgentSave() {
        return argentSave;
    }

    public void setArgentSave(int argentSave) {
        this.argentSave = argentSave;
    }

    public int getArgentPerdu() {
        return argentPerdu;
    }

    public void setArgentPerdu(int argentPerdu) {
        this.argentPerdu = argentPerdu;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
