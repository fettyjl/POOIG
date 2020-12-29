package Vue;


import javax.swing.*;
import java.awt.*;

public class MenuNiveau extends ImagePanel{
    Fenetre fenetre;
    JLabel titre=new JLabel("<html><h1><strong><i><font color=\"white\">Niveau Disponible :</font></i></strong><hr></h1></html>");
    JButton retour = new JButton("Retour");

    MenuNiveau(Fenetre fenetre){
        super("/imagejungle.png");
        this.fenetre=fenetre;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill =GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(this.titre,c);
        c.gridy++;
        int x=1;
        while(x<9){
            c.gridy++;
            this.add(new JButton("Niveau  "+x+"     * * *"),c);
            x++;
        }
        c.gridy++;

        this.add(this.retour,c);
        this.retour.addActionListener(e -> this.fenetre.cl.show(this.fenetre.container,"MenuOuverture"));
    }
}
