package Jeu;

import MVC.Affichage;

import javax.swing.*;

public class Test {
    public static void main(String[] args){

        //Initialisation :
        Plateau test=new Plateau();
        test.remplirCase();

        test.afficherPlateau();

        //Test :
        test.caseAppuyer(4,4);
        test.supprimerCase();
        test.refreshPlateau();

        System.out.println();
        test.afficherPlateau();


        JFrame fenetre= new Affichage();
    }
}
