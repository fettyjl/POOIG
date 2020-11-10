package boite.plateau;

import boite.plateau.bloc.*;

public class Grille {
    protected Bloc[][] bc;
    protected int[][] etats;
    protected int nbAnimaux;
    protected static int taille=8;

    protected Grille(int nbAnimaux){
        this.nbAnimaux=nbAnimaux;
        etats=new int[taille][taille];
        bc=new Bloc[taille][taille];

    }
}
