package Vue;

import javax.swing.*;
import java.awt.*;

public class MenuOuverture extends ImagePanel {

    Fenetre fenetre;
    JLabel titre=new JLabel("<html><h1><strong><i><font color=\"white\"> Pet Rescue Saga </font></i></strong></h1><hr></html>");
    JButton buttonJouer = new JButton("Jouer");
    JButton buttonAide = new JButton("Option");
    JButton buttonQuitter = new JButton("Quitter");
        MenuOuverture(Fenetre fenetre){
            super("/imagejungle.png");
            this.fenetre=fenetre;
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.anchor = GridBagConstraints.NORTH;
            c.fill =GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 0;
            this.add(this.titre,c);
            c.gridy = 1;
            this.add(this.buttonJouer,c);
            c.gridy = 2;
            this.add(this.buttonAide,c);
            c.gridy = 3;
            this.add(this.buttonQuitter,c);

            this.buttonJouer.addActionListener((e) -> this.fenetre.cl.show(this.fenetre.container,"MenuNiveau"));
            this.buttonQuitter.addActionListener((e) -> System.exit(0));
        }
}
