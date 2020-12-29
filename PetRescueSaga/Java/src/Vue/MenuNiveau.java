package Vue;


import javax.swing.*;
import java.awt.*;

public class MenuNiveau extends ImagePanel{
    JLabel titre=new JLabel("<html><h1><strong><i><font color=\"white\">Niveau Disponible :</font></i></strong><hr></h1></html>");
    JLabel panel=new JLabel();
    MenuNiveau(Fenetre fenetre){
        super("/imagejungle.png");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.NORTH;
        c.fill =GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(this.titre,c);
        c.gridx = 0;
        c.gridy = 1;
        int x=1;
        while(x<11){
            c.gridy++;
            this.add(new JButton("Niveau "+x),c);
            x++;
        }
        c.gridy++;
        this.add(new JButton("Retour"),c);
    }
}
