package Jeu;

import java.util.Random;

public class Plateau {
    int longueur, largeur;
    int nbAni;
    boolean [][] supression;
    Case [][] plateau;
    // Remplir le plateau d'animaux
    // Fonction qui verif annimaux mort et sauvés
    
    
    
    //Crée un plateau de taille 8*8, initialise un tableau boolean a false et un tableau avec des cases vides.
    Plateau(){
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
        
    }

    // Remplit les cases avec des Bloc dont les couleurs sont anotées de 0 à 3 :
    public void remplirCase(){
        for(int i=0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                this.plateau[i][j].container=new Bloc(new Random().nextInt(4));
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




    /*
    Supprime les cases dont la valeur est a true dans le tableau suppression:
     */
    public void supprimerCase() {
        for (int i = 0; i < this.supression.length; i++) {
            for (int j = 0; j < this.supression[i].length; j++) {
                if(this.supression[i][j]){
                    this.supression[i][j]=false;
                    this.plateau[i][j].container=null;
                }
            }
        }
    }

    // Prototype: Utilité Bonus Joueur
    public void suppressionEnColonne(int y){
        if(y>-1 && y< this.largeur) {
            int x = 0;
            while (x < this.longueur) {
                this.plateau[x][y].container = null;
                x++;
            }
        }
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

    //Met à jour le plateau :
    public void refreshPlateau(){
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                if(this.plateau[i][j].container==null)
                this.suppressionEspace(i,j);
            }
        }
    }
    // Deplace les colonnes vers la gauche : 4->
    public boolean verifColonneVide(int y){
        if(y>-1 && y< this.largeur) {
            int x = 0;
            while (x < this.longueur) {
                if(this.plateau[x][y].container != null){
                    return false;
                }
                x++;
            }
            return true;
        }
        return false;
    }
    public int NombreColonneVideApresY(int y){
        int a=0;
        if(y>-1 && y< this.largeur-1) {
            while(y+1<this.largeur-1 && verifColonneVide(y+1) ){
                a++;
                y++;
            }
        }
        return a;
    }
    public void DeplaceColonne(int i, int c){
        if(c>-1 && c< this.largeur-1) {
            int x = 0;
            while (x < this.longueur) {
                this.plateau[x][c].container = this.plateau[x][c+i].container;
                this.plateau[x][c+i].container=null;
                x++;
            }
        }
    }
    public void DecalageColonne(){
        for(int i=0; i<this.largeur-1; i++){
            if(verifColonneVide(i)){
                DeplaceColonne(NombreColonneVideApresY(i)+1,i);
            }
        }
    }
    // ajoute une colonne en dessous par nombre de colonnes supprimer:
    public void AjouteLigneEnBas(){
        for (int i=0; i<this.longueur-1; i++){
            for(int j=0;j<this.largeur;j++)
                this.plateau[i][j].container = this.plateau[i + 1][j].container;
        }
        for(int i=0;i<this.largeur;i++)
            this.plateau[this.longueur-1][i].container=new Bloc(new Random().nextInt(4));
    }
}
