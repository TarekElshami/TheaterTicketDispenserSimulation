package theaterapp;

import java.util.ArrayList;
import java.util.List;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;

/**
 *
 * @author t.elshami.2021
 */
public class ErrorScreen extends Screen{
    private WelcomeScreen w;
    private DispenserManager dm;

    /**
     *
     * @param w
     * @param dm
     * @param title
     * @param mode
     */
    public ErrorScreen(WelcomeScreen w, DispenserManager dm, String title, ScreenMode mode) {
        super(dm, title, mode);
        this.w=w;
        this.dm=dm;
    }
    
    /**
     *
     * @param dh
     * @return
     */
    @Override
    public ScreenResult begin (DispenserHardware dh){   
        setDescription("En estos momentos no podemos adenterle");
        List<String> opciones = new ArrayList<>();
        opciones.add("Cancelar");
        opciones.add(null);
        setOptions(opciones);
        return continueInScreen;
    }
    
    /**
     *
     * @param waitEvent
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed(char waitEvent) {
        switch (waitEvent) {
            case 0:
            case 'A':
                dm.showScreen(this.w);
                return exitScreen;
            default:
                return continueInScreen;
        }
    }
}
