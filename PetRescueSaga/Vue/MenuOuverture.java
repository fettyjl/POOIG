package Vue;
import javax.swing.*;
import java.awt.*;

public class MenuOuverture extends JFrame{
    JPanel button=new JPanel();
    public MenuOuverture(){
        super("PetRescueSaga");
        this.setSize(800,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.button.setLayout(new GridLayout(3,1));

        JButton buttonJouer = new JButton("Jouer");
        JButton buttonAide = new JButton("Aide");
        JButton buttonQuitter = new JButton("Quitter");
        this.button.add(buttonJouer);
        this.button.add(buttonAide);
        this.button.add(buttonQuitter;


        JPanel a=new JPanel(){
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                // Relier l'image du dossier à ce fichier !
                ImageIcon m = new ImageIcon("?????????/imagejungle.png");
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

