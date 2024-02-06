package theaterapp;

import java.util.ArrayList;
import java.util.List;
import static theaterapp.ScreenMode.optionsMode;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;

public class WelcomeScreen extends Screen{
    private DispenserManager dm;
    private IdiomSelectionScreen idiomScreen;
    private Theater theater;
    private DateSelectionScreen dateScreen;
    private Play play = new Play();
        
    /**
     *
     * @param theater
     * @param dm
     * @param title
     * @param mode
     */
    public WelcomeScreen(Theater theater, DispenserManager dm, String title, ScreenMode mode) {
        super(dm, title, mode);
        this.theater = theater;       
        this.dm=dm;
        this.dateScreen = new DateSelectionScreen(this, this.theater, dm, "Selecciona dia de representacion", optionsMode);
        this.idiomScreen = new IdiomSelectionScreen(this, this.theater, dm, "Seleccion de idioma",optionsMode);
    }
    
    /**
     *
     * @param c
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed(char c){
        switch (c) {
            case 'A':
                dm.showScreen(this.dateScreen);
                return exitScreen;
            case 'B':
                dm.showScreen(this.idiomScreen);
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
    public ScreenResult begin(DispenserHardware d){        
        super.setDescription(this.play.getDescription());
        super.setImage(this.play.getImage());
        super.setTitle(this.play.getTitle());
        
        List <String> opciones = new ArrayList<>();
        opciones.add(dateScreen.getTitle()); 
        opciones.add(idiomScreen.getTitle());
        opciones.add(null);        
        opciones.add(null);
        opciones.add(null);        
        opciones.add(null);
        setOptions(opciones);
        return continueInScreen;        
    }
    
}               
