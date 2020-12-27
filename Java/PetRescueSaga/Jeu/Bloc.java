package Jeu;

public class Bloc extends Container implements Comparable{
    int i;

    Bloc(int i) {
        this.i = i;
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
