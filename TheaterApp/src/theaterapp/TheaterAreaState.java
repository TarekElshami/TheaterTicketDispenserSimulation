package theaterapp;

import java.io.Serializable;
import static theaterapp.SeatState.occupied;

/**
 *
 * @author t.elshami.2021
 */
public class TheaterAreaState implements Serializable {
    
    private SeatState[][] seats;
    private String name;
    private int cols;
    private int rows;
    private int price;
    
    /**
     *
     * @param area
     */
    public TheaterAreaState (TheaterArea area){
        seats = area.getSeats();
        name = area.getName();
        cols = area.getCols();
        rows = area.getRows();
        price = area.getPrice();
    }

    /**
     *
     * @return
     */
    public SeatState[][] getSeats() {
        return seats;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getCols() {
        return cols;
    }

    /**
     *
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @param row
     * @param col
     * @param seats
     */
    public void setSeats(int row, int col, SeatState[][] seats) {
        this.seats[row][col] = occupied;
    }

    /**
     *
     * @return
     */
    public int getPrice() {
        return price;
    }
   
}
