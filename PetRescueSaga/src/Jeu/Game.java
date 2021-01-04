package Jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
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
            System.out.println("Bienvenue sur le Jeu Payday !");
            System.out.println("Liste des niveaux :");
            for (Niveau n : this.listeNiveau) {
                System.out.println("Niveau " + (n.difficulte+1));
            }
            int s;
            do {
                System.out.println("Quel niveau voulez-vous sélectionné ? ");
                s = sc.nextInt();
                if (s > 0 && s < listeNiveau.size()+1) {
                    Niveau choix = listeNiveau.get(s-1);
                    int x, y;
                    boolean perdu = false;
                    do {
                        System.out.println("Tour " + choix.nbrTour);
                        System.out.println("Score :" + choix.score);
                        System.out.println("Sac d'argent sauvés :" + choix.argentSave + "/4");
                        System.out.println("Sac perdu :" + choix.argentPerdu + "/4");
                        System.out.println("Bonus Peinture " + this.joueur.bonus.peinture + "/ Fusée " + this.joueur.bonus.fusee + "/ Sauvetage " + this.joueur.bonus.sauvetage);

                        choix.plateau.afficherPlateau();
                        System.out.println("Case à jouer (x,y)");
                        do {
                            System.out.println("Quel x ?");
                            x = sc.nextInt();
                            System.out.println("Quel y ?");
                            y = sc.nextInt();
                        } while ((x < -1 && x > choix.plateau.longueur) || (y < -1 && y > choix.plateau.largeur));

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
                                if (choix.argentPerdu > 1 || (choix.plateau.plusDeCoup() && joueur.bonus.plusDeFetP() && joueur.bonus.sauvetage < choix.plateau.resteASave())) {
                                    perdu = true;
                                    System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                                }
                            }
                            choix.nbrTour++;
                        }
                    } while ((choix.plateau.resteASave() != 0 || (choix.plateau.plusDeCoup() && joueur.bonus.plusDeBonus() && choix.argentSave>1))&& !perdu);
                    if (!perdu) {
                        this.joueur.scoreTot += choix.score;
                        System.out.println("Vous avez réussi a sauvé un bon Nombre de sac !");
                    }
                }
            } while (!(s > 1 && s < listeNiveau.size() + 1));
        }
    }

    public void ActionBonusSauvetage(int x, int y, int n) {
        if (joueur.bonus.sauvetage > 0) {
            if (listeNiveau.get(n).plateau.sauvetageArgent(x, y)) {
                listeNiveau.get(n).argentSave++;
                joueur.bonus.sauvetage--;
            }
        }
    }

    public void ActionBonusFusée(int y, int n) {
        if (joueur.bonus.fusee > 0) {
            listeNiveau.get(n).argentSave += listeNiveau.get(n).plateau.bonusEnColonne(y);
            listeNiveau.get(n).plateau.refreshPlateau();
            joueur.bonus.fusee--;
        }
    }

    public void ActionBonusPeinture(int x, int y, int n) {
        if ((joueur.bonus.peinture > 0) && (listeNiveau.get(n).plateau.plateau[x][y].container instanceof Bloc)) {
            listeNiveau.get(n).plateau.plateau[x][y].container = ((Bloc) listeNiveau.get(n).plateau.plateau[x][y].container).changementCouleur();
            joueur.bonus.peinture--;
        }
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

    public static void main(String[] args) {
        Game jeu = new Game();
        jeu.lanceurGame();
    }
}
