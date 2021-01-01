package Jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public Joueur joueur;
    public ArrayList<Niveau> listeNiveau = new ArrayList<>();

    public Game() {
        this.joueur = new Joueur();
        int i = 1;
        while (i < 9) {
            listeNiveau.add(new Niveau(i));
            i++;
        }
    }

    public void lanceurGame() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Bienvenue sur le Jeu Payday !");
            System.out.println("Liste des niveaux :");
            for (Niveau n : this.listeNiveau) {
                System.out.println("Niveau " + n.difficulte);
            }
            int s;
            do {
                System.out.println("Quel niveau voulez-vous sélectionné ? ");
                s = sc.nextInt();
                if (s > 0 && s < listeNiveau.size() + 1) {
                    Niveau choix = listeNiveau.get(s - 1);
                    int x, y;
                    boolean perdu = false;
                    do {
                        System.out.println("Tour " + choix.nbrTour);
                        System.out.println("Score :" + choix.score);
                        System.out.println("Sac d'argent sauvés :" + choix.argentSave + "/4");
                        System.out.println("Sac perdu :" + choix.argentPerdu + "/4");
                        System.out.println("Bonus Peinture " + this.joueur.bonus.peinture + "/ Fusée " + this.joueur.bonus.fusee + "/ Sauvetage " + this.joueur.bonus.sauvetage);
                        //Donner la possibilité au Joueur d'utiliser les bonus TODO

                        choix.plateau.afficherPlateau();

                        System.out.println("Case à jouer (x,y)");
                        do {
                            System.out.println("Quel x ?");
                            x = sc.nextInt();
                            System.out.println("Quel y ?");
                            y = sc.nextInt();
                        } while ((x > -1 && x > choix.plateau.longueur) || (y > -1 && y > choix.plateau.largeur));

                        choix.plateau.caseAppuyer(x, y);
                        int a = choix.plateau.nombreCaseSupp() * 10;
                        choix.score += a;
                        choix.plateau.supprimerCase();
                        choix.argentSave += choix.plateau.argentSave();
                        choix.plateau.refreshPlateau();

                        if (choix.nbrTour % (9 - choix.difficulte - 1) == 0) {
                            choix.argentPerdu += choix.plateau.nbrArgentPerdu();
                            choix.plateau.ajouteLigneEnBas();
                            if (choix.argentPerdu > 2) {
                                perdu = true;
                                System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                            }
                        }
                        if (a > 0)
                            choix.nbrTour++;
                    } while (choix.plateau.resteASave() != 0 && !perdu);
                    if (!perdu) {
                        this.joueur.scoreTot += choix.score;
                        System.out.println("Vous avez réussi a sauvé un bon Nombre de sac !");
                    }
                }
            } while (!(s > 1 && s < listeNiveau.size() + 1));
        }
    }
}
