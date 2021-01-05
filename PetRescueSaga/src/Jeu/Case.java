package Jeu;

import java.io.Serializable;

public class Case implements Serializable {
    Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
