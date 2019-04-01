/**
 * @ Francois Auxietre
 */

package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Generation {

// lancement avec FPClose et avec CloSpan


    /**
     * executeSpmf : execute la commande sur le jar
     * @param pourcentage
     * @param nomDataMining
     */
    public static void executeSpmf(int pourcentage, String nomDataMining) {


        Path sortieChemin = FileSystems.getDefault().getPath("sortie.txt");
        Path entreeChemin = FileSystems.getDefault().getPath("entree.txt");
        System.out.println(sortieChemin);
        String spmf = "java -jar ." + File.separator + "spmf.jar run " + nomDataMining + " "+ entreeChemin.toString() + " " + sortieChemin.toString() + " " + pourcentage + "%";
        //Main.af("sortie:"+ Files.exists(sortieChemin));
        //Main.af(spmf);

        if (Files.exists(sortieChemin)) {
            try {
                    //Files.delete(sortieChemin);
                    Runtime runtime = Runtime.getRuntime();
                    Process process = runtime.exec(spmf);
                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }else
        {
            System.out.println("Le fichier n'existe pas");
        }
    }
}

