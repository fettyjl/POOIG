package Vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    public Fenetre(){
        super("PetRescueSaga");
        this.setSize(800,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(new MenuOuverture());
    }

    public static void main(String[]args){
        EventQueue.invokeLater(() -> {
            String laf = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            try {
                UIManager.setLookAndFeel(laf);
                Fenetre a= new Fenetre();
            } catch (Exception e) {
                Fenetre a = new Fenetre();
            }
        });
    }
}

