package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author t.elshami.2021
 */
public class Play {
    private String title;
    private String image;
    private String description;

    public Play() {
        try {
            this.read("play.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Ha habido un error con el fichero");
        }
    }
    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
    
    /**
     *
     * @param play
     * @throws FileNotFoundException
     */
    private void read(String play) throws FileNotFoundException{
        File myFile = new File(play);
        Scanner scanner = new Scanner(myFile);
        while (scanner.hasNextLine()){
            String linea = scanner.nextLine();
            this.leeLineaConfiguracion(linea);
        }
    }
    
    /**
     *
     * @param linea
     */
    private void leeLineaConfiguracion (String linea){
        if (linea.startsWith("﻿play_name")){
            String [] aux = linea.split(":");
            this.title=aux[1];
        } else if (linea.startsWith("play_poster")){
            String [] aux = linea.split(":");
            this.image=aux[1];
        } else if (linea.startsWith("description")){
            String [] aux = linea.split(":");
            this.description=aux[1];
        }
    }
    
}
