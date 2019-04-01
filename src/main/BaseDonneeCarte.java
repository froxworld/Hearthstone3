/**
 * @ Francois Auxietre
 */

package main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class BaseDonneeCarte {

    private int compteur;
    private HashMap<String, ArrayList<Integer>> baseDonnee;
    public static ArrayList<String> pouvoirsDesHeros;

    /**
     * rendCompteur() {
     * @return int : le nombre de partie faites
     */
    public int rendCompteur() {
        return this.compteur;
    }


    /**
     * constructeur par defaut
     */
    public BaseDonneeCarte() {
        compteur = 0;
        this.baseDonnee = new HashMap<>();
        initialise();
    }

    /**
     * rendPouvoirDesHeros
     * @return ArrayList<String> : liste des pouvoirs des héros
     */
    public ArrayList<String> rendPouvoirDesHeros() {

        return this.pouvoirsDesHeros;
    }

    /**
     * rendBase
     * @return HashMap<String, ArrayList<Integer>> : la base de donne entiere
     */
    public HashMap<String, ArrayList<Integer>> rendBase() {

        return this.baseDonnee;
    }

    /**
     * initialise : initialisation des pouvoirs des heros
     *
     */
    private void initialise() {
        pouvoirsDesHeros = new ArrayList<String>();
        String[] pouvoir = {"Shapeshift", "SteadyShot", "Fireblast", "Reinforce", "LesserHeal", "DaggerMastery", "TotemicCall", "LifeTap", "ArmorUp!"};
        for (String s : pouvoir) {
            pouvoirsDesHeros.add(s);
        }
    }


    /**
     * estUnHero
     * @param carte
     * @return boolean :    vrai si la carte passee en argument est un heros
     *                      faux sinon
     */
    public boolean estUnHero(String carte) {
        //TODO verifier si le string passe en argument est un hero
        boolean retour = false;
        if (pouvoirsDesHeros.contains(carte)) {
            Main.af("la carte " + carte + " est un hero");
            retour = true;
        }
        return retour;
    }


    /**
     * ajoutableCarte
     *
     * permet de savoir avant de rajouter une carte si on est pas deja au maximum
     * le parametre <=1 pourra etre mis en final pour tout parametrer
     * si le jeu passait par exmple à trois ou à une seule carte par deck
     *
     * @param carte
     * @return boolean: vrai: si on peut encore ajouter la carte
     *          faux sinon
     */
    public Boolean ajoutableCarte(String carte) {
        boolean retour = false;
        if (baseDonnee.containsKey(carte)) {
            ArrayList<Integer> liste = baseDonnee.get(carte);
            if (liste.size() <= 1) {
                retour = true;
            }
        } else {
            retour = true;
        }
        return retour;
    }


    /**
     * ajouterCarte
     *
     * on ajoute deux cartes identiques car le joueur peut avoir dans son deck deux fois le meme carte
     * ajoute une carte a la base de donnee
     *
     * @param carte : un string nom de la carte en string
     */
    public int ajouterCarte(String carte) {
        //Main.af("compteur " +compteur);
        if (!pouvoirsDesHeros.contains(carte)) {
            if (baseDonnee.containsKey(carte)) {
                //Main.af("une premiere carte existe deja du nom de " + carte);
                ArrayList<Integer> listeTemp = baseDonnee.get(carte);
                compteur++;
                listeTemp.add(compteur);
                baseDonnee.replace(carte, listeTemp);
                //Main.af("ajout " +carte + " "+ compteur + " "+ listeTemp.size());
            }

            if (!baseDonnee.containsKey(carte)) {
                ArrayList<Integer> liste = new ArrayList<>();
                compteur++;
                liste.add(compteur);
                baseDonnee.put(carte, liste);
                //Main.af("ajout" +carte + " "+ compteur);
            }
        }

        return compteur;
    }


    /**
     * rendNomCarte
     *
     * retourne le nom de la carte en fonction de son identifiant
     *
     * @param valeur : l identifiant de la carte
     * @return String :le nom de la carte en string
     */
    public String rendNomCarte(int valeur) {
        String retour = "";
        if (valeur < compteur) {
            for (String cle : baseDonnee.keySet()) {
                ArrayList<Integer> listeTemp = baseDonnee.get(cle);
                if (listeTemp.contains(valeur)) {
                    //Main.af("la carte " + cle +" (N° " + valeur + " ): en  " + listeTemp.size() + " exemplaires");
                    retour = cle;
                }
            }
        } else {
            //Main.af("pas de carte avec l' Identifiant "+ valeur);
            retour = "";
        }
        return retour;
    }


    /**
     * rendIdentifiantsCarte
     *
     * retourne la liste des  identifiants de la carte dans la base de donnee
     *
     * @param carte le nom de la carte
     * @return ArrayList<Integer> : une liste des valeurs possible de la carte 0<carte<=2
     */
    public ArrayList<Integer> rendIdentifiantsCarte(String carte) {
        ArrayList<Integer> retour = new ArrayList<>();
        if (baseDonnee.containsKey(carte)) {
            ArrayList<Integer> listeTemp = baseDonnee.get(carte);
            ArrayList<Integer> liste = new ArrayList<>();
            for (Integer iteration : listeTemp) {
                liste.add(iteration);
            }
            retour = liste;

        } else {
            System.out.println("la carte n 'existe pas dans la base");
        }
        return retour;
    }


    /**
     * affiche toutes les cartes qui sont dans la base de donnee
     * Nom:   nom de la carte | numero 1 de la premiere carte de la base | numero 2...
     *
     * @return String :la liste des cartes de la base
     */
    public String affiche() {

        StringBuffer buffer = new StringBuffer();
        //for (String cle : baseDonnee.keySet()) {
            //Main.af(cle + baseDonnee.get(cle));
        //}

        ArrayList<String> listeDesCles = new ArrayList<>(baseDonnee.keySet());

        for (String cle : listeDesCles) {
            ArrayList<Integer> liste = baseDonnee.get(cle);
            buffer.append("Nom : " + cle);
            for (int i = 0; i < baseDonnee.get(cle).size(); i++) {
                buffer.append(" |  " + liste.get(i));
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * transforme : transforme une chaine de caractere apres un FPClose
     *
     * @param chaine
     * @param valeur
     * @return
     */
    public String transforme(String chaine, int valeur) {
        StringBuffer tampon = new StringBuffer();
        String[] lignes = chaine.split("\n");
        for (String ligne : lignes) {
            String[] debutLigne = ligne.split("#SUP:");
            String[] listeDesCartes = debutLigne[0].split(" ");
            int nombre = Integer.parseInt(debutLigne[1].substring(1));
            if (listeDesCartes.length - 1 >= valeur) {
                for (String sousLigne : listeDesCartes) {
                    int identifiantTemporaire = Integer.parseInt(sousLigne);
                    if (identifiantTemporaire != -1) {
                        String nomDeLaCarte = this.rendNomCarte(identifiantTemporaire);
                        tampon.append(nomDeLaCarte + " ");
                    } else {
                        tampon.append(" | ");
                    }
                    tampon.append(" || Support :" + nombre + "\n");
                }
            }
        }
        return tampon.toString();
    }
}