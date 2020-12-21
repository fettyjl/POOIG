package Jeu;

public class Test {
    public static void main(String[] args){
        Plateau test=new Plateau();
        test.remplirCase();
        test.afficherPlateau();
        test.caseAppuyer(4,4);
        test.supprimerCase();
        System.out.println();
        test.afficherPlateau();
        /*
        for(int i=0; i < test.supression.length; i++){
            for (int j=0; j< test.supression[i].length; j++){
                System.out.print(test.supression[i][j]+" ");
            }
            System.out.println();
        }
        */

    }
}
