package Vue;
import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    // Enlever le extends et mettre jframe en objet
    // Menu Ouverture:
    ImagePanel menuOuverture= new ImagePanel("/imagejungle.png");
    JLabel titre=new JLabel("<html><h1><strong><i><font color=\"white\"> Pet Rescue Saga </font></i></strong></h1><hr></html>");
    JButton buttonJouer = new JButton("Jouer");
    JButton buttonAide = new JButton("Option");
    JButton buttonQuitter = new JButton("Quitter");

    public Fenetre(){
        super("PetRescueSaga");
        this.setSize(1024,768);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menuOuverture.setLayout(null);
        this.getContentPane().add(this.menuOuverture);

        this.menuOuverture.add(this.titre);
        this.titre.setBounds(411,0,300,450);
        this.buttonJouer.setBounds(358, 340, 300, 25);
        this.buttonAide.setBounds(358, 380, 300, 25);
        this.buttonQuitter.setBounds(358, 420, 300, 25);

        this.menuOuverture.add(this.titre);
        this.menuOuverture.add(this.buttonJouer);
        this.menuOuverture.add(this.buttonAide);
        this.menuOuverture.add(this.buttonQuitter);
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

