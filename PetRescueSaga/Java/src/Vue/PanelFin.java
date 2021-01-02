package Vue;

import Jeu.Niveau;

import javax.swing.*;
import java.awt.*;

public class PanelFin extends ImagePanel {
    Fenetre fenetre;
    Niveau n;
    JButton retour = new JButton("Retour");

    PanelFin(Fenetre fenetre, Niveau n) {
        super("/voleur.jpeg");
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
        this.add(new JButton(), gbc);
        gbc.gridy++;
        this.retour.addActionListener(e -> {
            fenetre.menuNiveau = new MenuNiveau(fenetre);
            fenetre.container.add(fenetre.menuNiveau, "MenuNiveau");
            fenetre.cl.show(fenetre.container, "MenuNiveau");
        });
        this.add(this.retour, gbc);
        Niveau a = new Niveau(n.getDifficulte());
        a.setDispo(fenetre.game.getListeNiveau().get(n.getDifficulte()).isDispo());
        a.setNbEtoile(n.getArgentSave() - 1);
        fenetre.game.getListeNiveau().set(n.getDifficulte(),a);
        if (n.getDifficulte() < fenetre.game.getListeNiveau().size()) {
            Niveau b = fenetre.game.getListeNiveau().get(n.getDifficulte()+1);
            if (n.isResultat() && !b.isDispo()) {
                b.setDispo(true);
            }
        }
    }
}