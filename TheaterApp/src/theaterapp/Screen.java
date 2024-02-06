package theaterapp;

import java.util.List;
import static theaterapp.ScreenResult.continueInScreen;

public class Screen {
    //Atributos
    private DispenserManager dispenserManager;
    private String title;
    private String description;
    private String image;
    private List<String> options;
    private ScreenMode mode;

    /**
     *
     * @param dm
     * @param title
     * @param mode
     */
    public Screen (DispenserManager dm, String title, ScreenMode mode){
        this.dispenserManager = dm;
        this.title = title;
        this.mode = mode;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public List<String> getOptions() {
        return options;
    }

    public ScreenMode getMode() {
        return mode;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
    public void setDispenserManager(DispenserManager dispenserManager) {
        this.dispenserManager = dispenserManager;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public ScreenResult begin(DispenserHardware d){        
        return continueInScreen;        
    }
        
    public void seatButtonPressed (char waitEvent){

    }
            
    public ScreenResult creditCardDetected (DispenserHardware d){
        return null;
    }
            
    public ScreenResult optionButtonPressed (char c){
        return null;
    }
    
    public int getRows (){
        return 0;
    }
    
    public int getCols (){
        return 0;
    }
    
    public TheaterAreaState getArea(){
        return null;
    }
    
}