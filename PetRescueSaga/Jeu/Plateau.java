package Jeu;

import java.util.Random;

public class Plateau {
    int longueur, largeur;
    Case [][] plateau;
    Plateau(){
        this.longueur=8;
        this.largeur=8;
        this.plateau=new Case[this.longueur][this.largeur];
        for(int i=0; i < this.plateau.length; i++){
            for (int j=0; j< this.plateau[i].length; j++){
                this.plateau[i][j]=new Case();
            }
        }
    }
    public void remplirCase(){
        for(int i=0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                this.plateau[i][j].b=new Bloc(new Random().nextInt(4));
            }
        }
    }

    public void afficherPlateau(){
        for(int i=0; i < this.plateau.length; i++){
            for (int j=0; j< this.plateau[i].length; j++){
                if(this.plateau[i][j].b.i==0){
                    System.out.print("\u001B[31m" + "[0] "+"\u001B[0m");
                }else if(this.plateau[i][j].b.i==1){
                    System.out.print("\u001B[32m" + "[1] "+"\u001B[0m");
                }else if(this.plateau[i][j].b.i==2){
                    System.out.print("\u001B[34m" + "[2] "+"\u001B[0m");
                }else{
                    System.out.print("\u001B[33m" + "[3] "+"\u001B[0m");
                }
            }
            System.out.println();
        }
    }
}
