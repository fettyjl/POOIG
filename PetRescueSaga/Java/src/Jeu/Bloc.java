package Jeu;

public class Bloc extends Container implements Comparable{
    public int i;

    Bloc(int i) {
        this.i = i;
    }
    // Bonus :
    void changementCouleur(int a){
        this.i=a;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Bloc){
            Bloc a= (Bloc) o;
            if(a.i == this.i){
                return 1;
            }
        }
        return 0;
    }
}
