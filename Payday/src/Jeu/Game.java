package Jeu;

import Vue.MenuNiveau;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game implements Serializable {
    Joueur joueur;
    ArrayList<Niveau> listeNiveau = new ArrayList<>();

    //Initialise une Game avec un Joueur et une liste de niveaux:
    public Game() {
        this.joueur = new Joueur();
        int i = 0;
        while (i < 8) {
            listeNiveau.add(new Niveau(i));
            i++;
        }
    }

    //Lance la partie sur le terminal:
    public void lanceurGame() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Liste des niveaux :");
            for (Niveau n : this.listeNiveau) {
                int i = n.nbEtoile;
                System.out.print("Niveau " + (n.difficulte + 1));
                if (n.dispo) {
                    System.out.println();
                } else {
                    System.out.println(" (bloqué)");
                }
                if (i > 0)
                    System.out.print("\u001B[34m" + "*" + "\u001B[0m");
                else
                    System.out.print("*");
                i--;
                if (i > 0)
                    System.out.print("\u001B[34m" + "*" + "\u001B[0m");
                else
                    System.out.print("*");
                i--;
                if (i > 0)
                    System.out.print("\u001B[34m" + "*" + "\u001B[0m");
                else
                    System.out.print("*");
                i--;
                System.out.println();
            }
            int s;
            boolean rejouer = false;
            do {
                s = readInt(sc, "Quel niveau voulez-vous sélectionné ? ", "Ceci n'est pas un nombre entier. Recommencez : ");

                if (s > 0 && s < listeNiveau.size() + 1) {
                    if (!listeNiveau.get(s - 1).dispo)
                        System.out.println("Niveau pas encore débloqué !");
                    if (listeNiveau.get(s - 1).dispo) {
                        Niveau choix = listeNiveau.get(s - 1);
                        int x, y;
                        boolean perdu = false;
                        do {
                            System.out.println("Tour " + choix.nbrTour);
                            System.out.println("Score :" + choix.score);
                            System.out.println("Sac d'argent sauvés :" + choix.argentSave + "/4");
                            System.out.println("Sac perdu :" + choix.argentPerdu + "/4");
                            System.out.println("Bonus Peinture " + this.joueur.bonus.peinture + "/ Fusée " + this.joueur.bonus.fusee + "/ Sauvetage " + this.joueur.bonus.sauvetage);

                            choix.plateau.afficherPlateau();

                            if (utiliseBonus()) {
                                jouerBonus(choix);
                                int a = choix.plateau.nombreCaseSupp() * 10;
                                choix.score += a;
                                choix.plateau.supprimerCase();
                                choix.argentSave += choix.plateau.argentSave();
                                choix.plateau.refreshPlateau();

                                if (a > 0) {
                                    if (choix.nbrTour % (10 - choix.difficulte) == 0) {
                                        choix.argentPerdu += choix.plateau.nbrArgentPerdu();
                                        choix.plateau.ajouteLigneEnBas();
                                        if (choix.argentPerdu > 1 || ((choix.argentSave < 2) && !choix.plateau.plusDeCoup() && joueur.bonus.plusDeFetP() && joueur.bonus.sauvetage < choix.plateau.resteASave())) {
                                            perdu = true;
                                            System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                                        }
                                    }
                                }
                            } else {
                                int z = 0;
                                System.out.println("Donnez une case à jouer (x,y):");
                                do {
                                    if (z > 0)
                                        System.out.println("Case inexistante, Recommencez ...");
                                    x = readInt(sc, "Quel X ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                                    y = readInt(sc, "Quel Y ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                                    z++;

                                } while ((x < 0 || x > choix.plateau.longueur) || (y < 0 || y > choix.plateau.largeur));
                                choix.plateau.caseAppuyer(x, y);
                                int a = choix.plateau.nombreCaseSupp() * 10;
                                choix.score += a;
                                choix.plateau.supprimerCase();
                                choix.argentSave += choix.plateau.argentSave();
                                choix.plateau.refreshPlateau();

                                if (a > 0) {
                                    if (choix.nbrTour % (10 - choix.difficulte) == 0) {
                                        choix.argentPerdu += choix.plateau.nbrArgentPerdu();
                                        choix.plateau.ajouteLigneEnBas();
                                        if (choix.argentPerdu > 1 || ((choix.argentSave < 2) && !choix.plateau.plusDeCoup() && joueur.bonus.plusDeFetP() && joueur.bonus.sauvetage < choix.plateau.resteASave())) {
                                            perdu = true;
                                            System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                                        }
                                    }
                                    choix.nbrTour++;
                                }
                            }
                        } while ((choix.plateau.resteASave() != 0 || (!choix.plateau.plusDeCoup() && joueur.bonus.plusDeBonus() && choix.argentSave > 1)) && !perdu);
                        if (!perdu) {
                            this.joueur.scoreTot += choix.score;
                            this.joueur.addBonus(choix.score);
                            System.out.println("Vous avez réussi a sauvé un bon Nombre de sac !");
                            this.listeNiveau.get(choix.difficulte + 1).dispo = true;
                        }
                    }
                }
                boolean verif = false;
                String lettre = "NULL";
                do {
                    System.out.print("Choisir un autre niveau ? [Y/N]: ");
                    lettre = sc.next();
                    switch (lettre.toUpperCase()) {
                        case "Y":
                            lettre = "Y";
                            verif = true;
                            rejouer = true;
                        break;
                        case "N":
                            lettre = "N";
                            verif = true;
                        break;
                        default:
                            System.out.println("Choisir entre Y ou N");
                    }
                } while (!verif);
            } while (!(s > 0 && s < listeNiveau.size() + 1) || rejouer);
        }
    }

    public void botGame() {
        try (Scanner sc = new Scanner(System.in)) {
            afficherPayDay();
            System.out.println("Liste des niveaux :");
            int m = 1;
            while (m < 9) {
                System.out.println("Niveau " + m);
                m++;
            }
            int n;
            int x, y;
            boolean perdu = false;
            int f, p, s;
            do {
                n = readInt(sc, "Quel niveau voulez-vous sélectionné ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                f = readInt(sc, "Combien de fusée ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                p = readInt(sc, "Combien de peinture ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                s = readInt(sc, "Quel niveau voulez-vous sélectionné ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                if (!(n > 0 && n < listeNiveau.size()))
                    System.out.println("Saisir un niveau existant");
                Niveau niv = listeNiveau.get(n - 1);
                joueur.bonus.fusee = f;
                joueur.bonus.peinture = p;
                joueur.bonus.sauvetage = s;

                do {
                    x = new Random().nextInt(8);
                    y = new Random().nextInt(8);

                    if (niv.plateau.plateau[x][y].caseEstVide(x, y)) {

                    } else {
                        System.out.println("Tour " + niv.nbrTour);
                        System.out.println("Score :" + niv.score);
                        System.out.println("Sac d'argent sauvés :" + niv.argentSave + "/4");
                        System.out.println("Sac perdu :" + niv.argentPerdu + "/4");
                        System.out.println("Bonus Peinture " +joueur.bonus.peinture + "/ Fusée " + joueur.bonus.fusee + "/ Sauvetage " + joueur.bonus.sauvetage);
                        niv.plateau.afficherPlateau();
                        if (niv.plateau.plateau[x][y].caseArgent(x, y)) {
                            actionBonusSauvetage(x, y, n);
                            int a = niv.plateau.nombreCaseSupp() * 10;
                            niv.score += a;
                            niv.plateau.supprimerCase();
                            niv.argentSave += niv.plateau.argentSave();
                            niv.plateau.refreshPlateau();

                            if (a > 0) {
                                if (niv.nbrTour % (10 - niv.difficulte) == 0) {
                                    niv.argentPerdu += niv.plateau.nbrArgentPerdu();
                                    niv.plateau.ajouteLigneEnBas();
                                    if (niv.argentPerdu > 1 || ((niv.argentSave < 2) && !niv.plateau.plusDeCoup() && joueur.bonus.plusDeFetP() && joueur.bonus.sauvetage < niv.plateau.resteASave())) {
                                        perdu = true;
                                        System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                                    }
                                }
                            }
                        } else if (niv.plateau.plateau[x][y].caseBloc(x, y)) {
                            if (!niv.plateau.choixPossible(x, y)) {
                                if (joueur.bonus.peinture > 0) {
                                    actionBonusPeinture(x, y, n);
                                } else {
                                    actionBonusFusee(y, n);
                                }
                                int a = niv.plateau.nombreCaseSupp() * 10;
                                niv.score += a;
                                niv.plateau.supprimerCase();
                                niv.argentSave += niv.plateau.argentSave();
                                niv.plateau.refreshPlateau();

                                if (a > 0) {
                                    if (niv.nbrTour % (10 - niv.difficulte) == 0) {
                                        niv.argentPerdu += niv.plateau.nbrArgentPerdu();
                                        niv.plateau.ajouteLigneEnBas();
                                        if (niv.argentPerdu > 1 || ((niv.argentSave < 2) && !niv.plateau.plusDeCoup() && joueur.bonus.plusDeFetP() && joueur.bonus.sauvetage < niv.plateau.resteASave())) {
                                            perdu = true;
                                            System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                                        }
                                    }
                                }
                            } else {
                                int a = niv.plateau.nombreCaseSupp() * 10;
                                niv.score += a;
                                niv.plateau.supprimerCase();
                                niv.argentSave += niv.plateau.argentSave();
                                niv.plateau.refreshPlateau();
                                if (a > 0) {
                                    if (niv.nbrTour % (10 - niv.difficulte) == 0) {
                                        niv.argentPerdu += niv.plateau.nbrArgentPerdu();
                                        niv.plateau.ajouteLigneEnBas();
                                        if (niv.argentPerdu > 1 || ((niv.argentSave < 2) && !niv.plateau.plusDeCoup() && joueur.bonus.plusDeFetP() && joueur.bonus.sauvetage < niv.plateau.resteASave())) {
                                            perdu = true;
                                            System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                                        }
                                    }
                                    niv.nbrTour++;
                                }
                            }
                        }
                    }
                } while ((niv.plateau.resteASave() != 0 || (!niv.plateau.plusDeCoup() && joueur.bonus.plusDeBonus() && niv.argentSave > 1)) && !perdu);
                if (!perdu) {
                    this.joueur.scoreTot += niv.score;
                    this.joueur.addBonus(niv.score);
                    System.out.println("Vous avez réussi a sauvé un bon Nombre de sac !");
                    this.listeNiveau.get(niv.difficulte + 1).dispo = true;
                }
            } while (!(n > 0 && n < listeNiveau.size()));
        }
    }

    public void actionBonusSauvetage(int x, int y, int n) {
        if (joueur.bonus.sauvetage > 0) {
            if (listeNiveau.get(n).plateau.sauvetageArgent(x, y)) {
                listeNiveau.get(n).argentSave++;
                joueur.bonus.sauvetage--;
                listeNiveau.get(n).nbrTour++;
            }
        }
    }

    public void actionBonusFusee(int y, int n) {
        if (joueur.bonus.fusee > 0) {
            listeNiveau.get(n).argentSave += listeNiveau.get(n).plateau.bonusEnColonne(y);
            listeNiveau.get(n).plateau.refreshPlateau();
            joueur.bonus.fusee--;
            listeNiveau.get(n).nbrTour++;
        }
    }

    public void actionBonusPeinture(int x, int y, int n) {
        if ((joueur.bonus.peinture > 0) && (listeNiveau.get(n).plateau.plateau[x][y].container instanceof Bloc)) {
            listeNiveau.get(n).plateau.plateau[x][y].container = ((Bloc) listeNiveau.get(n).plateau.plateau[x][y].container).changementCouleur();
            joueur.bonus.peinture--;
            listeNiveau.get(n).nbrTour++;
        }
    }

    public boolean utiliseBonus() {
        boolean verif = false;
        boolean rep = false;
        String lettre = "NULL";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Voulez utiliser un BONUS ? [Y/N]: ");
            lettre = sc.next();
            switch (lettre.toUpperCase()) {
                case "Y":
                    lettre = "Y";
                    verif = true;
                    rep = true;
                break;
                case "N":
                    lettre = "N";
                    verif = true;
                break;
                default:
                    System.out.println("Choisir entre Y ou N");
            }
        } while (!verif);

        return rep;
    }


    public void jouerBonus(Niveau choix) {
        int x, y;
        Scanner sc = new Scanner(System.in);

        boolean verif2 = false;
        String lettre2 = "NULL";
        do {
            System.out.print("Quel BONUS voulez-vous utiliser ? Peinture/Fusée/Sauvetage ? [P/F/S]: ");
            lettre2 = sc.next();
            int s = 0;
            switch (lettre2.toUpperCase()) {
                case "P":
                    s=0;
                    System.out.println("Case à jouer pour bonus Peinture ? (x,y)");
                    do {
                        do {
                            if (s > 0)
                                System.out.println("Saisie incorrect, Recommencez...");
                            x = readInt(sc, "Quel X ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                            y = readInt(sc, "Quel Y ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                            s++;
                        } while ((x < 0 || x > choix.plateau.longueur) || (y < 0 || y > choix.plateau.largeur));
                    } while (!(choix.plateau.plateau[x][y].container instanceof Bloc));
                    actionBonusPeinture(x, y, choix.getDifficulte());
                    verif2 = true;
                break;
                case "F":
                     s = 0;
                    System.out.println("Colonne sur laquel jouer pour bonus Fusée ? (y)");
                    do {
                        if (s > 0)
                            System.out.println("Saisie incorrect, Recommencez...");
                        y = readInt(sc, "Quel Y ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                        s++;
                    } while (y < 0 || y > choix.plateau.largeur);
                    actionBonusFusee(y, choix.getDifficulte());
                    verif2 = true;
                break;
                case "S":
                    System.out.println("Case à jouer pour bonus Sauvetage ? (x,y)");
                    s = 0;
                    do {
                        do {
                            if (s > 0)
                                System.out.println("Saisie incorrect, Recommencez...");
                            x = readInt(sc, "Quel X ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                            y = readInt(sc, "Quel Y ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                            s++;
                        } while ((x < 0 || x > choix.plateau.longueur) || (y < 0 || y > choix.plateau.largeur));
                    } while (!(choix.plateau.plateau[x][y].container instanceof Argent));
                    actionBonusSauvetage(x, y, choix.getDifficulte());
                    verif2 = true;
                break;
                default:
                    System.out.println("FAIRE UN CHOIX ENTRE (P) (F) (S)");
            }
        } while (!verif2);
    }


    public static int readInt(Scanner scanner, String prompt, String promptOnError) {

        System.out.print(prompt);

        while (!scanner.hasNextInt()) {
            System.out.print(promptOnError);
            scanner.nextLine(); // vidage saisie incorrect
        }

        final int input = scanner.nextInt();
        scanner.nextLine(); // vidage buffer
        return input;

    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public ArrayList<Niveau> getListeNiveau() {
        return listeNiveau;
    }

    public void setListeNiveau(ArrayList<Niveau> listeNiveau) {
        this.listeNiveau = listeNiveau;
    }
}
