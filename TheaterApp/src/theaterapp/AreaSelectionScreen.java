package theaterapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static theaterapp.ScreenMode.theaterMode;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;

/**
 *
 * @author t.elshami.2021
 */
public class AreaSelectionScreen extends Screen{
    //Atributos
    private SeatSelectionScreen areaSelecionada;
    private List <TheaterArea> areas;
    private String direccion;
    private WelcomeScreen w ; 
    private DispenserManager dm;
    private Theater theater;
    
    
    /**
     *
     * @param welcomeScreen
     * @param theater
     * @param direccion
     * @param dispenserHardware
     * @param title
     * @param mode
     */
    
    public AreaSelectionScreen(WelcomeScreen welcomeScreen, Theater theater, String direccion, DispenserManager dispenserHardware, String title, ScreenMode mode) {
        super(dispenserHardware, title, mode);
        this.direccion= direccion;
        this.w = welcomeScreen;
        this.dm=dispenserHardware;
        this.theater = theater;
        
    }
    
    /**
     *
     * @param dispenserHardware
     * @return
     */
    
    @Override
    public ScreenResult begin (DispenserHardware dispenserHardware){   
        //El traductor transforma sin descripcion en un vacio
        setDescription("Sin Descripcion");
        this.areas = theater.getAreas();
        setImage(theater.getTheaterPlaneImageFile());
        return continueInScreen;
    }
    
    /**
     * 
     * @return
     */
    
    @Override
    public List<String> getOptions(){
        List<String> opciones = new ArrayList<>();
        for (int i=0; i<areas.size();i++){            
            opciones.add(areas.get(i).getName());
        }
        opciones.add("Cancelar");
        return opciones;
    }
    
    /**
     * Pide a this.leerFicheroTheaterState 
     * abrir el fichero cuya direccion llega en el constructor
     * (Porque depende del día escogido)
     * Y le entrega el estado actual del área escogida a SeatSelectionScreen
     * junto con la dirección para que pueda guardar en caso de cambio
     * 
     * @param waitEvent
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed (char waitEvent){
        try {
            TheaterState ts = this.leerFicheroTheaterState(direccion);
            List<TheaterAreaState> estadoAreas = ts.getAreas();
            switch (waitEvent) {
                case 'A':
                    this.areaSelecionada = new SeatSelectionScreen(w, estadoAreas.get(0),this.direccion,dm,"Elección de asientos",theaterMode);
                    dm.showScreen(this.areaSelecionada);
                    break;
                case 'B':
                    this.areaSelecionada = new SeatSelectionScreen(w, estadoAreas.get(1), this.direccion, dm, "Elección de asientos", theaterMode);
                    dm.showScreen(this.areaSelecionada);
                    break;
                case 'C':
                    this.areaSelecionada = new SeatSelectionScreen(w, estadoAreas.get(2), this.direccion, dm, "Elección de asientos", theaterMode);
                    dm.showScreen(this.areaSelecionada);
                    break;
                case 'D':
                    this.areaSelecionada = new SeatSelectionScreen(w, estadoAreas.get(3), this.direccion, dm, "Elección de asientos", theaterMode);
                    dm.showScreen(this.areaSelecionada);
                    break;
                case 'E':
                    this.areaSelecionada = new SeatSelectionScreen(w, estadoAreas.get(4), this.direccion, dm, "Elección de asientos", theaterMode);
                    dm.showScreen(this.areaSelecionada);
                    break;
                case 'F':
                case 0:
                    dm.showScreen(w);
                    break;
                default:
                    break;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AreaSelectionScreen.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return exitScreen;
    }
    
    /**
     *
     * @param direccion
     * @return
     * @throws ClassNotFoundException
     */
    private TheaterState leerFicheroTheaterState (String direccion) throws ClassNotFoundException{
        FileInputStream ficheroEntrada = null;
        TheaterState ts;
        try{
            ficheroEntrada = new FileInputStream(direccion);
            ObjectInputStream tuberiaEntrada = new ObjectInputStream(ficheroEntrada);
            ts = (TheaterState)tuberiaEntrada.readObject();
            tuberiaEntrada.close();
            return ts;
        }catch(FileNotFoundException ex){
            System.out.println("error"); 
        }catch(IOException | ClassNotFoundException ex){
            System.out.println("error");
        }
        return null;
    }
}
