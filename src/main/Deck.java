/**
 * @ Francois Auxietre
 */

package main;

import java.util.ArrayList;
import java.util.HashMap;


public class Deck {

    private int nombreTotalDeDeck = 0;
    private BaseDonneeCarte baseDonneeCarte;
    private ArrayList<String> listeDesCartes;
    private HashMap<Integer, HashMap<String, ArrayList<Integer>>> listeDesNumerosDesCartes;
    private int numeroDeLaPartie;



    /**
     * constructeur par defaut
     *
     * @param baseDonneeCarte base de donnee des cartes courantes du joueur
     */
    public Deck(BaseDonneeCarte baseDonneeCarte, int numeroDeLaPartie) {
        this.baseDonneeCarte = baseDonneeCarte;
        this.numeroDeLaPartie = numeroDeLaPartie;
        nombreTotalDeDeck++;
        this.listeDesCartes = new ArrayList<>();
        this.listeDesNumerosDesCartes = new HashMap<>();
    }


    /**
     * constructeur de deck avec un numero de la partie
     *
     * @param baseDonneeCarte   base de donnee des crates courantes du joueur
     * @param numeroDeLaPartie : int numero de la partie
     */
    public Deck(BaseDonneeCarte baseDonneeCarte, int numeroDeLaPartie, ArrayList<String> listeDesCartes) {
        this.baseDonneeCarte = baseDonneeCarte;
        this.numeroDeLaPartie = numeroDeLaPartie;
        this.listeDesCartes = listeDesCartes;
    }

    /**
     * rendListeDesCartes
     *
     * @return ArrayList<String> : liste des cartes
     * *
     */
    public ArrayList<String> rendListeDesCartes(){

        return this.listeDesCartes;
    }

    /**
     * nombreOccurence : rend le nombre d'occurence d'une carte
     * @param carte
     * @param liste
     * @return int: le nombre de fois qu une carte appparait dans la liste
     */
    public int nombreOccurence(String carte, ArrayList<String> liste){
        int compteur =0;
        for(String c:liste){

            if (c.equals(carte)){
                compteur++;
               // Main.af("com"+ carte+ " "+ compteur);
            }
        }
        return compteur-1;
    }


    /**
     * rendListeDesNumerosDesCartes
     *
     * @return ArrayList<Integer> : la liste des numeros des cartes de la base de donnee en cours
     */
    public ArrayList<Integer> rendListeDesNumerosDesCartes(){
        BaseDonneeCarte base = this.baseDonneeCarte;
        ArrayList<String> pouvoirsDesHeros = baseDonneeCarte.rendPouvoirDesHeros();
        StringBuffer buffer = new StringBuffer();
        ArrayList<String> nouvelleListe = new ArrayList<>();
        ArrayList<Integer> listeFinale = new ArrayList<>();

        for(int i=0; i<this.listeDesCartes.size(); i++){
            String carte = listeDesCartes.get(i);
            //Main.af(""+listeDesCartes.size());
            int compteur =0;

            if(!pouvoirsDesHeros.contains(carte)){
                nouvelleListe.add(carte);
                compteur=nombreOccurence(carte,nouvelleListe);
                //Main.af("o"+compteur);
                ArrayList<Integer> liste = baseDonneeCarte.rendBase().get(carte);

                int numero = liste.get(compteur);
                //Main.af(carte + " "+ numero+  " " +liste.size());
                buffer.append(""+numero+ " ");
                listeFinale.add(numero);
            }
        }
       return listeFinale;
    }

    /**
     * rendBaseDonneeCarte
     *
     * @return BaseDonneeCarte: rend la base de donnee des carte equivalent d'un "getBaseDonneeCarte"
     */
    public BaseDonneeCarte rendBaseDonneeCarte(){
        return this.baseDonneeCarte;
    }


    /**
     * ajouterCarte
     *
     * ajouter une carte au deck avec la verification qu'elle ne sera pas plus de deux fois
     * @param carte : String la carte a ajouter
     */
    public void ajouterCarte(String carte, int numeroCarte) {

        //Main.af(" carte  "+ numeroCarte);
        this.listeDesCartes.add(carte);
    }


    /**
     * rendNumeroDeLaPartie
     *
     * @return int : numero de la partie
     */
    public int rendNumeroDeLaPartie() {

        return this.numeroDeLaPartie;
    }


    /**
     * duplique un deck
     *
     * @return Deck : une copie de celui de depart
     */
    public Deck duplique() {
        Deck deck = new Deck(this.baseDonneeCarte, this.numeroDeLaPartie, this.listeDesCartes);
        Main.af("nouveau deck de la partie n°"+ this.numeroDeLaPartie+ " construit");
        return deck;
    }


    /**
     * convertiEnDonnee
     *
     * @return Donnee
     */
    public Donnee convertiEnDonnee() {
        /*
        int[] listeDonnee = new int[listeDesCartes.size()];
        int identifiant = 0;

        for (String carte : listeDesCartes.keySet()) {
            ArrayList<Integer> listeDesIdentifiant;

            //int tmp = baseDonneeCarte.rendIdentifiantCarte(carte);
            //listeDonnee[identifiant] = tmp;
            identifiant++;
        }
        Arrays.sort(listeDonnee);
        */
        return new Donnee();

    }
}