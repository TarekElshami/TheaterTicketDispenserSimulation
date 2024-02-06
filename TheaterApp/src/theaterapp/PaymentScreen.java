package theaterapp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import static theaterapp.ScreenMode.messageMode;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;
import urjc.UrjcBankServer;

/**
 *
 * @author t.elshami.2021
 */

public class PaymentScreen extends Screen{
    private int price;
    private UrjcBankServer bank = new UrjcBankServer();
    private WelcomeScreen w;
    private ErrorScreen errorScreen;
    private SeatSelectionScreen s;
    private DispenserManager dm;
    
    /**
     *
     * @param s
     * @param w
     * @param price
     * @param dm
     * @param title
     * @param mode
     */
    public PaymentScreen(SeatSelectionScreen s, WelcomeScreen w, int price, DispenserManager dm, String title, ScreenMode mode) {
        super(dm, title, mode);
        this.w = w;
        this.price= price;
        this.dm=dm;
        this.errorScreen = new ErrorScreen(w, dm, "Error", messageMode);
        this.s=s;
    }
    
    /**
     *
     * @param dh
     * @return
     */
    @Override
    public ScreenResult begin (DispenserHardware dh){   
        if (this.bank.comunicationAvaiable()){
            return continueInScreen;
        } else {
            dm.showScreen(this.errorScreen);
            return exitScreen;
        } 
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<String> getOptions() {
        List<String> opciones = new ArrayList<>();
        opciones.add("Cancelar");
        opciones.add(null);
        return opciones;
    }
    
    /**
     *
     * @param waitEvent
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed(char waitEvent) {
        switch (waitEvent) {
            case 'A':
            case '1':
            case 0:
                dm.showScreen(this.w);
                return exitScreen;
            default:
                return continueInScreen;            
        }
        
    }
    
    /**
     *
     * @param d
     * @return
     */
    @Override
    public ScreenResult creditCardDetected(DispenserHardware d) {
        long cardNumber = d.retainCreditCard(false);
        boolean pagado = false;
        try {
            pagado = bank.doOperation(cardNumber, price);
        } catch (CommunicationException ex) {
            Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pagado){
            s.updateState();        
            List<String> text = new ArrayList<>();
            String aux = getDescription();
            int aux1 = (aux.length())/(s.getSeats());
            for (int i=0; i<s.getSeats();i++){
               text.add(aux.substring(i*aux1,aux1*(i+1)));
               d.printTicket(text); 
               text.clear();
            }            
        }
        d.expelCreditCard();
        return exitScreen;
    }
    
    
}
