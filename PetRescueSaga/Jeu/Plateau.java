package Jeu;

import java.util.Random;

public class Plateau {
    int longueur, largeur;
    boolean [][] supression;
    Case [][] plateau;

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
    }

    // Remplit les cases avec des Bloc dont les couleurs sont anotées de 0 à 3 :
    public void remplirCase(){
        for(int i=0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                this.plateau[i][j].bloc=new Bloc(new Random().nextInt(4));
            }
        }
    }

    // Affiche le plateau :
    public void afficherPlateau(){
        for(int i=0; i < this.plateau.length; i++){
            for (int j=0; j< this.plateau[i].length; j++){
                if(this.plateau[i][j].bloc!=null) {
                    if (this.plateau[i][j].bloc.i == 0) {
                        System.out.print("\u001B[31m" + "[0] " + "\u001B[0m");
                    } else if (this.plateau[i][j].bloc.i == 1) {
                        System.out.print("\u001B[32m" + "[1] " + "\u001B[0m");
                    } else if (this.plateau[i][j].bloc.i == 2) {
                        System.out.print("\u001B[34m" + "[2] " + "\u001B[0m");
                    } else {
                        System.out.print("\u001B[33m" + "[3] " + "\u001B[0m");
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

    /* Verifie si les cases ajacentes sont de même types et si les conditions sont respectées ->
       si c'est possible appel caseAppuyer(x,y) sur les cases adjacentes pour changer leurs valeurs a true
     */
    public void validationSuppression(int x, int y){
        if((x & y)>-1 && x<this.longueur && y<this.largeur && (this.plateau[x][y].bloc!=null)){
            if(x!=this.longueur-1 && this.plateau[x+1][y].bloc.i==this.plateau[x][y].bloc.i){
                this.caseAppuyer(x+1,y);
            }
            if(x!=0 && this.plateau[x-1][y].bloc.i==this.plateau[x][y].bloc.i){
                this.caseAppuyer(x-1,y);
            }
            if(y!=this.largeur-1 && this.plateau[x][y+1].bloc.i==this.plateau[x][y].bloc.i){
                this.caseAppuyer(x,y+1);
            }
            if(y!=0 && this.plateau[x][y-1].bloc.i==this.plateau[x][y].bloc.i){
                this.caseAppuyer(x,y-1);
            }

        }
    }
    /* Verifie si les conditions sont respectées ->
       Change la valeur de la case dans supression a true et appel validationSuppression(x,y)
     */
    public void caseAppuyer(int x,int y){
        if((x & y)>-1 && x<this.longueur && y<this.largeur  && (this.plateau[x][y].bloc!=null)){
            if(!this.supression[x][y]){
                this.supression[x][y]=true;
                this.validationSuppression(x,y);
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
                    this.plateau[i][j].bloc=null;
                }
            }
        }
    }

    // Prototype: Utilité Bonus Joueur
    public void suppressionEnColonne(int y){
        if(y>-1 && y< this.largeur) {
            int x = 0;
            while (x < this.longueur) {
                this.plateau[x][y].bloc = null;
                x++;
            }
        }
    }

    //Déplace les cases non vides sur les cases vident du dessous :
    public void suppressionEspace(int x, int y){
        if(x>0 & y>-1 && x<this.longueur && y<this.largeur){
            while (x > 0) {
                this.plateau[x][y].bloc=this.plateau[x-1][y].bloc;
                this.plateau[x-1][y].bloc=null;
                x--;
            }
        }
    }

    //Met à jour le plateau :
    public void refreshPlateau(){
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                if(this.plateau[i][j].bloc==null)
                this.suppressionEspace(i,j);
            }
        }
    }

}
