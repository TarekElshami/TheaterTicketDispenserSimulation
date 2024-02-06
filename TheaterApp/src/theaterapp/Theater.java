package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Theater {
    private String teatherName;
    private String theaterPlaneImageFile;
    private List <TheaterArea> areas = new ArrayList<>();
    
    /**
     *
     */
    public Theater(){
        try {
            this.read("theater.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Ha habido un error con el fichero");
        }
    }
    
    /**
     *
     * @param theater
     * @throws FileNotFoundException
     */
    private void read(String theater) throws FileNotFoundException{
        File myFile = new File(theater);
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
        if (linea.startsWith("Area")){
            String [] aux = linea.split(":");
            TheaterArea area = new TheaterArea(aux[1]);
            this.areas.add(area);
        } else if (linea.startsWith("TheaterName")){
            String [] aux = linea.split(":");
            this.teatherName= aux[1];
        } else if (linea.startsWith("TheaterPlaneImageFile")){
            String [] aux = linea.split(":");
            this.theaterPlaneImageFile = aux[1];
        } 
    }

    /**
     *
     * @return
     */
    public int getNumAreas() {
        return this.areas.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public TheaterArea getArea(int i) {
        return this.areas.get(i);
    }

    /**
     *
     * @return
     */
    public String getTheaterPlaneImageFile() {
        return theaterPlaneImageFile;
    }

    /**
     *
     * @return
     */
    public List <TheaterArea> getAreas(){
        return this.areas;
    }
}
