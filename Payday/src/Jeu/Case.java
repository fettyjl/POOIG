package Jeu;

import java.io.Serializable;

public class Case implements Serializable {
    Container container;

    public boolean caseEstVide(int x, int y){
        return container==null;
    }
    public boolean caseArgent(int x,int y){
        return container instanceof Argent;
    }
    public boolean caseBloc(int x,int y){
        return container instanceof Bloc;
    }
    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
