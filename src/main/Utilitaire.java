/**
 * @ Francois Auxietre
 */

package main;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Utilitaire {

    public static void ecrit(ArrayList<Donnee> donnees) {
    }


    public static String lireFichierSortie() throws IOException {
        StringBuffer tampon = new StringBuffer();
        Path chemin = FileSystems.getDefault().getPath("sortie.txt");
        String [] lignes;
        lignes = Files.lines(chemin).toArray(String[]::new);
        for(String ligne:lignes){
            tampon.append(ligne + "\n");
        }
        return tampon.toString();
    }
}
