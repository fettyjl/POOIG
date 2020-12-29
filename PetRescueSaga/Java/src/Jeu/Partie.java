package Jeu;
import java.util.*;
public class Partie {
    Plateau p;
    Joueur j;
    int score;
    int animalSave;
    int animalDeath;

    public Partie(){
        this.p=new Plateau();
        this.j=new Joueur();
        this.score=0;
        this.animalSave=0;
        this.animalDeath=0;
    }
    public void round(){

        try (Scanner sc = new Scanner(System.in)) {
            int x,y;
            boolean perdu=false;
            int nbrTour=1;
            do{
                System.out.println("Score :"+this.score);
                System.out.println("Animaux sauvés :"+this.animalSave+"/4");
                System.out.println("Animaux mort :"+this.animalDeath+"/4");
                System.out.println("Bonus Peinture "+this.j.bonus.peinture+"/ Fusée "+this.j.bonus.fusee+"/ Sauvetage "+this.j.bonus.sauvatage);
                //Mettre en place les bonus, Peut être un system de niv

                this.p.afficherPlateau();
                System.out.println("Case à jouer (x,y)");
                System.out.println("Quel x ?");
                x=sc.nextInt();
                System.out.println("Quel y ?");
                y=sc.nextInt();
                this.p.caseAppuyer(x,y);
                this.score+=this.p.nombreCaseSupp()*10;
                this.p.supprimerCase();
                this.animalSave+=this.p.aniSave();
                this.p.refreshPlateau();
                if(nbrTour%6==0){
                    this.animalDeath+=this.p.nbrAnimauxPerdu();
                    this.p.ajouteLigneEnBas();
                    nbrTour++;
                    if(this.animalDeath>2) {
                        perdu = true;
                        System.out.println("VOUS AVEZ PERDU !");
                    }
                }
            }while(this.p.resteASave()!=0 && !perdu);

            System.out.println("VOUS AVEZ GAGNEE!");
        }

    }
}
