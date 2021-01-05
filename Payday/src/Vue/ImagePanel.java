package Vue;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    String path;

    public ImagePanel(String path) {
        this.path = path;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image monImage = new ImageIcon("./" + path).getImage();
        g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);

    }
}

