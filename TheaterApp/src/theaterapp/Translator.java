package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
    private Map<String, String> messages = new HashMap<>();
    
    /**
     *
     * @param translatorFile
     */
    public Translator (String translatorFile){
        try {
            this.read(translatorFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Ha habido un error con el fichero");
        }
    }
    
    /**
     *
     * @param msg
     * @return
     */
    public String translate (String msg){
        if (msg==null){
            return null;
        }
        else if ((msg.startsWith("martes"))||(msg.startsWith("miércoles"))||(msg.startsWith("jueves"))||(msg.startsWith("viernes"))||(msg.startsWith("sábado"))||(msg.startsWith("domingo"))){
            String[] split = msg.split(":");
            return messages.get(split[0]).concat(" ").concat(split[1]);
        } else if (msg.startsWith("El Rey Leon en ")){
            return this.traduceResumenDeCompra(msg);
        }   else
            return messages.get(msg);
    }

    /**
     *
     * @param translatorFile
     * @throws FileNotFoundException
     */
    private void read(String translatorFile) throws FileNotFoundException {
        File myFile = new File(translatorFile);
        Scanner scanner = new Scanner(myFile);
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            this.leeLineaConfiguracion(linea);
        }
    }

    /**
     *
     * @param linea
     */
    private void leeLineaConfiguracion(String linea) {
        String[] aux = linea.split(":");
        messages.put (aux[0], aux[1]);
    }

    /**
     *
     * @param msg
     * @return
     */
    private String traduceResumenDeCompra(String msg) {
        String[] lineas = msg.split("\n");
        
        String entrada = "Entrada";
        String resumen = new String();
        for (int i=0; i<lineas.length;i++){
            resumen = this.translate(entrada).concat(" : ").concat(this.traduceTicket(lineas[i])).concat("\n").concat(resumen);
        }
        
        return resumen;
    }

    /**
     *
     * @param linea
     * @return
     */
    private String traduceTicket(String linea) {
        String[] split = linea.split(" en ");

        String ElReyLeon = this.translate(split[0]);
        String en = this.translate("en");
        
        String[] aux = split[1].split(" F");

        String zona = this.translate(aux[0]);
        
        String[] split1 = aux[1].split(" C");
        String fila = "F";        
        String col = "C";
        String[] aux1 = split1[1].split("€ ");
        String[] split3 = aux1[0].split(" ");
        String[] split2 = aux1[1].split("el dia");
        String dia = this.translate("el dia");

        return ElReyLeon.concat(" ").concat("\n------------------------------\n")
                .concat(en).concat(" ").concat(zona).concat("\n")
                .concat(fila).concat(split1[0]).concat("\n")
                .concat(col).concat(split3[0]).concat("\n")
                .concat(split3[1]).concat("€ ").concat("\n")
                .concat(dia).concat(split2[1]);
    }
   
}
