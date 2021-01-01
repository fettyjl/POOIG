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
    boolean bf=false;
    boolean bc=false;
    boolean bs=false;

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
        JButton bonusF=new JButton("Bonus Fusée");
        bonusF.addActionListener(e ->{bf=!bf;});

        JButton bonusC=new JButton("Bonus Couleur");
        bonusF.addActionListener(e ->{bc=!bc;});

        JButton bonusS=new JButton("Bonus Sauvetage");
        bonusF.addActionListener(e ->{bs=!bs;});
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

            if(bs || bc ||bs) {
    //Mettre en place les bonus
            }else{
            n.plateau.caseAppuyer(this.x,this.y);
            int a= n.plateau.nombreCaseSupp()*10;
            if(a>0){
                n.score+=a;
                n.plateau.supprimerCase();
                n.argentSave+=n.plateau.argentSave();
                n.plateau.refreshPlateau();
                System.out.println(n.nbrTour%(10-n.difficulte));

                if(n.nbrTour%(10-n.difficulte)==0){
                    n.argentPerdu+=n.plateau.nbrArgentPerdu();
                    fenetre.partiePanel.refresh();
                    n.plateau.ajouteLigneEnBas();
                    fenetre.partiePanel.refresh();
                }
                n.nbrTour++;
            }
            if(n.argentPerdu>2) {
                fenetre.panelFin=new PanelFin(fenetre,n);
                fenetre.container.add(fenetre.panelFin,"PanelFin");
                fenetre.cl.show(fenetre.container, "PanelFin");
            }else if(n.plateau.resteASave()==0){
                fenetre.panelFin=new PanelFin(fenetre,n);
                fenetre.container.add(fenetre.panelFin,"PanelFin");
                fenetre.cl.show(fenetre.container, "PanelFin");
                fenetre.game.listeNiveau.set(n.difficulte-1,new Niveau(n.difficulte));
                fenetre.game.listeNiveau.get(n.difficulte-1).resultat=true;
            }else {
                fenetre.partiePanel.removeAll();
                fenetre.partiePanel.refresh();
                fenetre.validate();
            }
        }
        }
    }
}
