package Vue;

import Jeu.*;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    Game game = new Game();
    JPanel container = new JPanel();
    CardLayout cl;
    MenuOuverture menuOuverture = new MenuOuverture(this);
    MenuNiveau menuNiveau = new MenuNiveau(this);
    PartiePanel partiePanel = new PartiePanel(this, new Niveau(0));
    public PanelFin panelFin = new PanelFin(this, new Niveau(1));
    ;

    public Fenetre(Game game) {
        super("Payday !");
        this.game = game;
        this.setSize(1137, 853);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.cl = new CardLayout();
        this.container.setLayout(cl);
        this.container.add(this.menuOuverture, "MenuOuverture");
        this.container.add(this.menuNiveau, "MenuNiveau");
        this.container.add(this.partiePanel, "PartiePanel");
        this.container.add(this.panelFin, "PanelFin");

        this.add(this.container);
        this.cl.show(this.container, "MenuOuverture");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            String laf = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            try {
                UIManager.setLookAndFeel(laf);
                Fenetre a = new Fenetre(new Game());
                a.setVisible(true);
            } catch (Exception e) {
                Fenetre a = new Fenetre(new Game());
                a.setVisible(true);
            }
        });
    }
}

