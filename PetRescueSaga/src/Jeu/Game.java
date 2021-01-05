package Jeu;

import java.io.Serializable;
import java.util.ArrayList;
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
            afficherPayDay();
            System.out.println("Liste des niveaux :");
            for (Niveau n : this.listeNiveau) {
                System.out.println("Niveau " + (n.difficulte + 1));
            }
            int s;
            do {
                s = readInt(sc, "Quel niveau voulez-vous sélectionné ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
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

                        choix.plateau.afficherPlateau();

                        if (UtiliseBonus()) {
                            JouerBonus(choix);
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
                            int z=0;
                            System.out.println("Donnez une case à jouer (x,y):");
                            do {
                                if(z>0)
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
                    } while ((choix.plateau.resteASave() != 0 || (choix.plateau.plusDeCoup() && joueur.bonus.plusDeBonus() && choix.argentSave > 1)) && !perdu);
                    if (!perdu) {
                        this.joueur.scoreTot += choix.score;
                        this.joueur.addBonus(choix.score);
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
                listeNiveau.get(n).nbrTour++;
            }
        }
    }

    public void ActionBonusFusée(int y, int n) {
        if (joueur.bonus.fusee > 0) {
            listeNiveau.get(n).argentSave += listeNiveau.get(n).plateau.bonusEnColonne(y);
            listeNiveau.get(n).plateau.refreshPlateau();
            joueur.bonus.fusee--;
            listeNiveau.get(n).nbrTour++;
        }
    }

    public void ActionBonusPeinture(int x, int y, int n) {
        if ((joueur.bonus.peinture > 0) && (listeNiveau.get(n).plateau.plateau[x][y].container instanceof Bloc)) {
            listeNiveau.get(n).plateau.plateau[x][y].container = ((Bloc) listeNiveau.get(n).plateau.plateau[x][y].container).changementCouleur();
            joueur.bonus.peinture--;
            listeNiveau.get(n).nbrTour++;
        }
    }

    public boolean UtiliseBonus() {
        boolean verif = false;
        boolean rep = false;
        String lettre = "NULL";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Voulez utiliser un BONUS ? [Y/N]: ");
            lettre = sc.next();
            switch (lettre.toUpperCase()) {
                case "Y" -> {
                    lettre = "Y";
                    verif = true;
                    rep = true;
                }
                case "N" -> {
                    lettre = "N";
                    verif = true;
                }
                default -> System.out.println("Choisir entre Y ou N");
            }
        } while (!verif);

        return rep;
    }


    public void JouerBonus(Niveau choix) {
        int x, y;
        Scanner sc = new Scanner(System.in);

        boolean verif2 = false;
        String lettre2 = "NULL";
        do {
            System.out.print("Quel BONUS voulez-vous utiliser ? Peinture/Fusée/Sauvetage ? [P/F/S]: ");
            lettre2 = sc.next();
            switch (lettre2.toUpperCase()) {
                case "P" -> {
                    int s = 0;
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
                    ActionBonusPeinture(x, y, choix.getDifficulte());
                    verif2 = true;
                }
                case "F" -> {
                    int s = 0;
                    System.out.println("Colonne sur laquel jouer pour bonus Fusée ? (y)");
                    do {
                        if (s > 0)
                            System.out.println("Saisie incorrect, Recommencez...");
                        y = readInt(sc, "Quel Y ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                        s++;
                    } while (y < 0 || y > choix.plateau.largeur);
                    ActionBonusFusée(y, choix.getDifficulte());
                    verif2 = true;
                }
                case "S" -> {
                    System.out.println("Case à jouer pour bonus Sauvetage ? (x,y)");
                    int s = 0;
                    do {
                        do {
                            if (s > 0)
                                System.out.println("Saisie incorrect, Recommencez...");
                            x = readInt(sc, "Quel X ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                            y = readInt(sc, "Quel Y ? ", "Ceci n'est pas un nombre entier. Recommencez : ");
                            s++;
                        } while ((x < 0 || x > choix.plateau.longueur) || (y < 0 || y > choix.plateau.largeur));
                    }while( !(choix.plateau.plateau[x][y].container instanceof Argent));
                    ActionBonusSauvetage(x, y, choix.getDifficulte());
                    verif2 = true;
                }
                default -> System.out.println("FAIRE UN CHOIX ENTRE (P) (F) (S)");
            }
        } while (!verif2);
    }

    public static void afficherPayDay() {
        System.out.println("""
                ============================================================================================================================= 
                |                                                                                                                           |
                |     /$$$$$$$                     /$$$$$$$                             /$$$$$$                                  \s          | 
                |    | $$__  $$                   | $$__  $$                           /$$__  $$                                 \s          | 
                |    | $$  \\ $$ /$$$$$$  /$$   /$$| $$  \\ $$  /$$$$$$  /$$   /$$      | $$  \\__/  /$$$$$$  /$$$$$$/$$$$   /$$$$$$\s          |    
                |    | $$$$$$$/|____  $$| $$  | $$| $$  | $$ |____  $$| $$  | $$      | $$ /$$$$ |____  $$| $$_  $$_  $$ /$$__  $$          |
                |    | $$____/  /$$$$$$$| $$  | $$| $$  | $$  /$$$$$$$| $$  | $$      | $$|_  $$  /$$$$$$$| $$ \\ $$ \\ $$| $$$$$$$$          |    
                |    | $$      /$$__  $$| $$  | $$| $$  | $$ /$$__  $$| $$  | $$      | $$  \\ $$ /$$__  $$| $$ | $$ | $$| $$_____/          |
                |    | $$     |  $$$$$$$|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$      |  $$$$$$/|  $$$$$$$| $$ | $$ | $$|  $$$$$$$          |    
                |    |__/      \\_______/ \\____  $$|_______/  \\_______/ \\____  $$       \\______/  \\_______/|__/ |__/ |__/ \\_______/          |    
                |                        /$$  | $$                     /$$  | $$                                                 \s          |    
                |                       |  $$$$$$/                    |  $$$$$$/                                                 \s          |    
                |                        \\______/                      \\______/                                                  \s          |    
                |                                                                                                                           |
                =============================================================================================================================    
                                     
                """);
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
