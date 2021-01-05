package Jeu;

import java.io.*;

public class Serialisation implements Serializable {
    public static void ecriture(Object o) {
        try {
            FileOutputStream fichier = new FileOutputStream("./Ressource/game.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            System.out.println("La sauvegarde n'a pas pu être sauvegarder !");
        }
    }

    public static Game lecture(String ref) {
        try {
            FileInputStream fichier = new FileInputStream(ref);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            Game game = (Game) ois.readObject();
            ois.close();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            return new Game();
        }
    }
}
