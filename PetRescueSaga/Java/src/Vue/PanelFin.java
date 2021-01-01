package Vue;

import Jeu.Niveau;

import javax.swing.*;
import java.awt.*;

public class PanelFin extends ImagePanel{
    Fenetre fenetre;
    Niveau n;
    JButton retour = new JButton("Retour");

    PanelFin(Fenetre fenetre,Niveau n){
        super("/voleur.jpeg");
        this.fenetre=fenetre;
        this.n=n;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        this.add(new JButton("Score final :"+n.score),gbc);
        gbc.gridy++;
        this.add(new JButton("Sac d'argent sauvée :"+n.argentSave),gbc);
        gbc.gridy++;
        this.add(new JButton("Sac d'argent Perdu :"+(4-n.argentSave)),gbc);
        gbc.gridy++;
        if(n.resultat)
            this.add(new JButton("Vous avez Gagnée !"),gbc);
        else this.add(new JButton("Vous avez Perdu !"),gbc);
        gbc.gridy++;
        this.add(new JButton(),gbc);
        gbc.gridy++;
        this.retour.addActionListener(e -> { fenetre.cl.show(fenetre.container, "MenuNiveau"); });
        this.retour.setBackground(new Color(200,225,255));
        this.add(this.retour,gbc);
        Niveau a=new Niveau(n.difficulte);
        a.resultat=true;
        fenetre.game.listeNiveau.set(n.difficulte,a);
    }
}