package Jeu;

public class Case {
    Bloc bloc;
    Container container;

    public void ChangementCouleur(int a){
        if(this.container!=null && this.container instanceof Bloc){
            this.bloc.i=a;
        }
    }
}
