package Jeu;

import java.util.Random;

public class Plateau {
    int longueur, largeur;
    Case [][] plateau;
    Plateau(int longueur, int largeur){
        this.longueur=longueur;
        this.largeur=largeur;
        this.plateau=new Case[this.largeur][this.longueur];
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
                    System.out.print("0 ");
                }else if(this.plateau[i][j].b.i==0){
                    System.out.print("1 ");
                }else if(this.plateau[i][j].b.i==0){
                    System.out.print("2 ");
                }else{
                    System.out.print("3 ");
                }
            }
            System.out.println();
        }
    }
}
