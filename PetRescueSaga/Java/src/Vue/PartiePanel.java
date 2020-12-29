package Vue;

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
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                    gbc.gridx = col;
                    gbc.gridy = row;
                    add(new Carre(color), gbc);
                    index++;
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
