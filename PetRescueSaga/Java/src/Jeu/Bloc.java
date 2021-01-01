package Jeu;

import java.util.Random;

public class Bloc extends Container implements Comparable {
    public int i;

    Bloc(int i) {
        this.i = i;
    }

    // Bonus :
    public Bloc changementCouleur() {
        return new Bloc(new Random().nextInt(4));
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Bloc) {
            Bloc a = (Bloc) o;
            if (a.i == this.i) {
                return 1;
            }
        }
        return 0;
    }
}
