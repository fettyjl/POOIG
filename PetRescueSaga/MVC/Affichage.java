package MVC;

import javax.swing.*;
import java.awt.*;


public class Affichage extends JFrame {

    Font fontTitre= new Font("Times New Roman", Font.PLAIN, 60);
    Font fontBoutons = new Font("Times New Roman", Font.PLAIN, 30);


    public Affichage(){

        //Base de la fentre
        this.setTitle("JEUX JM");
        this.setSize(800, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Creation du Container
        Container container = new Container();
        this.setContentPane(container);

        //Parametres du Titre
        JPanel panTitre = new JPanel();
        panTitre.setBounds(100,100,600,150);
        JLabel titre = new JLabel("JEUX LOW COST");
        titre.setForeground(Color.black);
        titre.setFont(fontTitre);

        //Ajout du titre au Container
        panTitre.add(titre);

        //Creation des Boutons
        JPanel panelButons = new JPanel();
        panelButons.setBounds(300,270,200,300);
        JButton start = new JButton("start");
        start.setFont(fontBoutons);
        JButton help = new JButton("help");
        help.setFont(fontBoutons);
        JButton exit = new JButton("exit");
        exit.setFont(fontBoutons);

        //Ajustement de l'espace entre les boutons
        GridLayout gb = new GridLayout(3,1);
        panelButons.setLayout(gb);
        gb.setVgap(20);

        //Ajout des boutons au panelButons
        panelButons.add(start);
        panelButons.add(help);
        panelButons.add(exit);

        //Ajout des panels au Container
        container.add(panTitre);
        container.add(panelButons);

        this.setVisible(true);


    }


}
