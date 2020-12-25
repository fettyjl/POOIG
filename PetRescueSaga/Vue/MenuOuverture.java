package Vue;

import javax.swing.*;
import java.awt.*;

public class MenuOuverture extends JFrame{

    ImagePanel fond= new ImagePanel("/image/imagejungle.png");
    JPanel button=new JPanel();
    JButton buttonJouer = new JButton("Jouer");
    JButton buttonAide = new JButton("Aide");
    JButton buttonQuitter = new JButton("Quitter");

    public MenuOuverture(){
        super("PetRescueSaga");
        this.setSize(800,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setContentPane(this.fond);
        this.fond.setLayout(null);
        this.buttonJouer.setBounds(350, 200, 100, 25);
        this.buttonAide.setBounds(350, 230, 100, 25);
        this.buttonQuitter.setBounds(350, 260, 100, 25);
        this.fond.add(this.buttonJouer);
        this.fond.add(this.buttonAide);
        this.fond.add(this.buttonQuitter);

    }

    public static void main(String[]args){
        EventQueue.invokeLater(() -> {
            String laf = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            try {
                UIManager.setLookAndFeel(laf);
                MenuOuverture a= new MenuOuverture();
            } catch (Exception e) {
                MenuOuverture a = new MenuOuverture();
            }
        });
    }
}

