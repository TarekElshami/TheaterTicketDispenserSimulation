package theaterapp;

import java.util.ArrayList;
import java.util.List;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;

public class IdiomSelectionScreen extends Screen{
    private WelcomeScreen w;
    private DispenserManager dm;

    /**
     *
     * @param w
     * @param theater
     * @param dm
     * @param title
     * @param mode
     */
    public IdiomSelectionScreen(WelcomeScreen w, Theater theater, DispenserManager dm, String title, ScreenMode mode) {
        super(dm, title, mode);
        this.w=w;
        this.dm=dm;
    }
    
    /**
     * "Sin descripcion" el traductor la transforma en vacio
     * 
     * @param d
     * @return
     */
    @Override
    public ScreenResult begin(DispenserHardware d){        
        List <String> opciones = new ArrayList<>();
        opciones.add("castellano"); 
        opciones.add("english");
        opciones.add("euskara");        
        opciones.add("català");
        opciones.add("Cancelar");        
        opciones.add(null);
        setOptions(opciones);
        setImage("idiom.png");
        setDescription("Sin Descripcion");
        return continueInScreen;        
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed(char c){
        switch (c) {
            case 'A':dm.setActiveIdiom("castellano");
            break;
            case 'B':dm.setActiveIdiom("ingles");
            break;
            case 'C':dm.setActiveIdiom("euskera");
            break;
            case 'D':dm.setActiveIdiom("catalan");
            break;                
        }
        dm.showScreen(this.w);
        return exitScreen;
    }
    
}
