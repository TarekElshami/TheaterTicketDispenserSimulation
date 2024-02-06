package theaterapp;

import java.util.List;
import sienens.TheaterTicketDispenser;
import static theaterapp.ScreenMode.messageMode;
import static theaterapp.ScreenMode.optionsMode;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.SeatState.free;
import static theaterapp.SeatState.occupied;

/**
 *
 * @author t.elshami.2021
 */

public class DispenserManager {
    //Atributos
    private TranslatorManager translator;
    private DispenserHardware dh; 
    private TheaterTicketDispenser dispenser;
    private final int time = 30;
    
    //Constructor

    public DispenserManager() {
        this.translator = new TranslatorManager();   
        this.dispenser = new TheaterTicketDispenser();
        this.dh = new DispenserHardware(this.dispenser);
    }
    
    /**
     * Muestra titulo, descripcion, imagen y opciones
     * 
     * @param screen
     */
    public void showScreen(Screen screen) {  
        if (screen.begin(dh) == continueInScreen){        
            switch (screen.getMode()) {
                case optionsMode:
                    dispenser.setMenuMode();
                    this.dispenser.setDescription(translator.translate(screen.getDescription()));
                    this.dispenser.setImage(screen.getImage());                              
                    break;                                                    
                case theaterMode:
                    dispenser.setTheaterMode(screen.getRows(), screen.getCols());                          
                    this.drawArea(screen.getArea());                           
                    break;
                case messageMode:
                    dispenser.setMessageMode();
                    this.dispenser.setDescription(translator.translate(screen.getDescription()));
                    //Lo siguente es para traducir el ticket:
                    screen.setDescription(translator.translate(screen.getDescription()));
                    break;
            }
            this.dispenser.setTitle(translator.translate(screen.getTitle()));
            List<String> opciones = screen.getOptions();
            for (int i = 0; i < opciones.size(); i++) {
                dispenser.setOption(i, translator.translate(opciones.get(i)));
            }
            this.gestionEventos(screen);                    
        }     
    }
    
    /**
     * Dibuja las butacas del area recibida
     * 
     * @param theaterAreaState
     */
    private void drawArea(TheaterAreaState theaterAreaState) {
        SeatState[][] seats = theaterAreaState.getSeats();
        for (int i = 1; i < seats.length + 1; i++) {
            for (int j = 1; j < seats[i - 1].length + 1; j++) {
                if (seats[i - 1][j - 1] == free) {
                    this.dispenser.markSeat(i, j, 2);
                } else if (seats[i - 1][j - 1] == occupied) {
                    this.dispenser.markSeat(i, j, 1);
                } else {
                    this.dispenser.markSeat(i, j, 0);
                }
            }
        }
    }
    
    /**
     * Espera la elección del usuario e informa a la pantalla
     * 
     * @param screen
     */
    private void gestionEventos(Screen screen) {
        char waitEvent;
        do {
            waitEvent = dispenser.waitEvent(this.time);
            if (waitEvent != 'A' && waitEvent != 'B' && waitEvent != 'C' && waitEvent != 'D' && waitEvent != 'E' && waitEvent != 'F' && waitEvent != 0) {
                if (waitEvent != '1') {
                    screen.seatButtonPressed(waitEvent);
                    this.drawArea(screen.getArea());
                } else if (waitEvent == '1') {
                    screen.creditCardDetected(dh);
                }
            }
        } while (screen.optionButtonPressed(waitEvent) == continueInScreen);
    }
        
    /**
     *
     * @param idioma
     */
    public void setActiveIdiom(String idioma) {
        this.translator.setActiveIdiom(idioma);
    }               
}
