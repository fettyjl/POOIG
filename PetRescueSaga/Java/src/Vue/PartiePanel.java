package Vue;

import Jeu.Argent;
import Jeu.Bloc;
import Jeu.Niveau;
import Jeu.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PartiePanel extends ImagePanel {

    Fenetre fenetre;
    Niveau n;
    boolean bs, bf, bc;

    public PartiePanel(Fenetre fenetre, Niveau n) {
        super("/voleur.jpeg");
        this.fenetre = fenetre;
        this.n = n;
        this.refresh();
    }

    public void refresh() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color;
                gbc.gridx = col;
                gbc.gridy = row;
                if (n.plateau.plateau[row][col].container instanceof Bloc) {
                    Bloc a = (Bloc) n.plateau.plateau[row][col].container;
                    if (a.i == 0) {
                        color = new Color(41, 128, 185);
                    } else if (a.i == 1) {
                        color = new Color(231, 76, 60);
                    } else if (a.i == 2) {
                        color = new Color(244, 208, 63);
                    } else {
                        color = new Color(39, 174, 96);
                    }
                    add(new Carre(color, row, col), gbc);
                } else if (n.plateau.plateau[row][col].container instanceof Argent) {
                    add(new Bourse("/bourse.jpeg", row, col), gbc);
                } else {
                    color = new Color(191, 191, 191);
                    add(new Carre(color, row, col), gbc);
                }
            }
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 8;
        gbc.gridy = 0;
        JButton un = new JButton("Tour " + n.nbrTour);
        un.setEnabled(false);
        this.add(un, gbc);
        gbc.gridy++;
        JButton deux = new JButton("Score:" + n.score);
        deux.setEnabled(false);
        this.add(deux, gbc);
        gbc.gridy++;
        JButton trois = new JButton("Sac sauvée " + n.argentSave + "/ perdu " + n.argentPerdu);
        trois.setEnabled(false);
        this.add(trois, gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 4;

        //Mettre en place les bonus
        JButton bonusF = new JButton("Bonus Fusée " + fenetre.game.joueur.bonus.fusee);
        bonusF.addActionListener(e -> {
            bf = true;
        });
        this.add(bonusF, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        JButton bonusC = new JButton("Bonus Couleur " + fenetre.game.joueur.bonus.peinture);
        bonusC.addActionListener(e -> {
            bc = true;
        });
        this.add(bonusC, gbc);
        gbc.gridy++;
        JButton bonusS = new JButton("Bonus Sauvetage " + fenetre.game.joueur.bonus.sauvetage);
        bonusS.addActionListener(e -> {
            this.bs = true;
        });

        this.add(bonusS, gbc);
        gbc.gridx = 4;
        gbc.gridy = 9;
        JButton quatre = new JButton("Ligne apparation 1/" + (10 - n.difficulte) + " tours");
        quatre.setEnabled(false);
        this.add(quatre, gbc);

        gbc.gridx = 4;
        gbc.gridy = 10;
        JButton retour = (new JButton("Retour"));
        retour.addActionListener(e -> {
            fenetre.menuNiveau = new MenuNiveau(fenetre);
            fenetre.container.add(fenetre.menuNiveau, "MenuNiveau");
            fenetre.cl.show(fenetre.container, "MenuNiveau");
            fenetre.game.listeNiveau.get(n.difficulte - 1).plateau = new Plateau();
        });
        this.add(retour, gbc);
    }

    public class Carre extends JButton implements ActionListener {
        int x, y;

        public Carre(Color background, int x, int y) {
            this.x = x;
            this.y = y;
            addActionListener(this);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setBackground(background);
            setOpaque(true);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bf && fenetre.game.joueur.bonus.fusee > 0) {
                n.argentSave += n.plateau.bonusEnColonne(this.y);
                n.plateau.refreshPlateau();
                fenetre.game.joueur.bonus.fusee--;
                fenetre.partiePanel.removeAll();
                fenetre.partiePanel.refresh();
                fenetre.validate();
                if (n.plateau.resteASave() == 0) {
                    fenetre.game.joueur.scoreTot += n.score;
                    fenetre.game.joueur.addBonus();
                    n.resultat = true;
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                    fenetre.game.listeNiveau.get(n.difficulte - 1).plateau = new Plateau();
                    fenetre.game.listeNiveau.get(n.difficulte - 1).resultat = true;
                }
            } else if (bc && fenetre.game.joueur.bonus.peinture > 0) {
                if (n.plateau.plateau[x][y].container instanceof Bloc) {
                    n.plateau.plateau[x][y].container= ((Bloc) n.plateau.plateau[x][y].container).changementCouleur();
                    fenetre.game.joueur.bonus.peinture--;
                    fenetre.partiePanel.removeAll();
                    fenetre.partiePanel.refresh();
                    fenetre.validate();
                }
            } else {
                n.plateau.caseAppuyer(this.x, this.y);
                int a = n.plateau.nombreCaseSupp() * 10;
                if (a > 0) {
                    n.score += a;
                    n.plateau.supprimerCase();
                    n.argentSave += n.plateau.argentSave();
                    n.plateau.refreshPlateau();
                    fenetre.partiePanel.removeAll();
                    fenetre.partiePanel.refresh();
                    fenetre.validate();

                    if (n.nbrTour % (10 - n.difficulte) == 0) {
                        n.argentPerdu += n.plateau.nbrArgentPerdu();
                        n.plateau.ajouteLigneEnBas();
                        fenetre.partiePanel.removeAll();
                        fenetre.partiePanel.refresh();
                        fenetre.validate();
                    }
                    n.nbrTour++;
                }
                if (n.argentPerdu > 2) {
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                } else if (n.plateau.resteASave() == 0) {
                    fenetre.game.joueur.scoreTot += n.score;
                    fenetre.game.joueur.addBonus();
                    n.resultat = true;
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                    fenetre.game.listeNiveau.get(n.difficulte - 1).plateau = new Plateau();
                    fenetre.game.listeNiveau.get(n.difficulte - 1).resultat = true;
                } else {
                    fenetre.partiePanel.removeAll();
                    fenetre.partiePanel.refresh();
                    fenetre.validate();
                }
            }
            if (bs || bc || bf) {
                bs = false;
                bf = false;
                bc = false;
            }
        }
    }

    public class Bourse extends JButton implements ActionListener {
        String path;
        int x, y;

        Bourse(String path, int x, int y) {
            this.path = path;
            this.x = x;
            this.y = y;
            addActionListener(this);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(true);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image monImage = new ImageIcon(getClass().getResource(path)).getImage();
            g.drawImage(monImage, 0, 0, 50, 50, this);

        }

        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bs && fenetre.game.joueur.bonus.sauvetage > 0) {
                if (n.plateau.sauvetageArgent(this.x, this.y)) {
                    n.argentSave++;
                    fenetre.game.joueur.bonus.sauvetage--;
                    fenetre.partiePanel.removeAll();
                    fenetre.partiePanel.refresh();
                    fenetre.validate();
                }
                if (n.plateau.resteASave() == 0) {
                    fenetre.game.joueur.scoreTot += n.score;
                    fenetre.game.joueur.addBonus();
                    n.resultat = true;
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                    fenetre.game.listeNiveau.get(n.difficulte - 1).plateau = new Plateau();
                    fenetre.game.listeNiveau.get(n.difficulte - 1).resultat = true;
                }
            }
            if (bs || bc || bf) {
                bs = false;
                bf = false;
                bc = false;
            }
        }
    }
}
