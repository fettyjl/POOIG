package Vue;

import Jeu.Niveau;

import javax.swing.*;
import java.awt.*;

public class PanelFin extends ImagePanel {
    Fenetre fenetre;
    Niveau n;
    JButton retour = new JButton("Retour");

    PanelFin(Fenetre fenetre, Niveau n) {
        super("Image/voleur.jpeg");
        this.fenetre = fenetre;
        this.n = n;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JButton("Score final :" + n.getScore()), gbc);
        gbc.gridy++;
        this.add(new JButton("Sac d'argent sauvée :" + n.getArgentSave()), gbc);
        gbc.gridy++;
        this.add(new JButton("Sac d'argent Perdu :" + (4 - n.getArgentSave())), gbc);
        gbc.gridy++;
        if (n.isResultat())
            this.add(new JButton("Mission Réussite !"), gbc);
        else this.add(new JButton("Les policiers vous ont attrapé !"), gbc);
        gbc.gridy++;
        int r=(n.getArgentSave() - 1);
        if(r<0)
            r=0;
        this.add(new JButton("Etoile obtenu sur cette partie " + r), gbc);
        gbc.gridy++;
        this.retour.addActionListener(e -> {
            fenetre.menuNiveau.removeAll();
            fenetre.menuNiveau.init();
            fenetre.validate();
            fenetre.cl.show(fenetre.container, "MenuNiveau");
        });
        this.add(this.retour, gbc);

        fenetre.game.getJoueur().addBonus(n.getScore());
        Niveau a = new Niveau(n.getDifficulte());
        a.setDispo(fenetre.game.getListeNiveau().get(n.getDifficulte()).isDispo());
        // Conserve le nombre d'etoile obtenu le plus haut:
        if (fenetre.game.getListeNiveau().get(n.getDifficulte()).getNbEtoile() < (n.getArgentSave() - 1))
            a.setNbEtoile(n.getArgentSave() - 1);
        else
            a.setNbEtoile(fenetre.game.getListeNiveau().get(n.getDifficulte()).getNbEtoile());

        fenetre.game.getListeNiveau().set(n.getDifficulte(), a);
        // Debloque le prochain niveau :
        if (n.getDifficulte() < fenetre.game.getListeNiveau().size() - 1) {
            Niveau b = fenetre.game.getListeNiveau().get(n.getDifficulte() + 1);
            if (n.isResultat() && !b.isDispo()) {
                b.setDispo(true);
            }
        }
    }
}