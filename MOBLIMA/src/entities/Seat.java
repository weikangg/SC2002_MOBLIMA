package entities;

/**
 * Represents a Seat in the Cinema
 * @author Andrew Leung
 * @version 1.0
 * @since 06-11-2022
 */
public class Seat {

    /**
     * State of the Seat, 1 being reserved and 0 being free
     */
    private int state;

    /**
     * Type of Seat
     */
    private SeatType seatType;

    /**
     * Creates a new Seat, using a state and the seatType
     * @param state State of the Seat, 1 being reserved and 0 being free
     * @param seatType Type of Seat
     */
    public Seat(int state, SeatType seatType){
        this.state = state;
        this.seatType = seatType;
    }

    /**
     * Function to return the current state of the Seat
     * @return State of the Seat, 1 being reserved and 0 being free
     */
    public int getState() {
        return state;
    }

    /**
     * Function to return the Type of Seat
     * @return Type of Seat 
     */
    public SeatType getSeatType() {
        return seatType;
    }

    /**
     * Function to set the state of the Seat
     * @param state State of the Seat, 1 being reserved and 0 being free 
     */
    public void setState(int state) {
        this.state = state;
    }

} 
