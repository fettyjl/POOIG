import Jeu.Game;
import Jeu.Serialisation;
import Vue.Fenetre;

import javax.swing.*;
import java.io.*;

import static java.awt.EventQueue.invokeLater;

public class Launcher implements Serializable{

    public static void main(String[] args) {
        invokeLater(() -> {
            String laf = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            try {
                UIManager.setLookAndFeel(laf);
                Fenetre a = new Fenetre();
                a.setVisible(true);
            } catch (Exception e) {
                Fenetre a = new Fenetre();
                a.setVisible(true);
            }
        });
    }
}
