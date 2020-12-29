package Vue;
import Jeu.*;
import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    Partie part = new Partie();
    JPanel container = new JPanel();
    CardLayout cl;
    MenuOuverture menuOuverture=new MenuOuverture(this);
    MenuNiveau menuNiveau=new MenuNiveau(this);
    public Fenetre() {
        this.setSize(1024, 768);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.cl = new CardLayout();
        this.container.setLayout(cl);
        this.container.add(this.menuOuverture, "MenuOuverture");
        this.container.add(this.menuNiveau, "MenuNiveau");
        this.add(this.container);
        this.cl.show(this.container, "MenuOuverture");
    }

    public static void main(String[]args){
        EventQueue.invokeLater(() -> {
            String laf = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            try {
                UIManager.setLookAndFeel(laf);
                Fenetre a= new Fenetre();
                a.setVisible(true);
            } catch (Exception e) {
                Fenetre a = new Fenetre();
                a.setVisible(true);
            }
        });
    }
}

