package sample;

public class Seat {
    private int seatId;
    private Game game;
    private int seatNumber;
    private Category category;
    private int seatRow;
    private boolean isReserved;

    public Seat(int seatId, Game game, int seatNumber, Category category, int seatRow, boolean isReserved){
        this.seatId = seatId;
        this.game = game;
        this.seatNumber = seatNumber;
        this.category = category;
        this.seatRow = seatRow;
        this.isReserved = isReserved;
    }

    public int getSeatId() { return seatId; }

    public int getSeatNumber() { return seatNumber; }

    public boolean isReserved() { return isReserved; }
    public void setReserved(boolean reserved) { isReserved = reserved; }

    public String outputString(){
        return "Seat " + seatNumber + " with Id " + seatId + " has category " + category.getName() + " costs " + category.getPrice() + " CHF and is in row " + seatRow;
    }
}
