/**
 * @ Francois Auxietre
 */

package main;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Donnee {


    /**
     * transforme :  permet de creer le fichier pattern.txt qui
     * @param chaine
     * @param valeur
     * @param baseDonneeCarte
     * @return String : l'explication de toute les cartes avec leurs numeros ...
     */
    public String transforme(String chaine, int valeur, BaseDonneeCarte baseDonneeCarte) {


        StringBuffer tampon = new StringBuffer();
        String[] lignes = chaine.split("\n");

        for (String ligne : lignes) {

            String[] debutLigne = ligne.split("#SUP:");
            String nombreElement = debutLigne[debutLigne.length-1];

            String[] listeDesCartes = debutLigne[0].split(" ");
            if (listeDesCartes.length>1){
               tampon.append("les cartes : ");
                for(int i=0; i<listeDesCartes.length; i++){
                    int nombre = Integer.parseInt(listeDesCartes[i]);
                    //Main.af(nombre);
                    String nomDeLaCarte = baseDonneeCarte.rendNomCarte(nombre);
                    //Main.af(nomDeLaCarte);
                    tampon.append(nomDeLaCarte + " (N° "+ nombre + "), ");
                    }
                tampon.append("sont en nombre de"+ nombreElement + " exemplaires\n");
            }
            else{
                int nombre = Integer.parseInt(debutLigne[1].substring(1));
                if (listeDesCartes.length - 1 >= valeur) {
                    for (String sousLigne : listeDesCartes) {
                        int identifiantTemporaire = Integer.parseInt(sousLigne);
                        if (identifiantTemporaire != -1) {
                            String nomDeLaCarte = baseDonneeCarte.rendNomCarte(identifiantTemporaire);
                            tampon.append("la carte : " +nomDeLaCarte + " (N° "+ identifiantTemporaire + ") est en ");
                        } else {
                            tampon.append(" | ");
                        }
                        tampon.append(  nombre + " exemplaires\n");
                    }
                }
            }

        }
        return tampon.toString();
    }


    /**
     *
     * extracteDonnee : methode qui appel transforme
     *
     * @param baseDonneeCarte
     * @param fileWriterSortie
     * @param fileReaderSortie
     * @return String : La chaine correspondante au fichier transcrit en francias
     *
     * @throws IOException
     */
    public String  extracteDonnee(BaseDonneeCarte baseDonneeCarte, FileWriter fileWriterSortie, FileReader fileReaderSortie) throws IOException {


        Main.af("\n-----------------creations des fichiers-------------------------");
        Main.af("baseDonnee.txt : base de donnée de toutes les cartes des parties");
        Main.af("entree.txt : les decks des joueurs 1 et 2 pour les parties ");
        Main.af("sortie.txt : après avoir utiliser l'algo de FPClose");
        Main.af("pattern.txt : le changement de sortie.txt en lisible ");

        String str = "";
        int i = 0;
        //Lecture des données
        while ((i = fileReaderSortie.read()) != -1) {
            str += (char) i;
             }
        return transforme(str, 0, baseDonneeCarte);

    }

}
