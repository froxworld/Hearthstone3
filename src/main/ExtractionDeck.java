/**
 * @ Francois Auxietre
 */

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ExtractionDeck {

    private String nomDuFichier;
    private BaseDonneeCarte baseDonneeCarte;
    private int nomrbreDeCarte;
    private ArrayList<HashMap<Integer, Deck>> listeDesDecks;
    private Deck deckJoueur1;
    private Deck deckJoueur2;

    /**
     * rendNombreDePartie
     *
     * @return int : le nombre de partie faites
     */
    public int rendNombreDePartie(){
        return this.nomrbreDeCarte;
    }

    /**
     * constructeur par defaut pour extraire des fichier data les differents deck
     *
     * @param baseDonneeCarte : base de donnee contenant toutes les cartes
     * @param nomDuFichier : nom du fichier dont on veut extraire les informations
     */
    public ExtractionDeck(BaseDonneeCarte baseDonneeCarte, String nomDuFichier){
        this.baseDonneeCarte = baseDonneeCarte;
        this.nomDuFichier = nomDuFichier;
        deckJoueur1 = new Deck(baseDonneeCarte, 0);
        deckJoueur2 = new Deck(baseDonneeCarte, 0);
    }

    /**
     * rendNumeroCarte
     *
     * @param deck
     * @param baseDonneeCarteTemporaire
     * @return String : le numero de la carte
     */
    public String rendNumeroCarte(Deck deck, BaseDonneeCarte baseDonneeCarteTemporaire){
        StringBuffer buffer = new StringBuffer();
        return buffer.toString();
    }


    /**
     * extracteDeck :  extraction du deck
     *
     * @param baseDonneeCarte
     * @return ArrayList<HashMap<Integer, Deck>>  : liste des decks
     */
    public ArrayList<HashMap<Integer, Deck>> extracteDeck(BaseDonneeCarte baseDonneeCarte){
        //Main.af(nomDuFichier);
        Path path = Paths.get(nomDuFichier);

        //ClassLoader classLoader = getClass().getClassLoader();
        //String st = "" + classLoader.getResource(nomDuFichier).getFile();
        //Main.af("chemin "+path.toAbsolutePath());
        File file = new File(nomDuFichier);

        //numero de la partie , deck du joueur
        HashMap<Integer, Deck> j1 = new HashMap<>();
        HashMap<Integer, Deck> j2 = new HashMap<>();

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String ligne = scanner.nextLine();
                String[] tableau = ligne.split(" ");

                int numeroDeLaPartie = Integer.parseInt(tableau[0]);

                nomrbreDeCarte++;
                String joueur = tableau[1].substring(0,1);
                String carte = tableau[1].substring(1);

                if (!tableau[1].equals("Begin") && !carte.equals("TheCoin")) {
                    //Main.af("joueur:" + joueur + " carte:" + carte );
                    //Main.af("carte :" + carte);
                    if (joueur.equals("M")) {
                        int numero = baseDonneeCarte.ajouterCarte(carte);
                        j1.get(numeroDeLaPartie).ajouterCarte(carte, numero);
                        deckJoueur1.ajouterCarte(carte,numero);
                    }

                    if(joueur.equals("O")){
                        int numero =  baseDonneeCarte.ajouterCarte(carte);
                        j2.get(numeroDeLaPartie).ajouterCarte(carte, numero);
                        deckJoueur2.ajouterCarte(carte, numero);
                    }
                }
                else {
                    if (tableau[1].equals("Begin")){
                        //afficheDeck();
                        deckJoueur1 = new Deck(baseDonneeCarte, numeroDeLaPartie);
                        deckJoueur2 = new Deck(baseDonneeCarte, numeroDeLaPartie);
                    }

                    if (!carte.equals("TheCoin")){
                        //Main.af("cas du begin pour la partie n°" + numeroDeLaPartie);
                        j1.put(numeroDeLaPartie, new Deck(baseDonneeCarte, numeroDeLaPartie));
                        //Main.af("joueur 1 :"+j1.keySet().size());
                        j2.put(numeroDeLaPartie, new Deck(baseDonneeCarte, numeroDeLaPartie));
                        //Main.af("joueur 2 :"+j2.keySet().size());
                    }
                }
            }
            afficheDeck();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.listeDesDecks = new ArrayList<>();
        listeDesDecks.add(j1);
        listeDesDecks.add(j2);
        return listeDesDecks;
    }

    /**
     * afficheDeck : affiche le deck de chaque joueur
     *
     */
    private void afficheDeck() {
        Main.af("Carte du deck1" +deckJoueur1.rendListeDesCartes());
        Main.af("Carte du deck2" +deckJoueur2.rendListeDesCartes());
    }
}
