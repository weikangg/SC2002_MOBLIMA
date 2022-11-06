package entities;

public class Seat {

    private int state;
    private SeatType seatType;

    public Seat(int state, SeatType seatType){
        this.state = state;
        this.seatType = seatType;
    }

    public int getState() {
        return state;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setState(int state) {
        this.state = state;
    }

}
