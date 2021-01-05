package Vue;

import Jeu.*;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    Game game = Serialisation.lecture("./Ressource/game.ser");
    JPanel container = new JPanel();
    CardLayout cl;
    MenuOuverture menuOuverture = new MenuOuverture(this);
    MenuNiveau menuNiveau = new MenuNiveau(this);
    Aide aide = new Aide(this);
    PartiePanel partiePanel = new PartiePanel(this, game.getListeNiveau().get(0));
    public PanelFin panelFin = new PanelFin(this, game.getListeNiveau().get(0));


    public Fenetre() {
        super("Payday !");
        this.setSize(1137, 853);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Serialisation.ecriture(game);
                System.exit(0);
            }
        });
        this.cl = new CardLayout();
        this.container.setLayout(cl);
        this.container.add(this.menuOuverture, "MenuOuverture");
        this.container.add(this.menuNiveau, "MenuNiveau");
        this.container.add(this.partiePanel, "PartiePanel");
        this.container.add(this.panelFin, "PanelFin");
        this.container.add(this.aide, "Aide");

        this.add(this.container);
        this.cl.show(this.container, "MenuOuverture");
    }
}

