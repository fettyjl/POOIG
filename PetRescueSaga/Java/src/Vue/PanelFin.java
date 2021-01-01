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
        this.add(new JButton("Score final :" + n.score), gbc);
        gbc.gridy++;
        this.add(new JButton("Sac d'argent sauvée :" + n.argentSave), gbc);
        gbc.gridy++;
        this.add(new JButton("Sac d'argent Perdu :" + (4 - n.argentSave)), gbc);
        gbc.gridy++;
        if (n.resultat)
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
        fenetre.game.listeNiveau.get(n.difficulte - 1).dispo = true;
        fenetre.game.listeNiveau.get(n.difficulte - 1).nbEtoile = n.argentSave - 1;
        if (n.difficulte < fenetre.game.listeNiveau.size()) {
            Niveau b = fenetre.game.listeNiveau.get(n.difficulte);
            if (n.resultat && !b.dispo) {
                b.dispo = true;
            }
        }
    }
}