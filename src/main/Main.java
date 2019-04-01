/**
 * @ Francois Auxietre
 */

package main;

import java.io.*;
import java.util.*;

public class Main {

    private static String nom1 = "all_absolute+.txt";
    private static String nom2 = "all_absolute-.txt";
    private static String nom3 = "all_absoluteTest.txt";
    private static  String entree = "entree.txt";
    private static  String sortie = "sortie.txt";
    private static  String pattern = "pattern.txt";
    private static String baseDonnee ="baseDonnee.txt";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        BaseDonneeCarte baseDonneeCarte = new BaseDonneeCarte();
        ArrayList<HashMap<Integer, Deck>> listeDesDecks= new ArrayList<>();

        Scanner scanner1 = new Scanner(System.in);
        Main.af("quel entree vouler vous analyser\n 1: all_absolute+.txt \n 2: all_absolute.txt \n 3: all_absoluteTest");
        int reponse = scanner1.nextInt();
        switch (reponse){
            case 1:{
                ExtractionDeck extractionDeck = new ExtractionDeck(baseDonneeCarte, nom1);
                listeDesDecks = extractionDeck.extracteDeck(baseDonneeCarte);

                break;
            }
            case 2:{
                ExtractionDeck extractionDeck = new ExtractionDeck(baseDonneeCarte, nom2);
                listeDesDecks = extractionDeck.extracteDeck(baseDonneeCarte);
                break;
            }
            default:{
                ExtractionDeck extractionDeck = new ExtractionDeck(baseDonneeCarte, nom3);
                listeDesDecks = extractionDeck.extracteDeck(baseDonneeCarte);
            }
        }

        File fileEntree = new File(entree);
        File fileSortie = new File(sortie);
        File filePattern = new File(pattern);
        File fileBaseDonnee = new File(baseDonnee);

        FileWriter fileWriterEntree = new FileWriter(fileEntree);
        FileWriter fileWriterSortie = new FileWriter(fileSortie);
        FileWriter fileWriterPattern = new FileWriter(filePattern);
        FileWriter fileWriterBaseDonee = new FileWriter(fileBaseDonnee);

        //FileReader fileReaderEntree = new FileReader(fileEntree);
        FileReader fileReaderSortie = new FileReader(fileSortie);
        //FileReader fileReaderPattern = new FileReader(filePattern);

        //Main.af("Chemin absolu du fichier : " + fileEntree.getAbsolutePath());
        //Main.af("Nom du fichier : " + fileEntree.getName());
        //Main.af("Est-ce que entree  ? " + fileEntree.exists());
        //Main.af("Est-ce que pattern? " + filePattern.exists());
        //Main.af("Est-ce que sortie  ? " + fileSortie.exists());
        //Main.af("Est-ce un répertoire ? " + fileEntree.isDirectory());
        //Main.af("Est-ce un fichier ? " + fileEntree.isFile());

        String entree= "";
        //cette boucle permet de sortir tous les numeros des cartes dans le fichier entree.txt
        for(HashMap<Integer, Deck> deckBase: listeDesDecks){

            for(Integer numeroDePartie : deckBase.keySet()){
                Deck deck = deckBase.get(numeroDePartie);
                ArrayList<Integer> liste = deck.rendListeDesNumerosDesCartes();
                liste.sort(Comparator.naturalOrder());

                //Main.af(liste.size());
                //ici on avait les cas d'une entree avec juste un begin et pas de cartes, donc on le suprime du fichier final
                if (liste.size()>0){
                    for(Integer n: liste){
                        entree+=n;
                        entree+= " ";
                    }
                    entree+= "\n";
                    fileWriterEntree.write(entree);
                }
                entree ="";
            }
        }

        fileWriterBaseDonee.write(baseDonneeCarte.affiche());
        fileWriterBaseDonee.close();
        fileWriterEntree.close();

        //partie pattern mining
        System.out.println("extraction des decks reussie");
        ArrayList<Donnee> donnees = new ArrayList<>();
        boolean reponseB = false;
        int pourcentage =0;

        while (!reponseB){
            Main.af("Entrer votre pourcentage que vous voulez tester, entre 0 et 10 ");
            pourcentage =  scanner.nextInt();
            if (pourcentage>0 && pourcentage<=10){
                reponseB= true;
            }else
            {reponseB= false;}
        }
        System.out.println("Generation de FPclose ok");
        Generation.executeSpmf(pourcentage, "FPClose");
        //Utilitaire.ecrit(donnees);
        Donnee donnee = new Donnee();
        String pattern = donnee.extracteDonnee(baseDonneeCarte, fileWriterSortie, fileReaderSortie);
        fileWriterPattern.write(pattern);
        fileWriterPattern.close();

    }

    /**
     * methode System.out.println() qui me permet de tout afficher mes parametres cartes, numeros en decochant
     * tous les //Main.af()
     * @param chaine
     */
    public static void af(String chaine){
        System.out.println(chaine);

    }

    /**
     * surcharge
     * @param nombre
     */
    public static void af(int nombre){
        System.out.println(""+nombre);
    }
}
