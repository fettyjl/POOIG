package Vue;
import javax.swing.*;
import java.awt.*;

public class MenuOuverture extends JFrame{
    JPanel button=new JPanel();
    public MenuOuverture(){
        super("PetRescueSaga");
        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.button.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton buttonJouer = new JButton("Jouer");
        JButton buttonAide = new JButton("Aide");
        JButton buttonQuitter = new JButton("Quitter");

        this.button.add(buttonJouer, gbc);
        this.button.add(buttonAide, gbc);
        this.button.add(buttonQuitter, gbc);


        JPanel a=new JPanel(){
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                ImageIcon m = new ImageIcon("/home/fetty/Téléchargements/imagejungle.png");
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0,this);

            }
        };
        a.add(this.button);
        this.getContentPane().add(a);
    }
    public static void main(String[]args){
        MenuOuverture a = new MenuOuverture();
    }
}

