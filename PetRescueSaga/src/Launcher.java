import Jeu.Game;
import Vue.Fenetre;

import javax.swing.*;
import java.awt.*;

public class Launcher {

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
