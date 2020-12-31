package Vue;

import Jeu.Argent;
import Jeu.Bloc;
import Jeu.Niveau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PartiePanel extends ImagePanel {

    Fenetre fenetre;
    Niveau n;

    public PartiePanel(Fenetre fenetre, Niveau n) {
        super("/voleur.jpeg");
        this.fenetre = fenetre;
        this.n=n;
        this.refresh();
    }
    public void refresh(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color;
                gbc.gridx = col;
                gbc.gridy = row;
                if(n.plateau.plateau[row][col].container instanceof Bloc){
                    Bloc a=(Bloc) n.plateau.plateau[row][col].container;
                    if(a.i == 0){
                        color=new Color( 41, 128, 185 );
                    }else if(a.i == 1){
                        color=new Color( 231, 76, 60 );
                    }else if(a.i==2){
                        color=new Color( 244, 208, 63);
                    }else{
                        color=new Color(  39, 174, 96);
                    }
                    add(new Carre(color,row,col), gbc);
                }else if(n.plateau.plateau[row][col].container instanceof Argent){
                    color=new Color(  0, 0, 0);
                    add(new Carre(color,row,col), gbc);
                }else{
                    color=new Color( 191, 191, 191);
                    add(new Carre(color,row,col), gbc);
                }
            }
        }
    }

    public class Carre extends JButton implements ActionListener {
        int x,y;

        public Carre(Color background,int x, int y) {
            this.x=x;
            this.y=y;
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
            System.out.println(n.niveau);
            n.plateau.caseAppuyer(this.x,this.y);
            n.plateau.supprimerCase();
            n.plateau.refreshPlateau();
            n.plateau.afficherPlateau();
            fenetre.partiePanel.removeAll();
            fenetre.partiePanel.refresh();
            fenetre.validate();
        }
    }
    public class Anim extends JButton {
        int x,y;

        public Anim(Color background,int x, int y) {
            this.x=x;
            this.y=y;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setBackground(background);
            setOpaque(true);
            setEnabled(false);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
    }
}
