package theaterapp;

import java.util.List;
import sienens.TheaterTicketDispenser;

public class DispenserHardware {
    private TheaterTicketDispenser dispenser;

    /**
     *
     * @param dispenser
     */
    public DispenserHardware(TheaterTicketDispenser dispenser) {
        this.dispenser = dispenser;
    }

    /**
     *
     * @param b
     * @return
     */
    public long retainCreditCard(boolean b) {
        dispenser.retainCreditCard(b);
        long cardNumber = dispenser.getCardNumber();
        return cardNumber;
    }
    
    /**
     *
     */
    public void expelCreditCard(){
        dispenser.expelCreditCard(30);
        dispenser.retainCreditCard(true);
    }
    
    /**
     *
     * @param text
     */
    public void printTicket (List<String> text){
        dispenser.print(text);
    }
}
