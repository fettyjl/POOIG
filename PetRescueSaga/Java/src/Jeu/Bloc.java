package Jeu;

import java.util.Random;

public class Bloc extends Container implements Comparable {
    int i;

    Bloc(int i) {
        this.i = i;
    }

    // Bonus :
    public Bloc changementCouleur() {
        Bloc a = new Bloc(new Random().nextInt(4));
        while (a.i == this.i) {
            a = new Bloc(new Random().nextInt(4));
        }
        return a;
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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
