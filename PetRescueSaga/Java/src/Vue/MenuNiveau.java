package Vue;


import Jeu.Niveau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNiveau extends ImagePanel{
    Fenetre fenetre;
    JLabel titre=new JLabel("<html><h1><strong><i><font color=\"white\">Niveau Disponible :</font></i></strong><hr></h1></html>");
    JButton retour = new JButton("Retour");

    MenuNiveau(Fenetre fenetre){
        super("/prepa.jpeg");
        this.fenetre=fenetre;
        this.init();
    }
    public void init(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 1, 1, 1);
        c.fill =GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(this.titre,c);
        c.gridy++;
        this.add(new JButton("Score Total : "+fenetre.game.joueur.scoreTot),c);
        int x=1;
        while(x<9){
            c.gridy++;
            this.add(new BoutonNiveau(x),c);
            x++;
        }
        c.gridy++;
        this.retour.addActionListener(e -> { fenetre.cl.show(fenetre.container, "MenuOuverture"); });
        this.add(this.retour,c);
    }
    public class BoutonNiveau extends JButton implements ActionListener {
        int x;
        BoutonNiveau(int x){
            super("Mission "+x+"   ****");
            this.x=x-1;
            Niveau a=fenetre.game.listeNiveau.get(x-1);
            if(!a.resultat) {
                this.setEnabled(false);
                this.setText("(bloquée) "+this.getText());
            }
            addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.partiePanel=new PartiePanel(fenetre,fenetre.game.listeNiveau.get(this.x));
            fenetre.container.add(fenetre.partiePanel,"PartiePanel");
            fenetre.cl.show(fenetre.container, "PartiePanel");
        }
    }
}
