/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import static theaterapp.ScreenMode.messageMode;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;
import static theaterapp.SeatState.free;

/**
 *
 * @author t.elshami.2021
 */
public class SeatSelectionScreen extends Screen{
    private TheaterAreaState estadoArea;
    private String direccion;
    private PaymentScreen pantallaPago;
    private int seats = 0;
    private WelcomeScreen w; 
    private String resumen  = new String();
    private DispenserManager dm;

    /**
     *
     * @param w
     * @param estadoArea
     * @param direccion
     * @param dm
     * @param title
     * @param mode
     */
    public SeatSelectionScreen(WelcomeScreen w, TheaterAreaState estadoArea, String direccion, DispenserManager dm, String title, ScreenMode mode) {
        super(dm, title, mode);
        this.w = w ;
        this.dm= dm;
        this.estadoArea = estadoArea;
        this.direccion = direccion;
    }
    
    @Override
    public ScreenResult begin (DispenserHardware dh){         
        return continueInScreen;
    }

    public TheaterAreaState getEstadoArea() {
        return estadoArea;
    }
    
    @Override
    public TheaterAreaState getArea() {
        return this.estadoArea;
    }
    
    @Override
    public int getRows(){
        return estadoArea.getRows();
    }
    
    @Override
    public int getCols(){
        return estadoArea.getCols();
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<String> getOptions() {
        List<String> opciones = new ArrayList<>();
        opciones.add("Cancelar");
        opciones.add("Confirmar");
        return opciones;
    }
    
    /**
     *
     * @param waitEvent
     */
    @Override
    public void seatButtonPressed(char waitEvent) {

        byte col = (byte) (waitEvent & 0xFF);
        byte row = (byte) ((waitEvent & 0xFF00) >> 8);
        
        //Compruebo que la butaca / asiento seleccionado está libre. Ademas de comprobar que el usuario aún no escogio 4 asientos.
        if (this.seats < 4 && estadoArea.getSeats()[row-1][col-1]==free){            
            estadoArea.setSeats(row-1, col-1, estadoArea.getSeats());       
            this.seats++;
            resumen = resumen + w.getTitle() + " en " + estadoArea.getName() + " F" + (row) +" C" + (col) + " " + estadoArea.getPrice() +"€ el dia " + direccion.substring(12, 17) +"\n";
        }
    }
    
    /**
     *
     * @param waitEvent
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed(char waitEvent) {        
        switch (waitEvent) {
            
            case 'B':
                this.pantallaPago = new PaymentScreen(this, w, this.computePrice(), dm, "Inserte tarjeta" ,messageMode);
                this.pantallaPago.setDescription(resumen);
                dm.showScreen(this.pantallaPago);
                return exitScreen;
            case 'A':
            case 0:
                dm.showScreen(this.w);
                return exitScreen;
            default:
                return continueInScreen;
        }
    }
    
    /**
     *
     */
    public void updateState() {
        try {
            File file = new File (direccion);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            TheaterState ts = (TheaterState) ois.readObject();
            List<TheaterAreaState> areas = ts.getAreas();
            areas.set(this.calculaPosArea(), estadoArea);
            ts.setAreas(areas);

            file.delete();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(ts);
            oos.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        

    }
    
    /**
     *
     * @return
     */
    private int computePrice(){
        return this.seats*this.estadoArea.getPrice();
    }

    /**
     * Ésta función indica donde se debe guardar el area editada
     * 
     * @return
     */
    private int calculaPosArea() {
        String name = estadoArea.getName();
        if (null != name)switch (name) {
            case "Palco1":
                return 0;
            case "Palco2":
                return 1;
            case "Anfiteatro1":
                return 2;
            case "Anfiteatro2":
                return 3;
            case "Platea":
                return 4;
            default:
                break;
        }
        return 5;
    }

    public int getSeats() {
        return seats;
    }
    
    
}
