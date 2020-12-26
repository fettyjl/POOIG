package Vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    ImagePanel fond= new ImagePanel("/Image/imagejungle.png");

    // Menu Ouverture:
    JPanel menuOuverture=new JPanel();
    JButton buttonJouer = new JButton("Jouer");
    JButton buttonAide = new JButton("Aide");
    JButton buttonQuitter = new JButton("Quitter");

    public Fenetre(){
        super("PetRescueSaga");
        this.setSize(800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.fond.setLayout(null);
        this.getContentPane().add(this.fond);

        this.buttonJouer.setBounds(350, 200, 100, 25);
        this.buttonAide.setBounds(350, 230, 100, 25);
        this.buttonQuitter.setBounds(350, 260, 100, 25);

        this.fond.add(this.buttonJouer);
        this.fond.add(this.buttonAide);
        this.fond.add(this.buttonQuitter);
        this.buttonQuitter.addActionListener((e) -> System.exit(0));

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

