package Vue;

import javax.swing.*;


public class MenuOuverture extends JPanel{
    ImagePanel fond= new ImagePanel("/Image/imagejungle.png");
    JButton buttonJouer = new JButton("Jouer");
    JButton buttonAide = new JButton("Aide");
    JButton buttonQuitter = new JButton("Quitter");


    public MenuOuverture(){
        this.fond.setLayout(null);
        this.buttonJouer.setBounds(350, 200, 100, 25);
        this.buttonAide.setBounds(350, 230, 100, 25);
        this.buttonQuitter.setBounds(350, 260, 100, 25);
        this.fond.add(this.buttonJouer);
        this.fond.add(this.buttonAide);
        this.fond.add(this.buttonQuitter);

        this.buttonQuitter.addActionListener((e) -> System.exit(0));
        this.setVisible(true);
        this.add(this.fond);
    }

}
