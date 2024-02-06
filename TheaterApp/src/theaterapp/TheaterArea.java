package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
import static theaterapp.SeatState.free;

public class TheaterArea {
    private int rows;
    private int cols;
    private String name;
    private SeatState [][] seats;
    private int price;
    
    /**
     *
     * @param area
     */
    public TheaterArea (String area){
        
        String [] aux = area.split(";");
        
        this.name = aux[0];
        String [] aux2 = aux[1].split("€");
        this.price = parseInt(aux2[0]); //Precio sin €   
        
        
        try {
            this.read(aux[2]);
        } catch (FileNotFoundException ex) {
            System.out.println("Ha habido un error con el fichero de area");
        }
            
    }

    /**
     *
     * @param area
     * @throws FileNotFoundException
     */
    private void read(String area) throws FileNotFoundException{
        File myFile = new File(area);
        Scanner scanner = new Scanner(myFile);
        ArrayList<String> filas = new ArrayList<>();
        while (scanner.hasNextLine()){
            String linea = scanner.nextLine();
            if (linea.length()>1){ //Porque me guardaba los saltos de línea. 
                filas.add(linea);
            }            
        }
        this.seats = new SeatState[filas.size()][this.cadenaMasLarga(filas).length()];
        this.rows = filas.size();
        this.cols = this.cadenaMasLarga(filas).length();
        this.leeLineaConfiguracion(filas);
    }    
    
    /**
     *
     * @param lineas
     */
    private void leeLineaConfiguracion( ArrayList <String> lineas) {
        for (int i=0; i<lineas.size();i++){
            char[] columnas = lineas.get(i).toCharArray();
            for (int j=0; columnas.length>j;j++){
                if (columnas[j]=='*'){
                    seats[i][j] = free;
                }            
            }
        }
    }
    
    //Calcular y devolver la cadena de mayor longitud

    /**
     *
     * @param cadenas
     * @return
     */
    private String cadenaMasLarga(ArrayList<String> cadenas) {
        String mayor = cadenas.get(0);
        for (int i = 1; i < cadenas.size(); i++) {
            if (cadenas.get(i).length() > mayor.length()) {
                mayor = cadenas.get(i);
            }
        }
        return mayor;
    }
    
    /**
     *
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return
     */
    public int getCols() {
        return cols;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public SeatState[][] getSeats() {
        return seats;
    }

    /**
     *
     * @return
     */
    public int getPrice() {
        return price;
    }
    
    
    
    

}