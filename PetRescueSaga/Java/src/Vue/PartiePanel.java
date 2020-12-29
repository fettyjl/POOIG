package Vue;

import Jeu.Bloc;

import javax.swing.*;
import java.awt.*;

public class PartiePanel extends ImagePanel {

    Fenetre fenetre;

    public PartiePanel(Fenetre fenetre) {
        super("/imagejungle.png");
        this.fenetre = fenetre;
        int index = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color;
                    if(fenetre.part.p.plateau[row][col].container instanceof Bloc){
                        Bloc a=(Bloc) fenetre.part.p.plateau[row][col].container;
                        if(a.i == 0){
                            color=new Color( 41, 128, 185 );
                        }else if(a.i == 1){
                            color=new Color( 231, 76, 60 );
                        }else if(a.i==2){
                            color=new Color( 244, 208, 63);
                        }else{
                            color=new Color(  39, 174, 96);
                        }
                        gbc.gridx = col;
                        gbc.gridy = row;
                        add(new Carre(color), gbc);
                        index++;
                    }

            }
            index++;
        }


    }

    public class Carre extends JButton {
        public Carre(Color background) {

            setContentAreaFilled(false);
            setBorderPainted(false);
            setBackground(background);
            setOpaque(true);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(60, 60);
        }
    }


}
