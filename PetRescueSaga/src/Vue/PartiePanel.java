package Vue;

import Jeu.Argent;
import Jeu.Bloc;
import Jeu.Niveau;
import Jeu.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PartiePanel extends ImagePanel {

    Fenetre fenetre;
    Niveau n;
    boolean bs, bf, bc;

    public PartiePanel(Fenetre fenetre, Niveau n) {
        super("Image/voleur.jpeg");
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
                if (n.getPlateau().getPlateau()[row][col].getContainer() instanceof Bloc) {
                    Bloc a = (Bloc) n.getPlateau().getPlateau()[row][col].getContainer();
                    if (a.getI() == 0) {
                        color = new Color(41, 128, 185);
                    } else if (a.getI() == 1) {
                        color = new Color(231, 76, 60);
                    } else if (a.getI() == 2) {
                        color = new Color(244, 208, 63);
                    } else {
                        color = new Color(39, 174, 96);
                    }
                    add(new Carre(color, row, col), gbc);
                } else if (n.getPlateau().getPlateau()[row][col].getContainer() instanceof Argent) {
                    add(new Bourse("Image/bourse.jpeg", row, col), gbc);
                } else {
                    color = new Color(191, 191, 191);
                    add(new Carre(color, row, col), gbc);
                }
            }
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 8;
        gbc.gridy = 0;
        JButton un = new JButton("Tour " + n.getNbrTour());
        un.setEnabled(false);
        this.add(un, gbc);
        gbc.gridy++;
        JButton deux = new JButton("Score:" + n.getScore());
        deux.setEnabled(false);
        this.add(deux, gbc);
        gbc.gridy++;
        JButton trois = new JButton("Sac sauvée " + n.getArgentSave() + "/ perdu " + n.getArgentPerdu());
        trois.setEnabled(false);
        this.add(trois, gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 4;

        //Mettre en place les bonus
        JButton bonusF = new JButton("Bonus Fusée " + fenetre.game.getJoueur().getBonus().getFusee());
        bonusF.addActionListener(e -> bf = true);
        this.add(bonusF, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        JButton bonusC = new JButton("Bonus Couleur " + fenetre.game.getJoueur().getBonus().getPeinture());
        bonusC.addActionListener(e -> bc = true);
        this.add(bonusC, gbc);
        gbc.gridy++;
        JButton bonusS = new JButton("Bonus Sauvetage " + fenetre.game.getJoueur().getBonus().getSauvetage());
        bonusS.addActionListener(e -> this.bs = true);

        this.add(bonusS, gbc);
        gbc.gridx = 4;
        gbc.gridy = 9;
        JButton quatre = new JButton("Ligne apparation 1/" + (10 - n.getDifficulte()) + " tours");
        quatre.setEnabled(false);
        this.add(quatre, gbc);

        gbc.gridx = 4;
        gbc.gridy = 10;
        JButton retour = (new JButton("Retour"));
        retour.addActionListener(e -> {
            fenetre.menuNiveau = new MenuNiveau(fenetre);
            fenetre.container.add(fenetre.menuNiveau, "MenuNiveau");
            fenetre.cl.show(fenetre.container, "MenuNiveau");
            Niveau a = new Niveau(n.getDifficulte());
            a.setDispo(true);
            a.setNbEtoile(fenetre.game.getListeNiveau().get(n.getDifficulte()).getNbEtoile());
            fenetre.game.getListeNiveau().set(n.getDifficulte(), a);
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
            if (bf) {
                fenetre.game.ActionBonusFusée(this.y, n.getDifficulte());
                n.setNbrTour(n.getNbrTour() + 1);
                fenetre.partiePanel.removeAll();
                fenetre.partiePanel.refresh();
                fenetre.validate();
                if (n.getPlateau().resteASave() == 0) {
                    fenetre.game.getJoueur().setScoreTot(fenetre.game.getJoueur().getScoreTot() + n.getScore());
                    fenetre.game.getJoueur().addBonus();
                    n.setResultat(true);
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                    fenetre.game.getListeNiveau().get(n.getDifficulte()).setPlateau(new Plateau());
                    fenetre.game.getListeNiveau().get(n.getDifficulte()).setResultat(true);
                }
            } else if (bc) {
                fenetre.game.ActionBonusPeinture(this.x, this.y, n.getDifficulte());
                n.setNbrTour(n.getNbrTour() + 1);
                fenetre.partiePanel.removeAll();
                fenetre.partiePanel.refresh();
                fenetre.validate();
            } else {
                n.getPlateau().caseAppuyer(this.x, this.y);
                int a = n.getPlateau().nombreCaseSupp() * 10;
                if (a > 0) {
                    n.setScore(n.getScore() + a);
                    n.getPlateau().supprimerCase();
                    n.setArgentSave(n.getArgentSave() + n.getPlateau().argentSave());
                    n.getPlateau().refreshPlateau();

                    if (n.getNbrTour() % (10 - n.getDifficulte()) == 0) {
                        n.setArgentPerdu(n.getArgentPerdu() + n.getPlateau().nbrArgentPerdu());
                        n.getPlateau().ajouteLigneEnBas();
                    }
                    n.setNbrTour(n.getNbrTour() + 1);
                }
                if (n.getArgentPerdu() > 1 || (n.getPlateau().plusDeCoup() && fenetre.game.getJoueur().getBonus().plusDeFetP() && fenetre.game.getJoueur().getBonus().getSauvetage() < n.getPlateau().resteASave())) {
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                } else if (n.getPlateau().resteASave() == 0 || (n.getPlateau().plusDeCoup() && fenetre.game.getJoueur().getBonus().plusDeBonus() && n.getArgentSave() > 1)) {
                    fenetre.game.getJoueur().setScoreTot(fenetre.game.getJoueur().getScoreTot() + n.getScore());
                    fenetre.game.getJoueur().addBonus();
                    n.setResultat(true);
                    fenetre.panelFin = new PanelFin(fenetre, n);
                    fenetre.container.add(fenetre.panelFin, "PanelFin");
                    fenetre.cl.show(fenetre.container, "PanelFin");
                    fenetre.game.getListeNiveau().get(n.getDifficulte()).setPlateau(new Plateau());
                    fenetre.game.getListeNiveau().get(n.getDifficulte()).setResultat(true);
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
            Image monImage = new ImageIcon("./"+path).getImage();
            g.drawImage(monImage, 0, 0, 50, 50, this);

        }

        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bs) {
                fenetre.game.ActionBonusSauvetage(this.x, this.y, n.getDifficulte());
                n.setNbrTour(n.getNbrTour() + 1);
                fenetre.partiePanel.removeAll();
                fenetre.partiePanel.refresh();
                fenetre.validate();
            }
            if (n.getPlateau().resteASave() == 0) {
                fenetre.game.getJoueur().setScoreTot(fenetre.game.getJoueur().getScoreTot() + n.getScore());
                fenetre.game.getJoueur().addBonus();
                n.setResultat(true);
                fenetre.panelFin = new PanelFin(fenetre, n);
                fenetre.container.add(fenetre.panelFin, "PanelFin");
                fenetre.cl.show(fenetre.container, "PanelFin");
                fenetre.game.getListeNiveau().get(n.getDifficulte()).setPlateau(new Plateau());
                fenetre.game.getListeNiveau().get(n.getDifficulte()).setResultat(true);
            }
            if (bs || bc || bf) {
                bs = false;
                bf = false;
                bc = false;
            }
        }
    }
}

