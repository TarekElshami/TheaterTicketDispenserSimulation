package theaterapp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TheaterState implements Serializable {
    private LocalDateTime date;
    private List<TheaterAreaState> areas = new ArrayList<>();;

    public List<TheaterAreaState> getAreas() {
        return areas;
    }
    
    /**
     *
     * @param theater
     * @param date
     */
    public TheaterState(Theater theater, LocalDateTime date){
        this.date = date;
        for (int i=0; i<theater.getNumAreas();i++){
            TheaterAreaState area = new TheaterAreaState(theater.getArea(i));
            this.areas.add(area);
        }
    }

    /**
     *
     * @return
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     *
     * @param areas
     */
    public void setAreas(List<TheaterAreaState> areas) {
        this.areas = areas;
    }
    
    
    
}
