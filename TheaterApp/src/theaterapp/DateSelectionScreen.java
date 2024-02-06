package theaterapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import static theaterapp.ScreenMode.optionsMode;
import static theaterapp.ScreenResult.continueInScreen;
import static theaterapp.ScreenResult.exitScreen;

/**
 *
 * @author t.elshami.2021
 */

public class DateSelectionScreen extends Screen{
    //Atributos
    private Map <String, String> schedule = new HashMap<>();
    private Theater theater;    
    private AreaSelectionScreen areaSelectionScreen;
    private WelcomeScreen w;
    private DispenserManager dm;

    /**
     *
     * @param welcomeScreen
     * @param theater
     * @param dm
     * @param title
     * @param mode
     */
    public DateSelectionScreen(WelcomeScreen welcomeScreen, Theater theater, DispenserManager dm, String title, ScreenMode mode) {
        super(dm, title, mode);
        this.w = welcomeScreen;
        this.dm=dm;
        this.theater = theater;
    }
    
    /**
     *
     * @param dh
     * @return
     */
    @Override
    public ScreenResult begin (DispenserHardware dh){
        setDescription ("Los lunes no hay representaciones");
        setImage(theater.getTheaterPlaneImageFile());  
        this.loadStateFiles();        
        return continueInScreen;
    }
    
    /**
     * Crea un fichero por día en caso de que no exista
     */
    private void loadStateFiles() {
        List<Date> fechas = this.getDaysFromToday(5);
        for (int i=0; i<fechas.size();i++){
            try {
                LocalDateTime ldt = LocalDateTime.ofInstant(fechas.get(i).toInstant(),ZoneId.systemDefault());
                TheaterState theaterState = new TheaterState(this.theater, ldt);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
                String fic = "TheaterState"+sdf.format(fechas.get(i))+".TheaterState";
                this.schedule.put(sdf.format(fechas.get(i)), fic);
                File f = new File (fic);
                if (!f.exists()){
                    try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fic))) {
                        salida.writeObject(theaterState);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } 
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<String> getOptions(){
        List<String> opciones = new ArrayList<>();
        List<Date> fechas = this.getDaysFromToday(5);
        for (int i=0; i<fechas.size();i++){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
            Format f = new SimpleDateFormat("EEEE");
            opciones.add(f.format(fechas.get(i))+":"+sdf.format(fechas.get(i)));
        }
        opciones.add("Cancelar");
        return opciones;
    }

    /**
     * Devuelve (int days) días desde hoy, pero evita los lunes pues el teatro cierra
     * @param days
     * @return
     */
    private List<Date> getDaysFromToday(int days) {
        List<Date> dias = new ArrayList<>();        
        Calendar calendar = Calendar.getInstance();
        do {
            if (calendar.getTime().getDay()  !=  1){
                dias.add(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }
        while (dias.size()<days);
        return dias;
    }
    
    /**
     * Consigue la direccion del fichero del día seleccionado y se manda al constructor de
     * AreaSelectionScreen
     * 
     * @param c
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed (char c){
        List<Date> fechas = this.getDaysFromToday(5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        switch (c) {
            case 'A':
                areaSelectionScreen = new AreaSelectionScreen(w, theater, this.schedule.get(sdf.format(fechas.get(0))), dm, "Seleccion de area", optionsMode);
                dm.showScreen(this.areaSelectionScreen);
                return exitScreen;
            case 'B':
                areaSelectionScreen = new AreaSelectionScreen(w, theater, this.schedule.get(sdf.format(fechas.get(1))), dm, "Seleccion de area", optionsMode);
                dm.showScreen(this.areaSelectionScreen);
                return exitScreen;
            case 'C':
                areaSelectionScreen = new AreaSelectionScreen(w, theater, this.schedule.get(sdf.format(fechas.get(2))), dm, "Seleccion de area", optionsMode);
                dm.showScreen(this.areaSelectionScreen);
                return exitScreen;
            case 'D':
                areaSelectionScreen = new AreaSelectionScreen(w, theater, this.schedule.get(sdf.format(fechas.get(3))), dm, "Seleccion de area", optionsMode);
                dm.showScreen(this.areaSelectionScreen);
                return exitScreen;
            case 'E':
                areaSelectionScreen = new AreaSelectionScreen(w, theater, this.schedule.get(sdf.format(fechas.get(4))), dm, "Seleccion de area", optionsMode);
                dm.showScreen(this.areaSelectionScreen);
                return exitScreen;
            case 'F':
            case 0:
                dm.showScreen(w);
                return exitScreen;
            default:
                return continueInScreen;
        }
    }
}
