package Jeu;

public class Case {
    Bloc bloc;
    //Prototype: (Valeur -> a : encore flou en fonction de la modélisation de blocs ect..), Utilité: Bonus Joueur
    public void ChangementCouleur(int a){
        if(this.bloc!=null){
            this.bloc.i=a;
        }
    }
}
