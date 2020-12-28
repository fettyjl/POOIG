package Vue;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    String path;
        public ImagePanel(String path) {
            this.path=path;
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // Relier l'image du dossier à ce fichier !
            ImageIcon m = new ImageIcon(getClass().getResource(path));
            Image monImage = m.getImage();
            g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(),this);

        }
}
