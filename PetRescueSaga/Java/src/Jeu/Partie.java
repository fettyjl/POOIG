package Jeu;
import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.*;
public class Partie {
    Joueur j;
    Niveau n;
    int score;
    int argentSave;
    int argentPerdu;

    public Partie(Niveau n){
        this.j=j;
        this.n=n;
        this.score=0;
        this.argentSave=0;
        this.argentPerdu=0;
    }
    public void round(){

        try (Scanner sc = new Scanner(System.in)) {
            int x,y;
            boolean perdu=false;
            int nbrTour=1;
            do{
                System.out.println("Tour "+nbrTour);
                System.out.println("Score :"+this.score);
                System.out.println("Sac d'argent sauvés :"+this.argentSave+"/4");
                System.out.println("Sac perdu :"+this.argentPerdu+"/4");
                System.out.println("Bonus Peinture "+this.j.bonus.peinture+"/ Fusée "+this.j.bonus.fusee+"/ Sauvetage "+this.j.bonus.sauvatage);
                //Mettre en place les bonus

                this.n.plateau.afficherPlateau();

                System.out.println("Case à jouer (x,y)");
                do {
                    System.out.println("Quel x ?");
                    x = sc.nextInt();
                    System.out.println("Quel y ?");
                    y = sc.nextInt();
                }while((x > -1 && x > n.plateau.longueur) || (y > -1 && y > n.plateau.largeur));

                this.n.plateau.caseAppuyer(x,y);
                int a= this.n.plateau.nombreCaseSupp()*10;
                this.score+=a;
                this.n.plateau.supprimerCase();
                this.argentSave+=this.n.plateau.argentSave();
                this.n.plateau.refreshPlateau();

                if(nbrTour%(9-n.niveau)==0){
                    System.out.println("ON est la !!!!!!!!!!!!!!");
                    this.argentPerdu+=this.n.plateau.nbrArgentPerdu();
                    this.n.plateau.ajouteLigneEnBas();
                    if(this.argentPerdu>2) {
                        perdu = true;
                        System.out.println("La police vous a attrapé, vous n'avez pas réussi a sauvé un nombre de sac suffisant !");
                    }
                }
                if(a>0)
                nbrTour++;
            }while(this.n.plateau.resteASave()!=0 && !perdu);
            this.j.scoreTot+=this.score;
            System.out.println("Vous avez réussi a sauvé un bon Nombre de sac !");
        }

    }
}
