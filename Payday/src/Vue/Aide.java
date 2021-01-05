package Vue;

import javax.swing.*;
import java.awt.*;

public class Aide extends ImagePanel {
    Fenetre fenetre;
    JLabel titre = new JLabel("<html><h1><strong><i><font color=\"white\">COMMENT JOUER :</font></i></strong><hr></h1></html>");
    JButton retour = new JButton("Retour");


    Aide(Fenetre fenetre) {
        super("Image/bank.jpeg");
        this.fenetre = fenetre;
        this.init();
    }

    public void init() {
        JPanel cmj = new JPanel();
        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();

        l1.setText("<html><div class=\"container\">\n" +
                "<h1><i>BIENVENUE DANS PAYDAY</i></h1>\n" +
                "<h3><i>Tu as pour objectif de voler un maximum d'argent sans te faire attraper.</i></h3>\n" +
                "<h3><i>8 missions périlleuse au sein de 8 banques différentes t'attendent.</i></h3></html>");
        l1.setForeground(Color.white);
        l2.setText("<html><div class=\"container\">\n" +
                "<h3><i>Au sein d'une mission, pour sauver un maximum d'argent il te faudra supprimer les cases de mêmes couleurs pour les faire disparaître</i></h3>\n" +
                "<h3><i>Un sac est sauver si tu arrives à le faire atteindre la ligne du bas.</i></h3>\n" +
                "<h3><i>Plus tu avances dans les niveaux plus la difficulté augmente  , une ligne apparaîtra au bout d'un certain  nombre de tour défini qui augmente.</i></h3>\n" +
                "<h3><hr><i>Tu as plusieurs bonus :</i><hr></h3>\n" +
                "<h3><i>- Peinture, qui te permet de changer la couleur d'un bloc.</i></h3>\n" +
                "<h3><i>- Fusée, qui te permet de faire disparaître tout une colonne.</i></h3>\n" +
                "<h3><i>- Sauvetage, qui te permet de sauver un sac que tu n'aurais pas reussi à sauver.</i></h3>\n" +
                "<h3></h3>\n" +
                "<h3><i><hr>Comment les utiliser ?<hr></i></h3>\n" +
                "<h3><i>C'est très simple, clic sur un bonus pour le sélectionné  puis clic sur le bloc pour activer le bonus.</i></h3>\n" +
                "<h3><i>Si tu change d'avis et que tu as déjà sélectionné un bonus , re-clic sur le bonus pour le desactiver.</i></h3>\n" +
                "</div></html>");
        l2.setForeground(Color.white);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(l1, c);
        c.gridy = 1;
        this.add(l2, c);
        c.gridy = 2;
        this.add(retour,c);

        this.retour.addActionListener(e -> {
            fenetre.cl.show(fenetre.container, "MenuOuverture");
        });

    }
}
