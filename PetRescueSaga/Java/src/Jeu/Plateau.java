package Jeu;

import Vue.Fenetre;

import java.util.Random;

public class Plateau {
    int longueur, largeur;
    final int nbArgent=4;
    boolean [][] supression;
    Case [][] plateau;

    //Crée un plateau de taille 8*8, initialise un tableau boolean a false et un tableau avec des cases vides.
    public Plateau(){
        this.longueur=8;
        this.largeur=8;
        this.supression=new boolean[this.longueur][this.largeur];
        this.plateau=new Case[this.longueur][this.largeur];
        for(int i=0; i < this.plateau.length; i++){
            for (int j=0; j< this.plateau[i].length; j++){
                this.plateau[i][j]=new Case();
            }
        }
        this.remplirCase();
        this.remplirArgent();

    }

    // Remplit les cases avec des Bloc dont les couleurs sont anotées de 0 à 3 :
    public void remplirCase(){
        for(int i=0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                this.plateau[i][j].container=new Bloc(new Random().nextInt(4));
            }
        }
    }
    public void remplirArgent(){
        if(this.longueur>1 && this.largeur>1) {
            int a = this.nbArgent;
            while (a > 0) {
                int b = new Random().nextInt(this.largeur);
                int c = new Random().nextInt(2);
                if(!(this.plateau[0][b].container instanceof Argent) &&!((this.plateau[1][b].container instanceof Argent) )) {
                    if(c==1) {
                        this.plateau[1][b].container = new Argent();
                        this.plateau[0][b].container = null;
                    }else{
                        this.plateau[0][b].container = new Argent();
                    }
                    a--;
                }
            }
        }
    }

    // Affiche le plateau :
    public void afficherPlateau(){
        for(int i=0; i < this.plateau.length; i++){
            for (int j=0; j< this.plateau[i].length; j++){
                if(this.plateau[i][j].container!=null) {
                    if (this.plateau[i][j].container instanceof Bloc) {
                        Bloc a = (Bloc) this.plateau[i][j].container;
                        if (a.i == 0) {
                            System.out.print("\u001B[31m" + "[0] " + "\u001B[0m");
                        } else if (a.i == 1) {
                            System.out.print("\u001B[32m" + "[1] " + "\u001B[0m");
                        } else if (a.i == 2) {
                            System.out.print("\u001B[34m" + "[2] " + "\u001B[0m");
                        } else {
                            System.out.print("\u001B[33m" + "[3] " + "\u001B[0m");
                        }
                    }else if(this.plateau[i][j].container instanceof Argent){
                        System.out.print("[#] ");
                    }
                }else{
                    System.out.print("[-] ");
                }
            }
            System.out.println();
        }
    }

    public void affichageBoolean(){
        for(int i=0; i < this.supression.length; i++){
            for (int j=0; j< this.supression[i].length; j++){
                System.out.print(this.supression[i][j]+" ");
            }
            System.out.println();
        }
    }

    /* Verifie si les conditions sont respectées ->
      Change la valeur de la case dans supression a true et appel validationSuppression(x,y)
    */
    public void caseAppuyer(int x,int y){
        if((x & y)>-1 && x<this.longueur && y<this.largeur  && (this.plateau[x][y].container!=null) && (this.plateau[x][y].container instanceof Bloc)){
            if(!this.supression[x][y]){
                this.supression[x][y]=true;
                this.validationSuppression(x,y);
            }
        }
        if(this.nombreCaseSupp()==1){
            this.supression[x][y]=false;
        }
    }
    /* Verifie si les cases ajacentes sont de même types et si les conditions sont respectées ->
       si c'est possible appel caseAppuyer(x,y) sur les cases adjacentes pour changer leurs valeurs a true
     */
    public void validationSuppression(int x, int y){
        if((x & y)>-1 && x<this.longueur && y<this.largeur && (this.plateau[x][y].container!=null) && (this.plateau[x][y].container instanceof Bloc)){
            Bloc a= (Bloc) this.plateau[x][y].container;
            if(x != this.longueur-1 && a.compareTo(this.plateau[x+1][y].container)==1){
                this.caseAppuyer(x+1,y);
            }
            if(x!=0 && a.compareTo(this.plateau[x-1][y].container)==1){
                this.caseAppuyer(x-1,y);
            }
            if(y!=this.largeur-1 && a.compareTo(this.plateau[x][y+1].container)==1){
                this.caseAppuyer(x,y+1);
            }
            if(y!=0 && a.compareTo(this.plateau[x][y-1].container)==1){
                this.caseAppuyer(x,y-1);
            }

        }
    }



    //Nombre de case supp:
    public int nombreCaseSupp(){
        int a=0;
        for (int i = 0; i < this.supression.length; i++) {
            for (int j = 0; j < this.supression[i].length; j++) {
                if(this.supression[i][j]){
                    a++;
                }
            }
        }
        return a;
    }
    /*
    Supprime les cases dont la valeur est a true dans le tableau suppression:
     */
    public void supprimerCase() {
        for (int i = 0; i < this.supression.length; i++) {
            for (int j = 0; j < this.supression[i].length; j++) {
                if (this.supression[i][j]) {
                    this.supression[i][j] = false;
                    this.plateau[i][j].container = null;
                }
            }
        }
    }
    // Compte le nombre d'animaux save:
    public int argentSave(){
        int a=0;
        if(this.longueur>0) {
            int x = 0;
            int y=0;
            while (y< this.largeur){
                if(verifNoBloc(y)) {
                    while (x < this.longueur) {
                        if (this.plateau[x][y].container instanceof Argent) {
                            this.plateau[x][y].container=null;
                            a++;
                        }
                        x++;
                    }
                }
                y++;
            }
        }

        return a;
    }
    //Met à jour le plateau :
    public void refreshPlateau(){
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                if(this.plateau[i][j].container==null)
                    this.suppressionEspace(i,j);
            }
        }
        this.decalageColonne();
    }

    //Déplace les cases non vides sur les cases vident du dessous :
    public void suppressionEspace(int x, int y){
        if(x>0 & y>-1 && x<this.longueur && y<this.largeur){
            while (x > 0) {
                this.plateau[x][y].container=this.plateau[x-1][y].container;
                this.plateau[x-1][y].container=null;
                x--;
            }
        }
    }


    public void decalageColonne(){
        for(int i=0; i<this.largeur-1; i++){
            if(verifColonneVide(i)){
                deplaceColonne(nombreColonneVideApresY(i)+1,i);
            }
        }
    }

    public void deplaceColonne(int i, int c){
        if(c>-1 && c< this.largeur-1) {
            int x = 0;
            while (x < this.longueur) {
                this.plateau[x][c].container = this.plateau[x][c+i].container;
                this.plateau[x][c+i].container=null;
                x++;
            }
        }
    }

    public int nombreColonneVideApresY(int y){
        int a=0;
        if(y>-1 && y< this.largeur-1) {
            while(y+1<this.largeur-1 && verifColonneVide(y+1) ){
                a++;
                y++;
            }
        }
        return a;
    }

    public boolean verifNoBloc(int y){
        if(y>-1 && y< this.largeur) {
            int x = 0;
            while (x < this.longueur) {
                if(this.plateau[x][y].container instanceof Bloc){
                    return false;
                }
                x++;
            }
            return true;
        }
        return false;
    }
    // Deplace les colonnes vers la gauche : 4->
    public boolean verifColonneVide(int y){
        if(y>-1 && y< this.largeur) {
            int x = 0;
            while (x < this.longueur) {
                if(this.plateau[x][y].container!=null){
                    return false;
                }
                x++;
            }
            return true;
        }
        return false;
    }


    // ajoute une colonne en dessous par nombre de colonnes supprimer:
    public void ajouteLigneEnBas(){
        for (int i=0; i<this.longueur-1; i++){
            for(int j=0;j<this.largeur;j++)
                this.plateau[i][j].container = this.plateau[i + 1][j].container;
        }
        for(int i=0;i<this.largeur;i++)
            this.plateau[this.longueur-1][i].container=new Bloc(new Random().nextInt(4));
    }

    //Utiliser avant ajouteLigneEnbas:
    public int nbrArgentPerdu(){
        int a=0;
        if(this.longueur>0) {
            int x = 0;
            while (x<this.largeur){
                if(this.plateau[0][x].container instanceof Argent){
                    a++;
                }
                x++;
            }
        }
        return a;
    }

    //Plus d'annimaux à save = fin de partie
    public int resteASave(){
        int a=0;
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                if(this.plateau[i][j].container instanceof Argent)
                    a++;
            }
        }
        return a;
    }

    // Prototype Bonus Joueur: Renvoie le nombre d'argent supprimer avec:
    public int bonusEnColonne(int y){
        int a=0;
        if(y>-1 && y< this.largeur) {
            int x = 0;
            while (x < this.longueur) {
                if(this.plateau[x][y].container instanceof Argent)
                    a++;
                this.plateau[x][y].container = null;
                x++;
            }
        }
        return a;
    }

    public boolean sauvetageArgent(int x, int y) {
        if (x > -1 & y > -1 && x < this.longueur && y < this.largeur) {
            if (this.plateau[x][y].container instanceof Argent) {
                this.plateau[x][y].container = null;
                return true;
            }
        }
        return false;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getNbArgent() {
        return nbArgent;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }

    public boolean[][] getSupression() {
        return supression;
    }

    public void setSupression(boolean[][] supression) {
        this.supression = supression;
    }
}
